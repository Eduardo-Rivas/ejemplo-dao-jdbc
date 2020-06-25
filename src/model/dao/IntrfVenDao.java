package model.dao;

import java.util.List;

import model.entidades.Departamento;
import model.entidades.Vendedores;

public interface IntrfVenDao {
	
	//--Va a la Clase ImplVenDaoJdbc para Implementar--//
	//--El Metodo incluir() enviando obj             --//
	void incluir(Vendedores obj);
	
	
	void midificar(Vendedores obj);
	
	
	void eliminarPorId(Integer id);
	
	//--Va a la Clase ImplVenDaoJdbc para Implementar--//
	//--El Metodo busPorId enviando Integer id       --//
	Vendedores buscarPorId(Integer id);

	//--Va a la Clase ImplVenDaoJdbc para Implementar--//
	//--El Metodo busAllVen()                        --//
	List<Vendedores> busAllVen();
	
	//--Va a la Clase ImplVenDaoJdbc para Implementar--//
	//--El Metodo busPorDep enviando un Obj. dep.    --//
	List<Vendedores> busPorDep(Departamento dep);

}
