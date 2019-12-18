package immigration.model;

public interface DoublyLinkedInterface <E> {

    /*Adding a element on the first position*/
    public void addFirst(Node newNode);

    /*Adding a element on the last position*/
    public void addLast(Node newNode);

    /*Adding a element*/
    public Long add(Node data);

    /*Removing a element on the last position*/
    public void removeLast()throws Exception;

    /*Removing a element on the first position*/
    public Long removeFirst() throws Exception;

    /*Removing a element*/
    public People remove(People person) ;

    /* Returning the first element*/
    public Node getFirst();

    /* Returning the lats element*/
    public Node getLast();

    /* Number of elements in the list*/
    public int getSize();

    /*Return the element*/
    public Node get(Long data);

    /*Checking if is empty*/
    public boolean isEmpty();

}
