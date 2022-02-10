import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Experience implements Comparable<Experience>{
    LocalDate data_inceput;
    LocalDate data_final;
    String pozitie;
    String companie;

    public Experience(String dinceput, String dfinal, String pozitie, String companie) throws InvalidDatesException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        data_inceput = LocalDate.parse(dinceput, formatter);
        if(dfinal != null)
            data_final = LocalDate.parse(dfinal, formatter);
        else data_final = null;

        if(dfinal != null && data_final.isBefore(data_inceput))
            throw new InvalidDatesException();
        else {
            this.pozitie = pozitie;
            this.companie = companie;
        }
    }

    public int compareTo(Experience o) {
        if(o.data_final != null && this.data_final != null) {
            LocalDate data_f1 = this.data_final;
            LocalDate data_f2 = o.data_final;
            //compararea va functiona doar in cazul in care data de final este valida
            if(data_f1.isEqual(data_f2)) {
                String s1 = this.companie;
                String s2 = o.companie;

                if(s1.compareTo(s2) < 1)
                    return 1;
                else return -1;


            }

            else if(data_f1.isBefore(data_f2))
                return 1;
            else return -1;

        }
        return 0; //nu va fi atins niciodata

    }


    public String toString() {
        String s = "";
        if(data_final == null)
            s += "data de inceput =" + data_inceput + '\n';
        else s = "data de inceput =" + data_inceput + '\n' +
                "data de final =" + data_final + '\n';

        return  "Numele companiei = " + companie + '\n' +
                "pozitie = " + pozitie + '\n' +
                s;
    }
}