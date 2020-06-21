package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public void incluir(Vendedores obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void midificar(Vendedores obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarPorId(Integer id) {
		// TODO Auto-generated method stub

	} 

	//--Sobre Escribimos el Metodo buscarPorId--//
	//--Recibiendo un Integer id              --//
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
				//--Llamada al Metodo instanciacionDpto--//
				Departamento dep = instanciacionDpto(rs);

				//--Llamada al Metodo instanciacionVen--//
				Vendedores ven = instanciacionVen(rs, dep);

				//--Asignamos el Obj. Relacionado--//
				ven.setDepart(dep);
				
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

	@Override
	public List<Vendedores> busAllVen() {
		// TODO Auto-generated method stub
		return null;
	}

	//--Sobre Escribimos el Metodo busPorDep()--//
	//--Recibiendo un Objeto Departamento     --//
	@Override
	public List<Vendedores> busPorDep(Departamento departamento) {
       PreparedStatement st = null;
       ResultSet rs = null;
        
       try {
          st = conn.prepareStatement(
        	   "SELECT vendedor.*, departamento.Nombre AS DepNombre "
        	  +"FROM vendedor INNER JOIN departamento "
        	  +"ON vendedor.DepartamentoId = departamento.Id "
        	  +"WHERE vendedor.DepartamentoId = ? "
        	  +"ORDER BY Nombre"); 
          
          //--Asignamos Valor de Vuqueda--//
          st.setInt(1, departamento.getId());
          
          //--Ejecutamos la Consulta--//
          rs = st.executeQuery();
          
          //--Crea una Lista Vacia--//
          List<Vendedores> lista = new ArrayList<>();
          //--Crea un Map Vacio--//
          Map<Integer, Departamento> map = new HashMap<>();
	      while(rs.next()) {
	    	 //--Busca en el mep eñ Id para Tomar el Valor--// 
	    	 Departamento valDep = map.get(rs.getInt("DepartamentoId")); 
	    	 
	    	 //--No Existe Instancia Depto y Agreha al mep--//
	    	 if(valDep == null) {
			     //--Llamada al Metodo instanciacionDpto--//
	    		 valDep = instanciacionDpto(rs);
	    		 
	    		 //--Agregamos al mep--//
	    		 map.put(rs.getInt("DepartamentoId"), valDep);
	    	 }

			 //--Llamada al Metodo instanciacionVen--//
			 Vendedores ven = instanciacionVen(rs, valDep);
             //--Agregamos a la Lista--//
			 lista.add(ven);
			
	      }
	      return lista;
      
	   }  
       catch(SQLException e) {
          throw new DbIntegridadException(e.getMessage());
	   }
	}//--Fin del Metodo busPorDep()--//

	//--Metodo Para Instanciar Vendedores--//
	private Vendedores instanciacionVen(ResultSet rs, Departamento dep) throws SQLException {
		Vendedores ven = new Vendedores();
		ven.setId(rs.getInt("Id"));
		ven.setNombre(rs.getString("Nombre"));
		ven.setEmail(rs.getString("Email"));
		ven.setFecha(rs.getDate("Fecha"));
		ven.setSalarioBase(rs.getDouble("SalarioBase"));
		ven.setDepart(dep);
		return ven;
	}

	//--Metodo para Instanciar el Departamento--//
	private Departamento instanciacionDpto(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setNro(rs.getInt("DepartamentoId"));
		dep.setNombre(rs.getString("DepNombre"));
		return dep;
	}

}
