package pl.edu.agh.testify.tasks.view;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.agh.testify.tasks.model.Task;
import pl.edu.agh.testify.tasks.repository.TaskRepository;

import javax.servlet.annotation.WebServlet;

@SpringUI
public class MainView extends UI {

    TaskRepository repo;
    Grid<Task> grid;
    TextField filename;
    TextField taskName;
    TextField summary;
    Upload taskData;

    @Autowired
    public MainView(TaskRepository repo) {
        this.repo = repo;
        this.grid = new Grid<>(Task.class);
        this.filename = new TextField();
        this.taskName = new TextField();
        this.summary = new TextField();
        this.taskData = new Upload();
    }

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.addComponent(new Label("Here is list of found tasks:"));
        listTasks();
        layout.addComponents(grid);
        filename.setPlaceholder("Filename");
        taskName.setPlaceholder("Name for task");
        summary.setPlaceholder("Summary");
        taskData.setButtonCaption("Data");
        layout.addComponents(filename);
        layout.addComponents(taskName);
        layout.addComponents(summary);
        layout.addComponents(taskData);

        RestTemplate restTemplate = new RestTemplate();

        Button submit = new Button("Upload", event -> {
            MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();
//            mvm.add("file", new MockMultipartFile(taskName.getValue(), taskName.getValue(),
//                    "text/plain", taskData.getValue().getBytes()));
            mvm.add("taskName", taskName.getValue());
            mvm.add("summary", summary.getValue());
            restTemplate.postForObject("http://localhost:11226/task/upload", mvm, MultiValueMap.class);
        });
        layout.addComponents(submit);
        setContent(layout);
    }

    private void listTasks() {
        grid.setItems(repo.findAll());
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MainView.class)
    public static class Servlet extends VaadinServlet {
    }
}
