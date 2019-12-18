package immigration.model;

public interface DoublyLinkedInterface <E> {

    public void addFirst(Node newNode);

    public void addLast(Node newNode);

    public Long add(Node data);

    //remove from the end
    public void removeLast()throws Exception;

    public void removeFirst();

    public People remove(People person) ;

    public void removeNPeople(int quantity);

    public Node getFirst();

    public Node getLast();

    public int getSize();

    public Node get(Long data);

    public boolean isEmpty();

    public Long insert(Node data, int position);
}
