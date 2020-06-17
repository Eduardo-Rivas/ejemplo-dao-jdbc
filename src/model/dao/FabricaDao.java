package model.dao;

import model.dao.impl.VendedorImplDaoJdbc;

public class FabricaDao {
	
   public static VendedoresDao crearVendedoresDao() {
      return new VendedorImplDaoJdbc();	   
   } 
   
}
