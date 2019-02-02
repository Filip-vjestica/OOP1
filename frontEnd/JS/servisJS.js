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
function dodajServis(vozila){
    var voziloReg = document.getElementById("izaberiVozilo").value;
    var datum = document.getElementById("unosDatuma").value;
    var brojPredjenihKm = document.getElementById("unosPredjenihKm").value;

    if(!datum || !brojPredjenihKm){
        window.alert("Svi podaci moraju biti pravilno popunjeni!");
        return;
    }

    for(var i = 0; i < vozila.length; i++){
        if(vozila[i].regBR==voziloReg){
            var tempV = vozila[i];
        }
    }
    var izabranoVozilo = tempV;

    var noviServis = {
        "class" : "Servis",
        "vozilo" : izabranoVozilo,
        "datumServis" : datum,
        "brPredjenihKm" : brojPredjenihKm
    };
    
    var servis = "Klijent: poslati podaci (regBroj="+izabranoVozilo.regBR+"; Datum="+datum+";)";
    document.getElementById("demo").innerHTML = servis;

    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("demo1").innerHTML = xhttp.responseText; 
        }
       };

    xhttp.open("POST", "http://localhost:8080/servisi", true);
    xhttp.setRequestHeader("Content-type", "text/plain");
    xhttp.send(JSON.stringify(noviServis));
}

function proveriPodatke(){
    dodajServis(vozila);
}