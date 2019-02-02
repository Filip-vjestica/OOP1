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
import model.Servis;
import utils.MappingUtil;
import utils.URIUtil;
import wrapps.ServisiWrapp;

public class ListaServisaHandler implements HttpHandler {
	private ServisiWrapp servisi;
	private ObjectMapper mapper = new ObjectMapper();

	public ListaServisaHandler(ServisiWrapp servisi) {
		super();
		this.servisi = servisi;
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
				getServisa(ex, os, params);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("GET zahtev obradjen!");
			break;
		case "POST":
			System.out.println("Obrada POST zahteva!");
			try {
				postServisa(ex, os, params, bodyStr);
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

	private void getServisa(HttpExchange ex, Writer os, HashMap<String, String> params)
			throws JsonProcessingException, IOException {
		ex.sendResponseHeaders(200, mapper.writeValueAsString(servisi).getBytes().length);
		os.write(mapper.writeValueAsString(servisi));
	}

	private void postServisa(HttpExchange ex, Writer os, HashMap<String, String> params, String bodyStr)
			throws JsonParseException, JsonMappingException, IOException {
		Servis reqServis = mapper.readValue(bodyStr, Servis.class);
		servisi.add(reqServis);

		MappingUtil.toJson(new File("Servisi.json"), servisi);
		ex.sendResponseHeaders(200, "Server: primljeni su podaci i dodati u listu servisa".getBytes().length);
		os.write("Server: primljeni su podaci i dodati u listu servisa");

		os.close();

	}
}
