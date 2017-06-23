package pl.edu.agh.testify.client.view.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.testify.client.model.NewTask;
import pl.edu.agh.testify.client.model.Task;
import pl.edu.agh.testify.client.service.TaskService;

import java.util.List;

@SpringUI
@Theme("valo")
public class ClientVaadinUI extends UI {

    private final TaskService taskService;
    private Grid<Task> grid = new Grid<>(Task.class);
    private TaskForm form;

    @Autowired
    public ClientVaadinUI(TaskService taskService) {
        this.taskService = taskService;
        this.form = new TaskForm(taskService, this);
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        Button addTaskBtn = new Button("Add new task");
        addTaskBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setNewTask(new NewTask());
        });

        HorizontalLayout toolbar = new HorizontalLayout(addTaskBtn);

        grid.setColumns("id", "taskName", "description", "url", "grade", "failedTests");

        HorizontalLayout main = new HorizontalLayout(grid, form);
        main.setSizeFull();
        grid.setSizeFull();
        main.setExpandRatio(grid, 1);

        layout.addComponents(toolbar, main);

        updateList();

        setContent(layout);

        form.setVisible(false);

        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() == null) {
                form.setVisible(false);
            } else {
//                form.setNewTask(event.getValue());
            }
        });
    }

    public void updateList() {
        List<Task> tasks = taskService.allTasks();
        grid.setItems(tasks);
    }
}
