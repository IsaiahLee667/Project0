package Services;

import Entity.Account;
import Entity.User;

public interface UserServices {
    User loginCheck (String username, String password);

    User createAccount(User user);


}
