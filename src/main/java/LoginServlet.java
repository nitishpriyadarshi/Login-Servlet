import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(
        description = "Login page testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {
                // Setting a couple of initial parameter for the servlet
                @WebInitParam(name = "user", value = "Nitish"),
                @WebInitParam(name = "password", value = "Nitish@1994")
        }
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // The user and password are taken from the request
        String userInput = request.getParameter("user");
        String pwdInput = request.getParameter("pwd");

        // The user and password are retrieved from the initial params of the servlet
        String user = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");

        //Using the regex pattern and matcher method to check for userid of the user
        Pattern userPattern = Pattern.compile("^[A-Z]{1}[a-z]{2,}$");
        Matcher userMatcher = userPattern.matcher(userInput);

        //Using the regex pattern and matcher method to check for password of the user
        Pattern pwdPattern = Pattern.compile("((?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&*_]).{8,})");
        Matcher pwdMatcher = pwdPattern.matcher(pwdInput);

        // If the matcher does not matches then we will return it
        if (!userMatcher.matches()) {
            PrintWriter out = response.getWriter();
            out.println("<font color = red> Incorrect UserId Min 3 chars & first letter capital!!!<font>");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            rd.include(request, response);
            return;
        }

        // If the matcher does not matches then we will return it
        if (!pwdMatcher.matches()) {
            PrintWriter out = response.getWriter();
            out.println("<font color = blue> Incorrect Password Enter a valid Password Atleast 8 Elements !!<font>");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            rd.include(request, response);
            return;
        }

        //
        if (user.equals(userInput) && password.equals(pwdInput)) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/LoginSuccess.jsp").forward(request, response);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color = red> Incorrect UserId or Password. Please try again!!<font>");
            rd.include(request, response);
        }
    }
}