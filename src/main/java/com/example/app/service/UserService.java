package com.example.app.service;

import com.example.app.model.Role;
import com.example.app.model.User;
import com.example.app.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserService implements UserDetailsService {


    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    @Autowired
    private RoleService roleService;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format("Приветствую, %s \n" +
                            "Активируйте свою учетную запись по следующей ссылке: \n" +
                            "http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationcode());
            log.info("Отправка письма: %s \n Пользователю: %s", message, user.toString());
            mailSender.send(user.getEmail(), "Activation Code", message);
        }
    }

    public boolean addUser(User user) {
        User userFromDb = (User) loadUserByUsername(user.getUsername());
        if (userFromDb != null) {
            log.info("Пользователь %s уже существует!", userFromDb.toString());
            return false;
        }
        user.setActive(false);
        Role role = roleService.findById(6);
        user.setRole(role);
        user.setActivationcode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        save(user);
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format("Приветствую, %s \n" +
                            "Активируйте свою учетную запись по следующей ссылке: \n" +
                            "http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationcode());
            mailSender.send(user.getEmail(), "Activation Code", message);
        }
        log.info("Новый пользователь создан");
        return true;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public User findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void saveInEditor(Integer id, Boolean active, String username, String password, Integer roleId) {
        User user = findById(id);
        Role role = roleService.findById(roleId);
        user.setActive(active);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        repository.save(user);
    }

    public void save(User user) {
        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return repository.findUserByUsername(username);
    }

    public boolean activateUser(String code) {
        User user = repository.findByActivationcode(code);
        if (user == null) {
            log.info("Пользователь %s не найден!", user.toString());
            return false;
        }
        user.setActivationcode(null);
        user.setActive(true);
        save(user);
        log.info("Пользователь %s активирован!", user.toString());
        return true;
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();
        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));
        if (isEmailChanged) {
            user.setEmail(email);
            if (!StringUtils.isEmpty(email)) {
                user.setActivationcode(UUID.randomUUID().toString());
            }
        }
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(passwordEncoder.encode(password));
        }
        user.setActive(false);
        repository.save(user);
        if (isEmailChanged) {
            sendMessage(user);
        }
        log.info("Профиль пользователя %s обновлен!", user.toString());
    }
}