/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.InforOrder;

/**
 *
 * @author ADMIN
 */
public class InforOrderDAO extends DBContext {

    public List<InforOrder> getlistConfirm() {
        List<InforOrder> list = new ArrayList<>();
        connection = getConnection();
        String sql = "select name,phonenumber,city,Location,OrderId,Shipper,status,totalmoney\n"
                + "From [Order] o join InforOrder iod on o.id=iod.OrderId join OrderDetail od on o.id=od.oid\n"
                + "where [Shipper] is null ";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                InforOrder i = new InforOrder();
                i.setName(resultSet.getString("name"));
                i.setPhone(resultSet.getString("phonenumber"));
                i.setCity(resultSet.getString("City"));
                i.setLocation(resultSet.getString("Location"));
                i.setOrderid(resultSet.getInt("OrderId"));
                i.setShipper(resultSet.getString("Shipper"));
                i.setTotalMoney(resultSet.getInt("totalmoney"));
                i.setStatus(convertStatus(resultSet.getInt("status")));
                list.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InforOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public String convertStatus(int num) {
        String status = "";
        if (num == 0) {
            status += "Dang Xu ly";
        } else if (num == 1) {
            status += "Dang Giao";
        }
        if (num == 2) {
            status += "Da giao";
        }
        if (num == 3) {
            status += "Da huy";
        }
        return status;
    }

    public void updateOrder(int orderid, String shipper) {
        connection = getConnection();
        String sql = "UPDATE [dbo].[InforOrder]\n"
                + "   SET [Shipper] = ?\n"
                + "      ,[status] = ?\n"
                + " WHERE [OrderId] =?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, shipper);
            statement.setInt(2, 1);
            statement.setInt(3, orderid);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(InforOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<InforOrder> getlistOrderForShipper() {
        List<InforOrder> list = new ArrayList<>();
        connection = getConnection();
        String sql = "select name,phonenumber,city,Location,OrderId,Shipper,status,totalmoney\n"
                + "From [Order] o join InforOrder iod on o.id=iod.OrderId join OrderDetail od on o.id=od.oid\n"
                + "where  [status] = 1 ";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                InforOrder i = new InforOrder();
                i.setName(resultSet.getString("name"));
                i.setPhone(resultSet.getString("phonenumber"));
                i.setCity(resultSet.getString("City"));
                i.setLocation(resultSet.getString("Location"));
                i.setOrderid(resultSet.getInt("OrderId"));
                i.setShipper(resultSet.getString("Shipper"));
                i.setTotalMoney(resultSet.getInt("totalmoney"));
                i.setStatus(convertStatus(resultSet.getInt("status")));
                list.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InforOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void updateFromShipper(int orderFromshipperid, int status) {
        connection = getConnection();
        String sql = "UPDATE [dbo].[InforOrder]\n"
                + "   SET [status] = ?\n"
                + " WHERE [OrderId] =?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, status);
            statement.setInt(2, orderFromshipperid);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(InforOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<InforOrder> getHistoryOfShipper(String name) {
        List<InforOrder> list = new ArrayList<>();
        connection = getConnection();
        String sql = "select name,o.date,phonenumber,city,Location,OrderId,Shipper,status,totalmoney\n"
                + "From [Order] o join InforOrder iod on o.id=iod.OrderId "
                + "where  Shipper like ? and status =2 or status =3 ";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                InforOrder i = new InforOrder();
                i.setName(resultSet.getString("name"));
                i.setPhone(resultSet.getString("phonenumber"));
                i.setCity(resultSet.getString("City"));
                i.setLocation(resultSet.getString("Location"));
                i.setOrderid(resultSet.getInt("OrderId"));
                i.setShipper(resultSet.getString("Shipper"));
                i.setDate(resultSet.getDate("date"));
                i.setTotalMoney(resultSet.getInt("totalmoney"));
                i.setStatus(convertStatus(resultSet.getInt("status")));
                list.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InforOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void main(String[] args) {
        InforOrderDAO dao = new InforOrderDAO();
        for (InforOrder inforOrder : dao.getHistoryOfShipper("shipper1")) {
            System.out.println(inforOrder);
        }
    }
}
