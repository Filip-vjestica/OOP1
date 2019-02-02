package wrapps;

import java.util.ArrayList;

import model.Vozilo;



public class VozilaWrapp {
	private ArrayList<Vozilo> lista;
	
	public VozilaWrapp() {
		super();
	}
	
	public VozilaWrapp(ArrayList<Vozilo> lista) {
		super();
		this.lista = lista;
	}

	public ArrayList<Vozilo> getVozilaWrapp() {
		return lista;
	}

	public void setVozilaWrapp(ArrayList<Vozilo> lista) {
		this.lista = lista;
	}

	public void add(Vozilo v) {
		this.lista.add(v);
	}

	@Override
	public String toString() {
		return lista.toString();
	}
}
