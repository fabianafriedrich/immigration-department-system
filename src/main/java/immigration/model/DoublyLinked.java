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
            newNode.getData().setNext(null);
            newNode.getData().setPrevious(null);
        } else {
            //if linked list is not empty
            newNode.setNext(this.first);
            newNode.getData().setNext(this.first.getData().getId());
            this.first.getData().setPrevious(newNode.getData().getId());
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
            newNode.getData().setNext(null);
            newNode.getData().setPrevious(null);
            newNode.setNext(null);
            newNode.setPrevious(null);
        } else {
            newNode.setPrevious(this.last);
            newNode.getData().setPrevious(this.last.getData().getId());
            this.last.setNext(newNode);
            this.last.getData().setNext(newNode.getData().getId());
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
            People currentData = currentPerson.getData();

            if (data.getData().getPriorityL().getPriority() == 1) {

                do {

                    if (currentData.getPriorityL().getPriority() == 1) {
                        if(currentPerson.getData().getNext() == null) {
                            addLast(data);
                            positionFound = true;
                        }
                        else{
                            currentData = currentPerson.getNext().getData();
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

                    if (currentData.getPriorityL().getPriority() == 1 || currentData.getPriorityL().getPriority()  == 2) {
                        if(currentPerson.getData().getNext() == null) {
                            addLast(data);
                            positionFound = true;
                        }else{
                            currentData = currentPerson.getNext().getData();
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
    public void removeLast() throws Exception{
        if (size == 1){
            throw new Exception();
        } else {
            this.getLast().getPrevious().getData().setNext(null);
            this.last = this.last.getPrevious();
            size--;
        }
    }

    @Override
    public void removeFirst() {
        this.getFirst().getNext().getData().setPrevious(null);
        this.first = this.first.getNext();
        size--;
    }

    @Override
    public People remove(People person) {
        Node tempNode = first;
        while (tempNode.getNext() != null){
            if(person.getId().equals(tempNode.getData().getId())){
                size--;
                removeNode(tempNode);
                return tempNode.getData();
            }
            tempNode = tempNode.getNext();
        }
        try {
            removeLast();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempNode.getData();
    }
    private void removeNode(Node tempNode) {
        if(tempNode.getPrevious() != null){

            tempNode.getPrevious().setNext(tempNode.getNext());
            tempNode.getPrevious().getData().setNext(tempNode.getNext().getData().getId());

            tempNode.getNext().setPrevious(tempNode.getPrevious());
            tempNode.getNext().getData().setPrevious(tempNode.getPrevious().getData().getId());

        }else if(tempNode.getPrevious() == null){
            first = first.getNext();
            first.setPrevious(null);
            first.getData().setPrevious(null);
        }else{
            last = last.getPrevious();
        }
    }

    public void removeNPeople(int quantity) {
        if(quantity > size) {
            System.err.println("There is less than "+quantity+" elements.");
        }
        for (int i = 0; i < quantity; i++) {
            try {
                removeLast();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
    public Node get(Long data) {
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

    public Long findPosition(Long id){

        if(isEmpty()) {
            System.err.println("The Queue is empty");
        }

        Node currentPerson = first;
        for(Long i = 1L; i <= getSize(); i++) {
            if(currentPerson.getData().getId() == id) {
                return i;
            }

            currentPerson = currentPerson.getNext();
        }

        //if we didn't find it, then return -1
        return -1L;

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

            Node nodeNextPerson = currentPerson.getNext();
            People nextPerson = currentPerson.getNext().getData();

            currentPerson.setNext(data);
            currentPerson.getData().setNext(data.getData().getId());

            nextPerson.setPrevious(data.getData().getId());
            nodeNextPerson.setPrevious(data);

            data.getData().setNext(nextPerson.getId());
            data.setNext(nodeNextPerson);

            data.getData().setPrevious(currentPerson.getData().getId());
            data.setPrevious(currentPerson);
            size++;
        }
        return data.getData().getId();

    }

}
