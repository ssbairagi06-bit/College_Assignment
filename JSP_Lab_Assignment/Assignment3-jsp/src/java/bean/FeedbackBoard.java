/*
 * Name: Sudhangsu Shekhar Bairagi | Roll No: 13071025097 | Date: 28-10-2026
 * Assignment 3: FeedbackBoard - keeps all feedback (application scope) and
 * builds the summary for each faculty.
 */
package bean;

import java.util.*;

public class FeedbackBoard {
    private final List<String> facultyNames = Arrays.asList(
            "Dr. A. Sen", "Prof. R. Mehta", "Dr. S. Iyer", "Prof. K. Banerjee");
    private final List<FeedbackBean> feedbacks = new ArrayList<>();

    public List<String> getFacultyNames() { return facultyNames; }

    /** Stores a valid feedback. Returns true if saved. */
    public synchronized boolean addFeedback(FeedbackBean fb) {
        if (!fb.getErrorMessage().isEmpty() || !facultyNames.contains(fb.getFaculty())) {
            return false;
        }
        feedbacks.add(fb);
        return true;
    }

    /** One summary row per faculty. */
    public synchronized List<FacultySummary> getSummaries() {
        List<FacultySummary> result = new ArrayList<>();
        for (String f : facultyNames) {
            int n = 0;
            double t = 0, k = 0, c = 0, p = 0;
            for (FeedbackBean fb : feedbacks) {
                if (fb.getFaculty().equals(f)) {
                    n++;
                    t += fb.getTeaching();
                    k += fb.getKnowledge();
                    c += fb.getCommunication();
                    p += fb.getPunctuality();
                }
            }
            result.add(new FacultySummary(f, n, t, k, c, p));
        }
        return result;
    }
}
