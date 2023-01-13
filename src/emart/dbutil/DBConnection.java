package emart.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnection {
    private static Connection conn;
   static{
    try
    {
        Class.forName("oracle.jdbc.OracleDriver");
        conn=DriverManager.getConnection("jdbc:oracle:thin:@//BYOD667160:1521/Xe","system","110601");
        JOptionPane.showMessageDialog(null,"Connection Opened Successfully!","Sucess!!",JOptionPane.INFORMATION_MESSAGE);
    }
    catch(ClassNotFoundException ex)
    {
       JOptionPane.showMessageDialog(null,"Error in loading the driver!","Driver Error!",JOptionPane.ERROR_MESSAGE);
       ex.printStackTrace();
       System.exit(1);
    }
    catch(SQLException ex)
    {
       JOptionPane.showMessageDialog(null,"Error in Opening connection!","DB Error!",JOptionPane.ERROR_MESSAGE);
       ex.printStackTrace();
       System.exit(1);
    }
}
   public static Connection getConnection()
   {
       return conn;
   }
   public static void closeConnection()
   {
       try 
       {
           conn.close();
           JOptionPane.showMessageDialog(null,"Connection closed successfully!","Success!",JOptionPane.INFORMATION_MESSAGE);
       }
       catch(SQLException ex)
       {
           JOptionPane.showMessageDialog(null,"Error in closeing connection!","DB Error0!",JOptionPane.ERROR_MESSAGE);
           ex.printStackTrace();
       }
   }
}