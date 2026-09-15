package ResultsMain;

import java.util.Scanner;

import DaoImplement.ResultsDaoImpl;
import DaoInterface.ResultsDao;
import POJO.StudentResult;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ResultsDao dao = new ResultsDaoImpl();
		
		while(true) {
			System.out.println("+++++++++++++++++++++++++++++++++++++++");
			System.out.println("      STUDENT RESULT MANAGEMENT");
			System.out.println("+++++++++++++++++++++++++++++++++++++++");
			System.out.println("1. INSERT NEW RESULTS");
			System.out.println("2. DELETE RESULTS");
			System.out.println("3. UPDATE STUDENT RESULTS");
			System.out.println("4. SEARCH RESULTS BY STUDENT ID");
			System.out.println("5. DISPLAY ALL RESULTS");
			System.out.println("6. EXIT");
			
			int ch = sc.nextInt();
			
			switch(ch) {
			case 1: {
				System.out.println("Enter the Student id: ");
				int id = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the Student name: ");
				String name = sc.nextLine();
				System.out.println("Enter the Subject: ");
				String subject = sc.nextLine();
				System.out.println("Enter the Marks: ");
				int marks = sc.nextInt();
				StudentResult newStudent = new StudentResult(id, name, subject, marks);
				if(dao.addResult(newStudent)) {
					System.out.println("Student Result Add Successfully.");
				}
				break;
			}
			
			case 2: {
				System.out.println("Enter the Student id: ");
				int id = sc.nextInt();
				if(dao.deleteResult(id)) {
					System.out.println("Student Result Deleted Successfully ");
				}
				break;
			}
			case 3: {
				System.out.println("Enter the Student id: ");
				int id = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the Student name:(If Remain Same Keep it Blank) ");
				String name = sc.nextLine();
				System.out.println("Enter the Subject: (If Remain Same Keep it Blank)");
				String subject = sc.nextLine();
				System.out.println("Enter the Marks: (If Remain Same Keep it Blank)");
				String marksString =  sc.nextLine();
				int marks = 0;
				
				if(marksString == null || marksString.trim().equals("")) {
					marks = -1;
				}else {
					marks = Integer.parseInt(marksString);
				}
				
				if(dao.updateResult(id, name, subject, marks)) {
					System.out.println("Successfully Updated");
				}
				break;
			}
			case 4: {
				System.out.println("Enter the Student id: ");
				int id = sc.nextInt();
				
				StudentResult result = dao.searchById(id);
				if(result != null) {
					System.out.println("Result Found ");
					System.out.println(result.toString());
				}else {
					System.out.println("Result Not Found");
				}
				break;
			}
			case 5: {
				dao.displayAllResults();
				break;
			}
			case 6: {
				System.out.println("Thank You! See You Again......");
				return ;
			}
			default : {
				System.out.println("Invalid Input....");
				System.out.println("Try Again...");
				break;
			}
				
			}
			
		}

	}

}
