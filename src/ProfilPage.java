import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilPage extends JFrame{
    private JPanel userPanel;
    private JTextField UserName;
    private JLabel UserLabel;
    private JButton SearchButton;
    private JTextPane InformationField;
    private JTextPane ExperienceField;
    private JTextPane EducationField;
    private JButton BackMenu;


    public ProfilPage(String title) {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(userPanel);
        pack();
        SearchButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String nume = UserName.getText();
                Boolean gasit = false;
                for(User i : Application.getInstance().users) {
                    if(i.nume().compareTo(nume) == 0) {
                        gasit = true;

                        InformationField.setVisible(true);
                        InformationField.setText(i.resume.toStringInformation());

                        EducationField.setVisible(true);
                        EducationField.setText(i.resume.toStringEducation());

                        ExperienceField.setVisible(true);
                        ExperienceField.setText(i.resume.toStringExperience());

                        System.out.println(i.resume.toString());
                    }
                }
                if(gasit == false) {
                    InformationField.setVisible(true);
                    InformationField.setText("Nu exista acest utilizator");

                    EducationField.setVisible(true);
                    EducationField.setText("Nu exista acest utilizator");

                    ExperienceField.setVisible(true);
                    ExperienceField.setText("Nu exista acest utilizator");
                }
            }
        });
        BackMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame frame = new MenuPage("Menu");
                frame.setSize(900, 900);
                frame.setVisible(true);
            }
        });
    }

}
