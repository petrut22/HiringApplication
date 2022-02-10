import java.util.ArrayList;

class Company implements Subject{
    String nume;
    Manager m;
    //listele cu departamentele, cei care recruteaza si cei care sunt interesati
    // de companie
    ArrayList<Observer> observers;
    ArrayList<Department> departamente;
    ArrayList<Recruiter> recruiters;

    public Company(String nume, Manager m) {
        this.nume = nume;
        this.m = m;
        observers = new ArrayList<Observer>(100);
        departamente = new ArrayList<Department>(100);
        recruiters = new ArrayList<Recruiter>(100);
    }


    public ArrayList<Department> getDepartamente() {
        return departamente;
    }
    //functie care adauga un departament
    public void add(Department department) {
        if(!departamente.contains(department))
            departamente.add(department);
        //aceasta conditie ne ajuta pentru anuntarea celor interesati de aplicare
        if(department.joburi != null)
            notifyAllObservers("S - a adaugat joburi ");
    }


    public void add(Recruiter recruiter) {
        if(!recruiters.contains(recruiter))
            recruiters.add(recruiter);
    }

    public void add(Employee employee, Department department) {
        if(departamente.contains(department)) {
            //index-ul va detine pozitia departamentului
            int index = departamente.indexOf(department);
            //se apeleaza metodele necesare de adugare
            departamente.get(index).add(employee);
        }
    }

    public String getNume() {
        return nume;
    }

    public void remove(Employee employee) {
        // in cazul in care angajatul este si recrutar atunci il vom elimina
        if(recruiters.contains(employee))
            recruiters.remove(employee);

        //cautam in ce departament se afla si il eliminam
        for(Department i : departamente) {
            i.remove(employee);
        }
        //in cazul in care a fost cineva eliminat din post se va trimite o notificare
        //celor care sunt interesati de un job
        notifyAllObservers("S - a eliberat un post in compania " + nume);

    }
    //metoda pentru returnaera managerului
    public Manager getM() {
        return m;
    }

    //modificarea managerului
    public void setM(Manager m) {
        this.m = m;
    }

    public void remove(Department department) {
        if(departamente.contains(department)) {
            int index = departamente.indexOf(department);
            departamente.get(index).removeAll();
            departamente.remove(department);
        }
    }

    public void remove(Recruiter recruiter) {
        if(recruiters.contains(recruiter))
            recruiters.remove(recruiter);
    }


    public void move(Department source, Department destination) {
        if(departamente.contains(source)) {
            int index = departamente.indexOf(source);
            ArrayList<Employee> angajati = departamente.get(index).getEmployees();

            for(Employee i : angajati)
                add(i, destination);
        }
            departamente.remove(source);
    }

    public void move(Employee employee, Department newDepartment) {
        if(departamente.contains(newDepartment)) {
            int index = departamente.indexOf(newDepartment);
            departamente.get(index).add(employee);
        }
    }

    public boolean contains(Department department) {
        if(departamente.contains(department))
            return true;
        else return false;
    }

    public boolean contains(Employee employee) {
        for(Department i : departamente)
            if(i.angajati.contains(employee))
                return true;
            return false;

    }

    public boolean contains(Recruiter recruiter) {
        if(recruiters.contains(recruiter))
            return true;
        else return false;
    }

    //implementeaza metoda
    public Recruiter getRecruiter(User user) {

        return recruiters.get(0);
    }

    public ArrayList<Job> getJobs() {
        ArrayList<Job> jobs = new ArrayList<Job>(100);
        for(Department i : departamente) {
            jobs.addAll(i.getJobs());
        }
        //notificare trimisa celor care sunt interesati de job
        if(jobs.size() != 0 )
            notifyAllObservers("S - a eliberat un post in compania " + nume);
        return jobs;
    }



    public void addObserver(User user) {
        if(user.getCompanies().contains(nume))
            observers.add(user);
    }


    public void removeObserver(User c) {
       observers.remove(c);
    }


    public void notifyAllObservers(String notificare) {
       for(Observer i : observers) {
            i.update(notificare);
        }
    }

    public String toString() {
        return nume;
    }
}
