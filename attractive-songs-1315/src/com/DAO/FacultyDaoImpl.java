package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Bean.coursePlan;
import com.Exceptions.AdminException;
import com.Exceptions.FacultyException;
import com.Exceptions.coursePlanException;
import com.Utility.DButil;

public class FacultyDaoImpl implements FacultyDao{

	@Override
	public String facultyLogin(String username, String password) throws FacultyException {
		
		String str=null;
		
		try(Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from faculty where username=? AND password=?");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
				if(rs.next()) {
				
				str = "Login Successfull";
				
				}else
				throw new FacultyException("Invalid Username or password.. ");
			
	
		}catch(SQLException e) {
			throw new FacultyException(e.getMessage());
		}
		
		return str;
	}

	@Override
	public List<coursePlan> viewAllCoursePlanDetails() throws coursePlanException {
		
		List<coursePlan> list = new ArrayList<>();
		
		try(Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from coursePlan;");
	
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int pid = rs.getInt("planId");
				int bid = rs.getInt("batchId");
				int dn = rs.getInt("dayNumber");
				String topic = rs.getString("topic");
				String status = rs.getString("status");
				
				coursePlan CP = new coursePlan(pid, bid, dn, topic, status);
				list.add(CP);
				
			}

		}catch(SQLException e) {
			throw new coursePlanException(e.getMessage());
		}
		
		if(list.size()==0) {
			throw new coursePlanException("No course plan available");
		}

		return list;
	}

	@Override
	public String fillDayWisePlan(int planId, int dayNumber, String status) throws coursePlanException {
		String str = "Status not updated successfully";
		
			try(Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from coursePlan where planId=?");
			
			ps.setInt(1, planId);
			
			
			ResultSet rs = ps.executeQuery();
			
				if(rs.next()) {
				
					PreparedStatement ps2 = conn.prepareStatement("select * from coursePlan where dayNumber=? AND planId=?");
					
					ps2.setInt(1, dayNumber);
					ps2.setInt(2, planId);
					
					
					ResultSet rs2 = ps.executeQuery();
					
						if(rs2.next()) {
						
							PreparedStatement ps3 = conn.prepareStatement("update coursePlan set status=? where dayNumber=? AND planId=?");
							ps3.setString(1,status);
							ps3.setInt(2, dayNumber);
							ps3.setInt(3, planId);
							
							int x=ps3.executeUpdate();
							if(x>0) {
								str="Status updated Successfully";
							}
							else throw new coursePlanException("Invalid status");
						
						}else
						throw new coursePlanException("Invalid day Number / Day no. not exist");
				
				}else
				throw new coursePlanException("Invalid PlanId / / PlanId not exist");
			
	
				}catch(SQLException e) {
					throw new coursePlanException(e.getMessage());
				}

				return str;
	}

	@Override
	public String updatePassword(String username, String oldPassword, String newPassword) throws FacultyException {
		String str = "Password not changed";
			
			try(Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from faculty where username=? And password=?");
			ps.setString(1, username);
			ps.setString(2, oldPassword);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				PreparedStatement ps2 = conn.prepareStatement("update faculty set password=? where username=?");
				
				ps2.setString(1, newPassword);
				ps2.setString(2,username);
				
				int x = ps2.executeUpdate();
				
				if(x>0) {
					str = "Password Changed Successfully";
				}
			}else str = "Invalid username or old Password";
			
	
				}catch(SQLException e) {
					throw new FacultyException(e.getMessage());
				}

		
		return str;
	}

}
