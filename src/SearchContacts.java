import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
public class SearchContacts extends JDialog
{
    public SearchContacts()
    {
        JPanel searchContacts = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor=GridBagConstraints.WEST;
        constraints.insets=new Insets(20,20,20,20);
        
        
        constraints.gridx=0;
        constraints.gridy=0;
        searchContacts.add(searchID,constraints);
        
        constraints.gridx=0;
        constraints.gridy=1;
        searchContacts.add(firstname,constraints);
        
        
        constraints.gridx=0;
        constraints.gridy=2;
        searchContacts.add(middlename,constraints);
        
        constraints.gridx=0;
        constraints.gridy=3;
        searchContacts.add(lastname,constraints);
        
        constraints.gridx=0;
        constraints.gridy=4;
        searchContacts.add(address,constraints);
        
        constraints.gridx=0;
        constraints.gridy=5;
        searchContacts.add(phonenumber,constraints);
        
        constraints.gridx=0;
        constraints.gridy=6;
        searchContacts.add(gender,constraints);
        
        constraints.gridx=1;
        constraints.gridy=0;
        searchContacts.add(idTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=1;
        searchContacts.add(fName,constraints);
        
        constraints.gridx=1;
        constraints.gridy=2;
        searchContacts.add(mName,constraints);
        
        constraints.gridx=1;
        constraints.gridy=3;
        searchContacts.add(lName,constraints);
        
        constraints.gridx=1;
        constraints.gridy=4;
        searchContacts.add(add,constraints);
        
        constraints.gridx=1;
        constraints.gridy=5;
        searchContacts.add(pNumber,constraints);
        
        constraints.gridx=1;
        constraints.gridy=6;
        searchContacts.add(gd,constraints);
        
        constraints.gridx=2;
        constraints.gridy=0;
        searchContacts.add(search,constraints);
        
        constraints.gridx=2;
        constraints.gridy=1;
        searchContacts.add(exit,constraints);
        
        search.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e)
           {
               try {
                   DBConnection db = new DBConnection();
                   PreparedStatement ps = db.conn.prepareStatement("select * from contacts where id=?");
                   ps.setString(1,idTextField.getText());
                   ResultSet rs = ps.executeQuery();
                   
                   if(rs.next())
                   {
                       fName.setText(rs.getString("firstname"));
                       mName.setText(rs.getString("middlename"));
                       lName.setText(rs.getString("lastname"));
                       add.setText(rs.getString("address"));
                       gd.setText(rs.getString("gender"));
                       pNumber.setText(rs.getString("phonenumber"));
                   }else
                   {
                       JOptionPane.showMessageDialog(null, "It does not exist");
                   }
                   ps.close();
                   db.conn.close();
               } catch (SQLException ex) {
                   Logger.getLogger(SearchContacts.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        });
        exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Component comp = SwingUtilities.getRoot(searchContacts);   
                
                ((Window) comp).dispose(); 
            }
        });
       searchContacts.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Address Contacts")); 
       add(searchContacts);
        
               
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
            new SearchContacts().setVisible(true);
        }); 
    }
    
    private JLabel firstname = new JLabel("First Name: ");
    private JLabel fName = new JLabel("");
    private JLabel middlename = new JLabel("Middle Name: ");
    private JLabel mName = new JLabel("");
    private JLabel lastname = new JLabel("Last Name: ");
    private JLabel lName = new JLabel("");
    private JLabel address = new JLabel("Address: ");
    private JLabel add = new JLabel("");
    private JLabel phonenumber = new JLabel("Phone Number: ");
    private JLabel pNumber = new JLabel("");
    private JLabel gender = new JLabel("Gender");
    private JLabel gd = new JLabel("");
    private JLabel searchID = new JLabel("Search ID");
    private JTextField idTextField = new JTextField(20);
    private JButton search = new JButton("Search");
    private JButton exit = new JButton("Exit");
    
    
    
    
}
