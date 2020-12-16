package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Tabla de la base de datos
@Entity
public class Partida {
	/*ID y columnas de la tabla la generación puesta en id y genereda por la base de datos con un AUTO_INCREMENT
	en este caso tiene la anotación column por que el nombre de la columna no es exactamente el mismo en la base de datos por el uso de mayuscula en las variables*/ 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private String palabra;
	@Column(name = "palabrarellenada")
	private String palabraRellenada;
	@Column(name = "letrasusadas")
	private String letrasUsadas;
	@Column(name = "numerodefallos")
	private Integer numeroDeFallos;
		
	public Partida(Integer id, String palabra, String palabraRellenada, String letrasFalladas, Integer numeroDeFallos) {
		this.id = id;
		this.palabra = palabra;
		this.palabraRellenada = palabraRellenada;
		this.letrasUsadas = letrasFalladas;
		this.numeroDeFallos = numeroDeFallos;
	}
	
	public Partida() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPalabra() {
		return palabra;
	}
	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}
	public String getPalabraRellenada() {
		return palabraRellenada;
	}
	public void setPalabraRellenada(String palabraRellenada) {
		this.palabraRellenada = palabraRellenada;
	}
	public String getLetrasUsadas() {
		return letrasUsadas;
	}
	public void setLetrasUsadas(String letrasFalladas) {
		this.letrasUsadas = letrasFalladas;
	}
	public Integer getNumeroDeFallos() {
		return numeroDeFallos;
	}
	public void setNumeroDeFallos(Integer numeroDeFallos) {
		this.numeroDeFallos = numeroDeFallos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((letrasUsadas == null) ? 0 : letrasUsadas.hashCode());
		result = prime * result + ((numeroDeFallos == null) ? 0 : numeroDeFallos.hashCode());
		result = prime * result + ((palabra == null) ? 0 : palabra.hashCode());
		result = prime * result + ((palabraRellenada == null) ? 0 : palabraRellenada.hashCode());
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
		Partida other = (Partida) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (letrasUsadas == null) {
			if (other.letrasUsadas != null)
				return false;
		} else if (!letrasUsadas.equals(other.letrasUsadas))
			return false;
		if (numeroDeFallos == null) {
			if (other.numeroDeFallos != null)
				return false;
		} else if (!numeroDeFallos.equals(other.numeroDeFallos))
			return false;
		if (palabra == null) {
			if (other.palabra != null)
				return false;
		} else if (!palabra.equals(other.palabra))
			return false;
		if (palabraRellenada == null) {
			if (other.palabraRellenada != null)
				return false;
		} else if (!palabraRellenada.equals(other.palabraRellenada))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Partida [id=" + id + ", palabra=" + palabra + ", palabraRellenada=" + palabraRellenada
				+ ", letrasFalladas=" + letrasUsadas + ", numeroDeFallos=" + numeroDeFallos + "]";
	}

}
