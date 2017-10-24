package cr.rupel.discord.channellog;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Rupel on 11/08/2017.
 *
 * con = ds.getConnection(username, password);
 * //use connection
 * con.close();
 */
public class DBconnector {

    String username, pw;
    DataSource ds;

    //Opens database connection with given parameters
    public DBconnector(String username, String password) {
        this.username = username;
        pw = password;
        init();
        //createTables(); //TODO: Workaround because of server, delete
    }

    //Opens an existing database
    private void init() {
        try {
            /*InitialContext ctx = new InitialContext();

            PGSimpleDataSource ds = new PGSimpleDataSource();
            ds.setUrl("jdbc:postgresql://localhost:5432/channelogger");
            ds.setUser(username);
            ds.setPassword(pw);

            ctx.createSubcontext("java:comp");
            ctx.createSubcontext("java:comp/env");
            ctx.createSubcontext("java:comp/env/jdbc");*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Creates all necessary tables
    private void createTables() {
        try {
            Connection con = ds.getConnection(username, pw);
            Statement stmt = con.createStatement();
            String sql =
                    "CREATE TABLE IF NOT EXISTS servers (" +
                            "serv_id SERIAL primary key," +
                            "dc_server_id TEXT NOT NULL, " +
                            "owner varchar(50) NOT NULL, " +
                            "name varchar(50) NOT NULL);" +

                    "CREATE TABLE IF NOT EXISTS channels (" +
                            "chan_id SERIAL primary key, " +
                            "dc_channel_id text NOT NULL, " +
                            "name varchar(50) NOT NULL, " +
                            "fk_serv_id INT NOT NULL references servers(serv_id));" +

                    "CREATE TABLE IF NOT EXISTS users (" +
                            "user_id SERIAL primary key, " +
                            "dc_user_id TEXT NOT NULL, " +
                            "name VARCHAR(50) NOT NULL);" +

                    "CREATE TABLE IF NOT EXISTS users_channels (" +
                            "fk_chan_id INT NULL references channels(chan_id)," +
                            "fk_user_id INT NULL references users(user_id)," +
                            "timestamp TIMESTAMP NOT NULL," +
                            "joined BOOLEAN NULL," +
                            "PRIMARY KEY(fk_chan_id, fk_user_id));";

            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("RUPDEB: <" + this.getClass().getName() + "> Tables created successfully");
        } catch( Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }


    public void query(String sql) {
        try {
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}














