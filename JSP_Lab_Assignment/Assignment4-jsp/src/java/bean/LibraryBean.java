package bean;

import java.util.*;

public class LibraryBean {

    private static final List<BookBean> BOOKS = new ArrayList<>();

    static {
        BOOKS.add(new BookBean(1, "Java: The Complete Reference", "Herbert Schildt", true));
        BOOKS.add(new BookBean(2, "Head First Servlets and JSP", "Bryan Basham", true));
        BOOKS.add(new BookBean(3, "Effective Java", "Joshua Bloch", false));
        BOOKS.add(new BookBean(4, "Clean Code", "Robert C. Martin", true));
        BOOKS.add(new BookBean(5, "Introduction to Algorithms", "Thomas H. Cormen", false));
        BOOKS.add(new BookBean(6, "Database System Concepts", "Abraham Silberschatz", true));
        BOOKS.add(new BookBean(7, "Computer Networks", "Andrew S. Tanenbaum", false));
        BOOKS.add(new BookBean(8, "Core Java Volume I", "Cay S. Horstmann", true));
        BOOKS.add(new BookBean(9, "Wings of Fire", "A. P. J. Abdul Kalam", true));
        BOOKS.add(new BookBean(10, "The Alchemist", "Paulo Coelho", false));
    }

    private String keyword = "";

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? "" : keyword.trim();
    }

    public String getKeyword() {
        return keyword;
    }


    public String getErrorMessage() {
        return keyword.isEmpty() ? "Please enter a book title or author to search." : "";
    }

    
    public List<BookBean> getResults() {
        List<BookBean> found = new ArrayList<>();
        String k = keyword.toLowerCase();
        for (BookBean b : BOOKS) {
            if (b.getTitle().toLowerCase().contains(k) || b.getAuthor().toLowerCase().contains(k)) {
                found.add(b);
            }
        }
        return found;
    }
}
