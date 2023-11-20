package pl.murakami.sringstudy.FirstRestApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.murakami.sringstudy.FirstRestApp.model.User;
import pl.murakami.sringstudy.FirstRestApp.repos.UserRepos;
import pl.murakami.sringstudy.FirstRestApp.security.MyUserDetails;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepos userRepos;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepos.findByUsername(s);
        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found");
        return new MyUserDetails(user.get());
    }
}
