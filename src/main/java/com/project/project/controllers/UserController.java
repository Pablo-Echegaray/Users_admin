package com.project.project.controllers;

import com.project.project.dao.UserDao;
import com.project.project.models.User;
import com.project.project.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    // Inyeccion de dependencia
    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuario/{id}")
    public User getUser(@PathVariable Long id){
    User user = new User();
    user.setId(id);
    user.setName("Pablo");
    user.setSurName("Echegaray");
    user.setEmail("pablo@gmail.com");
    user.setPhone("1122334455");
    return user;
    }

    @RequestMapping(value = "api/usuarios")
    public List<User> getUsers(@RequestHeader(value="Authorization") String token){
         if(validateToken(token)){
             return null;
         }
         return userDao.getUsers();
    }

    private boolean validateToken(String token){
        String userId = jwtUtil.getKey(token);
        return userId != null;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void createUser(@RequestBody User user){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);
        userDao.createUser(user);
    }


    @RequestMapping(value = "user1")
    public User edit(){
        User user = new User();
        user.setName("Pablo");
        user.setSurName("Echegaray");
        user.setEmail("pablo@gmail.com");
        user.setPhone("1122334455");
        return user;
    }

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.DELETE)
    public void delete(@RequestHeader(value="Authorization") String token,@PathVariable Long id){
        if(validateToken(token)){
            return;
        }
        userDao.delete(id);
    }
}