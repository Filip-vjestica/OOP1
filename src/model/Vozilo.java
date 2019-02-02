package model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
//Basic tmp
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME, 
		include = JsonTypeInfo.As.PROPERTY, 
		property = "class")
@JsonSubTypes({ 
	@Type(value = Bicikl.class, name = "Bicikl"),
	@Type(value = PutnickoVozilo.class, name = "PutnickoVozilo"),
	@Type(value = TeretnaVozila.class, name = "TeretnoVozilo")
})
@JsonIgnoreProperties({"trenutnaCena"})
public abstract class Vozilo {
	private String vrstaVozila;
	private int regBR;
	private Gorivo gorivo;
	private int brServisa;
	private double potrosnja100;
	private int predjeno;
	private int preServis;
	private int cenaServis;
	private int cenaDan;
	private int brSedist;
	private int brVrata;
	private boolean vozObrisano;
	private ArrayList<Servis> servisiNadVozilom;

	public Vozilo() {
	}

	public Vozilo(String vrstaVozila, int regBR, Gorivo gorivo, int brServisa, double potrosnja100, int predjeno,
			int preServis, int cenaServis, int cenaDan, int brSedist, int brVrata, boolean vozObrisano,
			ArrayList<Servis> ServisiNadVozilom) {
		this.vrstaVozila = vrstaVozila;
		this.regBR = regBR;
		this.gorivo = gorivo;
		this.brServisa = brServisa;
		this.potrosnja100 = potrosnja100;
		this.predjeno = predjeno;
		this.preServis = preServis;
		this.cenaServis = cenaServis;
		this.cenaDan = cenaDan;
		this.brSedist = brSedist;
		this.brVrata = brVrata;
		this.vozObrisano = vozObrisano;
		this.servisiNadVozilom = ServisiNadVozilom;
	}

	public Servis getServisiNadVozilom(int i) {
		return servisiNadVozilom.get(i);
	}

	public void dodajServis(Servis s) {
		this.servisiNadVozilom.add(s);
	}

	public int velicinaServisa() {
		return servisiNadVozilom.size();
	}

	public String getVrstaVozila() {
		return vrstaVozila;
	}

	public void setVrstaVozila(String vrstaVozila) {
		this.vrstaVozila = vrstaVozila;
	}

	public abstract String toString();

	public Gorivo getGorivo() {
		return gorivo;
	}

	public void setGorivo(Gorivo gorivo) {
		this.gorivo = gorivo;
	}

	public int getBrServisa() {
		return brServisa;
	}

	public void setBrServisa(int brServisa) {
		this.brServisa = brServisa;
	}

	public int getPredjeno() {
		return predjeno;
	}

	public void setPredjeno(int predjeno) {
		this.predjeno = predjeno;
	}

	public int getPreServis() {
		return preServis;
	}

	public void setPreServis(int preServis) {
		this.preServis = preServis;
	}

	public boolean isVozObrisano() {
		return vozObrisano;
	}

	public void setVozObrisano(boolean vozObrisano) {
		this.vozObrisano = vozObrisano;
	}

	public int getRegBR() {
		return regBR;
	}

	public double getPotrosnja100() {
		return potrosnja100;
	}

	public int getCenaServis() {
		return cenaServis;
	}

	public int getCenaDan() {
		return cenaDan;
	}

	public int getBrSedist() {
		return brSedist;
	}

	public void setRegBR(int regBR) {
		this.regBR = regBR;
	}

	public void setPotrosnja100(double potrosnja100) {
		this.potrosnja100 = potrosnja100;
	}

	public void setCenaServis(int cenaServis) {
		this.cenaServis = cenaServis;
	}

	public void setCenaDan(int cenaDan) {
		this.cenaDan = cenaDan;
	}

	public void setBrSedist(int brSedist) {
		this.brSedist = brSedist;
	}

	public void setBrVrata(int brVrata) {
		this.brVrata = brVrata;
	}

	public int getBrVrata() {
		return brVrata;
	}
}
