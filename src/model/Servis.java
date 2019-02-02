package model;
//Basic tmp

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
		use=JsonTypeInfo.Id.NAME,
		include= JsonTypeInfo.As.PROPERTY,
		property= "class")

public class Servis {
	private Vozilo vozilo;
	private String datumServis;
	private int brPredjenihKm;

	public Servis() {
	}

	public Servis(Vozilo vozilo, String datumServis, int brPredjenihKm) {
		super();
		this.vozilo = vozilo;
		this.datumServis = datumServis;
		this.brPredjenihKm = brPredjenihKm;
	}

	public Vozilo getVozilo() {
		return vozilo;
	}

	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
	}

	public String getDatumServis() {
		return datumServis;
	}

	public void setDatumServis(String datumServis) {
		this.datumServis = datumServis;
	}

	public int getBrPredjenihKm() {
		return brPredjenihKm;
	}

	public void setBrPredjenihKm(int brPredjenihKm) {
		this.brPredjenihKm = brPredjenihKm;
	}

}
