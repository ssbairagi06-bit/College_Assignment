import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Assignment 3: Personalized Greeting using Cookies */
@WebServlet(name = "Assignment3_Sudhangsu", urlPatterns = {"/Assignment3_Sudhangsu"})
public class Assignment3_Sudhangsu extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        // ---- SAVE NAME in cookie ----
        if ("save".equals(action)) {
            String name = request.getParameter("name");
            if (name != null && !name.trim().isEmpty()) {
                // encode so spaces are allowed in cookie value
                Cookie c = new Cookie("userName", URLEncoder.encode(name.trim(), "UTF-8"));
                c.setMaxAge(7 * 24 * 60 * 60);   // expires after 7 days
                response.addCookie(c);
            }
            response.sendRedirect("Assignment3_Sudhangsu");
            return;
        }

        // ---- FORGET ME: delete cookie ----
        if ("forget".equals(action)) {
            Cookie c = new Cookie("userName", "");
            c.setMaxAge(0);                      // 0 = delete immediately
            response.addCookie(c);
            response.sendRedirect("Assignment3_Sudhangsu");
            return;
        }

        // ---- LOOK for the cookie ----
        String userName = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("userName") && !c.getValue().isEmpty()) {
                    userName = URLDecoder.decode(c.getValue(), "UTF-8");
                }
            }
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body><h2>Book Haven</h2>");
            if (userName != null) {
                out.println("<h3>Welcome back, " + userName + "!</h3>");
                out.println("<a href='Assignment3_Sudhangsu?action=forget'>Forget Me</a>");
            } else {
                out.println("<form action='Assignment3_Sudhangsu' method='post'>");
                out.println("<input type='hidden' name='action' value='save'>");
                out.println("Enter your name: <input type='text' name='name'> ");
                out.println("<input type='submit' value='Submit'></form>");
            }
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
