package org.smart4j.framework.Helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库操作助手类
 * Created by nanca on 12/30/2017.
 */
public final class DatabaseHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);

    // 数据库配置
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/demo";
    private static final String username = "root";
    private static final String password = "root";

    // 定义一个用于放置数据库连接的局部线程变量（使每个线程都拥有自己的连接）
    private static ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<Connection>();

    // 获取连接
    public static Connection getConnection() {
        Connection conn = CONNECTION_HOLDER.get();
        try {
            if (conn == null) {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CONNECTION_HOLDER.set(conn);
        }
        return conn;
    }

    // 关闭连接
    public static void closeConnection() {
        Connection conn = CONNECTION_HOLDER.get();
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CONNECTION_HOLDER.remove();
        }
    }

    /**
     * 开启事务
     */
    public static void beginTransaction() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                LOGGER.error("begin transaction failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_HOLDER.set(conn);
            }
        }
    }

    /**
     * 提交事务
     */
    public static void commitTransaction() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.commit();
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("commit transaction failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }

    /**
     * 回滚事务
     */
    public static void rollbackTransaction() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.rollback();
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("rollback transaction failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }
}
