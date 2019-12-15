package immigration.model;

public class DoublyLinked implements DoublyLinkedInterface{

    private Node first;
    private Node last;
    private int size;

    public DoublyLinked() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }


    @Override
    public void addFirst(Node newNode) {

        //if linked list is empty
        if (isEmpty()) {
            this.first = newNode;
            this.last = newNode;
            size++;
            newNode.setNext(null);
            newNode.setPrevious(null);
        } else {
            //if linked list is not empty
            newNode.setNext(this.first);
            this.first.setPrevious(newNode);
            this.first = newNode;
            size++;

        }
    }

    @Override
    public void addLast(Node newNode) {
        if (isEmpty()) {
            this.first = newNode;
            this.last = newNode;
            size++;
            newNode.setNext(null);
            newNode.setPrevious(null);
        } else {
            newNode.setPrevious(this.last);
            this.last.setNext(newNode);
            this.last = newNode;
            size++;
        }
    }

    @Override
    public Long add(Node data) {
        if(isEmpty()) {
            addLast(data);
        }else {
            int position = 1;
            Node currentPerson = first;
            boolean positionFound = false;

            if (data.getData().getPriorityL().getPriority() == 1) {

                do {

                    if (currentPerson.getData().getPriorityL().getPriority() == 1) {
                        if(currentPerson.getNext() == null) {
                            addLast(data);
                            positionFound = true;
                        }
                        else{
                            currentPerson = currentPerson.getNext();
                            position = position + 1;
                        }

                    }
                    else {
                        insert(data, position);
                        positionFound = true;

                    }

                } while (!positionFound);

            }
            else if (data.getData().getPriorityL().getPriority() == 2) {

                do {

                    if (currentPerson.getData().getPriorityL().getPriority() == 1 || currentPerson.getData().getPriorityL().getPriority()  == 2) {
                        if(currentPerson.getNext() == null) {
                            addLast(data);
                            positionFound = true;
                        }else{
                            currentPerson = currentPerson.getNext();
                            position += 1;
                        }

                    }
                    else {
                        insert(data, position);
                        positionFound = true;
                    }

                } while (!positionFound);

            }
            else if (data.getData().getPriorityL().getPriority() == 3) {
                addLast(data);

            }

        }
        return data.getData().getId();

    }

    @Override
    public void removeLast() {
        this.getLast().getPrevious().setNext(null);
        this.last = this.last.getPrevious();
        size--;
    }

    @Override
    public void removeFirst() {
        this.getFirst().getNext().setPrevious(null);
        this.first = this.first.getNext();
        size--;
    }

    @Override
    public People remove(People person) throws Exception {
        Node tempNode = first;
        while (tempNode.getNext() != null){
            if(person.getId().equals(tempNode.getData().getId())){
                size--;
                removeNode(tempNode);
                return tempNode.getData();
            }
            tempNode = tempNode.getNext();
        }
        throw new Exception();
    }
    private void removeNode(Node tempNode) {
        try{
            tempNode.getPrevious().setNext(tempNode.getNext());
            tempNode.getNext().setPrevious(tempNode.getPrevious());
        }catch (NullPointerException e){
            if(tempNode.getPrevious() == null){
                first = first.getNext();
            }else{
                last = last.getPrevious();
            }
        }
    }

    public void removeNPeople(int quantity) {
        if(quantity > size) {
            System.err.println("There is less than "+quantity+" elements.");
        }
        for (int i = 0; i < quantity; i++) {
            removeLast();
        }
    }
    @Override
    public Node getFirst() {
        return this.first;
    }

    @Override
    public Node getLast() {
        return this.last;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public Node get(int data) {
        Node foundNode = first;

        if(isEmpty()) {
            return null;
        }
        if(data == 1) {
            return foundNode;
        }
        for(int i = 2; i <= size; i++){
            if(i == data){
                foundNode = first.getNext();
            }
        }
        return foundNode;
    }

    @Override
    public boolean isEmpty() {
        if(this.size == 0){
            return true;
        }
        return false;
    }

    @Override
    public Long insert(Node data, int position) {
        if(position > getSize() + 1 ) {
            System.err.println("Impossible to insert into this position");
        }else if (position <= 0) {
            System.err.println("Error when inserting");
        }else if (position == getSize() + 1) {
            addLast(data);
        }
        else if (position == 1) {
            addFirst(data);
        }
        else {
            Node currentPerson = first;

            for(int i = 2; i < position; i++) {
                currentPerson = currentPerson.getNext();
            }

            Node nextPerson = currentPerson.getNext();

            currentPerson.setNext(data);
            currentPerson.getData().setNext(data.getData().getId());
            nextPerson.setPrevious(data);
            currentPerson.getData().setPrevious(data.getData().getId());

            data.setNext(nextPerson);
            data.getData().setNext(data.getData().getId());
            data.setPrevious(nextPerson);
            data.getData().setPrevious(data.getData().getId());
            size++;

        }
        return data.getData().getId();

    }

}