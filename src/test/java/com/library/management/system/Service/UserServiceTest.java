package com.library.management.system.Service;

import com.library.management.system.LibraryManagementSystemApplication;
import com.library.management.system.dto.UserRequestDto;
import com.library.management.system.exception.type.EmailNotFoundException;
import com.library.management.system.exception.type.IdNotFoundException;
import com.library.management.system.user.User;
import com.library.management.system.user.UserEntity;
import com.library.management.system.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = LibraryManagementSystemApplication.class)
class UserServiceTest {
//    @InjectMocks
//    UserService userService;
//
//    @Mock
//    UserRepository userRepository;
//
//    @Test
//    void addUser_returnTrue(){
//        UserEntity user= UserEntity.builder()
//                .name("tala")
//                .email("tala@gmail.com")
//                .password("talakafafi")
//                .build();
//
//        when(userRepository.save(any(UserEntity.class))).thenReturn(user);
//
//        UserRequestDto userRequestDto = UserRequestDto.builder().name("tala").build();
//        User result = userService.addUser(userRequestDto);
//
//        assertEquals(user.getName(),result.getName());
//    }
//
//    @Test
//    void getUserById() throws IdNotFoundException {
//        UserEntity user= UserEntity.builder()
//                .name("tala")
//                .email("tala@gmail.com")
//                .password("talakafafi")
//                .build();
//
//        when(userRepository.findById(0L)).thenReturn(Optional.of(user));
//
//        User result =userService.getUserById(0L);
//
//        assertEquals(result.getName(),user.getName());
//    }
//
//    @Test
//    void getUserById_ThrowException() throws IdNotFoundException {
//        when(userRepository.findById(0L)).thenReturn(Optional.empty());
//
//        assertThrows(IdNotFoundException.class , () -> {
//            userService.getUserById(0L);
//        });
//    }
//
//    @Test
//    void getUserByEmail() throws  EmailNotFoundException {
//        UserEntity user= UserEntity.builder()
//                .name("tala")
//                .email("tala@gmail.com")
//                .password("talakafafi")
//                .build();
//
//        when(userRepository.findByEmail("tala@gmail.com")).thenReturn(user);
//
//        User result =userService.getUserByEmail("tala@gmail.com");
//
//        assertEquals(result.getName(),user.getName());
//    }
//
//    @Test
//    void getUserByEmail_ThrowException() throws  EmailNotFoundException {
//        when(userRepository.findByEmail("tahseen@gmail.com")).thenReturn(null);
//
//        assertThrows(EmailNotFoundException.class,()->{
//            userService.getUserByEmail("tahseen@gmail.com");
//        });
//    }
//
//@Test
//    void  updateUser() throws IdNotFoundException {
//    UserEntity user= UserEntity.builder()
//            .name("tala")
//            .email("tala@gmail.com")
//            .password("talakafafi")
//            .build();
//
//    when(userRepository.findById(0L)).thenReturn(Optional.ofNullable(user));
//
//    UserRequestDto updatedUser = UserRequestDto.builder().email("talakafafi@gmail.com").build();
//
//    userService.updateUser(0L,updatedUser);
//
//    assert user != null;
//    assertEquals(user.getName(),"tala");
//    assertEquals(user.getEmail() , "talakafafi@gmail.com");
//
//}
//
//    @Test
//    void  updateUser_ThrowException() throws IdNotFoundException {
//        when(userRepository.findById(0L)).thenReturn(Optional.ofNullable(null));
//
//        UserRequestDto updatedUser = UserRequestDto.builder().email("talakafafi@gmail.com").build();
//
//        assertThrows(IdNotFoundException.class,()->{
//            userService.updateUser(0L,updatedUser);
//        });
//    }
//
//    @Test
//    void deleteUser() throws IdNotFoundException {
//        UserEntity user= UserEntity.builder()
//                .name("tala")
//                .email("tala@gmail.com")
//                .password("talakafafi")
//                .build();
//
//        when(userRepository.findById(0L)).thenReturn(Optional.ofNullable(user));
//
//        userService.deleteUser(0L);
//
//        assert user != null;
//        verify(userRepository).delete(user);
//    }
//
//    @Test
//    void deleteUser_ThrowException() throws IdNotFoundException {
//        when(userRepository.findById(0L)).thenReturn(Optional.empty());
//
//        assertThrows(IdNotFoundException.class,()->{
//            userService.deleteUser(0L);
//        });
//    }
//
//    @Test
//    void getAllUsers(){
//        when(userRepository.findAll()).thenReturn(Stream.of(UserEntity.builder().name("user1").email( "user1@gmail.com").build(),
//                UserEntity.builder().name("user2").email( "user2@gmail.com").build()).collect(Collectors.toList()));
//
//        List<User> allUsers = userService.getAllUsers();
//        assertEquals(2, allUsers.size());
//    }


}