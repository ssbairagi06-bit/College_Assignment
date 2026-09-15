package DaoInterface;

public interface QueryInterface {
	String ADD_RESULT = "INSERT INTO results (id, name, subject, marks) VALUES(?,?,?,?)";
	String UPDATE_RESULT = "UPDATE results SET name = ?, subject = ?, marks = ? WHERE id = ?";
	String DELETE_RESULT = "DELETE FROM results WHERE id = ?";
	String SEARCH_BY_ID = "SELECT * FROM results WHERE id = ?";
	String DISPLAY_ALL_RESULTS = "SELECT * FROM results";
}
