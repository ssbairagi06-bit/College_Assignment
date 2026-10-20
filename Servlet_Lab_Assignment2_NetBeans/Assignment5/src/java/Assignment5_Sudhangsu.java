import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Assignment 5: Feedback Management using URL Rewriting */
@WebServlet(name = "Assignment5_Sudhangsu", urlPatterns = {"/Assignment5_Sudhangsu"})
public class Assignment5_Sudhangsu extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String name = request.getParameter("name");
        String message = request.getParameter("message");

        // server-side validation
        boolean valid = name != null && !name.trim().isEmpty()
                     && message != null && !message.trim().isEmpty();

        // ---- SUBMIT: rewrite URL to carry the data ----
        if ("submit".equals(action)) {
            if (!valid) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("<html><body>");
                    out.println("<h3 style='color:red'>Name and feedback are required.</h3>");
                    out.println("<a href='index.html'>Go back</a></body></html>");
                }
                return;
            }
            // URL rewriting: data appended to the URL
            String url = "Assignment5_Sudhangsu?name=" + URLEncoder.encode(name, "UTF-8")
                       + "&message=" + URLEncoder.encode(message, "UTF-8");
            response.sendRedirect(response.encodeRedirectURL(url));
            return;
        }

        // ---- CONFIRMATION: read data from the URL ----
        if (!valid) {
            response.sendRedirect("index.html");
            return;
        }
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h3>Thank you, " + name + ", for your valuable feedback: " + message + "</h3>");
            out.println("<a href='index.html'>Submit another</a>");
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
