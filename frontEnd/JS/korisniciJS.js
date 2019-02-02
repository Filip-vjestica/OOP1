function dodajSluzbenika(){
    if(!localStorage.getItem("trenutniKorisnik")){
        window.alert("Samo ulogovani sluzbenik moze da dodaje druge sluzbenike!");
        return;
    }
    let trenutniKorisnik = JSON.parse(window.localStorage.getItem("trenutniKorisnik"));
    if(trenutniKorisnik.uloga == "Iznajmljivac"){
        window.alert("Samo ulogovani sluzbenik moze da dodaje druge sluzbenike!");
        return;
    }

    var korisnIme = document.getElementById("korisnickoIme").value;
    var lozinka = document.getElementById("lozinka").value;
    var jmbg = document.getElementById("jmbg").value;
    var ime = document.getElementById("ime").value;
    var prezime = document.getElementById("prezime").value;
    var uloga = "Sluzbenik";

    if(!korisnIme || !lozinka || !jmbg || !ime || !prezime){
        window.alert("Sva polja za sluzbenika moraju biti popunjena!");
        return;
    }
    
    var noviSluzbenik = {
        "class" : "Sluzbenik",
        "korisnIme" : korisnIme,
        "lozinka" : lozinka,
        "jmbg" : jmbg,
        "ime" : ime,
        "prezime" : prezime,
        "uloga" : uloga
    };

    var korisnik = "Klijent: poslati podaci (ime="+ime+"; prezime="+prezime+"; korisnicko_ime="+korisnIme+"; sifra="+lozinka+")";
	document.getElementById("demo").innerHTML = korisnik;
    
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            document.getElementById("demo1").innerHTML = xhttp.responseText;
        }
    };
    xhttp.open("POST", "http://localhost:8080/korisnici", true);
    xhttp.setRequestHeader("Content-type", "text/plain");
    //console.log(xhttp.queryParams);
    xhttp.send(JSON.stringify(noviSluzbenik));
    if(!localStorage.getItem("trenutniKorisnik")){
        window.location.href = "prijava.html";
    }
    

}
function dodajIznajmljivaca(){
    var korisnIme = document.getElementById("korisnickoIme").value;
    var lozinka = document.getElementById("lozinka").value;
    var jmbg = document.getElementById("jmbg").value;
    var ime = document.getElementById("ime").value;
    var prezime = document.getElementById("prezime").value;
    var uloga = "Iznajmljivac";
    var brojTelefon = document.getElementById("brojTelefona").value;
    var drzavljanstvo = document.getElementById("drzavljanstvo").value;

    if(!korisnIme || !lozinka || !jmbg || !ime || !prezime || !brojTelefon || !drzavljanstvo){
        window.alert("Sva polja za iznajmljivaca moraju biti popunjena!");
        return;
    }
    var noviIznajmljivac = {
        "class" : "Iznajmljivac",
        "korisnIme" : korisnIme,
        "lozinka" : lozinka,
        "jmbg" : jmbg,
        "ime" : ime,
        "prezime" : prezime,
        "uloga" : uloga,
        "brojTelefon" : brojTelefon,
        "drzavljanstvo" : drzavljanstvo
    };

    var korisnik = "Klijent: poslati podaci (ime="+ime+"; prezime="+prezime+"; korisnicko_ime="+korisnIme+"; sifra="+lozinka+")";
    document.getElementById("demo").innerHTML = korisnik;
    
    
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            document.getElementById("demo1").innerHTML = xhttp.responseText;
        }
    };
    xhttp.open("POST", "http://localhost:8080/korisnici", true);
    xhttp.setRequestHeader("Content-type", "text/plain");
    //console.log(xhttp.queryParams);
    xhttp.send(JSON.stringify(noviIznajmljivac));
    if(!localStorage.getItem("trenutniKorisnik")){
        window.alert("Korisnik uspesno dodat!");
        window.location.href = "prijava.html";
    }

}

function generisanjeNavBar(){
    let bar = document.getElementById("navBar");
    if(!localStorage.getItem("trenutniKorisnik")){
        let korisnici = document.createElement("a");
        korisnici.href = "korisnici.html";
        korisnici.innerHTML = "Korisnici";


        bar.appendChild(korisnici);
        return;
    }
    let trenutniKorisnik = JSON.parse(localStorage.getItem("trenutniKorisnik"));
    
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