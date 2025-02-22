package com.greetingapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GreetingService {
    @Autowired
    private GreetingRepository greetingRepository;
    public String getGreetingMessage() {
        return "Hello, World!";
    }

    public String getPersonalizedGreeting(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return "Hello, " + firstName + " " + lastName + "!";
        } else if (firstName != null) {
            return "Hello, " + firstName + "!";
        } else if (lastName != null) {
            return "Hello, " + lastName + "!";
        } else {
            return "Hello, World!";
        }
    }
    public Greeting saveGreeting(Greeting greeting) {
        return greetingRepository.save(greeting);
    }
    public Optional<Greeting> findGreetingById(Long id) {
        return greetingRepository.findById(id);
    }
    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }
    public Greeting updateGreeting(Long id, Greeting updatedGreeting) {
        return greetingRepository.findById(id).map(greeting -> {
            greeting.setMessage(updatedGreeting.getMessage());
            return greetingRepository.save(greeting);
        }).orElseThrow(() -> new RuntimeException("Greeting not found with id: " + id));
    }
}
