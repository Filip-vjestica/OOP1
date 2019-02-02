var trenutniKorisnik = JSON.parse(localStorage.getItem("trenutniKorisnik"));
var goriva;

function izborVozila(){
    if(trenutniKorisnik.uloga=="Iznajmljivac"){
        window.alert("Iznajmljivacima nije dozvoljeno dodavanje vozila!");
        return;
    }
    if(document.getElementById("odabirVozila").value == "Bicikl" && document.getElementById("izborGoriva").value !== "Nema"){
        window.alert("Biciklovi ne mogu da koriste goriva!");
        return;
    }
    if(document.getElementById("odabirVozila").value == "Bicikl"){
        dodajBicikl(goriva);
    }
    if(document.getElementById("odabirVozila").value == "Putnicko Vozilo"){
        dodajPutnickoVozilo(goriva);
    }
    if(document.getElementById("odabirVozila").value == "Teretno Vozilo"){
        dodajTeretnoVozilo(goriva);
    }
}

function generisanjeNavBar(){
    if(!localStorage.getItem("trenutniKorisnik")){
        window.alert("Morate biti ulogovani!");
        window.location.href = "prijava.html";
    }
    let trenutniKorisnik = JSON.parse(localStorage.getItem("trenutniKorisnik"));
    let bar = document.getElementById("navBar");
    
    let korisnici = document.createElement("a");
    korisnici.href = "korisnici.html";
    korisnici.innerHTML = "Korisnici";

    let vozila = document.createElement("a");
    vozila.href = "vozila.html";
    vozila.innerHTML = "Vozila";

    let logout = document.createElement("a");
    logout.href = "prvaStrana.html";
    logout.innerHTML = "Log out";
    logout.onclick = function(){
        localStorage.removeItem("trenutniKorisnik");
    }

    bar.appendChild(korisnici);
    bar.appendChild(vozila);

    if(trenutniKorisnik.uloga=="Sluzbenik"){
        let goriva = document.createElement("a");
        goriva.href = "goriva.html";
        goriva.innerHTML = "Goriva";

        let servisi = document.createElement("a");
        servisi.href = "servis.html";
        servisi.innerHTML = "Servis";
        bar.appendChild(goriva);
        bar.appendChild(servisi);
    }else{
        let rezervacije = document.createElement("a");
        rezervacije.href = "rezervacije.html";
        rezervacije.innerHTML = "Rezervacije";
        bar.appendChild(rezervacije);
    }

    bar.appendChild(logout);
    
}

function generisanjeGoriva(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
           goriva=  JSON.parse(xhttp.responseText);
           goriva = goriva.gorivaWrapp;
           console.log(goriva);
           napraviIzborGoriva(goriva);
        }
    };
    xhttp.open("GET", "http://localhost:8080/goriva", true);
	xhttp.setRequestHeader("Content-type", "text/plain");
	xhttp.send();

}

function napraviIzborGoriva(lista){
    var select = document.getElementById("izborGoriva");
    for(var i =0; i < lista.length; i++){
        var opcija = document.createElement("option");
        opcija.value = lista[i].naziv;
        opcija.text = lista[i].naziv;
        select.appendChild(opcija);
    }
}

function dodajBicikl(lista){
    var vrstaVozila = "Bicikl";
    var regBR = document.getElementById("regBR").value;
    var brServisa = document.getElementById("brServisa").value;
    var potrosnja100 = 0;
    var predjeno = document.getElementById("predjeno").value;
    var preServis = document.getElementById("preServis").value;
    var cenaServis = document.getElementById("cenaServis").value;
    var cenaDan = document.getElementById("cenaDan").value;
    var brSedist = 1;
    var brVrata = 0;
    var vozObrisano = false;

    if(!regBR || !brServisa || !predjeno || !preServis || !cenaServis || !cenaDan){
        window.alert("Sva polja moraju biti popunjena pravilno!");
        window.location.reload();
        return;
    }

    for(var i = 0; i < lista.length; i++){
        if(goriva[i].naziv == document.getElementById("izborGoriva").value) {
            var gorivo1 = goriva[i];
        }
    }
    gorivoK = gorivo1;

    var noviBicikl = {
        "class" : "Bicikl",
        "vrstaVozila" : vrstaVozila,
        "regBR" : regBR,
        "gorivo" : gorivoK,
        "brServisa" : brServisa,
        "potrosnja100" : potrosnja100,
        "predjeno" : predjeno,
        "preServis" : preServis,
        "cenaServis" : cenaServis,
        "cenaDan" : cenaDan,
        "brSedist" : brSedist,
        "brVrata" : brVrata,
        "vozObrisano" : vozObrisano
    };

    var vozilo = "Klijent: poslati podaci (regBroj="+regBR+"; Vrsta Vozila="+vrstaVozila+";)";
    document.getElementById("demo").innerHTML = vozilo;
    

    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("demo1").innerHTML = xhttp.responseText; 
        }
       };
    xhttp.open("POST", "http://localhost:8080/vozila", true);
    xhttp.setRequestHeader("Content-type", "text/plain");
    xhttp.send(JSON.stringify(noviBicikl));
}

function dodajPutnickoVozilo(lista){
    var vrstaVozila = "Putnicko Vozilo";
    var regBR = document.getElementById("regBR").value;
    var brServisa = document.getElementById("brServisa").value;
    var potrosanja100 = document.getElementById("potrosnja100").value;
    var predjeno = document.getElementById("predjeno").value;
    var preServis = document.getElementById("preServis").value;
    var cenaServis = document.getElementById("cenaServis").value;
    var cenaDan = document.getElementById("cenaDan").value;
    var brSedist = document.getElementById("brSedist").value;
    var brVrata = document.getElementById("brVrata").value;
    var vozObrisano = false;

    if(!regBR || !brServisa || !predjeno || !preServis || !cenaServis || !cenaDan || !potrosanja100 || !brSedist || !brVrata){
        window.alert("Sva polja moraju biti popunjena pravilno!");
        window.location.reload();
        return;
    }

    for(var i = 0; i < lista.length; i++){
        if(goriva[i].naziv == document.getElementById("izborGoriva").value) {
            var gorivo1 = goriva[i];
        }
    }
    gorivoK = gorivo1;
    
    var novoPutnickoVozilo = {
        "class" : "PutnickoVozilo",
        "vrstaVozila" : vrstaVozila,
        "regBR" : regBR,
        "gorivo" : gorivoK,
        "brServisa" : brServisa,
        "potrosnja100" : potrosanja100,
        "predjeno" : predjeno,
        "preServis" : preServis,
        "cenaServis" : cenaServis,
        "cenaDan" : cenaDan,
        "brSedist" : brSedist,
        "brVrata" : brVrata,
        "vozObrisano" : vozObrisano
    };

    var vozilo = "Klijent: poslati podaci (regBroj="+regBR+"; Vrsta Vozila="+vrstaVozila+";)";
    document.getElementById("demo").innerHTML = vozilo;


    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("demo1").innerHTML = xhttp.responseText;  
        }
       };

    xhttp.open("POST", "http://localhost:8080/vozila", true);
    xhttp.setRequestHeader("Content-type", "text/plain");
    xhttp.send(JSON.stringify(novoPutnickoVozilo));
}

function dodajTeretnoVozilo(lista){
    var vrstaVozila = "Teretno Vozilo";
    var regBR = document.getElementById("regBR").value;
    var brServisa = document.getElementById("brServisa").value;
    var potrosanja100 = document.getElementById("potrosnja100").value;
    var predjeno = document.getElementById("predjeno").value;
    var preServis = document.getElementById("preServis").value;
    var cenaServis = document.getElementById("cenaServis").value;
    var cenaDan = document.getElementById("cenaDan").value;
    var brSedist = document.getElementById("brSedist").value;
    var brVrata = document.getElementById("brVrata").value;
    var maxMasauKg = document.getElementById("maxMasauKg").value;
    var visinauM = document.getElementById("visinauM").value;
    var vozObrisano = false;

    if(!regBR || !brServisa || !predjeno || !preServis || !cenaServis || !cenaDan || !potrosanja100 || !brSedist || !brVrata || !maxMasauKg || !visinauM){
        window.alert("Sva polja moraju biti popunjena pravilno!");
        window.location.reload();
        return;
    }

    for(var i = 0; i < lista.length; i++){
        if(goriva[i].naziv == document.getElementById("izborGoriva").value) {
            var gorivo1 = goriva[i];
        }
    }
    gorivoK = gorivo1;

    var novoTeretnoVozilo = {
        "class" : "TeretnoVozilo",
        "vrstaVozila" : vrstaVozila,
        "regBR" : regBR,
        "gorivo" : gorivoK,
        "brServisa" : brServisa,
        "potrosnja100" : potrosanja100,
        "predjeno" : predjeno,
        "preServis" : preServis,
        "cenaServis" : cenaServis,
        "cenaDan" : cenaDan,
        "brSedist" : brSedist,
        "brVrata" : brVrata,
        "vozObrisano" : vozObrisano,
        "maxMasauKg" : maxMasauKg,
        "visinauM" : visinauM
    };

    var vozilo = "Klijent: poslati podaci (regBroj="+regBR+"; Vrsta Vozila="+vrstaVozila+";)";
    document.getElementById("demo").innerHTML = vozilo;

    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("demo1").innerHTML = xhttp.responseText;  
        }
       };

    xhttp.open("POST", "http://localhost:8080/vozila", true);
    xhttp.setRequestHeader("Content-type", "text/plain");
    xhttp.send(JSON.stringify(novoTeretnoVozilo));
}




