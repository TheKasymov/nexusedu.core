package kz.nexusedu.core.controller;

import kz.nexusedu.core.domain.User;
import kz.nexusedu.core.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    void shouldReturnAllUsers() throws Exception {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setFullName("Test User");
        user.setSystemRole("ADMIN");

        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName").value("Test User"))
                .andExpect(jsonPath("$[0].systemRole").value("ADMIN"));
    }

    @Test
    void shouldCreateUser() throws Exception {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setFullName("New User");

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fullName\": \"New User\", \"systemRole\": \"STUDENT\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("New User"));
    }
}