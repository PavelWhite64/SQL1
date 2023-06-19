package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static AuthInfo getAuthInfoTest() {
        return new AuthInfo("vasya", "qwerty123");
    }

    private static String getRandomLogin() {
        return faker.name().username();
    }

    private static String getRandomPassword() {
        return faker.internet().password();
    }

    public static AuthInfo genRandomUser() {
        return new AuthInfo(getRandomLogin(), getRandomPassword());
    }

    public static VerificationCode genRandomVerCode() {
        return new VerificationCode(faker.numerify("######"));
    }
}