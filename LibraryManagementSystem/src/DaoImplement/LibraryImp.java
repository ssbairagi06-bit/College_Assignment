package DaoImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DaoInterface.QueryInterface;
import DaoInterface.libraryDao;
import POJO.Book;
import POJO.User;

public class LibraryImp implements libraryDao, QueryInterface {

	private DbConnectionImp dbConnection;
	
	
	public LibraryImp() {		
		this.dbConnection = new DbConnectionImp();
	}

	@Override
	public boolean addBook(Book book) {
	
		try {
			Connection con = dbConnection.getConnection();
			PreparedStatement stmt = con.prepareStatement(INSERT_BOOK);
			stmt.setString(1, book.getBook_id());
			stmt.setString(2, book.getTitle());
			stmt.setString(3, book.getAuthor());
			stmt.setInt(4, book.getQuantity());
			
			stmt.executeUpdate();
			con.close();
			stmt.close();
			return true;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	
	}

	@Override
	public boolean registerUser(User user) {
		try {
			Connection conn = dbConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(INSERT_USER);
			ps.setString(1, user.getUser_id());
			ps.setString(2, user.getName());
			ps.setString(3, user.getUser_type());
			ps.setInt(4, user.getBorrow_limit());
			ps.executeUpdate();
			conn.close();
			ps.close();
			return true;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean issueBook(String user_id, String book_id) {

	    Connection conn = null;

	    try {
	        conn = dbConnection.getConnection();
	        conn.setAutoCommit(false);

	        // =========================
	        // 1. Check User
	        // =========================

	        PreparedStatement userPs =
	                conn.prepareStatement(GET_USER);

	        userPs.setString(1, user_id);

	        ResultSet userRs =
	                userPs.executeQuery();

	        if (!userRs.next()) {

	            System.out.println("User does not exist.");

	            conn.rollback();
	            return false;
	        }

	        // Get user details from database
//	        String name = userRs.getString("name");
//	        String userType = userRs.getString("user_type");
	        int borrowLimit = userRs.getInt("borrow_limit");
	        userRs.close();
	        userPs.close();


	        // =========================
	        // 2. Create User Object
	        // =========================
//
//	        User user;
//
//	        if (userType.equalsIgnoreCase("Student")) {
//
//	            user = new StudentUser(user_id, name);
//
//	        } else if (userType.equalsIgnoreCase("Faculty")) {
//
//	            user = new FacultyUser(user_id, name);
//
//	        } else {
//
//	            System.out.println("Invalid user type.");
//
//	            conn.rollback();
//	            return false;
//	        }
//


	        PreparedStatement bookPs =
	                conn.prepareStatement(GET_BOOK);

	        bookPs.setString(1, book_id);

	        ResultSet bookRs =
	                bookPs.executeQuery();

	        if (!bookRs.next()) {

	            System.out.println("Book does not exist.");

	            bookRs.close();
	            bookPs.close();

	            conn.rollback();
	            return false;
	        }

	        int quantity =
	                bookRs.getInt("quantity");

	        bookRs.close();
	        bookPs.close();


	        // =========================
	        // 5. Check Book Availability
	        // =========================

	        if (quantity <= 0) {

	            System.out.println(
	                    "Book is not available."
	            );

	            conn.rollback();
	            return false;
	        }




	        PreparedStatement countPs =
	                conn.prepareStatement(COUNT_BORROWED);

	        countPs.setString(1, user_id);

	        ResultSet countRs =
	                countPs.executeQuery();

	        int borrowedCount = 0;

	        if (countRs.next()) {

	            borrowedCount =
	                    countRs.getInt(1);
	        }

	        countRs.close();
	        countPs.close();


	        // =========================
	        // 7. Check Borrow Limit
	        // =========================

	        if (borrowedCount >= borrowLimit) {

	            System.out.println(
	                    "Borrow limit reached. " +
	                    "You can borrow maximum " +
	                    borrowLimit +
	                    " books."
	            );

	            conn.rollback();
	            return false;
	        }


	        // =========================
	        // 8. Issue Book
	        // =========================

	        PreparedStatement issuePs =
	                conn.prepareStatement(ISSUE_BOOK);

	        issuePs.setString(1, user_id);
	        issuePs.setString(2, book_id);

	        int issueRows =
	                issuePs.executeUpdate();

	        issuePs.close();


	        if (issueRows == 0) {

	            System.out.println(
	                    "Unable to issue book."
	            );

	            conn.rollback();
	            return false;
	        }


	        // =========================
	        // 9. Decrease Book Quantity
	        // =========================

	        PreparedStatement quantityPs =
	                conn.prepareStatement(
	                        DECREASE_QUANTITY
	                );

	        quantityPs.setString(1, book_id);

	        int updatedRows =
	                quantityPs.executeUpdate();

	        quantityPs.close();


	        if (updatedRows == 0) {

	            System.out.println(
	                    "Unable to update book quantity."
	            );

	            conn.rollback();
	            return false;
	        }


	        // =========================
	        // 10. Commit Transaction
	        // =========================

	        conn.commit();

	        System.out.println(
	                "Book issued successfully."
	        );

	        return true;


	    } catch (SQLException e) {

	        try {

	            if (conn != null) {
	                conn.rollback();
	            }

	        } catch (SQLException rollbackError) {

	            rollbackError.printStackTrace();
	        }

	        System.out.println(
	                "Error while issuing book: "
	                + e.getMessage()
	        );

	        return false;


	    } finally {

	        try {

	            if (conn != null) {
	                conn.close();
	            }

	        } catch (SQLException e) {

	            e.printStackTrace();
	        }
	    }
	}
	@Override
	public boolean returnBook(String user_id, String book_id) {

	    Connection conn = null;
	    PreparedStatement ps = null;
	    PreparedStatement updateQuantity = null;

	    try {

	        conn = dbConnection.getConnection();

	        // Start transaction
	        conn.setAutoCommit(false);

	        // 1. Return the issued book
	        ps = conn.prepareStatement(RETURN_BOOK);

	        ps.setString(1, user_id);
	        ps.setString(2, book_id);

	        int rows = ps.executeUpdate();

	        if (rows == 0) {

	            System.out.println(
	                "Book was not issued to this user."
	            );

	            conn.rollback();
	            return false;
	        }

	        // 2. Increase available quantity
	        updateQuantity = conn.prepareStatement(UPDATE_QUENTITY);

	        updateQuantity.setString(1, book_id);

	        int quantityRows = updateQuantity.executeUpdate();

	        if (quantityRows == 0) {

	            System.out.println(
	                "Book not found."
	            );

	            conn.rollback();
	            return false;
	        }

	        // Both operations successful
	        conn.commit();

	        System.out.println(
	            "Book returned successfully."
	        );

	        return true;

	    } catch (SQLException e) {

	        try {
	            if (conn != null) {
	                conn.rollback();
	            }
	        } catch (SQLException rollbackException) {
	            rollbackException.printStackTrace();
	        }

	        System.out.println(
	            "Error while returning book: "
	            + e.getMessage()
	        );

	        return false;

	    } finally {

	        try {
	            if (updateQuantity != null)
	                updateQuantity.close();

	            if (ps != null)
	                ps.close();

	            if (conn != null)
	                conn.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	@Override
    public void displayBooks() {

        try (Connection con =
                     dbConnection.getConnection();

             PreparedStatement ps =
                     con.prepareStatement(
                             DISPLAY_BOOKS);

             ResultSet rs =
                     ps.executeQuery()) {

            System.out.println(
                    "\n========== BOOKS =========="
            );

            while (rs.next()) {

                System.out.println(
                        "Book ID    : "
                        + rs.getString("book_id")
                );

                System.out.println(
                        "Title      : "
                        + rs.getString("title")
                );

                System.out.println(
                        "Author     : "
                        + rs.getString("author")
                );

                System.out.println(
                        "Quantity  : "
                        + rs.getInt("quantity")
                );

                System.out.println(
                        "---------------------------"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error displaying books: "
                    + e.getMessage()
            );
        }
    }

    @Override
    public void displayUsers() {

        try (Connection con =
                     dbConnection.getConnection();

             PreparedStatement ps =
                     con.prepareStatement(
                             DISPLAY_USERS);

             ResultSet rs =
                     ps.executeQuery()) {

            System.out.println(
                    "\n========== USERS =========="
            );

            while (rs.next()) {

                System.out.println(
                        "User ID    : "
                        + rs.getString("user_id")
                );

                System.out.println(
                        "Name       : "
                        + rs.getString("name")
                );

                System.out.println(
                        "User Type  : "
                        + rs.getString("user_type")
                );

                System.out.println(
                        "---------------------------"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error displaying users: "
                    + e.getMessage()
            );
        }
    }

}
