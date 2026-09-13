package LibraryMain;

import java.util.Scanner;

import DaoImplement.LibraryImp;
import DaoInterface.libraryDao;

import POJO.Book;
import POJO.FacultyUser;
import POJO.StudentUser;
import POJO.User;

import ValidateLibrary.LibraryValidation;

public class LibraryMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        libraryDao library = new LibraryImp();

        while (true) {

            System.out.println("\n================================");
            System.out.println("     LIBRARY MANAGEMENT SYSTEM");
            System.out.println("================================");

            System.out.println("1. Add Book");
            System.out.println("2. Register Student");
            System.out.println("3. Register Faculty");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Display Books");
            System.out.println("7. Display Users");
            System.out.println("8. Exit");

            System.out.print("Enter choice: ");

            int choice;

            // Validate menu input
            try {
                choice = sc.nextInt();
                sc.nextLine(); // consume \n
            } catch (Exception e) {

                System.out.println("Please enter a valid number.");

                sc.nextLine(); // clear invalid input

                continue;
            }


            switch (choice) {

                // =========================
                // 1. ADD BOOK
                // =========================

                case 1:

                    System.out.print("Enter Book ID: ");
                    String bookId = sc.nextLine();

                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();

                    System.out.print("Quantity: ");

                    int quantity;

                    try {
                        quantity = sc.nextInt();
                        sc.nextLine();
                    } catch (Exception e) {

                        System.out.println(
                                "Quantity must be a number."
                        );

                        sc.nextLine();

                        break;
                    }


                    // Validate Book ID
                    if (!LibraryValidation.validateId(bookId)) {
                        break;
                    }

                    // Validate Title
                    if (!LibraryValidation.validateText(title)) {
                        break;
                    }

                    // Validate Author
                    if (!LibraryValidation.validateText(author)) {
                        break;
                    }

                    // Validate Quantity
                    if (!LibraryValidation.validateQuantity(quantity)) {
                        break;
                    }


                    Book book = new Book(
                            bookId,
                            title,
                            author,
                            quantity
                    );


                    if (library.addBook(book)) {

                        System.out.println(
                                "Book added successfully."
                        );

                    }

                    break;


                // =========================
                // 2. REGISTER STUDENT
                // =========================

                case 2:

                    System.out.print("Enter Student ID: ");
                    String studentId = sc.nextLine();

                    System.out.print("Enter Student Name: ");
                    String studentName = sc.nextLine();


                    if (!LibraryValidation.validateId(studentId)) {
                        break;
                    }

                    if (!LibraryValidation.validateText(studentName)) {
                        break;
                    }


                    User student = new StudentUser(
                            studentId,
                            studentName
                    );


                    if (library.registerUser(student)) {

                        System.out.println(
                                "Student registered successfully."
                        );

                    }

                    break;


                // =========================
                // 3. REGISTER FACULTY
                // =========================

                case 3:

                    System.out.print("Enter Faculty ID: ");
                    String facultyId = sc.nextLine();

                    System.out.print("Enter Faculty Name: ");
                    String facultyName = sc.nextLine();


                    if (!LibraryValidation.validateId(facultyId)) {
                        break;
                    }

                    if (!LibraryValidation.validateText(facultyName)) {
                        break;
                    }


                    User faculty = new FacultyUser(
                            facultyId,
                            facultyName
                    );


                    if (library.registerUser(faculty)) {

                        System.out.println(
                                "Faculty registered successfully."
                        );

                    }

                    break;


                // =========================
                // 4. ISSUE BOOK
                // =========================

                case 4:

                    System.out.print("Enter User ID: ");
                    String issueUserId = sc.nextLine();

                    System.out.print("Enter Book ID: ");
                    String issueBookId = sc.nextLine();


                    if (!LibraryValidation.validateId(issueUserId)) {
                        break;
                    }

                    if (!LibraryValidation.validateId(issueBookId)) {
                        break;
                    }


                    library.issueBook(
                            issueUserId,
                            issueBookId
                    );

                    break;


                // =========================
                // 5. RETURN BOOK
                // =========================

                case 5:

                    System.out.print("Enter User ID: ");
                    String returnUserId = sc.nextLine();

                    System.out.print("Enter Book ID: ");
                    String returnBookId = sc.nextLine();


                    if (!LibraryValidation.validateId(returnUserId)) {
                        break;
                    }

                    if (!LibraryValidation.validateId(returnBookId)) {
                        break;
                    }


                    library.returnBook(
                            returnUserId,
                            returnBookId
                    );

                    break;


                // =========================
                // 6. DISPLAY BOOKS
                // =========================

                case 6:

                    library.displayBooks();

                    break;


                // =========================
                // 7. DISPLAY USERS
                // =========================

                case 7:

                    library.displayUsers();

                    break;


                // =========================
                // 8. EXIT
                // =========================

                case 8:

                    System.out.println(
                            "Thank you for using " +
                            "Library Management System."
                    );

                    sc.close();

                    return;


                // =========================
                // INVALID CHOICE
                // =========================

                default:

                    System.out.println(
                            "Invalid choice. " +
                            "Please select 1-8."
                    );
            }
        }
    }
}