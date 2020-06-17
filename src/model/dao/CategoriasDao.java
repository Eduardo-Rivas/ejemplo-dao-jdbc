package model.dao;

import java.util.List;

import model.entidades.Categorias;

public interface CategoriasDao {
 
	//--Definimos los Metodos a Sobre Escribir--//
	void incluir(Categorias obj);
	void midificar(Categorias obj);
	void eliminarPorId(Integer id);
	Categorias buscarPorId(Integer id);
	List<Categorias> busAllCat();

}
