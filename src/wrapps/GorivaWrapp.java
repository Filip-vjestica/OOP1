package wrapps;

import java.util.ArrayList;

import model.Gorivo;

public class GorivaWrapp {
	private ArrayList<Gorivo>lista;
	
	public GorivaWrapp() {
		super();
	}
	
	public GorivaWrapp(ArrayList<Gorivo> lista) {
		super();
		this.lista = lista;
	}

	public ArrayList<Gorivo> getGorivaWrapp() {
		return lista;
	}

	public void setGorivaWrapp(ArrayList<Gorivo> lista) {
		this.lista = lista;
	}

	public void add(Gorivo g) {
		this.lista.add(g);
	}

	@Override
	public String toString() {
		return lista.toString();
	}

}
