package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.Conexion;
import db.DbException;
import model.dao.IntrfVenDao;
import model.entidades.Departamento; 
import model.entidades.Vendedores;
 
public class ImplVenDaoJdbc implements IntrfVenDao {
    private Connection conn;
    
    //--Constructor para Hacer Inyeccion de Dependencia--//
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

	@Override
	public Vendedores buscarPorId(Integer id) {
       PreparedStatement st = null;
       ResultSet rs = null;
       
       try {
          st = conn.prepareStatement("SELECT vendedor.*,"
          	 + "departamento.Nombre AS DepNombre FROM "
          	 + "vendedor INNER JOIN departamento "
             + "ON vendedor.DepartamentoId = departamento.Id "
             + "WHERE vendedor.Id = ?"); 
          st.setInt(1, id);  
            
          rs = st.executeQuery();
          if(rs.next()) {//--Hay Datos--//
             //--Instanciamos Departamento. dep--//
        	 Departamento dep = new Departamento();
        	 dep.setNro(rs.getInt("DepartamentoId")); 
        	 dep.setNombre(rs.getString("DepNombre"));
        	   
        	 //--Instanciamos Vendedor. ven--//
        	 Vendedores ven = new Vendedores();
        	 ven.setId(rs.getInt("Id"));
        	 ven.setNombre(rs.getString("Nombre"));
        	 ven.setEmail(rs.getString("Email"));
        	 ven.setFecha(rs.getDate("Fecha"));
        	 ven.setSalarioBase(rs.getDouble("SalarioBase"));
        	 
        	 //--Asignamos el Obj. Relacionado--//
        	 ven.setDepart(dep);
        	 
        	 //--Retornamos el Obj. Vendedor--//
        	 return ven;
          }
          //--No Hay Datos--//
          return null;	  
       }
       catch(SQLException e) {
          throw new DbException(e.getMessage());	   
       }
       finally {
          Conexion.cierraStatement(st);
          Conexion.cierraResultSet(rs);
       }
	}

	@Override
	public List<Vendedores> busAllVen() {
		// TODO Auto-generated method stub
		return null;
	}

}
