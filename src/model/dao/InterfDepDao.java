package model.dao;

import java.util.List;

import model.entidades.Departamento;

public interface InterfDepDao {

	// --Definimos los Metodos a Sobre Escribir--//
	void incluir(Departamento obj);
	void midificar(Departamento obj);
	void eliminarPorId(Integer id);
	Departamento buscarPorId(Integer id);
	List<Departamento> busAllCat();
}
