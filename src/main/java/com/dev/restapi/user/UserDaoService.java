package com.dev.restapi.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
    private static List<User> users=new ArrayList<>();
    private static int userIdGenerator=1;
    static{
        users.add(new User(userIdGenerator++,"Mino",LocalDate.now().minusYears(10)));
        users.add(new User(userIdGenerator++,"Luffy",LocalDate.now().minusYears(10)));
        users.add(new User(userIdGenerator++,"Ichigo",LocalDate.now().minusYears(10)));
    }
    public List<User> findAllUsers(){
        return users;
    }
    
    
    public User findOne(int id){
        Predicate<? super User> predicate=user-> user.getId()==id;
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User createUser(User user){
        user.setId(userIdGenerator++);
        users.add(user);
        return user;
    }

    public void deleteById(int id){
        Predicate<? super User> predicate =user->user.getId()==id;
        users.removeIf(predicate);
    }
}
