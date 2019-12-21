package servlets;

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

@WebServlet("/rooms/create")
public class RoomCreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

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
        } catch (SQLException e) {
            throw new ServletException("ユーザ情報の取得に失敗しました。" + request.getSession(false).getId());
        }

        try {
            Room.create(talker.getId(), name, description);
        } catch (SQLException e) {
            throw new ServletException("スレッドの作成に失敗しました。");
        }

        response.sendRedirect("/threnta");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/rooms/room-create-form.jsp").forward(request, response);
    }
}
