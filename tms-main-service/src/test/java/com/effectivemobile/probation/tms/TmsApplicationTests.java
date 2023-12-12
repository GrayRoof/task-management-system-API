package com.effectivemobile.probation.tms;

import com.effectivemobile.probation.tms.controllers.TaskController;
import com.effectivemobile.probation.tms.controllers.UserController;
import com.effectivemobile.probation.tms.repositories.TaskRepository;
import com.effectivemobile.probation.tms.repositories.UserRepository;
import com.effectivemobile.probation.tms.services.TaskService;
import com.effectivemobile.probation.tms.services.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class TmsApplicationTests {

	@Autowired
	private TaskController taskController;

	@Autowired
	private TaskService taskService;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserController userController;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
		assertThat(userController).isNotNull();
		assertThat(userService).isNotNull();
		assertThat(userRepository).isNotNull();
		assertThat(taskController).isNotNull();
		assertThat(taskService).isNotNull();
		assertThat(taskRepository).isNotNull();
	}

}
