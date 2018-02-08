package factory.transformer;

import domain.User;
import factory.Factory;

import java.util.ArrayList;
import java.util.List;

public class FactoryDataToUserTransformer {

    public User trnasformByUserId(Factory factory, int id) {
        User user = new User();
        user.setId(factory.getUserById(id).getId());
        user.setName(factory.getUserById(id).getName());
        user.setAge(factory.getUserById(id).getAge());
        return user;
    }

    public List transformAll(Factory factory) {
        return factory.getAllUsers();
    }
}
