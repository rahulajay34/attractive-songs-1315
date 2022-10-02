package com.UseCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.Bean.Batch;
import com.Bean.Course;
import com.Bean.Faculty;
import com.Bean.coursePlan;
import com.DAO.AdminDao;
import com.DAO.AdminDaoImpl;
import com.DAO.FacultyDao;
import com.DAO.FacultyDaoImpl;
import com.Exceptions.AdminException;
import com.Exceptions.BatchException;
import com.Exceptions.FacultyException;
import com.Exceptions.courseException;
import com.Exceptions.coursePlanException;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AdminDao Adao = new AdminDaoImpl();
		FacultyDao Fdao = new FacultyDaoImpl();
		
		System.out.println("Welcome to course management system");
		System.out.println("===================================");
		System.out.println();
		
		int c = 0;
		
		while(c!=3) {
			
			System.out.println();
			System.out.println("1. Login as ADMIN");
			System.out.println("2. Login as FACULTY");
			System.out.println("3. Exit");
			System.out.println();
			System.out.println("Enter your choice : ");
			c = sc.nextInt();
			
			if(c==1) {
				
				System.out.println();
				System.out.println("Enter your username :");
				String username = sc.next();
				System.out.println("Enter your password :");
				String password = sc.next();
				String str="null";
				try {
					str= Adao.adminLogin(username, password);
				} catch (AdminException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					//e.printStackTrace();
				}
				if(str.equals("Login Successfull")) {
					System.out.println(str);
					System.out.println("Welcome "+username);
					System.out.println("===============================");
					int ac=0;
					while(ac!=17) {
					
					System.out.println("1. Create a Course");
					System.out.println("2. Update a Course");
					System.out.println("3. View all Courses Details");
					System.out.println("4. Create a Batch");
					System.out.println("5. Update a Batch");
					System.out.println("6. View all Batch Details");
					System.out.println("7. View Batch by Course ID");
					System.out.println("8. Create a Faculty");
					System.out.println("9. Update a Faculty");
					System.out.println("10. View all Faculty Details");
					System.out.println("11. Insert Faculty to a Batch");
					System.out.println("12. Create a Course Plan");
					System.out.println("13. Update a Course Plan");
					System.out.println("14. View all Course Plans");
					System.out.println("15. View Day-wise course plan Updates");
					System.out.println("16. View all Batches Report");
					System.out.println("17. Exit");
					System.out.println();
					System.out.println("Enter your Choice");
					sc.nextLine();
					ac = sc.nextInt();
					
					if(ac==1) {
						System.out.println();
						
						System.out.println("Enter course ID :");
						int courseId = sc.nextInt();
						System.out.println("Enter course name :");
						String courseName = sc.next();
						System.out.println("Enter course fee :");
						int fee = sc.nextInt();
						System.out.println("Enter course description :");
						String courseDescription = sc.next();
						System.out.println();
						
						Course course = new Course(courseId, courseName, fee, courseDescription);
						String cc="null";
						try {
							cc = Adao.createCourse(course);
						} catch (courseException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							//e.printStackTrace();
						}
						System.out.println(cc);
						
						System.out.println();
					}
					
					else if(ac==2) {
						System.out.println();
						
						System.out.println("Enter course ID :");
						int courseId = sc.nextInt();
						System.out.println("Enter course name :");
						String courseName = sc.next();
						System.out.println("Enter course fee :");
						int fee = sc.nextInt();
						System.out.println("Enter course description :");
						String courseDescription = sc.next();
						System.out.println();
						
						String uc = "null";
						try {
							uc = Adao.updateCourse(courseId, courseName, fee, courseDescription);
						} catch (courseException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							//e.printStackTrace();
						}
						System.out.println(uc);
						System.out.println();
					}
					
					else if(ac==3) {
						System.out.println();
						
						List<Course> list = new ArrayList<>();
						
						try {
							list = Adao.viewAllCourseDetails();
						} catch (courseException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							//e.printStackTrace();
						}
						
						for(Course cl:list) {
							System.out.println(cl);
						}
						System.out.println();
					}
					
					else if(ac==4) {
						System.out.println();
						
						System.out.println("Enter Batch ID :");
						int batchId = sc.nextInt();
						System.out.println("Enter course ID :");
						int courseId = sc.nextInt();
						System.out.println("Enter Faculty ID :");
						int facultyId = sc.nextInt();
						System.out.println("Enter Number of students :");
						int numberOfStudents = sc.nextInt();
						System.out.println("Enter Batch ID :");
						int duration_min = sc.nextInt();
						System.out.println();
						
						String cb="null";
						try {
							cb = Adao.createBatch(batchId, courseId, facultyId, numberOfStudents, duration_min);
						} catch (BatchException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							//e.printStackTrace();
						}
						
						System.out.println(cb);
						
						System.out.println();
					}
					
					else if(ac==5) {
						System.out.println();
						
						System.out.println("Enter Batch ID :");
						int batchId = sc.nextInt();
						System.out.println("Enter course ID :");
						int courseId = sc.nextInt();
						System.out.println("Enter Faculty ID :");
						int facultyId = sc.nextInt();
						System.out.println("Enter Number of students :");
						int numberOfStudents = sc.nextInt();
						System.out.println("Enter Batch ID :");
						int duration_min = sc.nextInt();
						System.out.println();
						
						String ub = "null";
						try {
							ub = Adao.updatebatch(batchId, courseId, facultyId, numberOfStudents, duration_min);
						} catch (BatchException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							//e.printStackTrace();
						}
						
						System.out.println(ub);
						System.out.println();
					}
					
					else if(ac==6) {
						System.out.println();
						
						List<Batch> list=new ArrayList<>();
						
						try {
							list = Adao.viewAllBatchDetails();
						} catch (BatchException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							//e.printStackTrace();
						}
						for(Batch bl:list) {
							System.out.println(bl);
						}
						
						System.out.println();
					}
					
					else if(ac==7) {
						System.out.println();
						
						System.out.println("Enter course ID");
						int courseId = sc.nextInt();
						System.out.println();
						
						List<Batch> list = new ArrayList<>();
						
						try {
							list = Adao.viewBatchByCourseId(courseId);
						} catch (BatchException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							//e.printStackTrace();
						} catch (courseException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							//e.printStackTrace();
						}
						
						for(Batch bl:list) {
							System.out.println(bl);
						}
						System.out.println();
					}
					
					else if(ac==8) {
						System.out.println();
						
						System.out.println("Enter faculty ID :");
						int facultyId = sc.nextInt();
						System.out.println("Enter faculty name :");
						String facultyName = sc.next();
						System.out.println("Enter faculty address(city) :");
						String facultyAdress = sc.next();
						System.out.println("Enter mobile no. :");
						String mobile = sc.next();
						System.out.println("Enter email :");
						String email = sc.next();
						System.out.println("Enter username :");
						String un = sc.next();
						System.out.println("Enter password :");
						String pass = sc.next();
						System.out.println();
						
						Faculty faculty = new Faculty(facultyId, facultyName, facultyAdress, mobile, email, un, pass);
						String cf="null";
						
						try {
							cf = Adao.createFaculty(faculty);
						} catch (FacultyException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							//e.printStackTrace();
						}
						
						System.out.println(cf);
						
						System.out.println();
					}
					
					else if(ac==9) {
						System.out.println();
						
						System.out.println("Enter faculty ID :");
						int facultyId = sc.nextInt();
						System.out.println("Enter faculty name :");
						String facultyName = sc.next();
						System.out.println("Enter faculty address(city) :");
						String facultyAdress = sc.next();
						System.out.println("Enter mobile no. :");
						String mobile = sc.next();
						System.out.println("Enter email :");
						String email = sc.next();
						System.out.println("Enter username :");
						String un = sc.next();
						System.out.println("Enter password :");
						String pass = sc.next();
						System.out.println();
						
						String uf = "null";
						try {
							uf = Adao.updateFaculty(facultyId, facultyName, facultyAdress, mobile, email, un, pass);
						} catch (FacultyException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							//e.printStackTrace();
						}
						
						System.out.println(uf);
						System.out.println();
					}
					
					else if(ac==10) {
						System.out.println();
						
						List<Faculty> list=new ArrayList<>();
						
						try {
							list = Adao.viewAllFacultyDetails();
						} catch (FacultyException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							//e.printStackTrace();
						}
						for(Faculty fl:list) {
							System.out.println(fl);
						}
						System.out.println();
					}
					
					else if(ac==11) {
						System.out.println();
						
						System.out.println("Enter Batch ID :");
						int batchId = sc.nextInt();
						System.out.println("Enter Faculty Id :");
						int facultyId = sc.nextInt();
						System.out.println();
						
						String iftb = "null";
						try {
							iftb = Adao.insertFacultyToBatch(batchId, facultyId);
						} catch (BatchException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							//e.printStackTrace();
						} catch (FacultyException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							//e.printStackTrace();
						}
						
						System.out.println(iftb);
						
						System.out.println();
					}
					
					else if(ac==12) {
						System.out.println();
						
						System.out.println("Enter plan ID :");
						int planId = sc.nextInt();
						System.out.println("Enter batch ID :");
						int batchId = sc.nextInt();
						System.out.println("Enter day number :");
						int dayNumber = sc.nextInt();
						System.out.println("Enter topic :");
						String topic = sc.next();
						System.out.println("Enter status (complete/pending) :");
						String status = sc.next();
						System.out.println();
						
						coursePlan coursePlan = new coursePlan(planId, batchId, dayNumber, topic, status);
						String ccp="null";
					
						try {
							ccp = Adao.createCoursePlan(coursePlan);
						} catch (coursePlanException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							//e.printStackTrace();
						}
						
						System.out.println(ccp);
						
						System.out.println();
					}
					
					else if(ac==13) {
						System.out.println();
						
						System.out.println("Enter plan ID :");
						int planId = sc.nextInt();
						System.out.println("Enter batch ID :");
						int batchId = sc.nextInt();
						System.out.println("Enter day number :");
						int dayNumber = sc.nextInt();
						System.out.println("Enter topic :");
						String topic = sc.next();
						System.out.println("Enter status (complete/pending) :");
						String status = sc.next();
						System.out.println();
						
						String ucp = "null";
						try {
							ucp = Adao.updateCoursePlan(planId, batchId, dayNumber, topic, status);
						} catch (coursePlanException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							//e.printStackTrace();
						}
						
						System.out.println(ucp);
						System.out.println();
					}
					
					else if(ac==14) {
						System.out.println();
						
						List<coursePlan> list=new ArrayList<>();
						
						try {
							list = Adao.viewAllCoursePlanDetails();
						} catch (coursePlanException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							//e.printStackTrace();
						}
						
						for(coursePlan cpl:list) {
							System.out.println(cpl);
						}
						
						System.out.println();
					}
					
					else if(ac==15) {
						System.out.println();
						
						List<coursePlan> list = new ArrayList<>();
						
						try {
							list = Adao.viewDayWiseCoursePlanUpdate();
						} catch (coursePlanException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							//e.printStackTrace();
						}
						
						for(coursePlan cp:list) {
							System.out.println(cp);
						}
						
						System.out.println();
					}
					
					else if(ac==16) {
						System.out.println();
						
						List<Batch> list=new ArrayList<>();
						
						try {
							list = Adao.viewAllBatchReport();
						} catch (BatchException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							//e.printStackTrace();
						}
						for(Batch bl:list) {
							System.out.println(bl);
						}
						System.out.println();
					}
					
					else if(ac==17) {
						c=3;
					}


					}
					
				}
				else {
					System.out.println("Please try again");
				}
			}
			
			else if(c==2) {
				System.out.println();
				System.out.println("Enter username : ");
				String username = sc.next();
				System.out.println("Enter password : ");
				String password = sc.next();
				
				String str="null";
				try {
					str = Fdao.facultyLogin(username, password);
				} catch (FacultyException e) {
					System.out.println(e.getMessage());
					//e.printStackTrace();
				}
				
				if(str.equals("Login Successfull")) {
					System.out.println();
					int fc = 0;
					while(fc!=4) {
					System.out.println("1. View all course plan details");
					System.out.println("2. Update daywise plan");
					System.out.println("3. Update password");
					sc.nextLine();
					fc = sc.nextInt();
					
					if(fc==1) {
						System.out.println();
						
						List<coursePlan> list=new ArrayList<>();
						
						try {
							list = Fdao.viewAllCoursePlanDetails();
						} catch (coursePlanException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							//e.printStackTrace();
						}
						
						for(coursePlan cpl:list) {
							System.out.println(cpl);
						}
						
						System.out.println();
					}
					
					else if(fc==2) {
						System.out.println();
						
						System.out.println("Enter planId :");
						int planId = sc.nextInt();
						System.out.println("Enter day number");
						int dayNumber = sc.nextInt();
						System.out.println("Enter Status");
						String status = sc.next();
						System.out.println();
						
						String fdwp = "null";
						
						try {
							fdwp = Fdao.fillDayWisePlan(planId, dayNumber, status);
						} catch (coursePlanException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
							System.out.println(e.getMessage());
						}
						
						System.out.println(fdwp);
						System.out.println();
					}
					
					else if(fc==3) {
						System.out.println();

						System.out.println("Enter username :");
						String un = sc.next();
						System.out.println("Enter old password :");
						String op = sc.next();
						System.out.println("Enter new password :");
						String np = sc.next();
						
						String up = "null";
						
						try {
							up = Fdao.updatePassword(un, op, np);
						} catch (FacultyException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							//e.printStackTrace();
						}
						
						System.out.println(up);
						System.out.println();
					}
					
					else if(fc==4) {
						c=3;
					}
					
					else System.out.println("Invalid Input, Try again...");
					
					}
					
					
					System.out.println();
				}
				else System.out.println("Try Again...");
				System.out.println();
			}
			
			else if(c==3) {
				continue;
			}
			else System.out.println("Invalid Input, Please try again.....");
			
		}
		if(c==3) {
			System.out.println("Thankyou");
		}
		
	
	}
	
	
	
}
