package DaoInterface;


import POJO.StudentResult;

public interface ResultsDao {
	boolean addResult(StudentResult result);
	boolean updateResult(int id, String name, String subject, int marks);
	boolean deleteResult(int id);
	StudentResult searchById(int id);
	void displayAllResults();
}
