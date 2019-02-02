package server;

import java.io.File;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.util.HashMap;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import model.Vozilo;
import utils.MappingUtil;
import utils.URIUtil;
import wrapps.VozilaWrapp;

public class ListaVozilaHandler implements HttpHandler {
	private VozilaWrapp vozila;
	private ObjectMapper mapper = new ObjectMapper();

	public ListaVozilaHandler(VozilaWrapp vozila) {
		super();
		this.vozila = vozila;
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
				getVozila(ex, os, params);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("GET zahtev obradjen!");
			break;
		case "POST":
			System.out.println("Obrada POST zahteva!");
			try {
				postVozila(ex, os, params, bodyStr);
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

	private void getVozila(HttpExchange ex, Writer os, HashMap<String, String> params)
			throws JsonProcessingException, IOException {
		ex.sendResponseHeaders(200, mapper.writeValueAsString(vozila).getBytes().length);
		os.write(mapper.writeValueAsString(vozila));
		
	}

	private void postVozila(HttpExchange ex, Writer os, HashMap<String, String> params, String bodyStr)
			throws JsonParseException, JsonMappingException, IOException {
		Vozilo reqVozilo = mapper.readValue(bodyStr, Vozilo.class);
		boolean duplicate = false;
		for(Vozilo v : vozila.getVozilaWrapp()) {
			if(v.getRegBR()==reqVozilo.getRegBR()) {
				duplicate = true;
				break;
			}
		}
		if (!duplicate) {
			vozila.add(reqVozilo);
			
			MappingUtil.toJson(new File("Vozila.json"), vozila);

			ex.sendResponseHeaders(200, "Server: primljeni su podaci i dodati u listu vozila".getBytes().length);
			os.write("Server: primljeni su podaci i dodati u listu vozila");
		} else {
			System.out.println("Duplikat - vozila");
			ex.sendResponseHeaders(200, "Server: duplikat - vec postoji vozilo sa istim ID".getBytes().length);
			os.write("Server: duplikat - vec postoji vozilo sa istim ID");
		}
		os.close();

	}
}
