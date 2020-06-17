package aplicacion;


import java.util.Date;
import model.entidades.Categorias;
import model.entidades.Vendedores;

public class Principal {

	public static void main(String[] args) {
       Categorias dep = new Categorias(1, "Zapateria");
       Vendedores ven = new Vendedores(21, "Bob", "bob@gmail.com", new Date(), 7000.0, dep);
       
       System.out.println(ven);
 
	}

}
