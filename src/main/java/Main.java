import faker.FakeData;
import user.UserEntity;


public class Main {

    public static void main(String[] args) {
        UserEntity userEntity = FakeData.generateFakeUser();

        System.out.println(userEntity);
    }
}
