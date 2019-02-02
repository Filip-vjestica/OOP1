package wrapps;

import java.util.ArrayList;

import model.Rezervacija;


public class RezervacijeWrapp {
	private ArrayList<Rezervacija> lista;
	
	public RezervacijeWrapp() {
		super();
	}

	public RezervacijeWrapp(ArrayList<Rezervacija> lista) {
		super();
		this.lista = lista;
	}
	
	public ArrayList<Rezervacija> getRezervacijeWrapp() {
		return lista;
	}

	public void setRezervacijeWrapp(ArrayList<Rezervacija> lista) {
		this.lista = lista;
	}

	public void add(Rezervacija r) {
		this.lista.add(r);
	}

	@Override
	public String toString() {
		return lista.toString();
	}

}
