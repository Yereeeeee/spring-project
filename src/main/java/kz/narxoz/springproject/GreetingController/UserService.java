package kz.narxoz.springproject.GreetingController;


import kz.narxoz.springproject.repository.RoleRepository;
import kz.narxoz.springproject.repository.UserRepository;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(Collections.singletonList(roleRepository.getById(1L)))); // USER
        userRepository.save(user);
    }
}
