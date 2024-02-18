package dao;

import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import model.Bill;

/**
 * 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license Click
 * nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
public class BillDao {

    public static String getId() {
        int id = 1;
        try {
            ResultSet rs = DbOperations.getData("SELECT MAX(id) FROM bill");
            if (rs.next()) {
                id = rs.getInt(1);
                id = id + 1;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return String.valueOf(id);
    }

    public static void save(Bill bill) {
        String query = "INSERT INTO bill VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionProvider.getCon();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, bill.getId());
            preparedStatement.setString(2, bill.getName());
            preparedStatement.setString(3, bill.getPhoneNumber());
            preparedStatement.setString(4, bill.getEmail());
            preparedStatement.setString(5, bill.getDate());
            preparedStatement.setString(6, bill.getTotal());
            preparedStatement.setString(7, bill.getCreateBy());

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Bill Details Added Successfully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static ArrayList<Bill> getAllRecordsByINC(String date) {
        ArrayList<Bill> list = new ArrayList<>();
        try{
            ResultSet rs = DbOperations.getData("select * from bill where date like '%"+date+"%'");
            while(rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setName(rs.getString("name"));
                bill.setPhoneNumber(rs.getString("phoneNumber"));
                bill.setEmail(rs.getString("email"));
                bill.setDate(rs.getString("date"));
                bill.setTotal(rs.getString("total"));
                bill.setCreateBy(rs.getString("createBy"));
                list.add(bill);
            }
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
    
    public static ArrayList<Bill> getAllRecordsByDESC(String date) {
        ArrayList<Bill> list = new ArrayList<>();
        try{
            ResultSet rs = DbOperations.getData("select * from bill where date like '%"+date+"%' order By id DESC" );
            while(rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setName(rs.getString("name"));
                bill.setPhoneNumber(rs.getString("phoneNumber"));
                bill.setEmail(rs.getString("email"));
                bill.setDate(rs.getString("date"));
                bill.setTotal(rs.getString("total"));
                bill.setCreateBy(rs.getString("createBy"));
                list.add(bill);
            }
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
}
