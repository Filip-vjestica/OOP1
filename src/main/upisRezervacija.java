package main;

import java.io.File;
import java.util.ArrayList;

import model.Gorivo;
import model.Iznajmljivac;
import model.PutnickoVozilo;
import model.Rezervacija;
import model.Servis;
import utils.MappingUtil;
import wrapps.RezervacijeWrapp;

public class upisRezervacija {

	public static void main(String[] args) {
		RezervacijeWrapp rezervacije = new RezervacijeWrapp(new ArrayList<Rezervacija>());
		Gorivo benzin = new Gorivo("Benzin", 140.5d);
		
		PutnickoVozilo a1 = new PutnickoVozilo("Putnicko Vozilo", 2425, benzin, 3, 6.1d, 160000, 5000, 10000,
				2500, 4, 4, false, new ArrayList<Servis>());
		
		
		
		Iznajmljivac o2 = new Iznajmljivac("ime2", "lozinka2", "blablajmbg", "Filip", "fica", "Iznajmljivac",
				"06333222", "Srbija");
		
		Rezervacija r1 = new Rezervacija(a1, o2, 15000d, false, "10/10/2015", "20/10/2015");
		
		rezervacije.add(r1);
		
		MappingUtil.toJson(new File("Rezervacije.json"), rezervacije);
		

	}

}
