package ec.edu.ups.icc.fundamentos01.users.entities;

public class User {
    private int id;
    private String name;
    private String email;
    private String password; // no se expone en la API
    private String createdAt;

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = java.time.LocalDateTime.now().toString();
    }

    public String getEmail() {
        return email;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}
