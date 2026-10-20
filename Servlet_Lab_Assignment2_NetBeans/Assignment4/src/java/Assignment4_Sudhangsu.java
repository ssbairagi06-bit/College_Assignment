import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Assignment 4: E-Commerce Cart using Hidden Form Fields */
@WebServlet(name = "Assignment4_Sudhangsu", urlPatterns = {"/Assignment4_Sudhangsu"})
public class Assignment4_Sudhangsu extends HttpServlet {

    // Item details (price kept on the server)
    private final String[] ids    = {"book", "gadget", "apparel"};
    private final String[] items  = {"Java Book", "Wireless Mouse", "T-Shirt"};
    private final int[]    prices = {450, 700, 400};

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String step = request.getParameter("step");

        // read quantities (from the form OR from hidden fields)
        int[] qty = new int[ids.length];
        int count = 0;
        for (int i = 0; i < ids.length; i++) {
            try {
                qty[i] = Integer.parseInt(request.getParameter(ids[i]));
            } catch (Exception e) {
                qty[i] = 0;                       // missing or invalid -> 0
            }
            if (qty[i] < 0) qty[i] = 0;
            count += qty[i];
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");

            if (count == 0) {                     // validation
                out.println("<h3 style='color:red'>Your cart is empty. Select at least one item.</h3>");
                out.println("<a href='index.html'>Back to shop</a></body></html>");
                return;
            }

            if ("checkout".equals(step)) {
                // ---- FINAL BILL ----
                int total = 0;
                out.println("<h2>Final Bill</h2><table border='1' cellpadding='6'>");
                out.println("<tr><th>Item</th><th>Price</th><th>Qty</th><th>Amount</th></tr>");
                for (int i = 0; i < ids.length; i++) {
                    if (qty[i] > 0) {
                        int amount = prices[i] * qty[i];
                        total += amount;
                        out.println("<tr><td>" + items[i] + "</td><td>" + prices[i] + "</td><td>"
                                + qty[i] + "</td><td>" + amount + "</td></tr>");
                    }
                }
                out.println("</table>");
                out.println("<h3>Total Bill: Rs. " + total + "</h3>");
                out.println("<p>Purchase confirmed. Thank you!</p>");
                out.println("<a href='index.html'>Continue shopping</a>");
            } else {
                // ---- CART SUMMARY (data passed forward in hidden fields) ----
                out.println("<h2>Cart Summary</h2><table border='1' cellpadding='6'>");
                out.println("<tr><th>Item</th><th>Price</th><th>Qty</th></tr>");
                out.println("<form action='Assignment4_Sudhangsu' method='post'>");
                out.println("<input type='hidden' name='step' value='checkout'>");
                for (int i = 0; i < ids.length; i++) {
                    if (qty[i] > 0) {
                        out.println("<tr><td>" + items[i] + "</td><td>" + prices[i]
                                + "</td><td>" + qty[i] + "</td></tr>");
                    }
                    // hidden field carries the quantity to the next page
                    out.println("<input type='hidden' name='" + ids[i] + "' value='" + qty[i] + "'>");
                }
                out.println("</table><br>");
                out.println("<input type='submit' value='Confirm Purchase'></form>");
                out.println("<a href='index.html'>Modify selection</a>");
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
