package servlets;

import models.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class RoomServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Room> rooms;
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            rooms = Room.index();
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("error", e);
            request.getRequestDispatcher("/WEB-INF/jsp/rooms/room-list.jsp").forward(request, response);
            return;
        }

        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("/WEB-INF/jsp/rooms/room-list.jsp").forward(request, response);
    }
}
