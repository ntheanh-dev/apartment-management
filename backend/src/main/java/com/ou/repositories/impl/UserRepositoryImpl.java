package com.ou.repositories.impl;

import com.ou.dto.request.ChangePasswordRequest;
import com.ou.exception.AppException;
import com.ou.exception.ErrorCode;
import com.ou.pojo.Room;
import com.ou.pojo.User;
import com.ou.repositories.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Objects;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE username = :username");
        q.setParameter("username", username);
        if (q.getResultList().isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        return (User) q.getSingleResult();
    }

    @Override
    public User addUser(User user) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(user);
        return user;
    }

    @Override
    public User authUser(String username, String password) {
        User u = this.getUserByUsername(username);
        if(!bCryptPasswordEncoder.matches(password, u.getPassword())){
            throw new AppException(ErrorCode.INCORRECT_PASSWORD);
        }
        return u;
    }

    @Override
    public boolean userExistsByUsername(String username) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            Query q = s.createQuery("FROM User WHERE username = :username");
            q.setParameter("username", username);
            return !q.getResultList().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void changePassword(User user) {
        Session s = this.factory.getObject().getCurrentSession();
        s.update(user);
    }

    @Override
    public void deleteUser(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        User u = s.get(User.class, id);
        s.delete(u);
    }
}
