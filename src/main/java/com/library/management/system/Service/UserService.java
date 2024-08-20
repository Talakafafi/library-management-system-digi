package com.library.management.system.Service;

import com.library.management.system.dto.UserRequestDto;
import com.library.management.system.exception.type.EmailNotFoundException;
import com.library.management.system.exception.type.IdNotFoundException;
import com.library.management.system.user.User;
import com.library.management.system.user.UserEntity;
import com.library.management.system.user.UserMapper;
import com.library.management.system.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {


        @Autowired
        UserRepository userRepository;

        public User addUser(UserRequestDto userRequestDto){
            UserEntity user= UserEntity.builder()
                    .name(userRequestDto.getName())
                    .email(userRequestDto.getEmail())
                    .password(userRequestDto.getPassword())
                    .build();
            return  UserMapper.toModel(userRepository.save(user));
        }

    /**
     * The orElseThrow method does not return an Optional. Instead, it returns the actual value contained within the Optional if the value is present.
     * If the Optional is empty, orElseThrow will throw the exception, so the code after this line will not execute.
     * It originally returns optional , either have a value of type UserEntity or throw exception
     */
    public User getUserById(Long id) throws IdNotFoundException {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("User not found with ID: " , id));//this returns Optional<UserEntity>
        return UserMapper.toModel(userEntity);
    }

    public User getUserByEmail(String email) throws EmailNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
                if(userEntity==null){
               throw new EmailNotFoundException("User not found with email: " + email);
                }
        return UserMapper.toModel(userEntity);
    }

    public void updateUser(Long id, UserRequestDto userRequestDto) throws IdNotFoundException {
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("User not found with ID: " , id));

        existingUser.setName((userRequestDto.getName()==null)?existingUser.getName():userRequestDto.getName());
        existingUser.setEmail((userRequestDto.getEmail()==null)?existingUser.getEmail():userRequestDto.getEmail());
        existingUser.setPassword((userRequestDto.getPassword()==null)?existingUser.getPassword():userRequestDto.getPassword());


        UserMapper.toModel(userRepository.save(existingUser));
    }

    public void deleteUser(Long id) throws IdNotFoundException {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("User not found with ID: " , id));
        userRepository.delete(userEntity);
    }

    public List<User> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(UserMapper::toModel)
                .collect(Collectors.toList());
    }


}
