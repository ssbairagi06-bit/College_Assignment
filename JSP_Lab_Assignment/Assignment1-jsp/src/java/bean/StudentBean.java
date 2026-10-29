/*
 * Name: Sudhangsu Shekhar Bairagi | Roll No: 13071025097 | Date: 28-10-2026
 * Assignment 1: StudentBean - stores registration data and validates it (Model).
 */
package bean;

public class StudentBean {
    // Private fields (encapsulation)
    private String name = "";
    private String rollNo = "";
    private String email = "";
    private String course = "";
    private String residence = "";   
    private String hostelName = "";
    private String roomNo = "";

    // Setters (null-safe, trim spaces)
    public void setName(String name)           { this.name = clean(name); }
    public void setRollNo(String rollNo)       { this.rollNo = clean(rollNo); }
    public void setEmail(String email)         { this.email = clean(email); }
    public void setCourse(String course)       { this.course = clean(course); }
    public void setResidence(String residence) { this.residence = clean(residence); }
    public void setHostelName(String h)        { this.hostelName = clean(h); }
    public void setRoomNo(String roomNo)       { this.roomNo = clean(roomNo); }

    // Getters
    public String getName()       { return name; }
    public String getRollNo()     { return rollNo; }
    public String getEmail()      { return email; }
    public String getCourse()     { return course; }
    public String getResidence()  { return residence; }
    public String getHostelName() { return hostelName; }
    public String getRoomNo()     { return roomNo; }

    /** Business rule: true if the student lives in the hostel. */
    public boolean isHosteller() {
        return "Hosteller".equals(residence);
    }

    /** Validation logic lives in the bean. Returns "" when data is valid. */
    public String getErrorMessage() {
        if (name.isEmpty())   return "Name is required.";
        if (rollNo.isEmpty()) return "Roll number is required.";
        if (!email.matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$")) return "Enter a valid email address.";
        if (course.isEmpty()) return "Please select a course.";
        if (residence.isEmpty()) return "Please choose Hosteller or Day Scholar.";
        if (isHosteller() && (hostelName.isEmpty() || roomNo.isEmpty()))
            return "Hostel name and room number are required for hostellers.";
        return "";
    }

    private String clean(String s) {
        return s == null ? "" : s.trim();
    }
}
