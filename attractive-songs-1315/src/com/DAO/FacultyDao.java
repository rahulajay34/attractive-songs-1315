package com.DAO;

import java.util.List;

import com.Bean.coursePlan;
import com.Exceptions.FacultyException;
import com.Exceptions.coursePlanException;

public interface FacultyDao {

	public String facultyLogin(String username, String password) throws FacultyException;
	
	public List<coursePlan> viewAllCoursePlanDetails() throws coursePlanException;
	
	public String fillDayWisePlan(int planId, int dayNumber, String status) throws coursePlanException;
	
	public String updatePassword(String username, String oldPassword, String newPassword) throws FacultyException;
	
}
