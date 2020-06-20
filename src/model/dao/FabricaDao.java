package model.dao;

import db.Conexion;
import model.dao.impl.ImplVenDaoJdbc;

public class FabricaDao {
	
   public static IntrfVenDao crearVendedoresDao() {
      return new ImplVenDaoJdbc(Conexion.abrirConexion());	   
   } 
   
}
