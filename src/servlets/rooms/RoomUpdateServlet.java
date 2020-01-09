package servlets.rooms;

import exceptions.ModelNotFoundException;
import models.Room;
import models.Talker;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/threads/update")
public class RoomUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        String threadId = request.getParameter("thread");
        long roomId = Long.parseLong(threadId);

        try {
            Talker talker = Talker.findBySessionId(request.getSession(false).getId());
            Room room = Room.find(roomId);

            if (talker.getId() != room.getCreateTalkerId()) {
                throw new ServletException("権限がありません。");
            }else{
                room.setName(name);
                room.setDescription(description);
                room.update();

                response.sendRedirect("/threnta/threads/user");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (ModelNotFoundException e) {
            request.setAttribute("error", e);
            responseRoomList(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String threadId = request.getParameter("thread");
        long roomId = Long.parseLong(threadId);

        try {
            Talker talker = Talker.findBySessionId(request.getSession(false).getId());
            Room room = Room.find(roomId);

            if (talker.getId() != room.getCreateTalkerId()) {
                throw new ServletException("権限がありません。");
            }else{
                request.setAttribute("room", room);
                request.getRequestDispatcher("/WEB-INF/jsp/views/rooms/update.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (ModelNotFoundException e) {
            request.setAttribute("error", e);
            responseRoomList(request, response);
        }
    }

    private void responseRoomList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Room> rooms;
        try {
            rooms = Room.get();

            request.setAttribute("rooms", rooms);
            request.getRequestDispatcher("/WEB-INF/jsp/views/rooms/room-list.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
