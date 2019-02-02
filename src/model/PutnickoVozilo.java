package model;

import java.util.ArrayList;



//Basic tmp
public class PutnickoVozilo extends Vozilo{

	public PutnickoVozilo() {
	}

	public PutnickoVozilo(String vrstaVozila, int regBR, Gorivo gorivo, int brServisa, double potrosnja100,
			int predjeno, int preServis, int cenaServis, int cenaDan, int brSedist, int brVrata, boolean vozObrisano,
			ArrayList<Servis> ServisiNadVozilom) {
		super(vrstaVozila, regBR, gorivo, brServisa, potrosnja100, predjeno, preServis, cenaServis, cenaDan, brSedist,
				brVrata, vozObrisano, ServisiNadVozilom);
	}

	public String toString() {
		return "Putnicko vozilo-Registarski broj:" + super.getRegBR() + " | Gorivo:" + super.getGorivo()
				+ " | Prosecna potrosnja/100km:" + super.getPotrosnja100() + " | Cena na dan:" + super.getCenaDan()
				+ " | Broj sedista:" + super.getBrSedist() + " | Broj vrata:" + super.getBrVrata();
	}

}
