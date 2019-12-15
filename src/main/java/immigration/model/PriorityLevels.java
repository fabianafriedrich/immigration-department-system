package immigration.model;

public enum PriorityLevels {
    HIGH(1),
    MEDIUM(2),
    LOW(3);

    private final int priority;

    PriorityLevels(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
