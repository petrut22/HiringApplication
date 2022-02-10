import java.util.ArrayList;

class Employee extends Consumer {
    Double salary;
    String company;

    public Employee(Information info, ArrayList<Education> ed, ArrayList<Experience> ex) {

        super(info, ed, ex);

    }

    public Employee() {
        super();
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }


}