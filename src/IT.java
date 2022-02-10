import java.util.ArrayList;

class IT extends Department implements Factory{

    public IT () {
        super();
    }

    public IT (ArrayList<Employee> angajati, ArrayList<Job> joburi) {
        super(angajati, joburi);
    }

    public double getTotalSalaryBudget() {
        double salariuTotal = 0;
        for(Employee i : angajati) {
            salariuTotal += i.getSalary();
        }
        return salariuTotal;
    }


}
