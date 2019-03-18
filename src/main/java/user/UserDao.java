package user;

import faker.FakeData;

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

    public void createUser(UserEntity userEntity) {
        String sqlreq = "INSERT INTO users (name,phone) VALUES (?,?)";

        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DBConnection.getConnection();
            stmt = con.prepareStatement(sqlreq);
            stmt.setString(1, userEntity.name);
            stmt.setString(2, userEntity.phone);

            stmt.executeQuery();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnection(con);
            closePreparedStatement(stmt);
        }
    }


    private void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void closePreparedStatement(PreparedStatement stmt) {
        try {
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void fillTable(int ammountOfRows) throws SQLException {
        Connection con = DBConnection.getConnection();
        String sqlreq = "INSERT INTO users (name,phone) VALUES (?,?)";
        PreparedStatement stmt = con.prepareStatement(sqlreq);


        for (int i = 0; i < ammountOfRows; i++) {
            UserEntity userEntity = FakeData.generateFakeUser();
            stmt.setString(1, userEntity.name);
            stmt.setString(2, userEntity.phone);

            stmt.executeUpdate();
        }

        closeConnection(con);
        closePreparedStatement(stmt);
    }


    public void fillTableOptimazeBatch(int ammountOfRows) throws SQLException {
        Connection con = DBConnection.getConnection();
        con.setAutoCommit(false);
        String sqlreq = "INSERT INTO users (name,phone) VALUES (?,?)";
        PreparedStatement stmt = con.prepareStatement(sqlreq);
        int batchAmmount = 0 ;


        for (int i=0;i<ammountOfRows;i++){

            UserEntity userEntity = FakeData.generateFakeUser();
            stmt.setString(1,userEntity.name);
            stmt.setString(2,userEntity.phone);
            stmt.addBatch();

            batchAmmount++;
            if(batchAmmount==1000){
                batchAmmount=0;
                stmt.executeBatch();
            }

        }

        stmt.executeBatch();
        con.commit();

        closeConnection(con);
        closePreparedStatement(stmt);

    }


    public void fillTableOptimazeCommits(int ammountOfRows) throws SQLException {
        Connection con = DBConnection.getConnection();
        con.setAutoCommit(false);
        String sqlreq = "INSERT INTO users (name,phone) VALUES (?,?)";
        PreparedStatement stmt = con.prepareStatement(sqlreq);
        int commitAmmount = 0 ;


        for (int i = 0; i < ammountOfRows; i++) {
            UserEntity userEntity = FakeData.generateFakeUser();
            stmt.setString(1, userEntity.name);
            stmt.setString(2, userEntity.phone);
            stmt.executeUpdate();

            commitAmmount++;
            if(commitAmmount==5000){
                commitAmmount=0;
                con.commit();
            }
        }

        con.commit();
        closeConnection(con);
        closePreparedStatement(stmt);

    }

}
