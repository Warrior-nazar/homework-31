package model;

import enums.Priority;
import enums.Status;

import java.time.LocalDate;

public class Task {
    private int id;
    private String name;
    private String description;
    private LocalDate completionDate;
    private LocalDate createdDate;
    private Status status;
    private Priority priority;
    public int getId() {
        return id;
    }

    public Task(int id, String name, String description, LocalDate completionDate, LocalDate createdDate, Status status, Priority priority) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.completionDate = completionDate;
        this.createdDate = createdDate;
        this.status = status;
        this.priority = priority;
    }

    public void changeStatusToInProgress() {
        try {
            status.changeStatusToInProgress(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeStatusToDone() {
        try {
            status.changeStatusToDone(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeDescription() {
        try {
            status.changeDescription(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask() {
        try {
            status.deleteTask(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task {\n" +
                "   id = '" + id + "',\n" +
                "   name = '" + name + "',\n" +
                "   description = '" + description + "',\n" +
                "   completionDate = " + completionDate + ",\n" +
                "   createdDate = " + createdDate + ",\n" +
                "   status = " + status + ",\n" +
                "   priority = '" + priority + "'\n" +
                "}\n";
    }

}
