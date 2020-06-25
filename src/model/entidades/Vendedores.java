package model.entidades;

import java.io.Serializable;
import java.util.Date;
 
public class Vendedores implements Serializable{
	//--Versionamiento de Serializable--//
	private static final long serialVersionUID = 1L;
	
	// --Definimos los Atributos--//
	private Integer id;
	private String nombre;
	private String email;
	private Date fecha;
	private Double SalarioBase;

	// --Composicion de los Objs.--//
	private Departamento departamento;

	// --Costructor Padron--//
	public Vendedores() {
	}
	// --Costructor con Argumentos--//
	public Vendedores(Integer id, String nombre, String email, Date fecha, Double salarioBase, Departamento departamento) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.fecha = fecha;
		SalarioBase = salarioBase;
		this.departamento = departamento;
	}
	
	//--Getters y Setters--//
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getSalarioBase() {
		return SalarioBase;
	}
	public void setSalarioBase(Double salarioBase) {
		SalarioBase = salarioBase;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	//--hashCode e Equals--//
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
		Vendedores other = (Vendedores) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	//--Metodo toString--//
	@Override
	public String toString() {
		return id +"     "+ nombre +"      " + email
				  +"   "+ fecha  +"      "+ SalarioBase
				  +"   "+  departamento;
	}
}
