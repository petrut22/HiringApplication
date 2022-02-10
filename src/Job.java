import java.util.ArrayList;
import java.util.List;

class Job {
    String numeJob;
    String numeCompany;
    boolean flag;
    Constraint absolvire;
    Constraint experienta;
    Constraint medie;
    ArrayList<User> interested_users;
    int nrCandidati;
    double salariu;

    public Job(String numeJob, String numeCompany, boolean flag, Constraint absolvire, Constraint experienta, Constraint medie, double salariu) {
        this.numeJob = numeJob;
        this.numeCompany = numeCompany;
        this.flag = flag;
        this.absolvire = absolvire;
        this.experienta = experienta;
        this.medie = medie;
        interested_users = new ArrayList<User>(100);
        this.nrCandidati = 0;
        this.salariu = salariu;
    }

    public void apply(User user) {
    }

    public boolean meetsRequirments(User user) {

        if(absolvire.getInferior() != null && user.getGraduationYear() < absolvire.getInferior())
            return false;

        if(absolvire.getSuperior() != null && user.getGraduationYear() > absolvire.getSuperior())
            return false;

        if(experienta.getInferior() != null && user.nrAniExp() < experienta.getInferior())
            return false;

        if(experienta.getSuperior() != null && user.nrAniExp() > experienta.getSuperior())
            return false;

        if(medie.getInferior() != null && user.meanGPA() < medie.getInferior())
            return false;

        if(medie.getSuperior() != null && user.meanGPA() > medie.getSuperior())
            return false;

        return true;
    }

    public double getSalariu() {
        return salariu;
    }

    public boolean isFlag() {
        return flag;
    }


    public String toString() {
        return "Job{\n" +
                "numeJob=" + numeJob + '\n' +
                "numeCompany=" + numeCompany + '\n' +
                "flag=" + flag + '\n' +
                "absolvire=" + absolvire + '\n' +
                "experienta=" + experienta + '\n' +
                "medie=" + medie + '\n' +
                "interested_users=" + interested_users + '\n' +
                "nrCandidati=" + nrCandidati + '\n' +
                "salariu=" + salariu + '\n' +
                "}\n";
    }
}