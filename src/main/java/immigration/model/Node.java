package immigration.model;

import lombok.Data;

//Getters and Setters
@Data
public class Node {

    // Attributes of the node
    // The element that it is holding
    // The next node
    // The previous
    private Node next;
    private Node previous;
    private People data;

    // Two constructors. Both of them include
    // the element as a parameter because if we
    // are creating a new node, is because we
    // have a new element. So, the element is
    // mandatory

    // In this one, we don't know who the
    // next node is. So we're defaulting it to null
    public Node(People data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    // In this case, we know the next node.
    // So it can be passed in the costructor
    public Node (Node next, Node previous, People data) {
        this.next = next;
        this.previous = previous;
        this.data = data;
    }


}
