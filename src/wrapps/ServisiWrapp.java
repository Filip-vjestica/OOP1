package wrapps;

import java.util.ArrayList;

import model.Servis;

public class ServisiWrapp {
	private ArrayList<Servis> lista;
	
	public ServisiWrapp() {
		super();
	}
	public ServisiWrapp(ArrayList<Servis> lista) {
		super();
		this.lista = lista;
	}
	public ArrayList<Servis> getServisi() {
		return lista;
	}
	public void setServisi(ArrayList<Servis> lista) {
		this.lista = lista;
	}
	
	public void add(Servis s) {
		this.lista.add(s);
	}
	
	@Override
	public String toString() {
		return lista.toString();
	}

}
