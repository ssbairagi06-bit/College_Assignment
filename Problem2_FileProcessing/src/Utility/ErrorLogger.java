package Utility;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ErrorLogger {
	public static void logError(String errorLogFile, String message) {
		try(FileWriter fw = new FileWriter(errorLogFile, true);
		     PrintWriter pw = new PrintWriter(fw)){
			
			 pw.println(LocalDateTime.now()+ " : "+ message);			
		
			
		}catch(IOException e) {
			System.out.println("Unable to Write the error in the log file " + e.getMessage());
		}
	}
}
