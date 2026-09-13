package FileImplement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ExceptionHandling.InvalidDataException;
import FileInterface.FileProcessor;
import POJO.Students;
import Utility.ErrorLogger;

public class FileProcessorImpl implements FileProcessor {

	@Override
	public void processFile(String inputFile, String outputFile, String errorLogFile) {
		
		BufferedWriter writer = null;
		BufferedReader reader = null;
		
		try {
			writer = new BufferedWriter(new FileWriter(outputFile));
			reader = new BufferedReader(new FileReader(inputFile));
			
			String line;
			while((line = reader.readLine()) != null) {
				try {
					Students student = checkStudent(line);
					String result = calculateGM(student);
					
					writer.write(result);
					writer.newLine();
				}catch(NumberFormatException e) {
					String error = "NumberFormatException "+ line + " | " + e.getMessage();
					System.out.println(error);
					ErrorLogger.logError(errorLogFile, error);
				}catch(InvalidDataException e) {
					String error = "InvalidDataException "+ line + " | " + e.getMessage();
					System.out.println(error);
					ErrorLogger.logError(errorLogFile, error);
				}
				
				writer.flush();
				System.out.println( "\nFile processing completed.");

	            System.out.println("Results saved to: "+ outputFile);
			}
		
		}catch(FileNotFoundException e) {
			String error = "FileNotFoundException | "+ e.getMessage();
			System.out.println(error);
			ErrorLogger.logError(errorLogFile, error);
			
		}catch(IOException e) {
			String error = "IOException | "+ e.getMessage();
			System.out.println(error);
			ErrorLogger.logError(errorLogFile, error);
	}finally {
		if (reader != null) {

            try {

                reader.close();

            } catch (IOException e) {

                ErrorLogger.logError(
                        errorLogFile,
                        "Error closing reader: "
                        + e.getMessage()
                );
            }
        }

        // Close writer
        if (writer != null) {

            try {

                writer.close();

            } catch (IOException e) {

                ErrorLogger.logError(
                        errorLogFile,
                        "Error closing writer: "
                        + e.getMessage()
                );
            }
        }

        System.out.println(
                "File resources released."
        );
    }
	}
		
    private Students checkStudent(String line) throws InvalidDataException {
    	if(line == null || line.trim().isEmpty()) {
    		throw new InvalidDataException("File Empty");
    	}
    	
    	String[] data = line.split(",", -1);
    	
    	if(data.length != 3) {
    		throw new InvalidDataException("Not in poper format");
    	}
    	
    	String idT = data[0].trim();
    	String name = data[1].trim();
    	String marksT = data[2].trim();
    	
    	 if (idT.isEmpty()) {

             throw new InvalidDataException("Student ID is empty.");
         }

         if (name.isEmpty()) {

             throw new InvalidDataException("Student name is empty.");
         }

         if (marksT.isEmpty()) {

             throw new InvalidDataException("Marks field is empty.");
         }
         
         int id = Integer.parseInt(idT);
         int marks = Integer.parseInt(marksT);
         
         if (id <= 0) {

             throw new InvalidDataException("Student ID must be positive.");
         }

         if (marks < 0 || marks > 100) {

             throw new InvalidDataException("Marks must be between 0 and 100."
             );
         }

         return new Students(id,name,marks);
         	
    	
    }
    
    private String calculateGM(Students student) {

        int marks =
                student.getMarks();

        String grade;
        String remarks;

        if (marks >= 90) {
            grade = "A";
            remarks = "Excellent";

        } else if (marks >= 80) {
            grade = "B";
            remarks = "Very Good";

        } else if (marks >= 70) {
            grade = "C";
            remarks = "Good";
        } else if (marks >= 60) {
            grade = "D";
            
            remarks = "Satisfactory";
        } else if (marks >= 50) {
            grade = "E";
            remarks = "Pass";
        } else {
            grade = "F";
            remarks = "Fail";   
        }

        return "ID: "
                + student.getID()
                + ", Name: "
                + student.getName()
                + ", Marks: "
                + student.getMarks()
                + ", Grade: "
                + grade
                + ", Remarks: "
                + remarks;
    }
    
}
