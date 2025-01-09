package model;

import enums.Priority;
import enums.Status;
import util.FileUtil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class TaskManager {
    public static List<Task> tasks = new ArrayList<>();

    public TaskManager() {
        tasks = FileUtil.loadTasks();
        sortTasksByPriority();
        FileUtil.saveTasks(tasks);
    }

    public void run() {
        boolean flag = true;

        try {
            while (flag) {
                menu();
                int choice = sc().nextInt();
                switch (choice) {
                    case 0:
                        FileUtil.saveTasks(tasks);
                        flag = false;
                        break;
                    case 1:
                        showAllTasks();
                        break;
                    case 2:
                        addNewTask();
                        sortTasksByPriority();
                        FileUtil.saveTasks(tasks);
                        break;
                    case 3:
                        workWithExactTask();
                        break;
                    default:
                        System.out.println("Вы выбрали несуществующий вариант!");
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели не номер задачи!");
            run();
        }
    }

    private void menu() {
        print("0 - выход");
        print("1 - Показать все задачи");
        print("2 - Добавить новую задачу");
        print("3 - Работайть над какой-нибудь задачей");
    }

    private void showAllTasks() {
        if (tasks.isEmpty()) {
            print("Нет никаких задач!");
            print("Хотите добавить немного? (yes/no)");
            String answer = sc().nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                addNewTask();
            }
        } else {
            while (true) {
                try {
                    System.out.println("Сортировать по? - (приоритетам, описание, дата создания)");
                    System.out.println("1 - по приоритетам\n2 - по описание\n3 - по дата создания");
                    int answer = sc().nextInt();
                    if (answer == 1) {
                        for (Task task : tasks) {
                            System.out.println(task);
                        }
                        break;
                    } else if (answer == 2) {
                        printSortedTasksByDescription();
                        break;
                    } else if (answer == 3) {
                        printSortedTasksByCreatedDate();
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Вы ввели не номер задачи");
                }
            }
        }
    }

    private void addNewTask() {
        System.out.print("Введите задачу name: ");
        String name = sc().nextLine();

        System.out.println("Введите задачу description: ");
        String description = sc().nextLine();

        LocalDate completionDate = null;
        while (completionDate == null) {
            try {
                System.out.println("Введите задачу дата завершения в yyyy-mm-dd: ");
                LocalDate inputDate = LocalDate.parse(sc().nextLine());
                if (inputDate.isAfter(LocalDate.now())) {
                    completionDate = inputDate;
                } else {
                    System.out.println("Дата завершения задачи не может быть раньше текущей!");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Вы ввели дату в неправильном формате. Пожалуйста, используйте yyyy-mm-dd.");
            }
        }

        Priority priority = null;
        while (priority == null) {
            try {
                System.out.println("Priority: ");
                System.out.println("'Low', 'Medium', 'High'");
                priority = Priority.valueOf(sc().nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный priority. Пожалуйста, введите .  'Low', 'Medium', or 'High'.");
            }
        }

        LocalDate createdDate = LocalDate.now();
        Task newTask = new Task(tasks.size() + 1, name, description, completionDate, createdDate, Status.NEW, priority);
        tasks.add(newTask);
        System.out.println("Задача успешно добавлена!");
    }


    //--------------------------------------------------------------------------------------------------------

    private void menuForWorkingWithExactTask() {
        print("0 - выход в меню");
        print("1 - Изменить статус на <InProgress>");
        print("2 - Изменить статус на 'Done'");
        print("3 - Изменить описание");
        print("4 - Отметить статус (low, medium, high)");
        print("5 - Удалить");
    }

    private void workWithExactTask() {

        Task task = getTaskById(askTaskId());
        print(task.toString());

        boolean flag = true;

        while (flag) {
            menuForWorkingWithExactTask();
            int choice = sc().nextInt();
            switch (choice) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    task.changeStatusToInProgress();
                    break;
                case 2:
                    task.changeStatusToDone();
                    break;
                case 3:
                    task.changeDescription();
                    break;
                case 4:
                    markTask(task);
                    break;
                case 5:
                    task.deleteTask();
                    if (task.getStatus().equals(Status.NEW)) {
                        flag = false;
                    }
                    break;
                default:
                    System.out.println("Вы выбрали несуществующий вариант!");
                    break;
            }
            sortTasksByPriority();
            FileUtil.saveTasks(tasks);
            if (choice != 0) {
                print(task.toString());
            }
        }
    }

    private int askTaskId() {
        int id;
        while (true) {
            try {
                System.out.println("Введите задачу id:");
                String idInString = sc().nextLine();
                id = Integer.parseInt(idInString);
                if (id > tasks.size() || id <= 0) {
                    throw new NumberFormatException("ID задачи должен находиться в допустимом диапазоне.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Введите числовой ID задачи в допустимом диапазоне..");
            }
        }
        return id;
    }


    private Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return tasks.get(0);
    }

    private void markTask(Task task) {
        while (true) {
            System.out.println("Введите свою маркировку - (low, medium, high)");
            String newPriority = sc().nextLine();

            if (newPriority.equalsIgnoreCase(Priority.LOW.getValue())) {
                task.setPriority(Priority.LOW);
                break;
            } else if (newPriority.equalsIgnoreCase(Priority.MEDIUM.getValue())) {
                task.setPriority(Priority.MEDIUM);
                break;
            } else if (newPriority.equalsIgnoreCase(Priority.HIGH.getValue())) {
                task.setPriority(Priority.HIGH);
                break;
            }
        }
    }

    public static void deleteTask(Task task) {
        tasks.remove(task);
    }

    private void sortTasksByPriority() {
        Comparator cmp = Comparator.comparing(Task::getPriority);
        tasks.sort(cmp);
    }

    private void printSortedTasksByDescription() {
        List<Task> tasksForDesc = new ArrayList<>(tasks);
        Comparator cmp = Comparator.comparing(Task::getDescription);
        tasksForDesc.sort(cmp);

        for (var task : tasksForDesc) {
            System.out.println(task);
        }
    }

    private void printSortedTasksByCreatedDate() {
        List<Task> tasksForDate = new ArrayList<>(tasks);
        Comparator cmp = Comparator.comparing(Task::getCreatedDate);
        tasksForDate.sort(cmp);

        for (var task : tasksForDate) {
            System.out.println(task);
        }
    }

    private void print(String str) {
        System.out.println(str);
    }

    private Scanner sc() {
        return new Scanner(System.in);
    }
}