package com.dev.restapi.user;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name="user_details")
public class User {
    @Id
    @GeneratedValue
    private int id;
    @Size(min=2)
    @JsonProperty("PersonName")
    private String name;
    @Past
    @JsonProperty("D.O.B")
    private LocalDate dateOfBirth;
    
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Post> post;

    public User(){

    }

    public User(int id, String name, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }
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
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public List<Post> getPost(){
        return post;
    }
    public void setPost(List<Post> post){
        this.post=post;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + "]";
    }
}
