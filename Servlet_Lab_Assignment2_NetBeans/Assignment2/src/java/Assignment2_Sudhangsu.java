import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** Assignment 2: Online Event Registration using Session Tracking */
@WebServlet(name = "Assignment2_Sudhangsu", urlPatterns = {"/Assignment2_Sudhangsu"})
public class Assignment2_Sudhangsu extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        // ---- REGISTER: save form data in session ----
        if ("register".equals(action)) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String[] events = request.getParameterValues("events");

            // server-side validation
            if (name == null || name.trim().isEmpty() || email == null
                    || !email.contains("@") || events == null) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("<html><body>");
                    out.println("<h3 style='color:red'>Enter name, valid email and select at least one event.</h3>");
                    out.println("<a href='index.html'>Go back</a></body></html>");
                }
                return;
            }
            session.setAttribute("name", name);
            session.setAttribute("email", email);
            session.setAttribute("events", events);
            session.setAttribute("confirmed", false);
            response.sendRedirect("Assignment2_Sudhangsu?action=view"); // avoids resubmission
            return;
        }

        // ---- CONFIRM ----
        if ("confirm".equals(action)) {
            session.setAttribute("confirmed", true);
            response.sendRedirect("Assignment2_Sudhangsu?action=view");
            return;
        }

        // ---- VIEW: show details from session ----
        if (session.getAttribute("name") == null) {
            response.sendRedirect("index.html");   // nothing registered yet
            return;
        }
        String[] events = (String[]) session.getAttribute("events");
        boolean confirmed = (Boolean) session.getAttribute("confirmed");

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h2>Registration Details</h2>");
            out.println("<p>Name: " + session.getAttribute("name") + "</p>");
            out.println("<p>Email: " + session.getAttribute("email") + "</p>");
            out.println("<p>Events:</p><ul>");
            for (String e : events) {
                out.println("<li>" + e + "</li>");
            }
            out.println("</ul>");
            if (confirmed) {
                out.println("<h3>Registration Confirmed!</h3>");
            } else {
                out.println("<a href='Assignment2_Sudhangsu?action=confirm'>Confirm Registration</a>");
            }
            out.println("<p><a href='index.html'>Edit details</a></p>");
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
