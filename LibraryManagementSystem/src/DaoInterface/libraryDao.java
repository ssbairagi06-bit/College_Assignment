package DaoInterface;

import POJO.Book;
import POJO.User;

public interface libraryDao {
    boolean addBook(Book book);

    boolean registerUser(User user);

    boolean issueBook(String user_id, String book_id);

    boolean returnBook(String user_id, String book_id);

    void displayBooks();

    void displayUsers();
}
