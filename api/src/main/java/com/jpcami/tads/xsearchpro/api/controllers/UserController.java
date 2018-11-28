package com.jpcami.tads.xsearchpro.api.controllers;

import com.jpcami.tads.xsearchpro.api.entities.User;
import com.jpcami.tads.xsearchpro.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController(value = "users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    public ResponseEntity<HashMap<String, Object>> validate(@RequestBody User user) {
        User authenticated = repository.findByLoginAndPassword(user.getLogin(), user.getPassword());

        HashMap<String, Object> result = new HashMap<>();
        result.put("authenticated", authenticated != null);
        result.put("message", authenticated != null ? "Usuário logado com sucesso" : "Usuário ou senha inválidos");
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "users/new")
    public ResponseEntity<HashMap<String, Object>> save(@RequestBody User user) {
        repository.save(user);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping(value = "users/clear")
    public ResponseEntity<Object> clear() {
        repository.deleteAll();
        return ResponseEntity.ok(null);
    }
}
