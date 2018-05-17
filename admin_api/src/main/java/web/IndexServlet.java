package web;

import exception.EmptyRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.SendService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет для получения названия города
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private final Logger log = LoggerFactory.getLogger(SendService.class);

    @Inject
    private SendService sendService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response);
        String city = request.getParameter("city");
        try {
            sendService.sendMassage(city);
        } catch (EmptyRequestException e) {
            log.info("Field \"City\" is empty", e);
        }
    }
}
