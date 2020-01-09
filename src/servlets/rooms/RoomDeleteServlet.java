package servlets.rooms;

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

@WebServlet("/threads/delete")
public class RoomDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String thread = request.getParameter("thread");
        long roomId = Long.parseLong(thread);

        Talker talker;
        Room room;

        try {
            talker = Talker.findBySessionId(request.getSession(false).getId());
            room = Room.find(roomId);

            if (talker.getId() != room.getCreateTalkerId()) {
                throw new ServletException("権限がありません");
            } else {
                room.delete();
                response.sendRedirect("/threnta/threads/user");
            }
        } catch (SQLException | ModelNotFoundException e) {
            throw new ServletException(e);
        }
    }
}
