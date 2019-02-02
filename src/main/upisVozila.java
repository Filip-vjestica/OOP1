package main;

import java.io.File;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;

import model.Bicikl;
import model.Gorivo;
import model.PutnickoVozilo;
import model.Servis;
import model.TeretnaVozila;
import model.Vozilo;
import utils.MappingUtil;
import wrapps.VozilaWrapp;

public class upisVozila {

	public static void main(String[] args) throws JsonProcessingException {
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
		
		VozilaWrapp vozila = new VozilaWrapp(new ArrayList<Vozilo>());
		vozila.add(a1);
		vozila.add(a2);
		vozila.add(a3);
		vozila.add(a4);
		
		MappingUtil.toJson(new File("Vozila.json"),vozila);

	}

}
