/*
 * Name: Sudhangsu Shekhar Bairagi | Roll No: 13071025097 | Date: 28-10-2026
 * Assignment 3: FeedbackBean - holds ONE student's feedback and validates it.
 */
package bean;

public class FeedbackBean {
    private String faculty = "";
    private int teaching, knowledge, communication, punctuality;  // ratings 1-5
    private String comments = "";

    public String getFaculty()     { return faculty; }
    public int getTeaching()       { return teaching; }
    public int getKnowledge()      { return knowledge; }
    public int getCommunication()  { return communication; }
    public int getPunctuality()    { return punctuality; }
    public String getComments()    { return comments; }

    public void setFaculty(String faculty)      { this.faculty = faculty == null ? "" : faculty.trim(); }
    public void setTeaching(int teaching)       { this.teaching = teaching; }
    public void setKnowledge(int knowledge)     { this.knowledge = knowledge; }
    public void setCommunication(int c)         { this.communication = c; }
    public void setPunctuality(int punctuality) { this.punctuality = punctuality; }
    public void setComments(String comments)    { this.comments = comments == null ? "" : comments.trim(); }

    /** Average of the four ratings for this feedback. */
    public double getAverage() {
        return (teaching + knowledge + communication + punctuality) / 4.0;
    }

    private boolean badRating(int r) { return r < 1 || r > 5; }

    /** Returns "" when valid, otherwise a message. */
    public String getErrorMessage() {
        if (faculty.isEmpty()) return "Please select a faculty.";
        if (badRating(teaching) || badRating(knowledge)
                || badRating(communication) || badRating(punctuality))
            return "Please rate every parameter from 1 to 5.";
        if (comments.length() > 300) return "Comments must be within 300 characters.";
        return "";
    }
}
