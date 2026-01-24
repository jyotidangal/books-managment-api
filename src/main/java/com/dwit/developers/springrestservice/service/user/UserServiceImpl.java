package com.dwit.developers.springrestservice.service.user;

import com.dwit.developers.springrestservice.converters.UserConverter;
import com.dwit.developers.springrestservice.dto.UserDto;
import com.dwit.developers.springrestservice.entity.User;
import com.dwit.developers.springrestservice.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserConverter userConverter;

    public UserServiceImpl(UserRepo userRepo, UserConverter userConverter) {
        this.userRepo = userRepo;
        this.userConverter = userConverter;
    }

    @Override
    public UserDto create(UserDto userDto) {
        User user = userConverter.toEntity(userDto);
        User saved = userRepo.save(user);
        return userConverter.toDto(saved);
    }

    @Override
    public UserDto findById(Long aLong) {
      User user = userRepo.findById(aLong).orElseThrow(() -> new RuntimeException("User not found"));
      return userConverter.toDto(user);

    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepo.findAll();
        return userConverter.toDtoList(users);
    }

    @Override
    public UserDto update(UserDto userDto, Long aLong) {

        User user  = userRepo.findById(aLong).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        // save the updated details to the database
        User saved = userRepo.save(user);

        return userConverter.toDto(saved);
    }

    @Override
    public Boolean delete(Long aLong) {
        if (userRepo.existsById(aLong)) {
            userRepo.deleteById(aLong);
            return true;
        }
            return false;
    }
}
