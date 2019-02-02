package wrapps;

import java.util.ArrayList;

import model.Osoba;

public class OsobeWrapp {
	private ArrayList<Osoba>lista;
	
	public OsobeWrapp() {
		super();
	}
	
	public OsobeWrapp(ArrayList<Osoba> lista) {
		super();
		this.lista = lista;
	}

	public ArrayList<Osoba> getKorisnici() {
		return lista;
	}

	public void setKorisnici(ArrayList<Osoba> lista) {
		this.lista = lista;
	}
	
	public void add(Osoba k) {
		this.lista.add(k);
	}

	@Override
	public String toString() {
		return lista.toString();
	}
	
	

}
