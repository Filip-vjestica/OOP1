package main;

import java.io.File;
import java.util.ArrayList;

import model.Iznajmljivac;
import model.Osoba;
import model.Sluzbenik;
import utils.MappingUtil;
import wrapps.OsobeWrapp;

public class upisKorisnika {

	public static void main(String[] args) {
		OsobeWrapp korisnici = new OsobeWrapp(new ArrayList<Osoba>());
		
		Sluzbenik o1 = new Sluzbenik("ime1", "lozinka1", "34532", "Marko", "Maki", "Sluzbenik");
		Iznajmljivac o2 = new Iznajmljivac("ime2", "lozinka2", "blablajmbg", "Filip", "fica", "Iznajmljivac",
				"06333222", "Srbija");
		
		korisnici.add(o1);
		korisnici.add(o2);
		
		MappingUtil.toJson(new File("Korisnici.json"), korisnici);

	}

}
