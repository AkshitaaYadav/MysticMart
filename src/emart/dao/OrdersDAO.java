
package emart.dao;

import emart.dbutil.DBConnection;
import emart.pojo.ProductsPojo;
import emart.pojo.UserProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersDAO {
    public static String getNextProductId() throws SQLException 
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select max(order_id) from orders");
        rs.next();
        String ordId=rs.getString(1);
        if(ordId==null)
        {
            return "O-101";
        }
        int ordno=Integer.parseInt(ordId.substring(2));
        ordno++;
        return "O-"+ordno;
                
    }
    public static boolean addOrder(ArrayList<ProductsPojo>al,String ordId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Insert into orders values(?,?,?,?)");
        int count=0;
        for (ProductsPojo p:al)
        {
            ps.setString(1,ordId);
            ps.setString(2,p.getProductId());
            ps.setInt(3,p.getQuantity());
            ps.setString(4,UserProfile.getUserid());
            count=count+ps.executeUpdate();
        }
        return count==al.size();
    }
    
    public static List<ProductsPojo> getOrdersDetails(String Id) throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select products.* from products join orders on orders.p_id=products.p_id where orders.order_id=?");
        ps.setString(1,Id);
        ResultSet rs=ps.executeQuery();
        ArrayList <ProductsPojo> al=new ArrayList<>();
        while(rs.next())
        { 
            ProductsPojo p=new ProductsPojo();
            p.setProductId(rs.getString(1));
            p.setProductName(rs.getString(2));
            p.setProductCompany(rs.getString(3));
            p.setProductPrice(rs.getDouble(4));
            p.setOurPrice(rs.getDouble(5));
            p.setTax(rs.getInt(6));
            p.setQuantity(rs.getInt(7));
            al.add(p);
        }
        return al;
        
    }
   
   public static List<String> getAllOrderId() throws SQLException 
   {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select distinct order_id,p_id from orders");
        List<String>orderList=new ArrayList<>();
        while(rs.next())
        {
            String id=rs.getString(1);
            orderList.add(id);
        }
        return orderList;
   }
}
