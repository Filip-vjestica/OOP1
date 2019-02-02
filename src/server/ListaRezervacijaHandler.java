package server;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controller.UtillMethod;
import model.Rezervacija;
import model.Vozilo;
import utils.MappingUtil;
import utils.URIUtil;
import wrapps.RezervacijeWrapp;

public class ListaRezervacijaHandler implements HttpHandler {
	private RezervacijeWrapp rezervacije;
	private ObjectMapper mapper = new ObjectMapper();

	public ListaRezervacijaHandler(RezervacijeWrapp rezervacije) {
		super();
		this.rezervacije = rezervacije;
	}
	public  boolean postojiLiRezervacijaH(Vozilo v, LocalDate p, LocalDate k) {
		for (Rezervacija e : rezervacije.getRezervacijeWrapp()) {
			if (e.getVozilo().equals(v) && !e.isRezervacijaObrisana()
					&& UtillMethod.parsovanjeDatuma(e.getDatumPocetka()).isAfter(p)
					&& UtillMethod.parsovanjeDatuma(e.getDatumKraja()).isBefore(k)) {
				return true;
			} else if (e.getVozilo().equals(v) && !e.isRezervacijaObrisana()
					&& UtillMethod.parsovanjeDatuma(e.getDatumPocetka()).isBefore(p)
					&& UtillMethod.parsovanjeDatuma(e.getDatumKraja()).isAfter(p)) {
				return true;
			} else if (e.getVozilo().equals(v) && !e.isRezervacijaObrisana()
					&& UtillMethod.parsovanjeDatuma(e.getDatumPocetka()).isBefore(k)
					&& UtillMethod.parsovanjeDatuma(e.getDatumKraja()).isAfter(k)) {
				return true;
			} else if (e.getVozilo().equals(v) && !e.isRezervacijaObrisana()
					&& UtillMethod.parsovanjeDatuma(e.getDatumPocetka()).isBefore(p)
					&& UtillMethod.parsovanjeDatuma(e.getDatumKraja()).isAfter(k)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void handle(HttpExchange ex) throws IOException {
		String reqMethod = ex.getRequestMethod();

		Headers resHeaders = ex.getResponseHeaders();
		resHeaders.set("Content-Type", "application/json; charset=UTF-8");
		resHeaders.set("Access-Control-Allow-Origin", "*");
		resHeaders.set("Access-Control-Allow-Methods", "GET,POST");

		URI reqURI = ex.getRequestURI();
		HashMap<String, String> params = URIUtil.queryToMap(reqURI);
		Writer os = new OutputStreamWriter(ex.getResponseBody(), "UTF-8");

		InputStream in = ex.getRequestBody();
		byte[] body = in.readAllBytes();
		String bodyStr = new String(body, "UTF-8");

		switch (reqMethod) {
		case "GET":
			System.out.println("Obrada GET zahteva!");
			try {
				getRezervacija(ex, os, params);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("GET zahtev obradjen!");
			break;
		case "POST":
			System.out.println("Obrada POST zahteva!");
			try {
				postRezervacija(ex, os, params, bodyStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("POST zahtev obradjen!");
			break;
		default:
			System.out.println("Neodgovarajuci metod zahteva!");
		}
		os.close();
	}

	private void getRezervacija(HttpExchange ex, Writer os, HashMap<String, String> params)
			throws JsonProcessingException, IOException {
		ex.sendResponseHeaders(200, mapper.writeValueAsString(rezervacije).getBytes().length);
		os.write(mapper.writeValueAsString(rezervacije));
	}

	private void postRezervacija(HttpExchange ex, Writer os, HashMap<String, String> params, String bodyStr)
			throws JsonParseException, JsonMappingException, IOException {
		Rezervacija reqRezervacija = mapper.readValue(bodyStr, Rezervacija.class);
		boolean duplicate = false;
		
		for(Rezervacija e : rezervacije.getRezervacijeWrapp()) {
			if(e.getVozilo().getRegBR()==reqRezervacija.getVozilo().getRegBR()) {
				duplicate = true;
				break;
			}
		}
		if(!duplicate) {
			rezervacije.add(reqRezervacija);
			
			MappingUtil.toJson(new File("Rezervacije.json"), rezervacije);
			ex.sendResponseHeaders(200, "Server: primljeni su podaci i dodati u listu rezervacija".getBytes().length);
			os.write("Server: primljeni su podaci i dodati u listu rezervacija");
		}
		else {
			System.out.println("Duplikat - rezervacija");  

			ex.sendResponseHeaders(200, "Server: duplikat - vec postoji rezervacija koja se poklapa sa vasom".getBytes().length);
			os.write("Server: duplikat - vec postoji rezervacija koja se poklapa sa vasom");
		}
		os.close();
		
}
}