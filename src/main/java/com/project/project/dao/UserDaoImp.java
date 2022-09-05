package com.project.project.dao;

import com.project.project.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List getUsers() {
        String query = "FROM User";
        List resultList = entityManager.createQuery(query).getResultList();
        return resultList;
    }

    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void createUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserByCredentials(User user){
        String query = "FROM User WHERE email = :email";
        List <User> resultList = entityManager.createQuery(query)
                .setParameter("email", user.getEmail())
                .getResultList();
        if(resultList.isEmpty()){
            return null;
        }
        String passwordHashed = resultList.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, user.getPassword())){
            return resultList.get(0);
        }
        return null;
    }
}
