package com.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Bean.Batch;
import com.Bean.Course;
import com.Bean.Faculty;
import com.Bean.coursePlan;
import com.Exceptions.AdminException;
import com.Exceptions.BatchException;
import com.Exceptions.FacultyException;
import com.Exceptions.courseException;
import com.Exceptions.coursePlanException;
import com.Utility.DButil;

public class AdminDaoImpl implements AdminDao{

	@Override
	public String adminLogin(String username, String password) throws AdminException {
		String str="try again";
		
		try(Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from adminLogin where username=? AND password=?");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
				if(rs.next()) {
				
				str = "Login Successfull";
				
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
				ps2.setInt(2, fee);
				ps2.setString(3, courseDescription);
				ps2.setInt(4, courseId);
				
				int x=ps2.executeUpdate();
				if(x>0) {
				str = "Course updated Successfully....";
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
				
				PreparedStatement ps = conn.prepareStatement("select * from course;");
		
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

	@Override
	public String createBatch(int BatchId, int courseId, int facultyId, int numberOfStudents, int duration_min)
			throws BatchException {
		String str ="Batch not created...";
		
		try(Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("insert into batch values(?,?,?,?,curDate(),?)");
			
			ps.setInt(1, BatchId);
			ps.setInt(2, courseId);
			ps.setInt(3, facultyId);
			ps.setInt(4, numberOfStudents);
			ps.setInt(5, duration_min);
			
			int x = ps.executeUpdate();
			
				if(x>0) {
				
				str = "Batch added Successfully....";
				
				}else
				throw new BatchException("Invalid Details.. ");
			
	
			}catch(SQLException e) {
			throw new BatchException(e.getMessage());
			}
	
			return str;
	}

	@Override
	public String updatebatch(int batchId, int courseId, int facultyId, int numberOfStudent, int duration_min)
			throws BatchException {
		String str="Batch Not Updated";
		
		try(Connection conn = DButil.provideConnection()){

		PreparedStatement ps = conn.prepareStatement("select * from batch where batchId=?");

		ps.setInt(1, batchId);
		

		ResultSet rs = ps.executeQuery();

		if(rs.next()) {
	
			PreparedStatement ps2 = conn.prepareStatement("update batch set courseId=?,facultyId=?,numberOfStudent=?, duration_min=? where batchId=?");
			
			ps2.setInt(1, courseId);
			ps2.setInt(2, facultyId);
			ps2.setInt(3, numberOfStudent);
			ps2.setInt(4, duration_min);
			ps2.setInt(5, batchId);
			
			int x=ps2.executeUpdate();
			if(x>0) {
			str = "Batch Updated Successfully....";
			}
			else throw new BatchException("Invalid Details");
	
			}else
			throw new BatchException("BatchId not exist.. ");
		

			}catch(SQLException e) {
				throw new BatchException(e.getMessage());
			}

		return str;
	}

	@Override
	public List<Batch> viewAllBatchDetails() throws BatchException {
		List<Batch> list = new ArrayList<>();
		
		try(Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from batch;");
	
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int bid = rs.getInt("batchId");
				int cid = rs.getInt("courseId");
				int fid = rs.getInt("facultyId");
				int nos = rs.getInt("numberOfStudents");
				Date date=rs.getDate("batchStartDate");
				int min = rs.getInt("duration_min");
				
				Batch batch = new Batch(bid, cid, fid, nos, date, min);
				list.add(batch);
				
			}

		}catch(SQLException e) {
			throw new BatchException(e.getMessage());
		}
		
		if(list.size()==0) {
			throw new BatchException("No Batch available");
		}

		return list;
	}

	@Override
	public List<Batch> viewBatchByCourseId(int courseId) throws BatchException, courseException {
			List<Batch> list = new ArrayList<>();
		
			try(Connection conn = DButil.provideConnection()){
				
			PreparedStatement ps2 = conn.prepareStatement("select * from course where courseId=?;");
			
			ps2.setInt(1, courseId);
			ResultSet rs2=ps2.executeQuery();
			
			if(rs2.next()) {
			PreparedStatement ps = conn.prepareStatement("select * from batch where courseId=?;");
			ps.setInt(1, courseId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int bid = rs.getInt("batchId");
				int cid = rs.getInt("courseId");
				int fid = rs.getInt("facultyId");
				int nos = rs.getInt("numberOfStudents");
				Date date=rs.getDate("batchStartDate");
				int min = rs.getInt("duration_min");
				
				Batch batch = new Batch(bid, cid, fid, nos, date, min);
				list.add(batch);
				
			}
			}else
				throw new courseException("CourseId doesn't exist...");
			
			}catch(SQLException e) {
			throw new BatchException(e.getMessage());
			}
		
			if(list.size()==0) {
			throw new BatchException("No Batch available");
			}

			return list;
		
		
	}

	@Override
	public String createFaculty(Faculty faculty) throws FacultyException {
		String str="Faculty Not Inserted...";
		
		try(Connection conn = DButil.provideConnection()){
		
		PreparedStatement ps = conn.prepareStatement("insert into faculty values(?,?,?,?,?,?,?)");
		
		ps.setInt(1, faculty.getFacultyId());
		ps.setString(2, faculty.getFacultyName());
		ps.setString(3, faculty.getFacultyAdress());
		ps.setString(4, faculty.getMobile());
		ps.setString(5, faculty.getEmail());
		ps.setString(6, faculty.getUsername());
		ps.setString(7, faculty.getPassword());
		
		
		int x = ps.executeUpdate();
		
			if(x>0) {
			
			str = "Faculty added Successfully....";
			
			}else
			throw new FacultyException("Invalid Details.. ");
		

		}catch(SQLException e) {
		throw new FacultyException(e.getMessage());
		}
	
		return str;
	}

	@Override
	public String updateFaculty(int facultyId, String facultyName, String facultyAdress, String mobile, String email,
			String username, String password) throws FacultyException {
			
			String str="Faculty Not Updated";
		
			try(Connection conn = DButil.provideConnection()){

			PreparedStatement ps = conn.prepareStatement("select * from faculty where facultyId=?");

			ps.setInt(1, facultyId);
		

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
	
			PreparedStatement ps2 = conn.prepareStatement("update faculty set facultyId=?,facultyName=?, facultyAddress=?, mobile=?,email=?,username=?,password=? where facultyId=?");
			
			ps2.setInt(1, facultyId);
			ps2.setString(2, facultyName);
			ps2.setString(3, facultyAdress);
			ps2.setString(4, mobile);
			ps2.setString(5, email);
			ps2.setString(6, username);
			ps2.setString(7, password);
			ps2.setInt(8, facultyId);
			
			
			int x=ps2.executeUpdate();
			if(x>0) {
			str = "Faculty Updated Successfully....";
			}
			else throw new FacultyException("Invalid Details");
	
			}else
			throw new FacultyException("BatchId not exist.. ");
		

			}catch(SQLException e) {
				throw new FacultyException(e.getMessage());
			}

			return str;
	}

	@Override
	public List<Faculty> viewAllFacultyDetails() throws FacultyException {
		List<Faculty> list = new ArrayList<>();
		
		try(Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from faculty;");
	
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int fid = rs.getInt("facultyId");
				String fName = rs.getString("facultyName");
				String fAddress = rs.getString("facultyAddress");
				String mob = rs.getString("mobile");
				String email = rs.getString("email");
				String un = rs.getString("username");
				String pass = rs.getString("password");
				
				Faculty faculty = new Faculty(fid, fName, fAddress, mob, email, un, pass);
				list.add(faculty);
				
			}

		}catch(SQLException e) {
			throw new FacultyException(e.getMessage());
		}
		
		if(list.size()==0) {
			throw new FacultyException("No faculty available");
		}

		return list;
	}

	@Override
	public String insertFacultyToBatch(int batchId, int facultyId) throws BatchException, FacultyException {
		String str="Faculty not Inserted...";
		
			try(Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from batch where batchId=?;");
			
			ps.setInt(1, batchId);
	
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				PreparedStatement ps2 = conn.prepareStatement("update batch set facultyId=? where batchId=?");
				
				ps2.setInt(1, facultyId);
				ps2.setInt(2, batchId);
				
				int x=ps2.executeUpdate();
				
				if(x>0) {
					str="Faculty inserted to the batch successfully";
				}
				else throw new FacultyException("Inavlid faculty Id or Faculty is already in this batch");
			}else
				throw new BatchException("Invalid Batch ID");

		}catch(SQLException e) {
			throw new FacultyException(e.getMessage());
		}
	
		return str;
	}

	@Override
	public String createCoursePlan(coursePlan coursePlan) throws coursePlanException {
		String str="Course Plan Not Inserted...";
		
		try(Connection conn = DButil.provideConnection()){
		
		PreparedStatement ps = conn.prepareStatement("insert into coursePlan values(?,?,?,?,?)");
		
		ps.setInt(1, coursePlan.getPlanId());
		ps.setInt(2, coursePlan.getBatchId());
		ps.setInt(3, coursePlan.getDayNumber());
		ps.setString(4, coursePlan.getTopic());
		ps.setString(5, coursePlan.getStatus());
		
		
		
		int x = ps.executeUpdate();
		
			if(x>0) {
			
			str = "CoursePlan added Successfully....";
			
			}else
			throw new coursePlanException("Invalid Details.. ");
		

		}catch(SQLException e) {
		throw new coursePlanException(e.getMessage());
		}
	
		return str;
	}

	@Override
	public String updateCoursePlan(int planId, int batchId, int dayNumber, String topic, String status)
			throws coursePlanException {
		
		String str="coursePlan Not Updated";
		
		try(Connection conn = DButil.provideConnection()){

		PreparedStatement ps = conn.prepareStatement("select * from coursePlan where planId=?");

		ps.setInt(1, planId);
	

		ResultSet rs = ps.executeQuery();

		if(rs.next()) {

		PreparedStatement ps2 = conn.prepareStatement("update coursePlan set batchId=?,dayNumber=?, topic=?, status=? where planId=?");
		
		ps2.setInt(1, batchId);
		ps2.setInt(2, dayNumber);
		ps2.setString(3, topic);
		ps2.setString(4, status);
		ps2.setInt(5, planId);
		
		
		int x=ps2.executeUpdate();
		if(x>0) {
		str = "coursePlan Updated Successfully....";
		}
		else throw new coursePlanException("Invalid Details");

		}else
		throw new coursePlanException("Plan Id not exist.. ");
	

		}catch(SQLException e) {
			throw new coursePlanException(e.getMessage());
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
	public List<coursePlan> viewDayWiseCoursePlanUpdate() throws coursePlanException {
		List<coursePlan> list = new ArrayList<>();
		
		try(Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from coursePlan order by dayNumber;");
	
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
	public List<Batch> viewAllBatchReport() throws BatchException {
		List<Batch> list = new ArrayList<>();
		
		try(Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from batch order by batchId;");
	
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int bid = rs.getInt("batchId");
				int cid = rs.getInt("courseId");
				int fid = rs.getInt("facultyId");
				int nos = rs.getInt("numberOfStudents");
				Date date=rs.getDate("batchStartDate");
				int min = rs.getInt("duration_min");
				
				Batch batch = new Batch(bid, cid, fid, nos, date, min);
				list.add(batch);
				
			}

		}catch(SQLException e) {
			throw new BatchException(e.getMessage());
		}
		
		if(list.size()==0) {
			throw new BatchException("No Batch available");
		}

		return list;
	}
	
	
	
	
	

}
