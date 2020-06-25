package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.Conexion;
import db.DbException;
import db.DbIntegridadException;
import model.dao.IntrfVenDao;
import model.entidades.Departamento;
import model.entidades.Vendedores;

public class ImplVenDaoJdbc implements IntrfVenDao {
	private Connection conn;

	// --Constructor para Hacer Inyeccion de Dependencia--//
	public ImplVenDaoJdbc(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void incluir(Vendedores ven) {
       PreparedStatement st = null;
       
       try { 
    	   st = conn.prepareStatement("INSERT INTO vendedor "
    			                     +"(Nombre,Email,Fecha,"
    			                     +"SalarioBase,DepartamentoId) "
    		                         +"VALUES(?,?,?,?,?)",
    		                         +Statement.RETURN_GENERATED_KEYS); 
    	   st.setString(1, ven.getNombre());
    	   st.setString(2, ven.getEmail());
    	   st.setDate(3, new java.sql.Date(ven.getFecha().getTime()));
    	   st.setDouble(4, ven.getSalarioBase());
    	   st.setInt(5, ven.getDepartamento().getId());
    	   
    	   int filas = st.executeUpdate();
    	   //--Si Inserto Filas--//
    	   if(filas > 0) {
    		  //--Asigna a rs el Id Generado--// 
    	      ResultSet rs = st.getGeneratedKeys();
    	      if(rs.next()) {
    	         int id = rs.getInt(1);	
    	         //--Asigna al Obj. id Tomado--//
    	         ven.setId(id);
    	      }
    	      Conexion.cierraResultSet(rs);
    	   }
    	   else {//--No Inserto Filas--//
    	      throw new DbException("Error Inesperado, No Hay Filas Afectadas...");	   
    	   }
	   } 
       catch (SQLException e) {
          throw new DbException(e.getMessage());
	   } 
       finally {
          Conexion.cierraStatement(st);	   
       }
	}//--Fin del Metodo Incluir--//

	@Override
	public void midificar(Vendedores ven) {
	       PreparedStatement st = null;
	       
	       try { 
	    	   st = conn.prepareStatement("UPDATE vendedor "
	    			                     +"SET Nombre=?, "
	    			                     +"Email=?, "
	    			                     +"Fecha=?, "
	    			                     +"SalarioBase=?, "
	    			                     +"DepartamentoId=? "
	    			                     +"WHERE Id =?"); 
	    	   
	    	   st.setString(1, ven.getNombre());
	    	   st.setString(2, ven.getEmail());
	    	   st.setDate(3, new java.sql.Date(ven.getFecha().getTime()));
	    	   st.setDouble(4, ven.getSalarioBase());
	    	   st.setInt(5, ven.getDepartamento().getId());
	    	   st.setInt(6, ven.getId());
	    	   
	    	   st.executeUpdate();
		   } 
	       catch (SQLException e) {
	          throw new DbException(e.getMessage());
		   } 
	       finally {
	          Conexion.cierraStatement(st);	   
	       }
	}//--Fin de Modificar--//

	@Override
	public void eliminarPorId(Integer id) {
		// TODO Auto-generated method stub

	}

	// --Sobre Escribimos el Metodo buscarPorId--//
	// --Recibiendo un Integer id --//
	@Override
	public Vendedores buscarPorId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT vendedor.*," + "departamento.Nombre AS DepNombre FROM "
					+ "vendedor INNER JOIN departamento " + "ON vendedor.DepartamentoId = departamento.Id "
					+ "WHERE vendedor.Id = ?");
			st.setInt(1, id);

			rs = st.executeQuery();
			if (rs.next()) {// --Hay Datos--//
				// --Llamada al Metodo instanciacionDpto--//
				Departamento dep = instanciacionDpto(rs);

				// --Llamada al Metodo instanciacionVen--//
				Vendedores ven = instanciacionVen(rs, dep);

				// --Asignamos el Obj. Relacionado--//
				ven.setDepartamento(dep);

				// --Retornamos el Obj. Vendedor--//
				return ven;
			}
			// --No Hay Datos--//
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Conexion.cierraStatement(st);
			Conexion.cierraResultSet(rs);
		}
	}

	// --Sobre Escribimos el Metodo busAllVen()--//
	@Override
	public List<Vendedores> busAllVen() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT vendedor.*, departamento.Nombre AS DepNombre "
					+ "FROM vendedor INNER JOIN departamento " 
					+ "ON vendedor.DepartamentoId = departamento.Id "
					+ "ORDER BY Nombre");

			// --Ejecutamos la Consulta--//
			rs = st.executeQuery();

			// --Crea una Lista Vacia--//
			List<Vendedores> lista = new ArrayList<>();
			// --Crea un Map Vacio--//
			Map<Integer, Departamento> map = new HashMap<>();
			while (rs.next()) {
				// --Busca en el mep eñ Id para Tomar el Valor--//
				Departamento valDep = map.get(rs.getInt("DepartamentoId"));

				// --No Existe Instancia Depto y Agreha al mep--//
				if (valDep == null) {
					// --Llamada al Metodo instanciacionDpto--//
					valDep = instanciacionDpto(rs);

					// --Agregamos al mep--//
					map.put(rs.getInt("DepartamentoId"), valDep);
				}

				// --Llamada al Metodo instanciacionVen--//
				Vendedores ven = instanciacionVen(rs, valDep);
				// --Agregamos a la Lista--//
				lista.add(ven);

			}
			return lista;

		} catch (SQLException e) {
			throw new DbIntegridadException(e.getMessage());
		}

	}//--Fin del Metodo busAllVen()--//

	// --Sobre Escribimos el Metodo busPorDep()--//
	// --Recibiendo un Objeto Departamento --//
	@Override
	public List<Vendedores> busPorDep(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT vendedor.*, departamento.Nombre AS DepNombre "
					+ "FROM vendedor INNER JOIN departamento " + "ON vendedor.DepartamentoId = departamento.Id "
					+ "WHERE vendedor.DepartamentoId = ? " + "ORDER BY Nombre");

			// --Asignamos Valor de Vuqueda--//
			st.setInt(1, departamento.getId());

			// --Ejecutamos la Consulta--//
			rs = st.executeQuery();

			// --Crea una Lista Vacia--//
			List<Vendedores> lista = new ArrayList<>();
			// --Crea un Map Vacio--//
			Map<Integer, Departamento> map = new HashMap<>();
			while (rs.next()) {
				// --Busca en el mep eñ Id para Tomar el Valor--//
				Departamento valDep = map.get(rs.getInt("DepartamentoId"));

				// --No Existe Instancia Depto y Agreha al mep--//
				if (valDep == null) {
					// --Llamada al Metodo instanciacionDpto--//
					valDep = instanciacionDpto(rs);

					// --Agregamos al mep--//
					map.put(rs.getInt("DepartamentoId"), valDep);
				}

				// --Llamada al Metodo instanciacionVen--//
				Vendedores ven = instanciacionVen(rs, valDep);
				// --Agregamos a la Lista--//
				lista.add(ven);

			}
			return lista;

		} catch (SQLException e) {
			throw new DbIntegridadException(e.getMessage());
		}
	}// --Fin del Metodo busPorDep()--//

	// --Metodo Para Instanciar Vendedores--//
	private Vendedores instanciacionVen(ResultSet rs, Departamento dep) throws SQLException {
		Vendedores ven = new Vendedores();
		ven.setId(rs.getInt("Id"));
		ven.setNombre(rs.getString("Nombre"));
		ven.setEmail(rs.getString("Email"));
		ven.setFecha(rs.getDate("Fecha"));
		ven.setSalarioBase(rs.getDouble("SalarioBase"));
		ven.setDepartamento(dep);
		return ven;
	}

	// --Metodo para Instanciar el Departamento--//
	private Departamento instanciacionDpto(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setNro(rs.getInt("DepartamentoId"));
		dep.setNombre(rs.getString("DepNombre"));
		return dep;
	}

}
