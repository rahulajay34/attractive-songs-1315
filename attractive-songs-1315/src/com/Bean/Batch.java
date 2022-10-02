package com.Bean;

import java.sql.Date;

public class Batch {

	private int batchId;
	private int courseId;
	private int facultyId;
	private int numberOfStudents;
	private Date batchStartDate;
	private int duration_min;
	
	public Batch() {
		
	}

	public Batch(int batchId, int courseId, int facultyId, int numberOfStudents, Date batchStartDate,
			int duration_min) {
		super();
		this.batchId = batchId;
		this.courseId = courseId;
		this.facultyId = facultyId;
		this.numberOfStudents = numberOfStudents;
		this.batchStartDate = batchStartDate;
		this.duration_min = duration_min;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}

	public Date getBatchStartDate() {
		return batchStartDate;
	}

	public void setBatchStartDate(Date batchStartDate) {
		this.batchStartDate = batchStartDate;
	}

	public int getDuration_min() {
		return duration_min;
	}

	public void setDuration_min(int duration_min) {
		this.duration_min = duration_min;
	}

	@Override
	public String toString() {
		return "Batch [batchId=" + batchId + ", courseId=" + courseId + ", facultyId=" + facultyId
				+ ", numberOfStudents=" + numberOfStudents + ", batchStartDate=" + batchStartDate + ", duration_min="
				+ duration_min + "]";
	}

	
	
	
	
}
