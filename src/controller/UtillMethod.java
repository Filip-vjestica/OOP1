package controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UtillMethod{
	// Metoda za parsuje datum u obliku stringa u LocalDate
	public static LocalDate parsovanjeDatuma(String zeljeniDatum) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(zeljeniDatum, dateFormat);
		return date;
	}
}
