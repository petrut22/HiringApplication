
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;


public class Test {
    //metoda pentru construirea informatiilor
    public static Information getInformatie(JSONObject element) {
        //se retine intregul nume al personei si se face delimitarea atat al numelui
        //cat si al prenumelui

        //obtinerea detaliilor necesare pentru construirea obiectului
        //se apeleaza metoda get pentru obtinerea campurilor necesare date dupa denumire
        String allName = (String) element.get("name");
        String words[] =  allName.split(" ", 2);

        String email = (String) element.get("email");

        String telefon = (String) element.get("phone");

        String nastere = (String) element.get("date_of_birth");

        String genre = (String) element.get("genre");

        ArrayList<String> languages = (ArrayList<String>) element.get("languages");

        ArrayList<String> languages_level = (ArrayList<String>) element.get("languages_level");

        Information info = new Information(words[1], words[0], email, telefon, nastere, genre, languages, languages_level);


        return info;
    }
    //metoda pentru construirea unui vector de joburi
    public static  ArrayList<Job> getJobs(JSONArray jobs, String numeCompany) {
        ArrayList<Job> jobS = new ArrayList<Job>(100);
        //parcurgem toate elementele din jobs si le convertim
        for(Object i : jobs) {
            JSONObject element = (JSONObject) i;
            //obtinem datele din job
            String numeJob = (String) element.get("name");

            String state = (String) element.get("state");

            boolean flag;
            //setam daca jobul este disponibil sau nu
            if(state.compareTo("yes") == 0)
                flag = true;
            else flag = false;
            //extragem
            JSONArray graduation = (JSONArray) element.get("graduation");

            //setam constrangerile jobului din punct de vedere al mediei
            Double inferior;
            if(graduation.get(0) != null)
                inferior = Double.parseDouble(graduation.get(0).toString());
            else inferior = null;

            Double superior;
            if(graduation.get(1) != null)
                superior = Double.parseDouble(graduation.get(0).toString());
            else superior = null;

            Constraint absolvire = new Constraint(inferior, superior);

            //setam constrangerile jobului din punct de vedere al anilor de experienta
            JSONArray experience = (JSONArray) element.get("experience");

            if(experience.get(0) != null)
                inferior = Double.parseDouble(experience.get(0).toString());
            else inferior = null;

            if(experience.get(1) != null)
                superior = Double.parseDouble(experience.get(0).toString());
            else superior = null;

            Constraint experienta = new Constraint(inferior, superior);

            //setam constrangerile jobului din punct de vedere al anilor de absolvire
            JSONArray average = (JSONArray) element.get("average");

            if(average.get(0) != null)
                inferior = Double.parseDouble(average.get(0).toString());
            else inferior = null;

            if(average.get(1) != null)
                superior = Double.parseDouble(average.get(0).toString());
            else superior = null;

            Constraint medie = new Constraint(inferior, superior);
            //obtinem salariul
            double salary = Double.parseDouble(element.get("salary").toString());
            //adaugam jobul
            Job j = new Job(numeJob, numeCompany, flag, absolvire, experienta, medie, salary);
            jobS.add(j);

        }

        return jobS;
    }
    //metoda pentru construirea vectorului cu Experience
    public static ArrayList<Experience> getExperienta(JSONArray experience) throws InvalidDatesException {
        ArrayList<Experience> experienta = new ArrayList<Experience>(100);
        //parcurgem elementele din JSON care contin detaliile legate de experienta
        for(Object j : experience) {
            JSONObject elementEd = (JSONObject) j;
            //se obtin campurile necesare
            String data_inceput = (String) elementEd.get("start_date");
            String data_final = (String) elementEd.get("end_date");
            String companie = (String) elementEd.get("company");
            String pozitie = (String) elementEd.get("position");
            //se apeleaza constructorul
            Experience ex = new Experience(data_inceput, data_final, pozitie, companie);
            //se adauga in vector
            experienta.add(ex);
        }


        return experienta;
    }
    //metoda pentru construirea vectorului cu Education
    public static ArrayList<Education> getEducatie(JSONArray education) throws InvalidDatesException {
        ArrayList<Education> educatie = new ArrayList<Education>(100);
        //parcurgem elementele din JSON care contin detaliile legate de educatie
        for(Object j : education) {
            JSONObject elementEd = (JSONObject) j;
            //se obtin campurile necesare
            String data_inceput = (String) elementEd.get("start_date");
            String data_final = (String) elementEd.get("end_date");
            String nume = (String) elementEd.get("name");
            Double medie = Double.parseDouble(elementEd.get("grade").toString());
            String nivel = (String) elementEd.get("level");
            //se apeleaza constructorul
            Education ed = new Education(data_inceput, data_final, nume, nivel, medie);
            //se adauga in vector
            educatie.add(ed);
        }


        return educatie;
    }
    //metoda pentru cautarea unui angajat din vectorul de angajati
    public static Employee getEmployee(ArrayList<Employee> employees, String nume) {
        for(Employee i : employees)
            if(i.nume().compareTo(nume) == 0)
                return i;

        return null;
    }
    //metoda pentru cautarea unui recruiter din vectorul de recrutieri
    public static Recruiter getRecruiter(ArrayList<Recruiter> recruiters, String nume) {
        for(Recruiter i : recruiters)
            if(i.nume().compareTo(nume) == 0)
                return i;

        return null;
    }
    //metoda pentru cautarea unui manager din vectorul de manageri
    public static Manager getManager(ArrayList<Manager> managers, String nume) {
        for(Manager i : managers)
            if(i.nume().compareTo(nume) == 0)
                return i;

        return null;
    }
    //metoda pentru cautarea unui user din vectorul de utlizatori
    public static User getUser(ArrayList<User> users, String nume) {
        for(User i : users)
            if(i.nume().compareTo(nume) == 0)
                return i;

        return null;
    }
    //retunarea vectorului de job-uri din JSONObject
    public static ArrayList<Job> obtainjobs(JSONObject companies, String numeCompany, String s) {
        JSONArray jobsJ = (JSONArray) companies.get(s);
        ArrayList<Job> jobs= getJobs(jobsJ, numeCompany);
        return jobs;
    }
    //construim compania cu ajutorul unor campuri din parcurgerea fisierului date ca parametrii
    //s1 va reprezenta sirul de unde se vor extrage toti angajatii
    //s2 va reprezenta sirul de unde se vor extrage toti care recruteaza
    //nameCompany va fi numele companiei
    public static Company builderCompany(JSONObject companies, ArrayList<Employee> employees1, ArrayList<Recruiter> recruiters1, ArrayList<Job> jobs, Company c, String s1, String s2, String nameCompany) {
        JSONObject employees = (JSONObject) companies.get(s1);

        //se adauga angajatii in departamentul IT
        String nume_angajat = (String) employees.get("IT");
        IT it = new IT();
        it.add(getEmployee(employees1, nume_angajat));
        //se adauga joburile in departament
        for(Job i : jobs)
            it.add(i);
        //se adauga departamentul IT in companie
        c.add(it);

        //se adauga angajatii in departamentul Management
        nume_angajat = (String) employees.get("Management");
        Management management = new Management();
        management.add(getEmployee(employees1, nume_angajat));
        //se adauga departamentul Management in companie
        c.add(management);

        //se adauga angajatii in departamentul Finance
        nume_angajat = (String) employees.get("Finance");
        Finance finance = new Finance();
        finance.add(getEmployee(employees1, nume_angajat));
        //se adauga departamentul Finance in companie
        c.add(finance);

        //se retin angajatii din departamentul Marketing in vector
        ArrayList<String> nume_angajati = (ArrayList<String>) employees.get("Marketing");

        //se adauga angajatii in departamentul Marketing
        Marketing marketing = new Marketing();
        for(String angajat : nume_angajati) {
            Employee e = getEmployee(employees1, angajat);
            e.company = nameCompany;
            marketing.add(e);
        }
        //se adauga departamentul Marketing in companie
        c.add(marketing);

        ArrayList<String> nume_recruiters = (ArrayList<String>) companies.get(s2);

        //in urmatoarea instructiune adaugam toti recrutarii in departamentul IT
        for(String recruiter : nume_recruiters) {
            Recruiter r = getRecruiter(recruiters1, recruiter);
            r.company = nameCompany;
            c.add(r);
            //adaugam la sectiunea de angajati care recruteaza
            for(Department i : c.getDepartamente()) {
                if(i.getClass().toString().compareTo("class IT") == 0)
                    i.add(getRecruiter(recruiters1, recruiter));
            }
        }

        return c;
    }


    public static void main(String args[]) throws Exception{
        //instantiem aplicatia
        Application app = Application.getInstance();
        //se retin informatiile din fisier in obj
        Object obj = new JSONParser().parse(new FileReader("consumers.json"));
        //se face un cast pentru citerea datelor
        JSONObject jo = (JSONObject) obj;

        //se retin persoanele angajate din instanta jo
        JSONArray employees = (JSONArray) jo.get("employees");
        //vector folosit pentru retinerea tuturor angajatiilor
        ArrayList<Employee> employees1 = new ArrayList<Employee>(100);

        //se parcurg toti angajatii si se retin datele acestora pentru procesare
        for(Object i : employees) {
            //se face un cast
            JSONObject element = (JSONObject) i;

            // retinem datele din campurile education si experience
            JSONArray education = (JSONArray) element.get("education");
            JSONArray experience = (JSONArray) element.get("experience");

            //obtinerea unui angajat se face cu ajutorul unor functii pentru construire
            Employee e = new Employee(getInformatie(element), getEducatie(education), getExperienta(experience));

            employees1.add(e);
            // se adauga salariul la angajat
            Double salary = Double.parseDouble(element.get("salary").toString());
            e.setSalary(salary);
            System.out.println(e.resume.toString());
        }

        JSONArray recruiters = (JSONArray) jo.get("recruiters");
        //vector folosit pentru retinerea tuturor care recruteaza
        ArrayList<Recruiter> recruiters1 = new ArrayList<Recruiter>(100);

        for(Object i : recruiters) {
            JSONObject element = (JSONObject) i;

            // retinem datele din campurile education si experience
            JSONArray education = (JSONArray) element.get("education");
            JSONArray experience = (JSONArray) element.get("experience");

            //obtinerea unui recruiter se face cu ajutorul unor functii pentru construire
            Recruiter r = new Recruiter(getInformatie(element), getEducatie(education), getExperienta(experience));
            recruiters1.add(r);

            // se adauga salariul la angajat
            Double salary = Double.parseDouble(element.get("salary").toString());
            r.setSalary(salary);
            System.out.println(r.resume.toString());
        }

        JSONArray users = (JSONArray) jo.get("users");

        for(Object i : users) {
            JSONObject element = (JSONObject) i;

            JSONArray education = (JSONArray) element.get("education");
            JSONArray experience = (JSONArray) element.get("experience");
            ArrayList<String> interested_companies = (ArrayList<String>) element.get("interested_companies");

            //obtinerea unui user se face cu ajutorul unor functii pentru construire
            User u = new User(getInformatie(element), getEducatie(education), getExperienta(experience), interested_companies);
            //se adauga in aplicatie userul obtinut
            app.add(u);
            System.out.println(u.resume.toString());
        }

        JSONArray managers = (JSONArray) jo.get("managers");
        ArrayList<Manager> managers1 = new ArrayList<Manager>(100);
        //acelasi rationament pentru manageri
        for(Object i : managers) {
            JSONObject element = (JSONObject) i;

            JSONArray education = (JSONArray) element.get("education");
            JSONArray experience = (JSONArray) element.get("experience");
            ArrayList<String> interested_companies = (ArrayList<String>) element.get("interested_companies");
            //se construieste managerul
            Manager m = new Manager(getInformatie(element), getEducatie(education), getExperienta(experience));
            managers1.add(m);
            //se adauga salariul
            Double salary = Double.parseDouble(element.get("salary").toString());
            m.setSalary(salary);
            System.out.println(m.resume.toString());
        }

        //construim companiile si joburile
        //data.json va retine detaliile din enunt pentru construirea
        //companiilor, departamentelor si adaugarea unor persoane
        obj = new JSONParser().parse(new FileReader("data.json"));
        jo = (JSONObject) obj;

        JSONObject companies = (JSONObject) jo.get("companies");
        ArrayList<String> companies_name = (ArrayList<String>) companies.get("companies_name");

        ArrayList<String> manageri = (ArrayList<String>) companies.get("managers");
        //instantiem companiile amazon si google, prin nume si manager
        Company amazon = new Company(companies_name.get(1), getManager(managers1, manageri.get(1)));
        Company google = new Company(companies_name.get(0), getManager(managers1, manageri.get(0)));

        String numeCompany;

        //joburile la Google
        numeCompany = (String) companies_name.get(0);
        ArrayList<Job> jobsGoogle = obtainjobs(companies, numeCompany, "jobs_google");

        //joburile la Amazon
        numeCompany = (String) companies_name.get(1);
        ArrayList<Job> jobsAmazon = obtainjobs(companies, numeCompany, "jobs_amazon");

        //aici construim companiile cu toate datele din fisiere
        amazon = builderCompany(companies, employees1, recruiters1, jobsAmazon, amazon, "employees_amazon", "recruiters_amazon","Amazon");
        google = builderCompany(companies, employees1, recruiters1, jobsGoogle, google, "employees_google", "recruiters_google","Google");

        //adaugam companiile in aplicatie
        app.add(amazon);
        app.add(google);

        //structura aceasta repetitiva este cu rolul de a adauga utilizatorii ca si observer
        for(User i : app.users) {
            for(String numec : i.interested_companies)
                //daca compania de care este interesat userul este chiar Google-ul
                if(numec.compareTo("Google") == 0) {
                    app.getCompany("Google").addObserver(i);
                    //se analizeaza userul cu ajutorul unui recruiter si se trimite la manager
                    Recruiter r = app.getCompany("Google").getRecruiter(i);
                    int scor = r.evaluate(jobsGoogle.get(0), i);
                }
                else {
                    app.getCompany("Amazon").addObserver(i);
                    //se analizeaza userul cu ajutorul unui recruiter si se trimite la manager
                    Recruiter r = app.getCompany("Amazon").getRecruiter(i);
                    int scor = r.evaluate(jobsAmazon.get(0), i);
                }

        }


        //pagina de menu care va duce la aparitia interfetelor
        JFrame frame = new MenuPage("Menu");
        frame.setSize(900, 900);
        frame.setVisible(true);

    }


}
