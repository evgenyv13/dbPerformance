import faker.FakeData;
import user.UserDao;
import user.UserEntity;


public class Main {

    public static void main(String[] args) {
        UserEntity userEntity = FakeData.generateFakeUser();
        UserDao userDao = UserDao.getInstance();
        userDao.createUser(userEntity);
    }
}
