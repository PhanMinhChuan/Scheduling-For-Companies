package sterben.scheduling.demo.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "user")
public class User {
    @Id
    private long id;

    @Field(value = "username")
    //@Column(name = "username", length = 32, nullable = false)
    private String username;

    @Field(value = "password")
    //@Column(name = "password", length = 100, nullable = false)
    private String password;

    @Field(value = "idJob")
    private List<Long> idJob;

    @Field(value = "role")
    private String role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Long> getIdJob() {
        return idJob;
    }

    public void setIdJob(List<Long> idJob) {
        this.idJob = idJob;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(long id, String username, String password, List<Long> idJob, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.idJob = idJob;
        this.role = role;
    }

    public User() {
    }
}
