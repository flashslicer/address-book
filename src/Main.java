import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Main extends JFrame {
    JFrame jf = new JFrame();
    public Main()
    {
        JPanel addressBook = new JPanel(new GridBagLayout());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor=GridBagConstraints.WEST;
        constraints.insets=new Insets(5,5,5,5);
        
        constraints.gridx=0;
        constraints.gridy=0;
        addressBook.add(addNewContact,constraints);
      
        constraints.gridy=1;
        addressBook.add(searchContacts,constraints);
        
        
        constraints.gridy=2;
        addressBook.add(updateContacts,constraints);
        
        
        constraints.gridy=3;
        addressBook.add(showContacts,constraints);
        
        constraints.gridy=4;
        addressBook.add(exitButton,constraints);
        
        
        updateContacts.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                    DeleteContacts dc = new DeleteContacts();
                    dc.setVisible(true);
            }
        });
        addNewContact.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                DBConnection db = new DBConnection();
                db.createTable();
                AddContacts add = new AddContacts();
                add.setVisible(true);
            }
        });
        
        showContacts.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ShowTableContacts sh = new ShowTableContacts();
                sh.setVisible(true);
            }
        });
        
        exitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:derby:;shutdown=true");
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        });
        
        searchContacts.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                SearchContacts sc = new SearchContacts();
                sc.setVisible(true);
            }
        });
        
         addressBook.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Adress Book Menu")); 
       add(addressBook);
        
        pack();
        setLocationRelativeTo(null);
    }
    
        public static void main(String args[])
    {
           try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex)
        {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        }); 
    }
    
    
    private JButton addNewContact = new JButton("New Contacts");
    private JButton searchContacts = new JButton("Search Contacts");
    private JButton updateContacts = new JButton("Update or Delete Contacts");
    private JButton exitButton = new JButton("Exit");
    private JButton showContacts = new JButton("Show all contacts");
}
