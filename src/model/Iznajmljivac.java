package model;

//Basic tmp
public class Iznajmljivac extends Osoba {

	private String brojTelefon;
	private String drzavljanstvo;

	public Iznajmljivac() {
	}

	public String getBrojTelefon() {
		return brojTelefon;
	}

	public String getDrzavljanstvo() {
		return drzavljanstvo;
	}

	public Iznajmljivac(String korisnIme, String lozinka, String jmbg, String ime, String prezime, String uloga,
			String brojTelefona, String drzavljanstvo) {
		super(korisnIme, lozinka, jmbg, ime, prezime, uloga);
		this.brojTelefon = brojTelefona;
		this.drzavljanstvo = drzavljanstvo;
	}

	public void setBrojTelefon(String brojTelefon) {
		this.brojTelefon = brojTelefon;
	}

	public void setDrzavljanstvo(String drzavljanstvo) {
		this.drzavljanstvo = drzavljanstvo;
	}

	@Override
	public String toString() {
		return "Iznajmljivac:" + super.getIme() + " " + super.getPrezime() + " JMBG:" + super.getJmbg()
				+ "Broj telefona:" + this.getBrojTelefon() + "Drzavljanstvo:" + this.getDrzavljanstvo();
	}
}
