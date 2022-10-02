package com.DAO;

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

public interface AdminDao {

	public String adminLogin(String username, String password) throws AdminException;
	
	public String createCourse(Course course) throws courseException;
	
	public String updateCourse(int courseId, String courseName, int fee, String courseDescription) throws courseException;
	
	public List<Course> viewAllCourseDetails() throws courseException;
	
	public String createBatch(int BatchId, int courseId, int facultyId, int numberOfStudents, int duration_min) throws BatchException;
	
	public String updatebatch(int batchId, int courseId, int facultyId, int numberOfStudent,int duration_min) throws BatchException;

	public List<Batch> viewAllBatchDetails() throws BatchException;

	public List<Batch> viewBatchByCourseId(int courseId) throws BatchException,courseException;
	
	public String createFaculty(Faculty faculty) throws FacultyException;
	
	public String updateFaculty(int facultyId, String facultyName, String facultyAdress, String mobile, String email,
			String username, String password) throws FacultyException;
	
	public List<Faculty> viewAllFacultyDetails() throws FacultyException;
	
	public String insertFacultyToBatch(int batchId, int facultyId) throws BatchException,FacultyException;
	
	public String createCoursePlan(coursePlan coursePlan) throws coursePlanException;
	
	public String updateCoursePlan(int planId, int batchId, int dayNumber, String topic, String status) throws coursePlanException;
	
	public List<coursePlan> viewAllCoursePlanDetails() throws coursePlanException;
	
	public List<coursePlan> viewDayWiseCoursePlanUpdate() throws coursePlanException;
	
	public List<Batch> viewAllBatchReport() throws BatchException;
}
