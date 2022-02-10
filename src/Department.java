import java.util.ArrayList;

abstract class Department {
    ArrayList<Employee> angajati;
    ArrayList<Job> joburi;

    public Department () {
        angajati = new ArrayList<Employee>(100);
        joburi = new ArrayList<Job>(100);
    }

    public Department (ArrayList<Employee> angajati, ArrayList<Job> joburi) {
        this.angajati = angajati;
        this.joburi = joburi;
    }


    public ArrayList<Job> getJobs() {
        ArrayList<Job> jobs = new ArrayList<Job>(100);

        for(Job i : joburi) {
            if (i.isFlag() == true)
                jobs.add(i);
        }

        return jobs;

    }

    public void add(Employee employee) {
        angajati.add(employee);
    }

    public void remove(Employee employee) {
        if(angajati.contains(employee))
            angajati.remove(employee);
    }

    public void add(Job job) {
        joburi.add(job);
    }

    public ArrayList<Employee> getEmployees() {
        return angajati;
    }

    //am folosit-o cu scopul de a furniza vectorul de joburi
    //ca sa fie verificate cele deschise

    //elimina toti angajatii din departament
    public void removeAll() {
        angajati.clear();
    }

    public String toStringJobs() {
        String s = "";

        s+= "\nJoburile disponibile: \n";
        for(Job i : joburi) {
            s += i.numeJob + "\n";
        }

        return s;
    }

    public String toStringEmployees() {
        String s = "";
        s+= "Angajatii departamentului: \n";
        for(Employee i : angajati) {
            s += i.nume() + "\n";
        }

        return s;
    }


}