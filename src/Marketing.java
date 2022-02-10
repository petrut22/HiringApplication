import java.util.ArrayList;

public class Marketing extends Department implements Factory{

    public Marketing () {
        super();
    }

    public Marketing (ArrayList<Employee> angajati, ArrayList<Job> joburi) {
        super(angajati, joburi);
    }

    public double getTotalSalaryBudget() {
        double salariuTotal = 0;
        for(Employee i : angajati) {
                salariuTotal += ( i.getSalary()+ i.getSalary()*0.16 );
        }
        return salariuTotal;
    }


}
