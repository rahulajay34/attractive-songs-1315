package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Bean.Course;
import com.Exceptions.AdminException;
import com.Exceptions.courseException;
import com.Utility.DButil;

public class AdminDaoImpl implements AdminDao{

	@Override
	public String adminLogin(String username, String password) throws AdminException {
		String str=null;
		
		try(Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from adminLogin where username=? AND password=?");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
				if(rs.next()) {
				
				str = "Login Successfull....";
				
				}else
				throw new AdminException("Invalid Username or password.. ");
			
	
		}catch(SQLException e) {
			throw new AdminException(e.getMessage());
		}
		
		return str;
	}

	@Override
	public String createCourse(Course course) throws courseException{
			String str="Not Inserted";
		
			try(Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("insert into course values(?,?,?,?)");
			
			ps.setInt(1, course.getCourseId());
			ps.setString(2, course.getCourseName());
			ps.setInt(3, course.getFee());
			ps.setString(4, course.getCourseDescription());
			
			int x = ps.executeUpdate();
			
				if(x>0) {
				
				str = "Course added Successfully....";
				
				}else
				throw new courseException("Invalid Details.. ");
			
	
			}catch(SQLException e) {
			throw new courseException(e.getMessage());
			}
		
		return str;
	}

	@Override
	public String updateCourse(int courseId, String courseName, int fee, String courseDescription) throws courseException{
			String str="Not Updated";
	
			try(Connection conn = DButil.provideConnection()){
	
			PreparedStatement ps = conn.prepareStatement("select * from course where courseId=?");
	
			ps.setInt(1, courseId);
			
	
			ResultSet rs = ps.executeQuery();
	
			if(rs.next()) {
		
				PreparedStatement ps2 = conn.prepareStatement("update course set courseName=?,fee=?,courseDescription=? where courseId=?");
				
				ps2.setString(1, courseName);
				ps2.setInt(1, fee);
				ps2.setString(1, courseDescription);
				ps2.setInt(1, courseId);
				
				int x=ps2.executeUpdate();
				if(x>0) {
				str = "Course added Successfully....";
				}
				else throw new courseException("Invalid Details");
		
			}else
				throw new courseException("courseId not exist.. ");
			

		}catch(SQLException e) {
			throw new courseException(e.getMessage());
		}

		return str;
	}

	@Override
	public List<Course> viewAllCourseDetails() throws courseException {
			List<Course> list = new ArrayList<>();
			
			try(Connection conn = DButil.provideConnection()){
				
				PreparedStatement ps = conn.prepareStatement("select * from course where courseId=?");
		
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					int cid = rs.getInt("courseId");
					String name = rs.getString("courseName");
					int fee = rs.getInt("fee");
					String des = rs.getString("courseDescription");
					
					Course course=new Course(cid, name, fee, des);
					list.add(course);
					
				}

			}catch(SQLException e) {
				throw new courseException(e.getMessage());
			}
			
			if(list.size()==0) {
				throw new courseException("No course available");
			}
	
			return list;
	}
	
	
	
	

}
