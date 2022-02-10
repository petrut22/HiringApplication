import java.util.ArrayList;

class Finance extends Department implements Factory{

    public Finance () {
        super();
    }

    public Finance (ArrayList<Employee> angajati, ArrayList<Job> joburi) {
        super(angajati, joburi);
    }
     public double getTotalSalaryBudget() {
         double salariuTotal = 0;
         for(Employee i : angajati) {
             if(i.nrAniExp() >= 1)
                 salariuTotal += ( i.getSalary()+ i.getSalary()*0.1 );
             else salariuTotal += ( i.getSalary() + i.getSalary()*0.16 );
         }
         return salariuTotal;
     }

}
