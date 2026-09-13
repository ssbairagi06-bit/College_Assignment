package POJO;

public class Book {
	private String book_id;
	private String title;
	private String author;
	private int quantity;
	
	public Book() {
		
	}
		
	public Book(String book_id, String title, String author, int quantity) {

		this.book_id = book_id;
		this.title = title;
		this.author = author;
		this.quantity = quantity;
	}
	
	
	public boolean isAvailable() {
		return quantity > 0;
	}


	public String getBook_id() {
		return book_id;
	}


	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
