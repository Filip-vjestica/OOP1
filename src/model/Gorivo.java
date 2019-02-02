package model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
		use=JsonTypeInfo.Id.NAME,
		include= JsonTypeInfo.As.PROPERTY,
		property= "class")
//Basic tmp
public class Gorivo {
	private String naziv;
	private double cena;

	public Gorivo() {
	}

	public Gorivo(String naziv, double cena) {
		this.naziv = naziv;
		this.cena = cena;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String toString() {
		return "Gorivo-naziv:" + naziv + " Cena goriva:" + cena;
	}

}
