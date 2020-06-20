package aplicacion;


import java.util.Date;

import model.dao.FabricaDao;
import model.dao.IntrfVenDao;
import model.entidades.Departamento;
import model.entidades.Vendedores;

public class Principal {
 
	public static void main(String[] args) {
        
       //--Llamamos a la Interfase (IntrfVenDao) --//
       IntrfVenDao interfvendao = FabricaDao.crearVendedoresDao();
       
       //--Llamamos al Metodo buscarPorId(id) para Sobre Escribirlo--//
       Vendedores ven = interfvendao.buscarPorId(1);
       
       System.out.println("=== Prueba Nro. 1 Vendedor buscarPorId() ===");
       System.out.println(ven);
 
	}

}
