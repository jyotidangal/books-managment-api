package com.dwit.developers.springrestservice.converters;

import com.dwit.developers.springrestservice.dto.UserDto;
import com.dwit.developers.springrestservice.entity.Role;
import com.dwit.developers.springrestservice.entity.User;
import com.dwit.developers.springrestservice.repo.RoleRepo;
import com.dwit.developers.springrestservice.utils.PasswordEncryptor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter extends AbstractConverter<User, UserDto> {
    // Inversion Control : By dependency injection
    private final RoleRepo roleRepo;
    private final PasswordEncryptor passwordEncryptor;


    public UserConverter(RoleRepo roleRepo, PasswordEncryptor passwordEncryptor) {
        this.roleRepo = roleRepo;
        this.passwordEncryptor = passwordEncryptor;
    }
    @Override
     public User toEntity(UserDto userDto) {
        User user    = new User();
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        //password should encrypt before stored to the database
        user.setPassword(passwordEncryptor.encryptPassword(userDto.getPassword()));

        user.setEmail(userDto.getEmail());
        user.setEnabled(userDto.isStatus());

     // for foreign key role
        if(userDto.getRoleId() != null){
            Role role = roleRepo.findById(userDto.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role is not found with this id" +userDto.getRoleId()));
                user.setRole(role);
        }
        return user;
    }

    @Override
   public List<User> toEntityList(List<UserDto> userDtos) {
        return List.of();
    }

    @Override
   public List<UserDto> toDtoList(List<User> users) {
        List<UserDto> userDtoList  = new ArrayList<>();
        for(User user : users){
            UserDto  userDto = toDto(user);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
   public UserDto toDto(User user) {
        UserDto userDto  = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setStatus(user.isEnabled());
//   password shouldn't be readable
//   userDto.setUsername(user.getPassword());

        // for foreign key
        if(user.getRole() != null){
            userDto.setRoleId(user.getRole().getId());
        }
        return userDto;
    }
}
