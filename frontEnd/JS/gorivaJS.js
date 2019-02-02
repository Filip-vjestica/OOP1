function dodajGorivo(){
    var nazivGoriva = document.getElementById("nazivGoriva").value;
    var cenaGoriva = document.getElementById("cenaGoriva").value;

    if(!nazivGoriva || !cenaGoriva){
        window.alert("Sva polja moraju biti pravilno popunjena!");
        return;
    }

    var novoGorivo = {
        "class" : "Gorivo",
        "naziv" : nazivGoriva,
        "cena" : cenaGoriva
    };

    var gorivo = "Klijent: poslati podaci (Naziv="+nazivGoriva+"; Cena Goriva="+cenaGoriva+";)";
    document.getElementById("demo").innerHTML = gorivo;

    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("demo1").innerHTML = xhttp.responseText;  
        }
       };

    xhttp.open("POST", "http://localhost:8080/goriva", true);
    xhttp.setRequestHeader("Content-type", "text/plain");
    xhttp.send(JSON.stringify(novoGorivo));

}