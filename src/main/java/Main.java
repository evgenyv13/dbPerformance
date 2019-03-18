import faker.FakeData;
import user.UserDao;
import user.UserEntity;

import java.sql.SQLException;


public class Main {

    public static void main(String[] args) {
        UserDao userDao = UserDao.getInstance();
        long startTime, endTime, totalTime;
        /* not optimize data */
        startTime = System.currentTimeMillis();
        try {
            userDao.fillTable(1000000);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        totalTime=endTime-startTime;

        System.out.println("total time fillTable = "+totalTime);

        /* optimize data */
        startTime = System.currentTimeMillis();
        try {
            userDao.fillTableOptimaze(1000000);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        totalTime=endTime-startTime;
        /*  results */


        System.out.println("total time fillTableOptimaze = "+totalTime);
    }
}
