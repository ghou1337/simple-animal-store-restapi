package pl.murakami.sringstudy.FirstRestApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.murakami.sringstudy.FirstRestApp.model.User;
import pl.murakami.sringstudy.FirstRestApp.repos.UserRepos;

@Service
public class RegistrationService {
    @Autowired
    private UserRepos userRepos;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities("USER");
        userRepos.save(user);
    }
}
