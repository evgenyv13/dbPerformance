import faker.FakeData;
import user.UserDao;
import user.UserEntity;


public class Main {

    public static void main(String[] args) {
        UserDao userDao = UserDao.getInstance();
        long startTime, endTime, totalTime;

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            UserEntity userEntity = FakeData.generateFakeUser();
            userDao.createUser(userEntity);
        }
        endTime = System.currentTimeMillis();
        totalTime=endTime-startTime;

        System.out.println(totalTime);
    }
}
