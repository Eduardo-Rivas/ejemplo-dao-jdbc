package model.entidades;

import java.io.Serializable;

public class Departamento implements Serializable{
	//--Definimos Nro. de Version Padron Serializable --//
	private static final long serialVersionUID = 1L;
	
	//--Definimos los Atributos--//
	private Integer id;
	private String nombre;

	//--Definimos constructor Padron--//
	public Departamento() {
	}
	//--Definimos constructor con Argumentos--//
	public Departamento(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	//--Definimos Getters y Setters--//
	public Integer getId() {
		return id;
	}
	public void setNro(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	//--Definimos de Comparacion hashCode e Equals --//
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	//--Definimos Metodo de Salia de Datos--//
	@Override
	public String toString() {
		return "Departamentos :" + id + " - Nombre :" + nombre;
	}
	
}
