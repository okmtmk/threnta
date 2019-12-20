package models;

import java.sql.*;
import java.util.Calendar;

abstract class Model {
    static final String ID = "ID";
    static final String CREATED_AT = "CREATED_AT";
    static final String UPDATED_AT = "UPDATED_AT";

    protected long id;
    protected Timestamp createdAt;
    protected Timestamp updatedAt;

    public Model(long id, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /*
    Properties
     */

    public long getId() {
        return id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /*
    Static methods
     */

    /**
     * コネクションの取得
     *
     * @return Connection
     * @throws SQLException SQLエラー
     */
    protected static Connection getConnection() throws SQLException {
        String driver = "jdbc:derby:/Users/okamoto/Repositories/threnta/database/threnta;";
        return DriverManager.getConnection(driver, "okmt", "threnta");
    }

    /**
     * テーブルに最後に追加されたレコードのIDを取得する
     *
     * @param table     テーブル名
     * @param statement レコードを追加するときに使ったStatement
     * @return 最後に追加されたレコードのID
     * @throws SQLException SQLエラー
     */
    protected static long getLastInsertedId(String table, Statement statement) throws SQLException {
        ResultSet set = statement.executeQuery("select IDENTITY_VAL_LOCAL() as ID from " + table);
        set.next();

        return set.getLong("ID");
    }

    /**
     * 現在のTimestampを取得
     *
     * @return 現在時
     */
    protected static Timestamp now() {
        return new Timestamp(Calendar.getInstance().getTime().getTime());
    }
}
