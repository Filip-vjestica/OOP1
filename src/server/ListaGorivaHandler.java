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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import model.Gorivo;
import utils.MappingUtil;
import utils.URIUtil;
import wrapps.GorivaWrapp;

public class ListaGorivaHandler implements HttpHandler {
	private GorivaWrapp goriva;
	private ObjectMapper mapper = new ObjectMapper();

	public ListaGorivaHandler(GorivaWrapp goriva) {
		super();
		this.goriva = goriva;
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
				getGoriva(ex, os, params);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("GET zahtev obradjen!");
			break;
		case "POST":
			System.out.println("Obrada POST zahteva!");
			try {
				postGoriva(ex, os, params, bodyStr);
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

	private void getGoriva(HttpExchange ex, Writer os, HashMap<String, String> params)
			throws JsonProcessingException, IOException {
		ex.sendResponseHeaders(200, mapper.writeValueAsString(goriva).getBytes().length);
		os.write(mapper.writeValueAsString(goriva)); 
	}

	private void postGoriva(HttpExchange ex, Writer os, HashMap<String, String> params, String bodyStr)
			throws JsonParseException, JsonMappingException, IOException {
		Gorivo reqGorivo = mapper.readValue(bodyStr, Gorivo.class);

		boolean duplicate = false;
		for (Gorivo g : goriva.getGorivaWrapp()) {
			if (g.getNaziv().equals(reqGorivo.getNaziv())) {
				duplicate = true;
				break;
			}
		}
		if (!duplicate) {
			goriva.add(reqGorivo);
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			MappingUtil.toJson(new File("Goriva.json"), goriva);

			ex.sendResponseHeaders(200, "Server: primljeni su podaci i dodati u listu goriva".getBytes().length);
			os.write("Server: primljeni su podaci i dodati u listu goriva");
		} else {
			System.out.println("Duplikat - goriva");
			ex.sendResponseHeaders(200, "Server: duplikat - vec postoji gorivo sa istim ID".getBytes().length);
			os.write("Server: duplikat - vec postoji gorivo sa istim ID");
		}
		os.close();

	}

}
