package com.example.responses;

import com.example.entity.Partida;

//Clase response de partida para entregarla al front sin la palabra correcta
public class PartidaResponse {
    private Integer id;
	private String palabraRellenada;
	private String letrasUsadas;
	private Integer numeroDeFallos;
		
	public PartidaResponse(Partida partida) {
		this.id = partida.getId();
		this.palabraRellenada = partida.getPalabraRellenada();
		this.letrasUsadas = partida.getLetrasUsadas();
		this.numeroDeFallos = partida.getNumeroDeFallos();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setLetrasUsadas(String letrasUsadas) {
		this.letrasUsadas = letrasUsadas;
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
		PartidaResponse other = (PartidaResponse) obj;
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
		if (palabraRellenada == null) {
			if (other.palabraRellenada != null)
				return false;
		} else if (!palabraRellenada.equals(other.palabraRellenada))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PartidaResponse [id=" + id + ", palabraRellenada=" + palabraRellenada + ", letrasUsadas=" + letrasUsadas
				+ ", numeroDeFallos=" + numeroDeFallos + "]";
	}

}
