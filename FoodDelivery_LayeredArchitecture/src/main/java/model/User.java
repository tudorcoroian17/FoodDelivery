package model;

public class User {

    private int user_id;
    private int user_type;
    private String username;
    private String password;
    private String name;
    private String id_card;
    private String cnp;
    private String address;
    private int deleted;

    public User () {

    }

    public User(int user_type, String username, String password, String name, String id_card, String cnp, String address) {
        this.user_type = user_type;
        this.username = username;
        this.password = password;
        this.name = name;
        this.id_card = id_card;
        this.cnp = cnp;
        this.address = address;
        this.deleted = 0;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
