package org.apache.maven.model;

import org.apache.maven.clients.UsersAPI;
import org.apache.maven.domain.Tasks.Task;

import java.util.Date;

public class TaskModel {

    private int id;
    private String name;
    private String description;
    private Date createDate;
    private UserModel user;


    public TaskModel(Task task){
        if(task != null) {
            this.id = task.getId();
            this.name = task.getName();
            this.description = task.getDescription();
            this.createDate = task.getCreateDate();
            this.user = UsersAPI.GetUser(task.getUser().getId());
        }
    }

    public TaskModel(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserModel getUsers() {
        return user;
    }

    public void setUsers(UserModel users) {
        this.user = users;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
