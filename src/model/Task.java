package model;
import enums.Priority;
import java.time.LocalDate;
public class Task {
    private String name;
    private String description;
    private LocalDate completionDate;
    private LocalDate createdDate;
    private Priority priorityObj;
    private String priority;
    public Task(String name, String description, LocalDate completionDate, LocalDate createdDate, Priority priorityObj, String priority) {
        this.name = name;
        this.description = description;
        this.completionDate = completionDate;
        this.createdDate = createdDate;
        this.priorityObj = priorityObj;
        this.priority = priority;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
    public void setPriorityObj(Priority priorityObj) {
        this.priorityObj = priorityObj;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public LocalDate getCompletionDate() {
        return completionDate;
    }
    public LocalDate getCreatedDate() {
        return createdDate;
    }
    public Priority getPriorityObj() {
        return priorityObj;
    }
    public String getPriority() {
        return priority;
    }
}