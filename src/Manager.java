import java.util.ArrayList;

class Manager extends Employee {
    //nu sunt sigur cu Employee
    ArrayList<Request<Job, Consumer>> lista;

    public Manager(Information info, ArrayList<Education> ed, ArrayList<Experience> ex) {
        super(info, ed, ex);
        lista = new ArrayList<Request<Job, Consumer>>(100);
    }

    public void addRequest(Request<Job, Consumer> r) {
        lista.add(r);
    }

    public void process(Job job) {
        for(Request<Job, Consumer> i: lista) {

        }
    }

}