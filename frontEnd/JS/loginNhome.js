function login(){
    var korisnickoIme = document.getElementById("korisnickoIme").value;
    var korisnickaLozinka = document.getElementById("korisnickaLozinka").value;
    
    var korisnik = {
        "korisnickoIme" : korisnickoIme,
        "korisnickaLozinka" : korisnickaLozinka
    };

    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/korisnici", true);
    xhttp.onload = function(){
        if(this.status == 200){
            content = JSON.parse(this.responseText);
            content = content.korisnici;
            console.log(content);
            for(var i = 0; i < content.length; i++){
                if(korisnik.korisnickoIme == content[i].korisnIme && korisnik.korisnickaLozinka == content[i].lozinka){
                    window.alert("Prijava uspesna!");
                    window.location.href = "korisnici.html";
                    window.localStorage.setItem("trenutniKorisnik",JSON.stringify(content[i]));
                    return;
                }
            }
        }else{
            window.alert("Prijava neuspesna!");
            window.location.href = "prvaStrana.html";   
        }
    }
    xhttp.send();
}

function check(){
    if(localStorage.getItem("trenutniKorisnik")){
        window.alert("Ulogovani ste!");
        window.location.href = "korisnici.html";
    }
}