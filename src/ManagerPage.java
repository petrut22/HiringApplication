import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerPage extends JFrame{
    private JPanel managerPanel;
    private JList cereriList;
    private JButton acceptButton;
    private JButton deniedButton;
    private JButton menuButton;
    private JTextPane cereriField;
    private DefaultListModel list;
    private DefaultListModel listinfo;

    public ManagerPage(String title) {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(managerPanel);
        pack();

        Manager m = Application.getInstance().companies.get(0).m;

        list = new DefaultListModel();
        listinfo = new DefaultListModel();
        int nr = 1;
        for(Request<Job, Consumer> i : m.lista) {
            list.addElement("Cererea " + nr++);
            listinfo.addElement(i.toString());
        }

        cereriList.setModel(list);




        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame frame = new MenuPage("Menu");
                frame.setSize(900, 900);
                frame.setVisible(true);
            }
        });
        cereriList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String s = "";
                if(cereriList.getSelectedIndex() > -1) {
                    s += listinfo.getElementAt(cereriList.getSelectedIndex());
                    cereriField.setText(s);

                }
            }
        });

    }
}
