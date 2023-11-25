package com.application.services;
import com.application.model.User;
import com.application.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
  @Mock
  private UserRepository userRepository;
  private UserService userServiceUnderTest;

  @BeforeEach
  void freshInstanceForStudentService() {
    userServiceUnderTest = new UserService(userRepository);
  }

  @Test
  void canGetAllStudents() {
    // when
    userServiceUnderTest.getAllUsers ();
    // then
    verify(userRepository).findAll();
  }

  @Test
  void canDeleteUser() {
    // given
    String id = "khouloudsaid1999@gmail.com";
    User user = new User(); // Create a user object or use a mocking library like Mockito
    when(userRepository.findByEmail(id)).thenReturn(user);
    // when
    userServiceUnderTest.deleteUser(id);

    // then
    verify(userRepository).deleteByEmail(id);
  }


}
