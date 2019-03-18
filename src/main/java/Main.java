import faker.FakeData;
import user.UserDao;
import user.UserEntity;

import java.sql.SQLException;


public class Main {

    public static void main(String[] args) {
        UserDao userDao = UserDao.getInstance();
        long startTime, endTime, totalTime;
        /*-----------------------------------------------------------------*/
/*        startTime = System.currentTimeMillis();
        try {
            userDao.fillTable(1000000);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        totalTime=endTime-startTime;

        System.out.println("total time fillTable = "+totalTime);*/

        /*-----------------------------------------------------------------*/

        startTime = System.currentTimeMillis();
        try {
            userDao.fillTableOptimazeBatch(1000000);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        totalTime=endTime-startTime;

        System.out.println("total time fillTableOptimaze = "+totalTime);

        /*-----------------------------------------------------------------*/

/*        startTime = System.currentTimeMillis();
        try {
            userDao.fillTableOptimazeCommits(1000000);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        totalTime=endTime-startTime;

        System.out.println("total time fillTableOptimazeCommits = " + totalTime);*/
    }
}
