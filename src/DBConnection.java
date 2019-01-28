
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBConnection {
    private static final String DRIVER="org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL="jdbc:derby:addressbook;create=true";
    Connection conn=null;
    
    public DBConnection()
    {
          try {
                if(conn==null)
                {
                        Class.forName(DRIVER);
                        conn =DriverManager.getConnection(JDBC_URL);
                        System.out.println("Good");
                }
      } catch (SQLException ex) {
          Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
      } catch (ClassNotFoundException ex) {
          Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
      }

    }
    
    public void createTable()
    {
        try {
            String contacts="create table contacts"+
                    "("+
                    "id integer not null generated always as identity (start with 1, increment by 1),"+
                    "firstname varchar(255),"+
                    "middlename varchar(255),"+
                    "lastname varchar(255),"+
                    "gender varchar(255),"+
                    "address varchar(255),"+
                    "phonenumber varchar(255),"+
                    "constraint primary_key primary key (id)"+
                    ")";
            
            conn.createStatement().execute(contacts);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dropTable()
    {
        String trunc="drop table contacts";
        
        try {
            conn.createStatement().execute(trunc);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void exitConnect()
    {
         try {
                     
                     Connection conn = DriverManager.getConnection("jdbc:derby:;shutdown=true");
                     
                 } catch (SQLException ex) {
                     Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                     System.exit(0);
                 }
    }
    
  
}
