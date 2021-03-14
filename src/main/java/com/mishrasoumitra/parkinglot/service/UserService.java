package com.mishrasoumitra.parkinglot.service;

import com.mishrasoumitra.parkinglot.exceptions.IncorrectPasswordException;
import com.mishrasoumitra.parkinglot.exceptions.UserAlreadyExistsException;
import com.mishrasoumitra.parkinglot.exceptions.UserNotFoundException;
import com.mishrasoumitra.parkinglot.model.User;
import com.mishrasoumitra.parkinglot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;



@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> listAllUser() {
        return userRepository.findAll();
    }

    public void updateUser(User user) throws UserNotFoundException {
        if (!userRepository.existsById(user.getUserName())) {
            throw new UserNotFoundException(user.getUserName());
        }
        user.setPassword(encryptPassword(user.getPassword()));
        userRepository.save(user);
    }

    public void addUser(User user) throws UserAlreadyExistsException {

        if (userRepository.existsById(user.getUserName())) {
            throw new UserAlreadyExistsException(user.getUserName());
        }

        user.setPassword(encryptPassword(user.getPassword()));
        userRepository.save(user);
    }

    private String encryptPassword(String password) {
        String hashText = password;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder sb = new StringBuilder();
            sb.append(no.toString(16));
            while (sb.length()<32)
                sb.append('0');
            hashText = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashText;
    }

    public User getUser(String id) throws UserNotFoundException {

        if(!userRepository.existsById(id))
            throw new UserNotFoundException(id);
        return userRepository.findById(id).get();
    }

    public void deleteUser(String id) throws UserNotFoundException {
        if(!userRepository.existsById(id))
            throw new UserNotFoundException(id);
        userRepository.deleteById(id);
    }

    public String login(String userName, String password) throws UserNotFoundException, IncorrectPasswordException {
        if(!userRepository.existsById(userName))
            throw new UserNotFoundException(userName);
        User user = userRepository.findById(userName).get();
        String storedPassword = user.getPassword();
        String enteredPassword = password;
        String encryptedPassword = encryptPassword(enteredPassword);
        System.out.println(storedPassword+":"+enteredPassword+":"+encryptedPassword);
        if(!encryptPassword(password).equals(user.getPassword())) {
            throw new IncorrectPasswordException();
        }
        else {
            String sessionId = encryptPassword(userName + new Date().getTime());
            user.setSessionId(sessionId);
            userRepository.save(user);
            return user.getSessionId();
        }
    }
}
