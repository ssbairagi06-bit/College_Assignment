import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** Assignment 1: Secure Student Login and Dashboard using Session */
@WebServlet(name = "Assignment1_Sudhangsu", urlPatterns = {"/Assignment1_Sudhangsu"})
public class Assignment1_Sudhangsu extends HttpServlet {

    // Predefined credentials
    private final String[] usernames = {"rahul", "priya"};
    private final String[] passwords = {"pass123", "abc456"};
    private final String[] names     = {"Rahul Sharma", "Priya Das"};

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        // ---- LOGIN ----
        if ("login".equals(action)) {
            String user = request.getParameter("username");
            String pass = request.getParameter("password");

            // server-side validation
            if (user == null || pass == null || user.isEmpty() || pass.isEmpty()) {
                showError(response, "Username and password are required.");
                return;
            }
            for (int i = 0; i < usernames.length; i++) {
                if (usernames[i].equals(user) && passwords[i].equals(pass)) {
                    HttpSession session = request.getSession();      // create session
                    session.setAttribute("studentName", names[i]);   // store name
                    session.setAttribute("loginTime", new Date());   // store time
                    response.sendRedirect("Assignment1_Sudhangsu?action=dashboard");
                    return;
                }
            }
            showError(response, "Invalid credentials. Try again.");
            return;
        }

        // ---- LOGOUT ----
        if ("logout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();            // end the session
            }
            response.sendRedirect("index.html");
            return;
        }

        // ---- DASHBOARD ----
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("studentName") == null) {
            response.sendRedirect("index.html"); // not logged in
            return;
        }
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h2>Welcome " + session.getAttribute("studentName") + "</h2>");
            out.println("<p>Login time: " + session.getAttribute("loginTime") + "</p>");
            out.println("<p>Current date/time: " + new Date() + "</p>");
            out.println("<a href='Assignment1_Sudhangsu?action=logout'>Logout</a>");
            out.println("</body></html>");
        }
    }

    private void showError(HttpServletResponse response, String msg) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h3 style='color:red'>" + msg + "</h3>");
            out.println("<a href='index.html'>Back to Login</a>");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
