package enums;
import model.Task;
public enum Priority {
    LOW("Low") {
        @Override
        public void changePriority(Task task) throws Exception {
        }
        @Override
        public void changeDescription(Task task) throws Exception {
        }
        @Override
        public void deleteTask(Task task) throws Exception {
        }
    },
    MEDIUM("Medium") {
        @Override
        public void changePriority(Task task) throws Exception {
        }
        @Override
        public void changeDescription(Task task) throws Exception {
        }
        @Override
        public void deleteTask(Task task) throws Exception {
        }
    },
    HIGH("High") {
        @Override
        public void changePriority(Task task) throws Exception {
        }
        @Override
        public void changeDescription(Task task) throws Exception {
        }
        @Override
        public void deleteTask(Task task) throws Exception {
        }
    };
    private String value;
    Priority(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    public abstract void changePriority(Task task) throws Exception;
    public abstract void changeDescription(Task task) throws Exception;
    public abstract void deleteTask(Task task) throws Exception;
}