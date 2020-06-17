package model.dao;

import java.util.List;
import model.entidades.Vendedores;

public interface VendedoresDao {
	
	//--Definimos los Metodos a Sobre Escribir--//
	void incluir(Vendedores obj);
	void midificar(Vendedores obj);
	void eliminarPorId(Integer id);
	Vendedores buscarPorId(Integer id);
	List<Vendedores> busAllVen();

}
