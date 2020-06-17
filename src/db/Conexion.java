package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//--Define Clase Conexion--//
public class Conexion {
	private static Connection conn = null;

	// --Creamos Metodo para Abrir la Conexion--//
	public static Connection abrirConexion() {
    	if(conn == null) {
    		try {
    	       //--Crgamos las Propiedades  del Archivo--//	
    	       Properties props = cargarProps();
    	           
    	       //--Tomamos la url del Obj props--//
    	       String url = props.getProperty("dburl");
    	           
    	       //--Hacemos la Conexion--//
    	       conn = DriverManager.getConnection(url, props);
    	       System.out.println("Conexion Exitosa Ok...");
    		}
    		catch(SQLException e) {
    		   throw new DbException(e.getMessage());	
    		}
    		
    	}
    	
    	return conn;
    }
 
	//--Cerra la Conexion--//
	public static void cerrarConexion() {
		if(conn != null) {
		   try {
			   conn.close();	   
		   }
		   catch(SQLException e) {
		      throw new DbException(e.getMessage());   
		   }
	
		}
	}
	
	// --Creamos Metodo statico para tomar las Propiedades--//
	// --Del Archivo db.properties --//
	private static Properties cargarProps() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}

	}
	
	//--Metodo para cerrar el Statement--//
	public static void cierraStatement(Statement st) {
	   if(st != null) {
	      try {
		     st.close(); 
	      }
	      catch(SQLException e) {
		     throw new DbException(e.getMessage()); 
	      }
		
	   }	
	}
	
	//--Metodo para cerrar el ResulSet--//
	public static void cierraResultSet(ResultSet rs) {
	   if(rs != null) {
	      try {
		     rs.close(); 
	      }
	      catch(SQLException e) {
		     throw new DbException(e.getMessage()); 
	      }
		
	   }	
	}
	
}//--Fin Clase Conexion--//
