package POJO;

public class Students {
	private int ID;
	private String name;
	private int marks;
	public Students(int iD, String name, int marks) {	
		ID = iD;
		this.name = name;
		this.marks = marks;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "Students [ID=" + ID + ", name=" + name + ", marks=" + marks + "]";
	}
	
}
