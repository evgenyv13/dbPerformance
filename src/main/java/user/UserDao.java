package user;

import java.sql.*;
import java.util.List;

public class UserDao {
    private static volatile UserDao instance;

    public static UserDao getInstance() {
        UserDao localInstance = instance;
        if (localInstance == null) {
            synchronized (UserDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new UserDao();
                }
            }
        }
        return localInstance;
    }

    public int createUser(UserEntity userEntity){
        String sqlreq = "INSERT INTO users (name,phone) VALUES (?,?)";

        Connection con = null;
        PreparedStatement stmt = null;
        int id=-1;
        try{
            con = DBConnection.getConnection();
            stmt = con.prepareStatement(sqlreq, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,userEntity.name);
            stmt.setString(2,userEntity.phone);

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                id = rs.getInt(1);
            }

        }catch(Exception e){
            System.out.println(e);
        }finally{
            closeConnection(con);
            closePreparedStatement(stmt);
        }

        return id;
    }

    private void closeConnection(Connection con){
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    private void closePreparedStatement(PreparedStatement stmt){
        try {
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    private void closeCallableStatement(CallableStatement stmt){
        try {
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
