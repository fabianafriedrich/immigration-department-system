package immigration.model;

import immigration.view.User;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * @author Fabiana Friedrich
 */
public class DoublyLinked implements DoublyLinkedInterface{

   /*Attributes. To keep track of the first element, the last element
    and the number of elements in the list*/
    private Node first;
    private Node last;
    private int size;

    /*Constructor. Default to the initial values*/
    public DoublyLinked() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }


    /*Adding a element on the first position*/
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
            //if doublylinked list is not empty it will add at the first position
            newNode.setNext(this.first);
            newNode.getData().setNext(this.first.getData().getId());
            this.first.getData().setPrevious(newNode.getData().getId());
            this.first.setPrevious(newNode);
            this.first = newNode;
            size++;

        }
    }

    /*Adding a element on the last position*/
    @Override
    public void addLast(Node newNode) {
        //if doublylinked list is empty
        if (isEmpty()) {
            this.first = newNode;
            this.last = newNode;
            size++;
            newNode.getData().setNext(null);
            newNode.getData().setPrevious(null);
            newNode.setNext(null);
            newNode.setPrevious(null);
        } else {
            //if doublylinked list is not empty it will add at the last position
            newNode.setPrevious(this.last);
            newNode.getData().setPrevious(this.last.getData().getId());
            this.last.setNext(newNode);
            this.last.getData().setNext(newNode.getData().getId());
            this.last = newNode;
            size++;
        }
    }

    /*Adding a element*/
    @Override
    public Long add(Node data) {
        //if doublylinked list is empty
        if(isEmpty()) {
            addLast(data);
        }else {
            //if doublylinked list is not empty it will add the element on the right position
            int position = 1;
            Node currentPerson = first;
            boolean positionFound = false;
            People currentData = currentPerson.getData();

            if (data.getData().getPriorityL().getPriority() == 1) {
                // If the Priority is 1 that is HIGH it will be add on the first position or after the last HIGH position
                do {
                    //if the currentData is a priority HIGH it will enter here
                    if (currentData.getPriorityL().getPriority() == 1) {
                        //If the the currentPerson has the next null it will enter
                        if(currentPerson.getData().getNext() == null) {
                            addLast(data);
                            positionFound = true;
                        }else{
                            //if the current node dont have the next null
                            //it will set the currentData with the
                            // next person of the currentPerson
                            currentData = currentPerson.getNext().getData();
                            position = position + 1;
                        }

                    }else {
                        //If is not a HIGH  priority it will be insert into the right position
                        insert(data, position);
                        positionFound = true;

                    }

                } while (!positionFound);

            }else if (data.getData().getPriorityL().getPriority() == 2) {
                // If the Priority is 2 that is MEDIUM it will be add on the position after the last HIGH if there is one

                do {
                    //if the currentData is a priority HIGH or MEDIUM it will enter here
                    if (currentData.getPriorityL().getPriority() == 1 || currentData.getPriorityL().getPriority()  == 2) {
                        //If the the currentPerson has the next null it will enter
                        if(currentPerson.getData().getNext() == null) {
                            addLast(data);
                            positionFound = true;
                        }else{
                            //if the current node dont have the next null
                            //it will set the currentData with the
                            // next person of the currentPerson
                            currentData = currentPerson.getNext().getData();
                            position += 1;
                        }
                    }else {
                        //If is not a HIGH or MEDIUM priority it will be insert into the right position
                        insert(data, position);
                        positionFound = true;
                    }

                } while (!positionFound);

            }else if (data.getData().getPriorityL().getPriority() == 3) {
                // If the Priority is 3 that is LOW it will be add on the last position
                addLast(data);

            }

        }
        return data.getData().getId();

    }

    /*Removing a element on the last position*/
    @Override
    public void removeLast() throws Exception{
        //If the list onlly have a size 1 it will throw a error
        if (size == 1){
            System.err.println("The queue only has one element it can't be remove.");
            throw new Exception();
        }else {
            //it will remove the element and set the next, previous and the last
            this.getLast().getPrevious().getData().setNext(null);
            this.last = this.last.getPrevious();
            size--;
        }
    }

    /*Removing a element on the first position*/
    @Override
    public void removeFirst() throws Exception {
        //If the list onlly have a size 1 it will throw a error
        if (size == 1){
            throw new Exception();
        } else {
            //it will remove the element and set the next, previous and the first
            this.getFirst().getNext().getData().setPrevious(null);
            this.first = this.first.getNext();
            size--;
        }
    }

    /*Removing a element*/
    @Override
    public People remove(People person) {
        Node temp = first;
        //while the temp Node getNext is not null
        while (temp.getNext() != null){
            //if the person has the same ID as the temp it will enter
            if(person.getId().equals(temp.getData().getId())){
                //It will remove from the size and it will call the removeNode() to remove the element
                size--;
                removeNode(temp);
                return temp.getData();
            }
            //it's getting the next on the temp
            temp = temp.getNext();
        }
        //if the temp Node getNext is null it will remove the last
        try {
            removeLast();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp.getData();
    }

    /*Remove a node*/
    private void removeNode(Node temp) {
        //If the temp previous is not null  means is not the first
        if(temp.getPrevious() != null){
            //it wil set the next and previous
            temp.getPrevious().setNext(temp.getNext());
            temp.getPrevious().getData().setNext(temp.getNext().getData().getId());

            temp.getNext().setPrevious(temp.getPrevious());
            temp.getNext().getData().setPrevious(temp.getPrevious().getData().getId());

        }else if(temp.getPrevious() == null){
            //if the previous is null it will it wil set the next, previous and the first
            first = first.getNext();
            first.setPrevious(null);
            first.getData().setPrevious(null);
        }else{
            //else is going to add at the last
            last = last.getPrevious();
        }
    }

    /*Remove a number of people*/
    public void removeNPeople(int quantity) {
        if(quantity > size) {
            // the quantity is bigger than the size
            //there will be print a error because we don't
            // have the quantity of elements to be removed
            System.err.println("There is less than "+quantity+" elements.");
        }
        //a loop that will remove a element on the last position
        // until the quantity of the person is bigger than the i.
        for (int i = 0; i < quantity; i++) {
            try {
                removeLast();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* Returning the first element*/
    @Override
    public Node getFirst() {
        return this.first;
    }

    /* Returning the lats element*/
    @Override
    public Node getLast() {
        return this.last;
    }

    /* Number of elements in the list*/
    @Override
    public int getSize() {
        return this.size;
    }

    /*Return the element*/
    @Override
    public Node get(Long data) {
        Node foundNode = first;

        //if isEmpty it will return null
        if(isEmpty()) {
            return null;
        }
        //if is the data ID is 1 it will return the first
        if(data == 1) {
            return foundNode;
        }
        //loop to search for the element
        for(int i = 2; i <= size; i++){
            if(i == data){
                foundNode = first.getNext();
            }
        }
        return foundNode;
    }

    /*Return the position of a person on the queue*/
    public Long findPosition(Long id){
        //checking if is empty
        if(isEmpty()) {
            System.err.println("The Queue is empty");

        }
        Node currentPerson = first;
        //a loop to Find the position of a person on the queue
        for(Long i = 1L; i <= getSize(); i++) {
            //if the current person that start on the first
            // has the same ID of the person we are looking for
            if(currentPerson.getData().getId() == id) {
                return i;
            }
            //seting the currentPerson with the next
            currentPerson = currentPerson.getNext();
        }

        //if we didn't find it, then return -1
        return -1L;

    }

    /*Checking if is empty*/
    @Override
    public boolean isEmpty() {
        if(this.size == 0){
            return true;
        }
        return false;
    }

    /*Insert Node when adding*/
    public Long insert(Node data, int position) {
        //if the position is bigger than the size it can't be insert
        if(position > getSize() + 1 ) {
            System.err.println("Impossible to insert into this position");
        }else if (position <= 0) {
            //if the position is less or equal to 0
            System.err.println("Error when inserting");
        }else if (position == getSize() + 1) {
            //if the position is equal to size + 1 add last
            addLast(data);
        }else if (position == 1) {
            //if the position is equal to size add first
            addFirst(data);
        }else {
            Node currentPerson = first;

            //loop to see where is the position
            for(int i = 2; i < position; i++) {
                currentPerson = currentPerson.getNext();
            }

            //setting next and previous
            Node nodeNextPerson = currentPerson.getNext();
            People nextPerson = currentPerson.getNext().getData();

            currentPerson.setNext(data);
            currentPerson.getData().setNext(data.getData().getId());

            nextPerson.setPrevious(data.getData().getId());
            nodeNextPerson.setPrevious(data);

            //set the data next an previous
            data.getData().setNext(nextPerson.getId());
            data.setNext(nodeNextPerson);

            data.getData().setPrevious(currentPerson.getData().getId());
            data.setPrevious(currentPerson);
            size++;
        }
        return data.getData().getId();

    }

}
