package main;

import java.io.File;
import java.util.ArrayList;

import model.Bicikl;
import model.Gorivo;
import model.PutnickoVozilo;
import model.Servis;
import model.TeretnaVozila;
import utils.MappingUtil;
import wrapps.ServisiWrapp;

public class upisServisa {

	public static void main(String[] args) {
		ServisiWrapp servisi = new ServisiWrapp(new ArrayList<Servis>());
		
		Gorivo benzin = new Gorivo("Benzin", 140.5d);
		Gorivo dizel = new Gorivo("Dizel", 148.0d);
		Gorivo nema = new Gorivo("Nema",0.0d);
		
		PutnickoVozilo a1 = new PutnickoVozilo("Putnicko Vozilo", 2425, benzin, 3, 6.1d, 160000, 5000, 10000,
				2500, 4, 4, false, new ArrayList<Servis>());
		TeretnaVozila a2 = new TeretnaVozila("Teretno Vozilo", 5253, dizel, 4, 7.0d, 200000, 3000, 12000,
				3200, 4, 4, false, new ArrayList<Servis>(), 2225, 2.2d);
		Bicikl a3 = new Bicikl("Bicikl", 6576, nema, 2, 0.0d, 1500, 100, 1500, 500, 1, 0, false,
				new ArrayList<Servis>());
		Bicikl a4 = new Bicikl("Bicikl", 6666, nema, 3, 0.0d, 1200, 250, 1500, 600, 1, 0, false,
				new ArrayList<Servis>());
		
		Servis e1 = new Servis(a1, "15/07/2017", 150000);
		Servis e2 = new Servis(a2, "13/05/2016", 120000);
		Servis e3 = new Servis(a3, "10/10/2015", 1399);
		Servis e4 = new Servis(a4, "15/10/2016", 1000);
		
		servisi.add(e1);
		servisi.add(e2);
		servisi.add(e3);
		servisi.add(e4);
		
		MappingUtil.toJson(new File("Servisi.json"), servisi);

	}

}
