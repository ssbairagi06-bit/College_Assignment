package DaoInterface;

public interface QueryInterface {
	String INSERT_BOOK = "INSERT INTO books (book_id, title, author, quantity) VALUES ( ?, ?, ?, ? )"; 
	String INSERT_USER = "INSERT INTO users (user_id, name,user_type, borrow_limit) VALUES (?, ?, ?, ?)";
	 
	String GET_BOOK =
	            "SELECT * FROM books WHERE book_id=?";

	String GET_USER =
	            "SELECT * FROM users WHERE user_id=?";

	String ISSUE_BOOK =
	            "INSERT INTO borrowed_books " +
	            "(user_id, book_id) VALUES (?, ?)";

	String RETURN_BOOK =
	            "DELETE FROM borrowed_books " +
	            "WHERE user_id=? AND book_id=?";
	
	String UPDATE_QUENTITY = "UPDATE books " + 
	            "SET quantity = quantity + 1 " + 
			     "WHERE book_id = ?";
	
	 String DECREASE_QUANTITY =
	            "UPDATE books " +
	            "SET quantity = quantity - 1 " +
	            "WHERE book_id = ? " +
	            "AND quantity > 0";

	String DISPLAY_BOOKS =
	            "SELECT * FROM books";

	String DISPLAY_USERS =
	            "SELECT * FROM users";

	String COUNT_BORROWED =
	            "SELECT COUNT(*) FROM borrowed_books " +
	            "WHERE user_id=?";
}
