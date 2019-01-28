import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
public class TableContacts extends JFrame
{
     DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);
    
    public TableContacts()
    {
         
         cnt.setLayout(new FlowLayout(FlowLayout.LEFT));
        model.addColumn("ID");
        model.addColumn("First Name");
        model.addColumn("Middle Name");
        model.addColumn("Last Name");
        model.addColumn("Gender");
        model.addColumn("Address");
        model.addColumn("Phone Number");
        
        try {
            DBConnection db = new DBConnection();
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //Connection conn = DriverManager.getConnection("jdbc:derby:/home/michael/.netbeans/8.2/derby/management");
            PreparedStatement pstm = db.conn.prepareStatement("SELECT * FROM contacts");
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()){
                model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5),Rs.getString(6),Rs.getString(7)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JScrollPane pg = new JScrollPane(jtbl);
        pg.setPreferredSize(new Dimension(800,800));
        cnt.add(pg);
        this.pack();
        
    }
    
}
