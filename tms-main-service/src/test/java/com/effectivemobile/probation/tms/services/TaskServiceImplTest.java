package com.effectivemobile.probation.tms.services;

import com.effectivemobile.probation.tms.enums.SortMethod;
import com.effectivemobile.probation.tms.enums.TaskPriority;
import com.effectivemobile.probation.tms.enums.TaskState;
import com.effectivemobile.probation.tms.exceptions.NotAvailableException;
import com.effectivemobile.probation.tms.exceptions.NotFoundException;
import com.effectivemobile.probation.tms.model.comment.CommentDto;
import com.effectivemobile.probation.tms.model.comment.NewCommentDto;
import com.effectivemobile.probation.tms.model.task.*;
import com.effectivemobile.probation.tms.model.user.User;
import com.effectivemobile.probation.tms.model.user.UserDto;
import com.effectivemobile.probation.tms.model.user.UserMapper;
import com.effectivemobile.probation.tms.repositories.TaskRepository;
import com.effectivemobile.probation.tms.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class TaskServiceImplTest {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    private static final long WRONG_ID = Long.MAX_VALUE;
    UserDto author;
    UserDto performer;


    @BeforeEach
    public void setUp() {

        if (author == null) {
            User first = new User(null, "Owner", "Owner@test.test", "pass");
            author = UserMapper.toDto(userRepository.save(first));
        }

        if (performer == null) {
            User second = new User(null, "Other", "Other@test.test", "pass");
            performer = UserMapper.toDto(userRepository.save(second));
        }
    }



    @Test
    void shouldThrowExceptionWhenGetByWrongId() {
        assertThrows(NotFoundException.class, () -> taskService.get(WRONG_ID));
    }


    @Test
    void shouldGetEntity() {
        Task task = new Task(null, "First", "First Item description",
                UserMapper.toUser(performer), UserMapper.toUser(author),
                TaskState.STARTED, TaskPriority.LOW, new HashSet<>());
        Task expected = taskRepository.save(task);
        Task actual = taskService.getEntity(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    void shouldGetAllByUserId() {
        Task newItem = new Task(null, "First", "First Item description",
                UserMapper.toUser(performer), UserMapper.toUser(author),
                TaskState.STARTED, TaskPriority.LOW, new HashSet<>());
        TaskDto saved = TaskMapper.toTaskDto(taskRepository.save(newItem));
        Collection<TaskDto> expected = List.of(saved);
        Collection<TaskDto> actual = taskService.getAllByAuthorId(author.getId(),
                SortMethod.NAME, Sort.Direction.ASC, 0, 10);
        assertEquals(expected, actual);
    }


    @Test
    void shouldAdd() {
        NewTaskDto newTaskDto = new NewTaskDto();
        newTaskDto.setName("NewItem");
        newTaskDto.setDescription("New Item description");
        newTaskDto.setPerformer(performer.getId());
        newTaskDto.setPriority(TaskPriority.HIGH.name());

        TaskDto added = taskService.add(newTaskDto, author.getId());
        added.setComments(new HashSet<>());
        assertEquals(added.getName(), newTaskDto.getName());
        TaskDto actual = taskService.get(added.getId());
        assertEquals(added, actual);
    }


    @Test
    void shouldPatch() {
        Task task = new Task(null, "First", "First Item description",
                UserMapper.toUser(performer), UserMapper.toUser(author),
                TaskState.STARTED, TaskPriority.LOW, null);
        Task saved = taskRepository.save(task);
        Task fromRepo = taskService.getEntity(saved.getId());
        fromRepo.setComments(new HashSet<>());
        TaskDto expected = TaskMapper.toTaskDto(fromRepo);
        expected.setName("Updated");

        UpdateTaskDto toUpdate = new UpdateTaskDto();
        toUpdate.setName("Updated");

        assertEquals(expected, taskService.patch(author.getId(), fromRepo.getId(), toUpdate));
        assertEquals(expected.getName(), taskService.get(saved.getId()).getName());
    }

    @Test
    void shouldThrowExceptionWhenPatchByNotOwner() {
        Task task = new Task(null, "First", "First Item description",
                UserMapper.toUser(performer), UserMapper.toUser(author),
                TaskState.STARTED, TaskPriority.LOW, new HashSet<>());
        Task saved = taskRepository.save(task);
        Task fromRepo = taskService.getEntity(saved.getId());
        UpdateTaskDto toUpdate = new UpdateTaskDto();
        toUpdate.setName("Updated");
        assertThrows(NotAvailableException.class, () -> taskService.patch(performer.getId(), fromRepo.getId(), toUpdate));
    }

    @Test
    void shouldThrowExceptionWhenPatchNotExistingItem() {
        UpdateTaskDto toUpdate = new UpdateTaskDto();
        toUpdate.setName("Updated");
        assertThrows(NotFoundException.class, () -> taskService.patch(author.getId(), WRONG_ID, toUpdate));
    }

    @Test
    void shouldDelete() {
        Task task = new Task(null, "First", "First Item description",
                UserMapper.toUser(performer), UserMapper.toUser(author), TaskState.STARTED,
                TaskPriority.LOW, new HashSet<>());
        Task saved = taskRepository.save(task);
        assertEquals(saved.getName(), taskService.get(saved.getId()).getName());
        taskService.delete(saved.getId(), author.getId());
        assertThrows(NotFoundException.class, () -> taskService.get(saved.getId()));
    }


    @Test
    void shouldAddComment() {
        Task task = new Task(null, "First", "First Item description",
                UserMapper.toUser(performer), UserMapper.toUser(author), TaskState.STARTED,
                TaskPriority.LOW, new HashSet<>());
        Task savedItem = taskRepository.save(task);

        NewCommentDto newComment = new NewCommentDto();
        newComment.setText("Comment for First Task");
        CommentDto savedCommentDto = taskService.addComment(savedItem.getId(), performer.getId(), newComment);
        assertEquals(newComment.getText(), savedCommentDto.getText());
        assertNotNull(savedCommentDto.getAuthor());
        assertEquals(performer.getName(), savedCommentDto.getAuthor().getName());
    }

}