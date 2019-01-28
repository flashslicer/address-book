import javax.swing.JDialog;
import javax.swing.JFrame;
public class ShowTableContacts extends JDialog {
      JFrame frame = new TableContacts();
    public ShowTableContacts()
    {
        
        frame.setTitle("Contacts Table");
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
     public static void main(String[] args) 
    {
        new ShowTableContacts();
    }
    
}
