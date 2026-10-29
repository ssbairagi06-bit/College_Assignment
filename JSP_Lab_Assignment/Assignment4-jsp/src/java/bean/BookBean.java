/*
 * Name: Sudhangsu Shekhar Bairagi | Roll No: 13071025097 | Date: 28-10-2026
 * Assignment 4: BookBean - one book record (Model).
 */
package bean;

public class BookBean {
    private final int id;
    private final String title;
    private final String author;
    private final boolean available;

    public BookBean(int id, String title, String author, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public int getId()          { return id; }
    public String getTitle()    { return title; }
    public String getAuthor()   { return author; }
    public boolean isAvailable(){ return available; }
}
