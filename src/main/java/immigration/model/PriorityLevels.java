package immigration.model;

/*Enum Priority Levels*/

public enum PriorityLevels {
    HIGH(1),
    MEDIUM(2),
    LOW(3);

    private final int priority;

    /*Parametrized Constructor*/
    PriorityLevels(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
