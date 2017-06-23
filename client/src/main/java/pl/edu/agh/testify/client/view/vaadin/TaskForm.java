package pl.edu.agh.testify.client.view.vaadin;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.testify.client.model.NewTask;
import pl.edu.agh.testify.client.service.TaskService;

public class TaskForm extends FormLayout {

    private static final Logger logger = LoggerFactory.getLogger(TaskForm.class);
    private final TaskService taskService;

    private ClientVaadinUI ui;

    private TextField taskName = new TextField("Task name");
    private TextField description = new TextField("Description");
    private TextField zipPath = new TextField("Path to task file");
    private Button save = new Button("Save");

    private NewTask task;
    private Binder<NewTask> binder = new Binder<>(NewTask.class);

    public TaskForm(TaskService taskService, ClientVaadinUI ui) {
        this.taskService = taskService;
        this.ui = ui;

        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save);

        addComponents(taskName, description, zipPath, buttons);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        binder.bindInstanceFields(this);

        save.addClickListener(e -> this.save());
    }

    private void save() {
        task.setTaskName(taskName.getValue());
        task.setDescription(description.getValue());
        task.setPath(zipPath.getValue());
        logger.info("Before Save task " + task);
        taskService.save(task);
        ui.updateList();
        setVisible(false);
    }

    public void setNewTask(NewTask newTask) {
        this.task = newTask;
        binder.setBean(newTask);

        setVisible(true);
        taskName.selectAll();
        description.selectAll();
        zipPath.selectAll();
    }
}
