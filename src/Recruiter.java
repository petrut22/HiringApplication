import java.util.ArrayList;

class Recruiter extends Employee{
    double rating;

    //probleme cu information
    public Recruiter(Information info, ArrayList<Education> ed, ArrayList<Experience> ex) {
        super(info, ed, ex);
        this.rating = 5;
    }


    public int evaluate(Job job, User user) {
        double scor;
        rating += 0.1;
        scor = rating * user.getTotalScore();
        Double scorClass = scor;
        //se creaza un request pentru angajare si se va trimite managerului
        Request<Job, Consumer> request = new Request(job, user, this, scorClass);
        Application.getInstance().getCompany(company).getM().addRequest(request);


        return (int)scor;

    }

}