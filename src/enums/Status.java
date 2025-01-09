package enums;

import model.Task;
import model.TaskManager;

import java.util.Scanner;

public enum Status {
    NEW("New") {
        @Override
        public void changeStatusToInProgress(Task task) {
            task.setStatus(IN_PROGRESS);
            System.out.println("Задача сейчас выполняется");
        }

        @Override
        public void changeStatusToDone(Task task) throws Exception {
            throw new Exception("не могу изменить приоритет на выполнено!");
        }

        @Override
        public void changeDescription(Task task) {
            System.out.println("Пожалуйста, введите новое описание:");
            String description = new Scanner(System.in).nextLine();
            task.setDescription(description);
            System.out.println("Описание задачи изменено");
        }

        @Override
        public void deleteTask(Task task) {
            TaskManager.deleteTask(task);
            System.out.println("Задача удалена");
        }
    },
    IN_PROGRESS("InProgress") {
        @Override
        public void changeStatusToInProgress(Task task) throws Exception {
            throw new Exception("Не могу изменить приоритет на InProgress");
        }

        @Override
        public void changeStatusToDone(Task task) {
            task.setStatus(DONE);
            System.out.println("Задача выполнена");
        }

        @Override
        public void changeDescription(Task task) throws Exception {
            throw new Exception("Не могу изменить описание");
        }

        @Override
        public void deleteTask(Task task) throws Exception {
            throw new Exception("Не могу удалить задачу!");
        }
    },
    DONE("Done") {
        @Override
        public void changeStatusToInProgress(Task task) throws Exception {
            throw new Exception("Не могу изменить приоритет на InProgress");
        }

        @Override
        public void changeStatusToDone(Task task) throws Exception {
            throw new Exception("не могу изменить приоритет на выполнено!");
        }

        @Override
        public void changeDescription(Task task) throws Exception {
            throw new Exception("Не могу изменить описание");
        }

        @Override
        public void deleteTask(Task task) throws Exception {
            throw new Exception("Не могу удалить задачу!");
        }
    };

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public abstract void changeStatusToInProgress(Task task) throws Exception;

    public abstract void changeStatusToDone(Task task) throws Exception;

    public abstract void changeDescription(Task task) throws Exception;

    public abstract void deleteTask(Task task) throws Exception;
}