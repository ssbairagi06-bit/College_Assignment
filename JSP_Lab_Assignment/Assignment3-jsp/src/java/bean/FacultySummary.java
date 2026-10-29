/*
 * Name: Sudhangsu Shekhar Bairagi | Roll No: 13071025097 | Date: 28-10-2026
 * Assignment 3: FacultySummary - average ratings of one faculty (read-only bean).
 */
package bean;

public class FacultySummary {
    private final String name;
    private final int count;
    private final double teaching, knowledge, communication, punctuality;

    /** Takes the sums of all ratings and the number of feedbacks. */
    public FacultySummary(String name, int count, double t, double k, double c, double p) {
        this.name = name;
        this.count = count;
        this.teaching      = count == 0 ? 0 : t / count;
        this.knowledge     = count == 0 ? 0 : k / count;
        this.communication = count == 0 ? 0 : c / count;
        this.punctuality   = count == 0 ? 0 : p / count;
    }

    public String getName()          { return name; }
    public int getCount()            { return count; }
    public double getTeaching()      { return teaching; }
    public double getKnowledge()     { return knowledge; }
    public double getCommunication() { return communication; }
    public double getPunctuality()   { return punctuality; }

    public double getOverall() {
        return (teaching + knowledge + communication + punctuality) / 4.0;
    }

    /** Business rule: overall average below 3.0 needs improvement. */
    public boolean isNeedsImprovement() {
        return count > 0 && getOverall() < 3.0;
    }
}
