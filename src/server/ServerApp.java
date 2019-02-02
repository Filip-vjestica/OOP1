package server;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.net.httpserver.HttpServer;

import wrapps.GorivaWrapp;
import wrapps.OsobeWrapp;
import wrapps.RezervacijeWrapp;
import wrapps.ServisiWrapp;
import wrapps.VozilaWrapp;

public class ServerApp {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		GorivaWrapp goriva = mapper.readValue(new File("Goriva.json"), GorivaWrapp.class);
		OsobeWrapp korisnici = mapper.readValue(new File("Korisnici.json"), OsobeWrapp.class);
		RezervacijeWrapp rezervacije = mapper.readValue(new File("Rezervacije.json"), RezervacijeWrapp.class);
		ServisiWrapp servisi = mapper.readValue(new File("Servisi.json"), ServisiWrapp.class);
		VozilaWrapp vozila = mapper.readValue(new File("Vozila.json"), VozilaWrapp.class);
		
		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
		
		server.createContext("/korisnici", new ListaOsobaHandler(korisnici));
		server.createContext("/vozila", new ListaVozilaHandler(vozila));
		server.createContext("/rezervacije",new ListaRezervacijaHandler(rezervacije));
		server.createContext("/servisi",new ListaServisaHandler(servisi));
		server.createContext("/goriva",new ListaGorivaHandler(goriva));
		
		server.setExecutor(null); // podrazumevani executor
		server.start();
		System.out.println("Server slusa na portu 8080!");
		
	}
}
