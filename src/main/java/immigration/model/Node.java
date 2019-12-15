package immigration.model;

import lombok.Data;

@Data
public class Node {

    private Node next;
    private Node previous;
    private People data;

    //Has all arguments
    public Node (Node next, Node previous, People data) {
        this.next = next;
        this.previous = previous;
        this.data = data;
    }

    public Node(People data) {
        this.data = data;
    }
}
