package pl.murakami.sringstudy.FirstRestApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.murakami.sringstudy.FirstRestApp.model.User;
import pl.murakami.sringstudy.FirstRestApp.repos.UserRepos;
import pl.murakami.sringstudy.FirstRestApp.security.MyUserDetails;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepos userRepos;

    public User loadByUsername(String username) {
        return userRepos.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));
    }
    public Optional<User> checkIfUsernameExist(String username) {
        return userRepos.findByUsername(username);
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        return myUserDetails.getPerson();
    }
}
