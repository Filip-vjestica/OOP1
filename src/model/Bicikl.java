package model;

import java.util.ArrayList;



public class Bicikl extends Vozilo  {
	// Basic tmp
	public Bicikl() {
	}

	public Bicikl(String vrstaVozila, int regBR, Gorivo gorivo, int brServisa, double potrosnja100, int predjeno,
			int preServis, int cenaServis, int cenaDan, int brSedist, int brVrata, boolean vozObrisano,
			ArrayList<Servis> ServisiNadVozilom) {
		super(vrstaVozila, regBR, gorivo, brServisa, potrosnja100, predjeno, preServis, cenaServis, cenaDan, brSedist,
				brVrata, vozObrisano, ServisiNadVozilom);
	}

	public String toString() {
		return "\nBicikl-Registarski broj:" + super.getRegBR() + " | Broj predjenih KM:" + super.getPredjeno()
				+ " | Cena na dan:" + super.getCenaDan();
	}

}
