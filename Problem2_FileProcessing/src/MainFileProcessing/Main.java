package MainFileProcessing;

import FileImplement.FileProcessorImpl;
import FileInterface.FileProcessor;

public class Main {

	public static void main(String[] args) {
		  String inputFile =
	                "students.txt";

	        String outputFile =
	                "results.txt";

	        String errorLogFile =
	                "error.log";

	        FileProcessor processor =
	                new FileProcessorImpl();

	       
	        System.out.println("STUDENT FILE PROCESSING");

	      

	        processor.processFile( inputFile,outputFile,errorLogFile );

	        System.out.println("\nProgram execution completed.");

	}

}
