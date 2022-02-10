import java.util.ArrayList;
import java.util.List;

class Application {
    //lista cu companiile si cu userii
    ArrayList<Company> companies;
    ArrayList<User> users;

    private static Application obj = null;


    private Application() {
        companies = new ArrayList<Company>(100);
        users = new ArrayList<User>(100);
    }
    //am folosit de patternul cerut pentru instantiere
    public static Application getInstance() {

        if(obj == null)
            obj = new Application();

        return obj;
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    //parcurgerea array-ul cu companii pentru obtinerea companiei dupa nume
    public Company getCompany(String name) {
        for(Company i : companies) {
            if(i.getNume().compareTo(name) == 0)
                return i;
        }
        //NU VA FI ATINS NICIODATA
        return null;

    }

    public void add(Company company) {
        companies.add(company);
    }

    public void add(User user) {
        users.add(user);
    }

    public boolean remove(Company company) {
        if(companies.contains(company)) {
            companies.remove(company);
            return true;
        }

        return false;
    }

    public boolean remove(User user) {
        if(users.contains(user)) {
            users.remove(user);
            return true;
        }

        return false;
    }
    //Determinarea job-urilor disponibile dintr-o companie (cele care nu au fost deja închise);
    public ArrayList<Job> getJobs(List<String> companies) {
        ArrayList<Job> jobs = new ArrayList<Job>(100);
        //parcurgem companiile
        for(String i : companies) {
            for(Company j : this.companies)
                //daca numele companiei este cel cautat, adaugam in vectorul de job-uri
                //joburile care sunt deschise
                if(j.getNume().compareTo(i) == 0)
                    jobs.addAll(j.getJobs());
        }

        return jobs;
    }

}
