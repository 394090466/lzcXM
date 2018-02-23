package MyServlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.OutputStream;

public class initServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html");
        HttpSession hs = request.getSession();

        Cookie[] cookies = request.getCookies();
        Cookie ck = new Cookie("lzc","123");
        ck.setPath("/");
        ck.setMaxAge(60*60*24*180);
        response.addCookie(ck);


        String str = getInitParameter("abc");
        OutputStream os = response.getOutputStream();
        os.write(str.getBytes());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
