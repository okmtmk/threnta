package servlets;

import exceptions.ModelNotFoundException;
import models.Room;
import models.Talker;
import utils.StringEscapeUtility;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        // todo エラー表示

        // 特殊文字のエスケープ
        if (name != null) {
            name = StringEscapeUtility.escape(name);
        }

        if (description != null) {
            description = StringEscapeUtility.escape(description);
        }

        if (name == null) {
            throw new IOException("nameは必ず必要です。");
        }

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("JDBCドライバの読込に失敗しました。");
        }

        Talker talker;
        try {
            talker = Talker.findBySessionId(request.getSession(false).getId());
        } catch (SQLException | ModelNotFoundException e) {
            throw new ServletException("ユーザ情報の取得に失敗しました。" + request.getSession(false).getId());
        }

        try {
            Room.create(talker.getId(), name, description);
        } catch (SQLException | ModelNotFoundException e) {
            throw new ServletException("スレッドの作成に失敗しました。");
        }

        response.sendRedirect("/threnta");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String threadId = request.getParameter("thread");

        if (threadId != null) {
            // スレッド表示

            try {
                long roomId = Long.parseLong(threadId);
                Room room = Room.find(roomId);

                // todo トーク画面へ遷移
            } catch (SQLException e) {
                request.setAttribute("error", e);
            } catch (ModelNotFoundException | NumberFormatException e) {
                responseRoomList(request, response);
            }
        } else {
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
            request.setAttribute("error", e);
            request.getRequestDispatcher("/WEB-INF/jsp/views/rooms/room-list.jsp").forward(request, response);
        }
    }
}
