package businessLogic;

import access.UserAccess;
import businessLogic.validators.*;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserBLL {

    private List<Validator<User>> validators;
    private UserAccess userAccess;

    public UserBLL() {
        validators = new ArrayList<Validator<User>>();
        validators.add(new NameValidator());
        validators.add(new CNPValidator());
        validators.add(new IdCardValidator());
        validators.add(new UsernameValidator());
        userAccess = new UserAccess();
    }

    public User findUserByUsername(String username) {
        User user = userAccess.findUserByUsername(username).get(0);
        if (user == null) throw new NoSuchElementException("Username " + username + " not found.");
        return user;
    }

    public User findUserByCNP(String cnp) {
        User user = userAccess.findUserByCNP(cnp).get(0);
        if (user == null) throw new NoSuchElementException("CNP " + cnp + " not found.");
        return user;
    }

    public User findUserByIdCard(String idCard) {
        User user = userAccess.findUserByIdCard(idCard).get(0);
        if (user == null) throw new NoSuchElementException("ID Card " + idCard + " not found.");
        return user;
    }

    public List<User> findAllClientsWODiscount() {
        List<User> users = userAccess.findAllClientsWODiscount();
        if (users == null || users.size() == 0) throw new NoSuchElementException("No client in the DB.");
        return users;
    }

    public List<User> findAllClinetsWDiscount() {
        List<User> users = userAccess.findAllClientsWDiscount();
        if (users == null || users.size() == 0) throw new NoSuchElementException("No client in the DB.");
        return users;
    }

    public List<User> findAllClients() {
        List<User> usersWOD = findAllClientsWODiscount();
        List<User> usersWD  = findAllClinetsWDiscount();
        for(int i = 0; i < usersWD.size(); i ++) {
            usersWOD.add(usersWD.get(i));
        }
        return usersWOD;
    }

    public User findUserById(int id) {
        User user = userAccess.findUserById(id).get(0);
        if (user == null) throw new NoSuchElementException("client_id " + Integer.toString(id) + " not found.");
        return user;
    }

    public List<User> findAllUsers() {
        List<User> users = userAccess.findAllUsers();
        if (users == null || users.size() == 0) throw new NoSuchElementException("No users in the DB.");
        return users;
    }

    public void insertUser(User user) {
        for (Validator<User> validator : validators) {
            validator.validate(user);
        }
        userAccess.insert(user);
    }

    public void updateUser(User userOld, User user) {
        for (Validator<User> validator : validators) {
            validator.validate(user);
        }
        //User userOld = findUserById(user.getUser_id());
        userAccess.update(userOld, user);
    }

    public void deleteUser(User user) {
        userAccess.delete(user);
    }

    public void deleteUserById(int id) {
        userAccess.deleteById(id);
    }

    //Maybe cascade the deletes and updates??

}
