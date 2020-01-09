package servlets.rooms;

import exceptions.ModelNotFoundException;
import models.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/threads/pk")
public class RoomPKSearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long pk = Long.parseLong(request.getParameter("pk"));
        Room room;
        try {
            room = Room.find(pk);
            request.setAttribute("search", String.valueOf(pk));

            List<Room> rooms = new ArrayList<>();
            rooms.add(room);

            request.setAttribute("rooms", rooms);
            request.getRequestDispatcher("/WEB-INF/jsp/views/rooms/search-room-list.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ModelNotFoundException e) {
            request.setAttribute("error", e);
            request.getRequestDispatcher("/threads/pk-search.jsp").forward(request, response);
        }
    }
}
