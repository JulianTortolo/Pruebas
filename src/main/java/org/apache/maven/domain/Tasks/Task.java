package org.apache.maven.domain.Tasks;

import org.apache.maven.domain.Tasks.Users.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Table(name="tasks")
@Entity
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(max = 50)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(max = 200)
    @Column(name = "description")
    private String description;

    public void setUser(User user) {
        this.user = user;
    }

    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @NotNull
    @Column(name = "createDate")
    private Date createDate;

    public Task(int id, String name, String description, User user, Date createDate){
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
        this.createDate = createDate;
    }

    public  Task(){}

    public Task setId(int id){
        this.id = id;
        return this;
    }

    public int getId(){
        return  id;
    }

    public Task setName(String name){
        this.name = name;
        return this;
    }

    public String getName(){
        return name;
    }

    public Task setDescription(String description){
        this.description = description;
        return this;
    }

    public String getDescription(){
        return description;
    }

    public Task setCreateDate(Date createDate){
        this.createDate = createDate;
        return this;
    }

    public Date getCreateDate(){
        return  createDate;
    }

    public User getUser(){
        return  user;
    }

}
