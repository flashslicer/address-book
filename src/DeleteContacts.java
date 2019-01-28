import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
public class DeleteContacts extends JDialog
{
    public DeleteContacts()
    {
        JPanel deleteContacts = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor=GridBagConstraints.WEST;
        constraints.insets=new Insets(20,20,20,20);
        
        
        constraints.gridx=0;
        constraints.gridy=0;
        deleteContacts.add(searchID,constraints);
        
        constraints.gridx=0;
        constraints.gridy=1;
        deleteContacts.add(firstname,constraints);
        
        
        constraints.gridx=0;
        constraints.gridy=2;
        deleteContacts.add(middlename,constraints);
        
        constraints.gridx=0;
        constraints.gridy=3;
        deleteContacts.add(lastname,constraints);
        
        constraints.gridx=0;
        constraints.gridy=4;
        deleteContacts.add(address,constraints);
        
        constraints.gridx=0;
        constraints.gridy=5;
        deleteContacts.add(phonenumber,constraints);
        
        constraints.gridx=0;
        constraints.gridy=6;
        deleteContacts.add(gender,constraints);
        
        constraints.gridx=1;
        constraints.gridy=0;
        deleteContacts.add(idTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=1;
        deleteContacts.add(fName,constraints);
        
        constraints.gridx=1;
        constraints.gridy=2;
        deleteContacts.add(mName,constraints);
        
        constraints.gridx=1;
        constraints.gridy=3;
        deleteContacts.add(lName,constraints);
        
        constraints.gridx=1;
        constraints.gridy=4;
        deleteContacts.add(add,constraints);
        
        constraints.gridx=1;
        constraints.gridy=5;
        deleteContacts.add(pNumber,constraints);
        
        constraints.gridx=1;
        constraints.gridy=6;
        deleteContacts.add(gd,constraints);
        
        constraints.gridx=2;
        constraints.gridy=0;
        deleteContacts.add(search,constraints);
        
        constraints.gridx=2;
        constraints.gridy=1;
        deleteContacts.add(update,constraints);
        
        constraints.gridx=2;
        constraints.gridy=2;
        deleteContacts.add(delete,constraints);
        
        constraints.gridx=2;
        constraints.gridy=3;
        deleteContacts.add(exit,constraints);
        
        delete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    DBConnection db = new DBConnection();
                    PreparedStatement pmt = db.conn.prepareStatement("delete from contacts where id=?");
                    pmt.setInt(1,Integer.parseInt(idTextField.getText()));
                    pmt.executeUpdate();
                    pmt.close();
                    JOptionPane.showMessageDialog(null,"Record deleted");
                    
                     idTextField.setText("");
                    fName.setText("");
                    mName.setText("");
                    lName.setText("");
                    add.setText("");
                    pNumber.setText("");
                    gd.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(DeleteContacts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        update.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                DBConnection db = new DBConnection();
                try {
                    PreparedStatement pmt = db.conn.prepareStatement("update contacts set firstname=?, middlename=?, lastname=?, address=?, phonenumber=? where id="+idTextField.getText());
                    
                    pmt.setString(1,fName.getText());
                    pmt.setString(2,mName.getText());
                    pmt.setString(3,lName.getText());
                    pmt.setString(4, add.getText());
                    pmt.setString(5, pNumber.getText());
                    pmt.executeUpdate();
                    pmt.close();
                    JOptionPane.showMessageDialog(null,"Record Updated");
                    
                    idTextField.setText("");
                    fName.setText("");
                    mName.setText("");
                    lName.setText("");
                    add.setText("");
                    pNumber.setText("");
                    gd.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(DeleteContacts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
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
                       gd.setText(rs.getString("gender"));
                       add.setText(rs.getString("address"));
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
                Component comp = SwingUtilities.getRoot(deleteContacts);   
                
                ((Window) comp).dispose(); 
            }
        });
       deleteContacts.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Address Contacts")); 
       add(deleteContacts);
        
               
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
    private JTextField fName = new JTextField(20);
    private JLabel middlename = new JLabel("Middle Name: ");
    private JTextField mName = new JTextField(20);
    private JLabel lastname = new JLabel("Last Name: ");
    private JTextField lName = new JTextField(20);
    private JLabel address = new JLabel("Address: ");
    private JTextField add = new JTextField(20);
    private JLabel phonenumber = new JLabel("Phone Number: ");
    private JTextField pNumber = new JTextField(20);
    private JLabel gender = new JLabel("Gender");
    private JLabel gd = new JLabel("");
    private JLabel searchID = new JLabel("Search ID");
    private JTextField idTextField = new JTextField(20);
    private JButton search = new JButton("Search");
    private JButton exit = new JButton("Exit");
    private JButton update = new JButton("Update");
    private JButton delete = new JButton("Delete");
    
    
    
    
}
