package user;

import lombok.Data;

@Data
public class UserEntity {
    int id = 0;
    String name;
    String phone;

    public UserEntity(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
