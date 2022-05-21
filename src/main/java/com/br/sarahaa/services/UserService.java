package com.br.sarahaa.services;

import com.br.sarahaa.dto.UserDto;
import com.br.sarahaa.mapper.ObjectsMapper;
import com.br.sarahaa.models.User;
import com.br.sarahaa.repositories.UserRepository;
import com.br.sarahaa.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final ObjectsValidator<UserDto> validator;
    private final ObjectsMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto save(UserDto userDto) {
        validator.validate(userDto);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User userToSave = mapper.toUser(userDto);
        if (userDto.getId() == null) {
            String generatedUserName = generateUserName(userDto.getFirstName(), userDto.getLastName());
            userToSave.setUserName(generatedUserName);
        }
        User savedUser = repository.save(userToSave);
        return mapper.toUserDto(savedUser);
    }

    private String generateUserName(String firstName, String lastName) {
        return firstName + "-" + lastName + new Random().nextInt(1000);
    }

    public UserDto findByEmail(String email) {
        return repository.findByEmail(email)
                .map(mapper::toUserDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<UserDto> recentlyJoinedUsers() {
        final LocalDateTime calculatedDate = LocalDateTime.now().minusDays(10);
        return repository.findByJoiningDateAfter(calculatedDate)
                .stream().map(mapper::toUserDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> searchUser(String firstName, String lastName, String userName) {
        return repository.findByFirstNameIgnoreCaseOrLastNameIgnoreCaseOrUserNameIgnoreCase(firstName, lastName, userName)
                .stream().map(mapper::toUserDto)
                .collect(Collectors.toList());
    }
}
