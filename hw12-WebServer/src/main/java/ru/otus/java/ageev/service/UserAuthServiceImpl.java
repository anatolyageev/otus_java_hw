package ru.otus.java.ageev.service;



public class UserAuthServiceImpl implements UserAuthService {

    private final DBServiceClient userDao;

    public UserAuthServiceImpl(DBServiceClient userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean authenticate(String login, String password) {
        return userDao.findByLogin(login)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

}
