import java.util.ArrayList;
import java.util.Arrays;

class Information {
    private String nume;
    private String prenume;
    private String email;
    private String telefon;
    private String nastere;
    private String genre;
    private ArrayList<String> languages;
    private ArrayList<String> level_languages;
    //constructor apelat pentru construirea obiectului
    public Information(String nume, String prenume, String email, String telefon, String nastere, String genre, ArrayList<String> languages, ArrayList<String> level_languages) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.telefon = telefon;
        this.nastere = nastere;
        this.genre = genre;
        this.languages = languages;
        this.level_languages = level_languages;
    }
    //metoda pentru returnarea numelui
    public String getNume() {
        return nume;
    }
    //metoda pentru returnarea prenumelui
    public String getPrenume() {
        return prenume;
    }
    //metoda pentru returnarea intregului nume
    public String getAllNume() {
        return prenume + " " + nume;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getNastere() {
        return nastere;
    }

    public String getGenre() {
        return genre;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public ArrayList<String> getLevel_languages() {
        return level_languages;
    }

    public String toString() {
        String s = "";
        for(int i = 0; i < languages.size(); i++) {
            s += languages.get(i) + " : " + level_languages.get(i) + " \n";
        }
        return  "nume = " + nume + '\n' +
                "prenume = " + prenume + '\n' +
                "email = " + email + '\n' +
                "telefon = " + telefon + '\n' +
                "nastere = " + nastere + '\n' +
                "genre = " + genre + '\n' + s;

    }
}