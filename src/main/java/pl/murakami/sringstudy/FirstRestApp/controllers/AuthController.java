package pl.murakami.sringstudy.FirstRestApp.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.murakami.sringstudy.FirstRestApp.dto.AuthDTO;
import pl.murakami.sringstudy.FirstRestApp.security.JwtUtil;
import pl.murakami.sringstudy.FirstRestApp.dto.UserDTO;
import pl.murakami.sringstudy.FirstRestApp.model.User;
import pl.murakami.sringstudy.FirstRestApp.security.MyUserDetails;
import pl.murakami.sringstudy.FirstRestApp.service.RegistrationService;
import pl.murakami.sringstudy.FirstRestApp.utils.PersonValidator;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public Map<String, String> registration(@RequestBody UserDTO userDTO,
                                            BindingResult bindingResult) {
        User user = convertToPerson(userDTO);
        personValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return Map.of("message: ", bindingResult.getFieldErrors().toString());
        }
        registrationService.register(user);

        String token = jwtUtil.generateToken(user.getUsername());
        return Map.of("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, String> preformLogin(@RequestBody AuthDTO authDTO) {
        System.out.println("Start preform login");
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(authDTO.getUsername(),
                        authDTO.getPassword());
        System.out.println("new auth token preform login");
        try {
            authenticationManager.authenticate(authToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "incorrect credentials");
        }

        String token = jwtUtil.generateToken(authDTO.getUsername());
        return Map.of("jwt-token", token);
    }

    @GetMapping("/get-info")
    public String getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        return myUserDetails.getUsername();
    }

    @GetMapping("/get-access")
    public Map<String, String> getSimpleInfo() {
        return Map.of("Message", "You have access to the pages, congratulations");
    }
    private User convertToPerson(UserDTO userDTO) {
        return this.modelMapper.map(userDTO, User.class);
    }
}
