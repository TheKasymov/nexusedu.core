package kz.nexusedu.core.controller;

import kz.nexusedu.core.domain.User;
import kz.nexusedu.core.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return repository.save(user);
    }

    @GetMapping
    public List<User> getAll() {
        return repository.findAll();
    }
}