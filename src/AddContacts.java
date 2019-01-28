import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class AddContacts extends JDialog {
    
    public AddContacts()
    {
        JPanel addressContacts = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor=GridBagConstraints.WEST;
        constraints.insets=new Insets(20,20,20,20);
        
        constraints.gridx=0;
        constraints.gridy=0;
        addressContacts.add(firstname,constraints);
        
        constraints.gridx=0;
        constraints.gridy=1;
        addressContacts.add(middlename,constraints);
        
        constraints.gridx=0;
        constraints.gridy=2;
        addressContacts.add(lastname,constraints);
        
        constraints.gridx=0;
        constraints.gridy=3;
        addressContacts.add(address,constraints);
        
        constraints.gridx=0;
        constraints.gridy=4;
        addressContacts.add(telephonenumber,constraints);
        
        constraints.gridx=0;
        constraints.gridy=5;
        addressContacts.add(gender,constraints);
        
        constraints.gridx=1;
        constraints.gridy=0;
        addressContacts.add(fNameTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=1;
        addressContacts.add(mNameTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=2;
        addressContacts.add(lNameTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=3;
        addressContacts.add(addressTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=4;
        addressContacts.add(phoneTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=5;
        addressContacts.add(genderComboBox,constraints);
        
        
        constraints.gridx=2;
        constraints.gridy=0;
        addressContacts.add(addButton,constraints);
        
        constraints.gridx=2;
        constraints.gridy=1;
        addressContacts.add(exit,constraints);
        
        
        addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
              
                try {
                    DBConnection db = new DBConnection();
                    Statement stmt = db.conn.createStatement();
                    ContactsClass  cnt = new ContactsClass();
                    
                    cnt.setFirstName(fNameTextField.getText());
                    cnt.setMiddleName(mNameTextField.getText());
                    cnt.setlastName(lNameTextField.getText());
                    cnt.setAddress(addressTextField.getText());
                    cnt.setGender(genderComboBox.getSelectedItem().toString());
                    cnt.setPhoneNumber(Long.parseLong(phoneTextField.getText()));
                    
                    String insert="insert into contacts (firstname, middlename, lastname, gender, address, phonenumber)"+
                            "values ("+"'"+cnt.getFirstName()+"'"+","+"'"+cnt.getMiddlename()+"'"+","+"'"+cnt.getLastName()+"'"+","+"'"+cnt.getGender()+"'"+","+"'"+cnt.getAddress()+"'"+","+"'"+cnt.getPhoneNumber()+"'"+")";
                    
                    stmt.executeUpdate(insert);
                    JOptionPane.showMessageDialog(null, "Records Added");
                    stmt.close();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(AddContacts.class.getName()).log(Level.SEVERE, null, ex);
                }
                
               
            }
        });
        exit.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e)
           {
             
               
               
                     Component comp = SwingUtilities.getRoot(addressContacts);   
                
                ((Window) comp).dispose(); 
                  
                   
              
           }
        });
         addressContacts.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Address Contacts")); 
       add(addressContacts);
        
               
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
            new AddContacts().setVisible(true);
        }); 
    }

    
    private JButton addButton = new JButton("Add");
    private JButton exit = new JButton("Exit");
    private JLabel firstname = new JLabel("First Name");
    private JLabel middlename = new JLabel("Middle Name");
    private JLabel lastname = new JLabel("Last Name");
    private JLabel address = new JLabel("Address");
    private JLabel telephonenumber = new JLabel("Telephone Number");
    private JLabel gender = new JLabel("Gender");
    private JTextField fNameTextField = new JTextField(20);
    private JTextField mNameTextField = new JTextField(20);
    private JTextField lNameTextField = new JTextField(20);
    private JTextField addressTextField = new JTextField(20);
    private JTextField phoneTextField = new JTextField(20);
    private String[]genderBox = {"Male","Female","Others"};
    private JComboBox genderComboBox = new JComboBox(genderBox);
}
