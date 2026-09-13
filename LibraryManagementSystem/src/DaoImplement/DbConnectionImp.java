package DaoImplement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import DaoInterface.DbConnection;

public class DbConnectionImp implements DbConnection {
	
	private static final String URL =
            "jdbc:mysql://localhost:3306/your_database_name";

	 private static final String USER =
	            "root";

	    private static final String PASSWORD =
	            "your_password";

	@Override
	public Connection getConnection() throws SQLException {
		
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

}
