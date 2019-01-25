import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
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
        addressBook.add(deleteContacts,constraints);
        
        
        constraints.gridy=3;
        addressBook.add(exitButton,constraints);
        
        
        addNewContact.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                AddContacts add = new AddContacts();
                add.setVisible(true);
            }
        });
        
        exitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
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
    private JButton deleteContacts = new JButton("Delete Contacts");
    private JButton exitButton = new JButton("Exit");
}
