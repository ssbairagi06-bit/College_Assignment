
package bean;

/**
 *
 * @author sudhangsu-shekhar-bairagi
 */
public class FacultySummary {

    private final String name;
    private final int count;
    private final double teaching, knowledge, communication, punctuality;


    public FacultySummary(String name, int count, double t, double k, double c, double p) {
        this.name = name;
        this.count = count;
        this.teaching = count == 0 ? 0 : t / count;
        this.knowledge = count == 0 ? 0 : k / count;
        this.communication = count == 0 ? 0 : c / count;
        this.punctuality = count == 0 ? 0 : p / count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public double getTeaching() {
        return teaching;
    }

    public double getKnowledge() {
        return knowledge;
    }

    public double getCommunication() {
        return communication;
    }

    public double getPunctuality() {
        return punctuality;
    }

    public double getOverall() {
        return (teaching + knowledge + communication + punctuality) / 4.0;
    }


    public boolean isNeedsImprovement() {
        return count > 0 && getOverall() < 3.0;
    }
}
