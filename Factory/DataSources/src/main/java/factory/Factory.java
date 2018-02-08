package factory;

import domain.User;

import java.util.List;

public interface Factory {

    List getAllUsers();

    User getUserById(int id);
}
