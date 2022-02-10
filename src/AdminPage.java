import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame{
    private JPanel adminPanel;
    private JTextPane usersField;
    private JList listCompanies;
    private JTextPane employeeField;
    private JList listDepartments;
    private JTextPane jobsField;
    private JButton buttonBudget;
    private JTextPane budgetField;
    private JButton menuButton;
    private DefaultListModel list;
    private DefaultListModel list2;
    double budgetvalue;


    public AdminPage(String title) {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(adminPanel);
        pack();

        String s = "";
        for(User i : Application.getInstance().users) {
            s += i.nume() + "\n";
        }

        usersField.setText(s);

        list = new DefaultListModel();
        for(Company i : Application.getInstance().getCompanies()) {
            list.addElement(i);
        }
        //listCompanies.addListSelectionListener(this);
        listCompanies.setModel(list);

        list2 = new DefaultListModel();
        list2.addElement("IT");
        list2.addElement("Management");
        list2.addElement("Finance");
        list2.addElement("Marketing");

        listCompanies.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                listDepartments.setModel(list2);
            }
        });
        listDepartments.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if(listDepartments.isSelectionEmpty()) {
                    return;
                }
                Company c = (Company) list.getElementAt(listCompanies.getSelectedIndex());
                String d = (String) list2.getElementAt(listDepartments.getSelectedIndex());
                if(d.compareTo("IT") ==0) {
                  IT it = (IT) c.getDepartamente().get(0);
                  budgetvalue = it.getTotalSalaryBudget();
                  jobsField.setText(it.toStringJobs());
                  employeeField.setText(it.toStringEmployees());
                }

                if(d.compareTo("Management") == 0) {
                    Management management = (Management) c.getDepartamente().get(1);
                    budgetvalue = management.getTotalSalaryBudget();
                    jobsField.setText(management.toStringJobs());
                    employeeField.setText(management.toStringEmployees());
                }

                if(d.compareTo("Finance") == 0) {
                    Finance finance = (Finance) c.getDepartamente().get(2);
                    budgetvalue = finance.getTotalSalaryBudget();
                    jobsField.setText(finance.toStringJobs());
                    employeeField.setText(finance.toStringEmployees());
                }

                if(d.compareTo("Marketing") == 0) {
                    Marketing marketing = (Marketing) c.getDepartamente().get(3);
                    budgetvalue = marketing.getTotalSalaryBudget();
                    jobsField.setText(marketing.toStringJobs());
                    employeeField.setText(marketing.toStringEmployees());
                }
            }
        });
        buttonBudget.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(listDepartments.getSelectedIndex() > -1) {
                        budgetField.setText(String.valueOf(budgetvalue));

                }

            }
        });
        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame frame = new MenuPage("Menu");
                frame.setSize(900, 900);
                frame.setVisible(true);
            }
        });
    }



}
