/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import core.RepairOrder;

/**
 *
 * @author Bob
 */
public class repairOrderDAO {
    private DBConnection conn;
    
    public repairOrderDAO(DBConnection conn) {
        this.conn = conn;
    }
    
    public List<RepairOrder> getAllRepairOrders() throws Exception {
        List<RepairOrder> list = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from repairOrder");
            while (rs.next()) {
                RepairOrder fund = convertRowToRepairOrder(rs);
                list.add(fund);
            }
            return list;
        } finally {
            conn.close(stmt, rs);
        }
    }
    
    public void addRepairOrder(RepairOrder repairOrder) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("insert into repair order values (?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, repairOrder.shipOut_CID);
            stmt.setInt(2, repairOrder.shipIn_CID);
            stmt.setInt(3, repairOrder.EID);
            stmt.setInt(4, repairOrder.RID);
            stmt.setString(5, repairOrder.dateRecd);
            stmt.setString(6, repairOrder.dateShipped);
            stmt.setString(7, repairOrder.shipOutType);
            stmt.execute();
        } finally {
            conn.close(stmt, null);
        }
    }
    
    public void deleteRepairOrder(RepairOrder repairOrder) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("delete from repair order where RID = ?");
            stmt.setInt(1, repairOrder.RID);
            stmt.execute();
        } finally {
            conn.close(stmt, null);
        }
    }
    
    public void updateRepairOrder(RepairOrder repairOrder) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("update repair order set dataRecd = ?," 
                    + "dateShipped = ?,"
                    + "shipOutType = ?,"
                    + "shipOut_CID = ?,"
                    + "EID = ?,"
                    + "shipIn_CID = ?"
                    + "where RID = ?");
            stmt.setInt(1, repairOrder.shipOut_CID);
            stmt.setInt(2, repairOrder.shipIn_CID);
            stmt.setInt(3, repairOrder.EID);
            stmt.setInt(4, repairOrder.RID);
            stmt.setString(5, repairOrder.dateRecd);
            stmt.setString(6, repairOrder.dateShipped);
            stmt.setString(7, repairOrder.shipOutType);
            stmt.execute();
        } finally {
            conn.close(stmt, null);
        }
    }
    
    private RepairOrder convertRowToRepairOrder(ResultSet rs) throws Exception {
        String dateRecd = rs.getString("Date Recieved");
        String dateShipped = rs.getString("Date Shipped");
        String shipOutType = rs.getString("Ship-out Type");
        int shipOut_CID = rs.getInt("Ship-out CID");
        int EID = rs.getInt("EID");
        int shipIn_CID = rs.getInt("Ship-in CID");
        int RID = rs.getInt("RID");
        return new RepairOrder();
    }
}
