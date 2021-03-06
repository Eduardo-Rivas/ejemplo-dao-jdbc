package aplicacion;


import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.FabricaDao;
import model.dao.IntrfVenDao;
import model.entidades.Departamento;
import model.entidades.Vendedores;

public class Principal {
 
	public static void main(String[] args) {
       Scanner sc = new Scanner(System.in); 
       
       //--Llamamos a la Interfase para Instanciarla (IntrfVenDao) --//
       IntrfVenDao interfvendao = FabricaDao.crearVendedoresDao();
       
       //--Llamamos al Metodo buscarPorId(id) para Sobre Escribirlo--//
       Vendedores vendedor = interfvendao.buscarPorId(1);
       
       System.out.println("=== Prueba Nro. 1 Vendedor buscarPorId() ===");
       System.out.println(vendedor);
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
       System.out.println();
       
       System.out.println("=== Prueba Nro. 3 interfvendao.busAllVen() ===");
       //--Pasa a interfvendao.busAllVen() Todos los Vendedores--//
       lista = interfvendao.busAllVen();
	   System.out.println("Cod.      Nombre       Email Fec. Nacimto   Salario Base   Nro. Dpto    Departamento");
	   System.out.println("----  --------------  ------  -----------  -------------   ---------  --------------");
	   for(Vendedores xobj : lista) {
          System.out.println(xobj);	    
       }
       System.out.println();
       
       System.out.println("=== Prueba Nro. 4 interfvendao.incluir() ===");
       Vendedores newVen = new Vendedores(null, "Sheky", "sheky@gmail.com", new Date(), 7000.0, dpto);
       interfvendao.incluir(newVen);
       System.out.println("Nuevo Registro Incluido "+newVen.getId());
       System.out.println();

       System.out.println("=== Prueba Nro. 5 interfvendao.modificar() ===");
       vendedor = interfvendao.buscarPorId(1);
       vendedor.setNombre("Genesarth Rivas");
       vendedor.setEmail("genesarth@gmail.com");
       interfvendao.midificar(vendedor);
       System.out.println("Registro "+vendedor.getId()+" Actualizado... ");
       System.out.println();

       System.out.println("=== Prueba Nro. 6 interfvendao.eliminarPorId() ===");
       System.out.println("Entre el Codigo a Eliminar :");
       int id = sc.nextInt();
       interfvendao.eliminarPorId(id);
       System.out.println("Registro Eliminado...");
       
       sc.close();
	}

}
