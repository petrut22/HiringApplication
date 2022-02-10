import java.util.ArrayList;

class Management extends Department implements Factory{

    public Management () {
        super();
    }

    public Management (ArrayList<Employee> angajati, ArrayList<Job> joburi) {
        super(angajati, joburi);
    }

     public double getTotalSalaryBudget() {
         double salariuTotal = 0;
         for(Employee i : angajati) {
             if(i.salary > 5000)
                 salariuTotal += ( i.getSalary() + i.getSalary()*0.1 );
             else if(i.salary  < 3000) salariuTotal += i.getSalary();
             else salariuTotal += ( i.getSalary() + i.getSalary()*0.16 );
         }
         return salariuTotal;
     }


}
