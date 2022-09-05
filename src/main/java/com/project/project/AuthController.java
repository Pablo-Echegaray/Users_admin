package com.project.project;

import com.project.project.dao.UserDao;
import com.project.project.models.User;
import com.project.project.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user){
        User userLoged = userDao.getUserByCredentials(user);

        if(userLoged != null){

            String tokenJwt = jwtUtil.create(String.valueOf(userLoged.getId()), userLoged.getEmail());
            return tokenJwt;
        }
        return "FAIL";
    }

}
