package servlets.talks;

import exceptions.ModelNotFoundException;
import models.Message;
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

@WebServlet("/messages")
public class MessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        long threadId = Long.parseLong(request.getParameter("thread"));

        // todo Viewへエラー表示

        // エスケープ処理
        if (message != null) {
            message = StringEscapeUtility.escape(message);
        } else {
            throw new IOException("メッセージが入力されていません。");
        }

        Talker talker;
        try {
            talker = Talker.findBySessionId(request.getSession(false).getId());
        } catch (SQLException | ModelNotFoundException e) {
            throw new ServletException("ユーザ情報の取得に失敗しました。" + e.getMessage());
        }

        try {
            Message.create(talker.getId(), threadId, message);
        } catch (ModelNotFoundException e) {
            throw new ServletException("スレッドが見つかりません");
        } catch (SQLException e) {
            throw new ServletException("メッセージの投稿に失敗しました。");
        }

        response.sendRedirect("/threnta/rooms?thread=" + threadId);
    }
}
