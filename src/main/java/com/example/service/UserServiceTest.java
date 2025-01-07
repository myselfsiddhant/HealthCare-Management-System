package com.example.service;

import com.example.dao.UserDAO;
import com.example.model.UserCredentials;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;

    @Test
    public void testRegisterUser_Success() {
        UserCredentials user = new UserCredentials();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password123");

        when(userDAO.registerUser(user)).thenReturn(true);

        boolean result = userService.registerUser(user);

        assertTrue(result);
        verify(userDAO).registerUser(user);
    }
}
