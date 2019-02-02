package model;

import java.util.ArrayList;


//Basic tmp
public class TeretnaVozila extends Vozilo {
	private int maxMasauKg;
	private double visinauM;

	public TeretnaVozila() {
	}

	public TeretnaVozila(String vrstaVozila, int regBR, Gorivo gorivo, int brServisa, double potrosnja100, int predjeno,
			int preServis, int cenaServis, int cenaDan, int brSedist, int brVrata, boolean vozObrisano,
			ArrayList<Servis> servisiNadVozilom, int maxMasauKg, double visinauM) {
		super(vrstaVozila, regBR, gorivo, brServisa, potrosnja100, predjeno, preServis, cenaServis, cenaDan, brSedist,
				brVrata, vozObrisano, servisiNadVozilom);
		this.maxMasauKg = maxMasauKg;
		this.visinauM = visinauM;

	}

	public String toString() {
		return "\nTeretno vozilo-Registarski broj:" + super.getRegBR() + " | Gorivo:" + super.getGorivo()
				+ " | Prosecna potrosnja/100km:" + super.getPotrosnja100() + " | Cena na dan:" + super.getCenaDan()
				+ " | Broj sedista:" + super.getBrSedist() + " | Broj vrata:" + super.getBrVrata()
				+ " | Maximanlna masa u kg:" + this.maxMasauKg + " | Visina u metrima:" + this.visinauM;
	}

	public int getMaxMasauKg() {
		return maxMasauKg;
	}

	public void setMaxMasauKg(int maxMasauKg) {
		this.maxMasauKg = maxMasauKg;
	}

	public double getVisinauM() {
		return visinauM;
	}

	public void setVisinauM(float visinauM) {
		this.visinauM = visinauM;
	}

}
