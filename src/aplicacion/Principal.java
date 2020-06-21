package aplicacion;


import java.util.Date;
import java.util.List;

import model.dao.FabricaDao;
import model.dao.IntrfVenDao;
import model.dao.impl.ImplVenDaoJdbc;
import model.entidades.Departamento;
import model.entidades.Vendedores;

public class Principal {
 
	public static void main(String[] args) {
        
       //--Llamamos a la Interfase para Instanciarla (IntrfVenDao) --//
       IntrfVenDao interfvendao = FabricaDao.crearVendedoresDao();
       
       //--Llamamos al Metodo buscarPorId(id) para Sobre Escribirlo--//
       Vendedores ven = interfvendao.buscarPorId(1);
       
       System.out.println("=== Prueba Nro. 1 Vendedor buscarPorId() ===");
       System.out.println(ven);
       System.out.println();
       
       System.out.println("=== Prueba Nro. 2 interfvendao.buscarPorDepartamento() ===");
       Departamento dpto = new Departamento(2, null);
       
       //--Pasa a interfvendao.busPorDep() un Obj. dpto--//
       List<Vendedores> lista = interfvendao.busPorDep(dpto);
	   System.out.println("Cod.      Nombre       Email Fec. Nacimto   Salario Base   Nro. Dpto    Departamento");
	   System.out.println("----  --------------  ------  -----------  -------------   ---------  --------------");
	   for(Vendedores xobj : lista) {
          System.out.println(xobj);	    
       }
       
 
	}

}
