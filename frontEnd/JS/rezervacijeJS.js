var vozila;
var trenutniKorisnik = JSON.parse(localStorage.getItem("trenutniKorisnik"));

function dodavanjeVozila(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if(this.readyState == 4 && this.status == 200){
            vozila = JSON.parse(xhttp.responseText);
            vozila = vozila.vozilaWrapp;
            console.log(vozila);
            popuniSelect(vozila);

        }
    };
    xhttp.open("GET", "http://localhost:8080/vozila", true);
	xhttp.setRequestHeader("Content-type", "text/plain");
    xhttp.send();

}

function popuniSelect(lista){
    var select = document.getElementById("izaberiVozilo");
    for(var i = 0; i < lista.length; i++){
        var opcija = document.createElement("option");
        opcija.value = lista[i].regBR;
        opcija.text = `${lista[i].vrstaVozila} Registarski broj: ${lista[i].regBR}`;
        select.appendChild(opcija);
    }
}

function danaIzmedju(datum1,datum2){
    var dan = 1000*60*60*24;

    var datum1ms = datum1.getTime();
    var datum2ms = datum2.getTime();

    var razlika = datum2ms-datum1ms;
    
    return Math.round(razlika/dan);
}

function dodajRezervaciju(vozila){
    var voziloReg = document.getElementById("izaberiVozilo").value;
    var pocetniDatum  = document.getElementById("unosPocetnogDatuma").value;
    var krajnjiDatum = document.getElementById("unosKrajnjegDatuma").value; 

    var pocetni1  = new Date(pocetniDatum);
    var krajnji1  = new Date(krajnjiDatum);

    var pDatumJ = pocetni1.toLocaleDateString("en-GB");
    var kDatumJ = krajnji1.toLocaleDateString("en-GB");

    pocetni1.setHours(0,0,0,0);
    krajnji1.setHours(0,0,0,0);



    if(!voziloReg || !pocetniDatum || !krajnjiDatum){
        window.alert("Svi podaci moraju biti pravilno popunjeni!");
        return;
    }

    if(krajnji1 < pocetni1){
        window.alert("Pocetni datum mora biti pre krajnjeg!");
        return;
    }

    var razlikaDana = danaIzmedju(pocetni1,krajnji1);

    for(var i = 0; i < vozila.length; i++){
        if(vozila[i].regBR==voziloReg){
            var tempV = vozila[i];
        }
    }
    var izabranoVozilo = tempV;

    cenaR = razlikaDana*izabranoVozilo.cenaDan;

    var novaRezervacija = {
        "class" : "Rezervacija",
        "vozilo" : izabranoVozilo,
        "iznajmljivac" : trenutniKorisnik,
        "cenaRezerv" : cenaR,
        "rezervacijaObrisana" : false,
        "datumPocetka" : pDatumJ,
        "datumKraja" : kDatumJ
    };

    var rezervacija = "Klijent: poslati podaci (regBroj="+izabranoVozilo.regBR+"; Datum pocetni="+pocetniDatum+"; Datum krajnji="+krajnjiDatum+"; Cena rezervacije="+cenaR+";)";

    document.getElementById("demo").innerHTML = rezervacija;

    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("demo1").innerHTML = xhttp.responseText; 
        }
       };

    xhttp.open("POST", "http://localhost:8080/rezervacije", true);
    xhttp.setRequestHeader("Content-type", "text/plain");
    xhttp.send(JSON.stringify(novaRezervacija));
}

function proveriPodatke(){
    dodajRezervaciju(vozila);
}