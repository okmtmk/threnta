package servlets;

import exceptions.ModelNotFoundException;
import models.Room;
import models.Talker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/threads/user")
public class TalkerRelatedRoomServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Talker talker;
        List<Room> createdRooms;
        List<Room> talkedRooms;

        try {
            talker = Talker.findBySessionId(request.getSession(false).getId());
        } catch (SQLException | ModelNotFoundException e) {
            throw new ServletException(e);
        }

        try {
            createdRooms = Room.getRoomsByCreatedTalkerId(talker.getId());
            talkedRooms = Room.getRoomsByTalkedTalkerId(talker.getId());
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        request.setAttribute("created-rooms", createdRooms);
        request.setAttribute("talked-rooms", talkedRooms);

        request.getRequestDispatcher("/WEB-INF/jsp/views/rooms/talker-room-list.jsp").forward(request, response);
    }
}
