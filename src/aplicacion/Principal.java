package aplicacion;


import java.util.Date;

import model.dao.FabricaDao;
import model.dao.IntrfVenDao;
import model.entidades.Departamento;
import model.entidades.Vendedores;

public class Principal {

	public static void main(String[] args) {
       
       //--Instanciamos la Interfase--//
       IntrfVenDao interfvendao = FabricaDao.crearVendedoresDao();
        
       Vendedores ven = interfvendao.buscarPorId(1);
       System.out.println(ven);
 
	}

}
