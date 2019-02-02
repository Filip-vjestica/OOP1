package main;

import java.io.File;
import java.util.ArrayList;



import model.Gorivo;
import utils.MappingUtil;
import wrapps.GorivaWrapp;

public class upisGoriva {
	
	public static void main(String[] args) {
		Gorivo benzin = new Gorivo("Benzin", 140.5d);
		Gorivo dizel = new Gorivo("Dizel", 148.0d);
		Gorivo nema = new Gorivo("Nema",0.0d);
		
		GorivaWrapp goriva = new GorivaWrapp(new ArrayList<Gorivo>());
		
		goriva.add(benzin);
		goriva.add(dizel);
		goriva.add(nema);
		
		
		MappingUtil.toJson(new File("Goriva.json"), goriva);
		

	}

}
