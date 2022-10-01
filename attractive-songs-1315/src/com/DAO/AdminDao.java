package com.DAO;

import java.util.List;

import com.Bean.Course;
import com.Exceptions.AdminException;
import com.Exceptions.courseException;

public interface AdminDao {

	public String adminLogin(String username, String password) throws AdminException;
	
	public String createCourse(Course course) throws courseException;
	
	public String updateCourse(int courseId, String courseName, int fee, String courseDescription) throws courseException;
	
	public List<Course> viewAllCourseDetails() throws courseException;
}
