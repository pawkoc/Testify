package pl.edu.agh.testify.model;

import org.springframework.data.annotation.Id;

public class Summary {

//    @Id
//    private String id;

    @Id
    private String taskName;
    private String professorName;
    private String summary;

    public Summary() {
    }

    public Summary(String taskName, String professorName, String summary) {
        this.taskName = taskName;
        this.professorName = professorName;
        this.summary = summary;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Summary{" +
                "taskName='" + taskName + '\'' +
                ", professorName='" + professorName + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
