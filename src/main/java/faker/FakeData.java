package faker;

import com.github.javafaker.Faker;
import user.UserEntity;

public class FakeData {
    private static Faker faker = new Faker();

    public static UserEntity generateFakeUser(){
        return new UserEntity(faker.firstName(),faker.phoneNumber());
    }
}
