package org.apache.maven.domain.Tasks.Users;

import org.apache.maven.domain.Tasks.Task;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(max = 50)
    @Column(name = "name")
    private String name;

    public User(int id, String name){
        this.id = id;
        this.name = name;
    }

    public User(){}

    public User setId(int id){
        this.id = id;
        return this;
    }

    public int getId(){
        return id;
    }

    public User setName(String name){
        this.name = name;
        return this;
    }

    public String getname(){
        return  name;
    }
}
