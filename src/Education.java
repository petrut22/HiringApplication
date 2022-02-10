import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Education implements Comparable<Education>{
    LocalDate data_inceput;
    LocalDate data_final;
    String nume;
    String nivel;
    Double medie;

    public Education(String dinceput, String dfinal, String nume, String nivel, Double medie) throws InvalidDatesException{
        //format folosit pentru data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        //se retine data de inceput si cea de final
        data_inceput = LocalDate.parse(dinceput, formatter);
        if(dfinal != null)
            data_final = LocalDate.parse(dfinal, formatter);
        else data_final = null;
        //daca data de inceput este mai mare decat cea de final se trateaza
        //ca o exceptie
        if(dfinal != null && data_final.isBefore(data_inceput))
            throw new InvalidDatesException();
        else {
            this.nume = nume;
            this.nivel = nivel;
            this.medie = medie;
        }
    }

    public double toMedie() {
        return medie;
    }

    public String toNume() {
        return nume;
    }

    public Integer toDataFinal() {
        Integer data = data_final.getYear();
        return data;
    }

    public int compareTo(Education o) {
        //compararea va functiona doar in cazul in care data de final este valida
        if(o.data_final != null && this.data_final != null) {
            LocalDate data_f1 = this.data_final;
            LocalDate data_f2 = o.data_final;

            if(data_f1.isEqual(data_f2)) {
                if(this.medie < o.medie)
                    return 1;
                else return -1;
            }

            else if(data_f1.isBefore(data_f2))
                return 1;
            else return -1;

        }
        else {
            LocalDate data_i1 = this.data_inceput;
            LocalDate data_i2 = o.data_inceput;

            if(data_i1.isAfter(data_i2))
                return 1;
            else return -1;
        }
    }

    public String toString() {
        String s = "";
        if(data_final == null)
            s += "data de inceput =" + data_inceput + '\n';
        else s = "data de inceput =" + data_inceput + '\n' +
                "data de final =" + data_final + '\n';

        return  "Numele institutiei = " + nume + '\n' +
                "nivel = " + nivel + '\n' + s;

    }
}