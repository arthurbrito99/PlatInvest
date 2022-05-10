package com.arthurb.PlatInvest;

import com.arthurb.PlatInvest.controller.UserController;
import com.arthurb.PlatInvest.model.User;
import com.arthurb.PlatInvest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {
    @MockBean
    UserRepository userRepository;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testCreateUser() throws Exception {
        User user = new User("Carlos", "28557826605", new ArrayList<>());

        Mockito.when(userRepository.save(user)).thenReturn(user);

        mockMvc.perform(post("/api/user/create")
                .contentType("application/json")
                .content("{\"name\":\"Carlos\",\"cpf\":\"28557826605\"}"))
        .andExpect(status().isOk());
    }

    @Test
    public void testCreateAndUpdateUser() throws Exception {
        User user = new User("Carlos", "28557826605", new ArrayList<>());
        User userToUpdate = user;
        user.setId(1L);
        userToUpdate.setName("Carlos2");
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(userToUpdate)).thenReturn(userToUpdate);

        mockMvc.perform(put("/api/user/" + 1L)
                .contentType(MediaType.valueOf("application/json"))
                .content("{\"name\":\"Carlos2\",\"cpf\":\"28557826605\"}"))
        .andExpect(status().isOk());
    }

}
