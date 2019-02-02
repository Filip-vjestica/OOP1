package model;

//Basic tmp
public class Sluzbenik extends Osoba {

	public Sluzbenik() {
	}

	public Sluzbenik(String korisnIme, String lozinka, String jmbg, String ime, String prezime, String uloga) {
		super(korisnIme, lozinka, jmbg, ime, prezime, uloga);
	}

	public String toString() {
		return "Sluzbenik:" + super.getIme() + " " + super.getPrezime();
	}
}
