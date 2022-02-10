import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPage extends JFrame{
    private JButton ProfilButton;
    private JButton AdminButton;
    private JButton ManagerButton;
    private JPanel menuPanel;

    private DefaultListModel list;

    public MenuPage(String title) {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(menuPanel);
        pack();
        ProfilButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame frame1 = new ProfilPage("Informatii utilizator");
                frame1.setVisible(true);
                frame1.setSize(900, 900);
            }
        });
        AdminButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame frame2 = new AdminPage("Informatii admin");
                frame2.setSize(900, 900);
                frame2.setVisible(true);
            }
        });
        ManagerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame frame3 = new ManagerPage("Informatii admin");
                frame3.setSize(900, 900);
                frame3.setVisible(true);
            }
        });
    }
}
