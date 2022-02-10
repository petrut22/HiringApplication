import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

abstract class Consumer {
    // lista cu prieteni
    ArrayList<Consumer> lista;
    //resume consumerului
    Resume resume;

    public Consumer(Information info, ArrayList<Education> ed, ArrayList<Experience> ex) {
        resume = new Resume.ResumeBuilder(info)
                .educatie(ed)
                .experienta(ex)
                .build();
        lista = new ArrayList<Consumer>(100);
    }

    public Consumer() {

    }

    public String nume() {
        return resume.info.getAllNume();
    }

    //adaugarea unor studii
    public void add(Education education) {
        resume.addEducation(education);
    }
    //adaugarea unei experiente profesionale
    public void add(Experience experience) {
        resume.addExperience(experience);
    }
    //adaugarea unui nou cunoscut
    public void add(Consumer consumer) {
        lista.add(consumer);
    }

    // aceasta functie realizazeaza reteaua de prietenie
    public int getDegreeInFriendship(Consumer consumer) {
        // se instantiaza coada pentru BFS
        Queue<Consumer> q = new LinkedList<Consumer>();

        //ne folosim de un HashMap pentru a retine distanta dintre userul curent
        //si ceilalti din retea
        HashMap<Consumer, Integer> map = new HashMap<Consumer, Integer>(100);
        map.put(this, 0);

        q.add(this);
        //cat timp coada nu este vida
        while(!q.isEmpty()) {
            //scoatem primul element
            Consumer current = q.remove();
            //conditie care determina daca s-a gasit userul dat ca parametru
            if(current == consumer)
                return map.get(consumer);
            //se parcurg toti useri din lista de prieteni ai elementul scos recent din coada
            for(Consumer i : lista)
                //daca coada nu il contine
                if(!q.contains(i)) {
                    int key = map.get(current) + 1;
                    //actulizam map-ul
                    map.put(i, key);
                    //adaugam in coada
                    q.add(i);
                }
        }
        // nu va fi atins niciodata
        return 0;
    }

    public void remove(Consumer consumer) {
        if(lista.contains(consumer)) {
            lista.remove(consumer);
        }
    }


    public Integer getGraduationYear(){
        return resume.graduationYear();
    }

    public Information getInformation() {
        return resume.toInformation();
    }

    public ArrayList<Education> getEducatie() {
        return resume.educatie;
    }

    public ArrayList<Experience> getExperinta() {
        return resume.experienta;
    }



    public Double meanGPA() {
        return resume.toMedieTotal();
    }

    public long nrAniExp() {return resume.nrAniExp();}



    static class Resume {
        Information info;
        ArrayList <Education> educatie;
        ArrayList <Experience> experienta;

        public Resume(ResumeBuilder builder) {
            this.info = builder.info;
            this.experienta = builder.experienta;
            Collections.sort(experienta);
            this.educatie = builder.educatie;
            Collections.sort(educatie);
        }

        public void addEducation(Education ed) {
            educatie.add(ed);
        }

        public void addExperience(Experience ex) {
            experienta.add(ex);
        }

        public Information toInformation() {

            return info;
        }

        public double toMedieTotal() {
            double medie_finala = 0;
            //parcurgem lista din educatie
            for(Education i : educatie) {
                //si adaugam mediile obtinute
                medie_finala+= i.toMedie();
            }
            //calculam media final pe nr de studii
            medie_finala = medie_finala/ educatie.size();
            return medie_finala;
        }

        public Integer graduationYear() {
            Integer an_final = null;
            for(Education i : educatie) {
                String nume = i.toNume();
                //cautam campul Education care se refera la colegiu si
                //returnam anul finalizarii
                if(nume.compareTo("college") == 0) {
                    an_final = i.toDataFinal();
                    break;
                }
            }

            return an_final;
        }

        public long nrAniExp() {
            //ani_exp va repezenta nr aniilor de experienta
            long ani_exp = 0;
            //period va contine perioada de lucru in luni
            long perioad = 0;

            for(Experience i : experienta) {
                //retinem data de inceput
                LocalDate start_data  = i.data_inceput;
                //daca persoana si a finalizat studiile
                if(i.data_final != null) {
                    //calculam perioada in intervale lunare
                    perioad += start_data.until(i.data_final, ChronoUnit.MONTHS);
                    //restul va contine nr de luni ramase
                    long rest = perioad % 12;
                    //obtinem nr de ani
                    perioad = perioad / 12;
                    //caz ipotetic daca utilizatorul are 3 ani si 6 luni de experienta
                    //atunci se va considera ca va 4 ani de experienta
                    if(rest > 0) perioad++;
                    ani_exp += perioad;
                }
            }

            return ani_exp;
        }
        //afisam datele personale
        public String toString() {
            return toStringInformation() + "\n" + toStringEducation() + "\n" + toStringExperience() +"\n\n";
        }
        //afiseaza doar datele din information
        public String toStringInformation() {
            return  "Informatii:\n" + info + '\n';
        }
        //afiseaza doar datele din Experience
        public String toStringExperience() {
            String s = "";
            s += "Experienta: " + '\n';
            for(Experience i : experienta)
                s += i.toString() + '\n';

            return s;
        }
        //returneza informatiile din Education
        public String toStringEducation() {
            String s = "";
            s += "Educatie: " + '\n';
            for(Education i : educatie)
                s += i.toString() + '\n';

            return s;
        }
        //patternul folosit pentru Resume
        static class ResumeBuilder {
            Information info;
            ArrayList<Education> educatie;
            ArrayList <Experience> experienta;

            public ResumeBuilder(Information info) {
                this.info = info;
                experienta = new ArrayList<Experience>(100);
                educatie = new ArrayList<Education>(100);
            }

            public ResumeBuilder educatie(ArrayList<Education> ed) {
                educatie.addAll(ed);
                return this;
            }

            public ResumeBuilder experienta(ArrayList<Experience> ex) {
                experienta.addAll(ex);
                return this;
            }


            public Resume build() {
                return new Resume(this);
            }
        }

    }


}
