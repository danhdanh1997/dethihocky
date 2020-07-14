/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dehocky1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class lophocDAO 
{
    static Connection connection=ConnectDB.getKetNoi();
    public static List<lophoc> dsPhanCong()
	{
		List<lophoc> dsPhanCong = new ArrayList<>();
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("select * from lophoc");
			while(rs.next()) {
				lophoc pc = new lophoc(rs.getString("tengv"),rs.getString("malop"),rs.getString("tenmonhoc"),rs.getInt("siso"),rs.getDate("thoigianbatdau"),rs.getDate("thoigiankethuc"),rs.getInt("sotietquydoi"));

				dsPhanCong.add(pc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhanCong;
	}
    
    public static int solop()
	{
		
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT  COUNT(*) malop FROM `lophoc`");
			int solop=0;
			while(rs.next())
			{
				solop= rs.getInt(1);
			}
			return solop;
		}
		catch(SQLException e)
		{
			return 0;
		}
	}
    
     public static int sogiangvien()
	{
		
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT  COUNT(DISTINCT tengv )   FROM `lophoc`; ");
			int sogiangvien=0;
			while(rs.next())
			{
				sogiangvien= rs.getInt(1);
			}
			return sogiangvien;
		}
		catch(SQLException e)
		{
			return 0;
		}
	}
    
     public static int solopkethuc()
	{
		
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT COUNT(DISTINCT malop) FROM `lophoc` WHERE thoigiankethuc < '2018-05-28'");
			int solopkethuc=0;
			while(rs.next())
			{
				solopkethuc= rs.getInt(1);
			}
			return solopkethuc;
		}
		catch(SQLException e)
		{
			return 0;
		}
	}
     
      public static int solopcon()
	{
		
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT COUNT(DISTINCT malop) FROM `lophoc` WHERE thoigiankethuc > '2020-07-14'");
			int solopcon=0;
			while(rs.next())
			{
				solopcon= rs.getInt(1);
			}
			return solopcon;
		}
		catch(SQLException e)
		{
			return 0;
		}
	}
      
      public static String lopSiSoMax()
	{
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT malop FROM `lophoc` WHERE siso>=ALL(SELECT siso from lophoc)");
			String malop="";
			while(rs.next())
			{
				malop= rs.getString(1);
			}
			return malop;
		}
		catch(SQLException e)
		{
			return "";
		}
	
	}
      
      public static String lopSiSoMin()
	{
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT malop FROM `lophoc` WHERE siso<=ALL(SELECT siso from lophoc)");
			String malop="";
			while(rs.next())
			{
				malop= rs.getString(1);
			}
			return malop;
		}
		catch(SQLException e)
		{
			return "";
		}
	
	}
      
      
     public static int them(lophoc pc)
	{
		
		String sql = "INSERT INTO lophoc VALUES (?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, pc.getTengv());
			ps.setString(2, pc.getMalop());
			ps.setString(3, (pc.getTenmonhoc()));
			ps.setInt(4, pc.getSiso());
			ps.setDate(5, (Date) pc.getThoigianbatdau());
			ps.setDate(6, (Date) pc.getThoigiankethuc());
			ps.setInt(7, pc.getSotietquydoi());
			return ps.executeUpdate();
		}
		catch(SQLException e)
		{
			return 0;
		}
	}
     
     public static int update(lophoc pc,String a)
	{
		
		String sql = "UPDATE `lophoc` SET `tengv`=?,`tenmonhoc`=?,`siso`=?,`thoigianbatdau`=?,`thoigiankethuc`=?,`sotietquydoi`=? WHERE `lophoc`.`malop`=? ";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
                        ps.setString(1, pc.getTengv());
			
			ps.setString(2, pc.getTenmonhoc());
                        ps.setInt(3, pc.getSiso());
			ps.setDate(4, (Date) pc.getThoigianbatdau());
			ps.setDate(5, (Date) pc.getThoigiankethuc());
			ps.setInt(6, pc.getSotietquydoi());
			
			ps.setString(7, a);
			
			return ps.executeUpdate();
		}
		catch(SQLException e)
		{
			return 0;
		}
	}
     
     public static int del(String malop,String tengv)
	{
		
		String sql = "DELETE FROM `lophoc` WHERE `lophoc`.`malop` = ? AND `lophoc`.`tengv` = ?";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, malop);
			ps.setString(2, tengv);
			return ps.executeUpdate();
		}
		catch(SQLException e)
		{
			return 0;
		}
	}
     
     public static List<lophoc> thongke()
	{
            List<lophoc> list = new ArrayList<>();
		
		String sql = "SELECT tengv, count(malop) , SUM(sotietquydoi) as tongsotiet from lophoc  WHERE tengv IN('phan nguyet minh','le thanh trong','huynh tuan anh','huynh nguyen khac huy','do thi thanh tuyen','nguyen cong hoan') GROUP BY tengv";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
                      
			ResultSet rs =ps.executeQuery();
                        lophoc lop;
			while(rs.next())
                        {
                            lop = new lophoc(rs.getString("tengv"),rs.getString("count(malop)"), rs.getInt("tongsotiet"));
                            list.add(lop);
                            System.out.println(lop);
                        }
                        return list;
		}
		catch(SQLException e)
		{
			return null;
		}
	}
}
