package Users;

import java.util.Date;
public class User {
    String firstname, lastname, email, password, tryPassword;
    Date birthdate;

    public User(String firstname, String lastname, String email, String password, String tryPassword, Date birthdate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.tryPassword = tryPassword;
        this.birthdate = birthdate;
    }
}
