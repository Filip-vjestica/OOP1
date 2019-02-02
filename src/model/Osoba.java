package model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

//Basic tmp
@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME, 
		include = JsonTypeInfo.As.PROPERTY, 
	    property = "class")
	@JsonSubTypes({ 
		@Type(value = Iznajmljivac.class, name = "Iznajmljivac"), 
		@Type(value = Sluzbenik.class, name = "Sluzbenik")
	})
public abstract class Osoba {

	private String korisnIme;
	private String lozinka;
	private String jmbg;
	private String ime;
	private String prezime;
	private String uloga;

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public Osoba() {
	}

	public Osoba(String korisnIme, String lozinka, String jmbg, String ime, String prezime, String uloga) {
		this.korisnIme = korisnIme;
		this.lozinka = lozinka;
		this.jmbg = jmbg;
		this.ime = ime;
		this.prezime = prezime;
		this.uloga = uloga;
	}

	public void setKorisnIme(String korisnIme) {
		this.korisnIme = korisnIme;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getKorisnIme() {
		return korisnIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public String getJmbg() {
		return jmbg;
	}

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public abstract String toString();

}
