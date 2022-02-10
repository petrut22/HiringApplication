
import java.util.ArrayList;

class User extends Consumer implements Observer{

    ArrayList<String> interested_companies;


    public User(Information info, ArrayList<Education> ed, ArrayList<Experience> ex, ArrayList<String> companies) {
        super(info, ed, ex);
        interested_companies = companies;
    }

    public Employee convert() {

        for(Company i : Application.getInstance().getCompanies())
            i.removeObserver(this);

        interested_companies.clear();
        Employee angajat = new Employee(getInformation(), getEducatie(), getExperinta());
        return angajat;
    }

    public Double getTotalScore() {
        Double n;
        double ani_exp = nrAniExp();
        n = ani_exp * 1.5 + meanGPA();
        return n;
    }

    public ArrayList<String> getCompanies() {
        return interested_companies;
    }
    public void update(String notificaton) {
        System.out.println(notificaton);
    }

}