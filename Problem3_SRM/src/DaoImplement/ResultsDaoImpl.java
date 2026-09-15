package DaoImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DaoInterface.DbConnection;
import DaoInterface.QueryInterface;
import DaoInterface.ResultsDao;
import POJO.StudentResult;

public class ResultsDaoImpl implements ResultsDao, QueryInterface {
	
	private DbConnection dbConn;
	
	public ResultsDaoImpl() {
		this.dbConn = new DbConnectionImp();
	}

	@Override
	public boolean addResult(StudentResult result) {
		
		try {
			Connection conn = dbConn.getConnection();
			PreparedStatement ps = conn.prepareStatement(ADD_RESULT);
			ps.setInt(1, result.getId());
			ps.setString(2, result.getName());
			ps.setString(3, result.getSubject());
			ps.setInt(4, result.getMarks());
			
			int rows = ps.executeUpdate();
			conn.close();
			ps.close();
			return rows > 0;
		}catch(SQLException e) {
			System.out.println("Error: Insert Result" + e.getMessage());
			return false;
		}
	
		
	}

	@Override
	public boolean updateResult(int id, String name, String subject, int marks) {
		Connection conn = null;
		try {
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement userPS = conn.prepareStatement(SEARCH_BY_ID);
			userPS.setInt(1, id);
			ResultSet userRS = userPS.executeQuery();
			if(!userRS.next()) {
				System.out.println("User does not exits.");
				conn.rollback();
				return false;
			}
			 String nameDB = (name == null || name.trim().isEmpty())
		                ? userRS.getString("name")
		                : name;

		        String subjectDB = (subject == null || subject.trim().isEmpty())
		                ? userRS.getString("subject")
		                : subject;

		        int marksDB = marks < 0
		                ? userRS.getInt("marks")
		                : marks;
		        
			userPS.close();
			userRS.close();
			
			PreparedStatement upPs = conn.prepareStatement(UPDATE_RESULT);

	        upPs.setString(1, nameDB);
	        upPs.setString(2, subjectDB);
	        upPs.setInt(3, marksDB);
	        upPs.setInt(4, id);

	        int rows = upPs.executeUpdate();

	        if (rows > 0) {

	            conn.commit();	           
	            upPs.close();
	            return true;

	        } else {

	            conn.rollback();
	            upPs.close();
	            return false;
	        }

	    } catch (SQLException e) {

	        try {

	            if (conn != null) {
	                conn.rollback();
	            }

	        } catch (SQLException ex) {

	            ex.printStackTrace();
	        }

	        System.out.println("Update failed: " +e.getMessage());

	        return false;

	    } finally {

	        try {

	            if (conn != null) {
	                conn.setAutoCommit(true);
	                conn.close();
	            }

	        } catch (SQLException e) {

	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public boolean deleteResult(int id) {
		Connection conn = null;
		try {
			conn = dbConn.getConnection();
			PreparedStatement userPS = conn.prepareStatement(SEARCH_BY_ID);
			userPS.setInt(1, id);
			ResultSet userRS = userPS.executeQuery();
			if(!userRS.next()) {
				System.out.println("User does not exits.");
				
				return false;
			}
			userPS.close();
			PreparedStatement deletePs = conn.prepareStatement(DELETE_RESULT);
			deletePs.setInt(1, id);
			int rows = deletePs.executeUpdate();
			deletePs.close();
			return rows > 0;
			
		}catch(SQLException e) {
			System.out.println("Error: Delete results"+ e.getMessage());
			return false;
		}finally {
			 try {

		            if (conn != null) {
		                conn.close();
		            }

		        } catch (SQLException e) {

		        	System.out.println("Error: Close the Resourses: " + e.getMessage());
		        }
		    }
	}

	@Override
	public StudentResult searchById(int id) {
		Connection conn = null;
		PreparedStatement userPS = null;
		try {
			conn = dbConn.getConnection();			
			userPS = conn.prepareStatement(SEARCH_BY_ID);
			userPS.setInt(1, id);
			ResultSet userRS = userPS.executeQuery();
			if(userRS.next()) {
				StudentResult sr = new StudentResult();
				sr.setId(userRS.getInt("id"));
				sr.setName(userRS.getString("name"));
				sr.setSubject(userRS.getString("subject"));
				sr.setMarks(userRS.getInt("marks"));			
							
				return sr;
			}
			
		}catch(SQLException e) {
			System.out.println("Error: Search Results" + e.getMessage());			
		}finally {
			try {

	            if (conn != null) {
	                conn.close();
	            }
	            if(userPS != null) {
	            	userPS.close();
	            }
	           
	        } catch (SQLException e) {

	        	System.out.println("Error: Close the Resourses: " + e.getMessage());
	        }
	    
		}
		return null;		
		
	}

	@Override
	public void displayAllResults() {

        try (
            Connection con =
                    dbConn.getConnection();

            PreparedStatement ps = con.prepareStatement(DISPLAY_ALL_RESULTS);

            ResultSet rs =
                    ps.executeQuery()
        ) {

            System.out.println(
                    "\n========== STUDENT RESULTS =========="
            );

            while (rs.next()) {
            	System.out.println("ID : "+ rs.getInt("id"));
            	System.out.println("Name : " + rs.getString("name"));
            	System.out.println("Subject : " + 	rs.getString("subject"));
            	System.out.println("Marks : " + rs.getInt("marks"));
 
                System.out.println(
                        "--------------------------------------"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error displaying results: "
                    + e.getMessage()
            );
        }
    }
		

}
