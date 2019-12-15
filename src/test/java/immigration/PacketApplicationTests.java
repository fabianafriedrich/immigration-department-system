package immigration;

import immigration.model.DoublyLinked;
import immigration.model.Node;
import immigration.model.People;
import immigration.model.PriorityLevels;
import immigration.repository.PeopleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacketApplicationTests {

    static DoublyLinked myList;
    static People alex, fabi, pedro, peopleDB, rach;
    static Node n1, n2, n3, n4;

    @BeforeAll
    public static void init(){
        myList = new DoublyLinked();
        alex = new People(1L, "alex","smith", new Date(), "as5625656", PriorityLevels.HIGH);
        n1 = new Node(alex);

        fabi = new People(2L,"Fabi", "smith", new Date(), "as5625656", PriorityLevels.LOW);
        n2 = new Node(fabi);

        pedro = new People(3L,"Pedro", "smith", new Date(), "as5625656", PriorityLevels.MEDIUM);
        n3 = new Node(pedro);

        rach = new People(4L,"RAch", "smith", new Date(), "as5625656", PriorityLevels.HIGH);
        n4 = new Node(rach);

    }

    @Autowired
    private PeopleRepository peopleRepository;

    @Test
    @Order(1)
    public void addAtTheStartList() {

        //Testing add at the start in the List
        myList.addFirst(n2);
        myList.addFirst(n1);
        assertEquals(n1, myList.getFirst());
    }

    @Test
    @Order(2)
    public void addAtTheEndList() {

        //Testing end at the start in the List
        myList.addLast(n3);
        assertEquals(n3, myList.getLast());


    }

    @Test
    public void Add(){
        myList.add(n1);
        myList.add(n2);
        myList.add(n3);
        myList.add(n4);

        assertEquals(myList.getSize(), 4);

    }

    @Test
    @Order(4)
    public void removeAtTheStartList() {

        Node next = myList.getFirst().getNext();
        //Testing remove at the start in the List
        myList.removeFirst();
        assertEquals(next, myList.getFirst());

    }

    @Test
    @Order(4)
    public void remove() throws Exception {
        myList.add(n1);
        myList.add(n2);
        assertEquals(myList.getSize(), 2);
        myList.remove(n1.getData());
        assertEquals(myList.getSize(), 1);
    }

    @Test
    @Order(5)
    public void size() {
        //Testing remove at the start in the List
        assertEquals(2, myList.getSize());
    }

    /*DB Tests*/
    @Test
    @Order(3)
    public void insertDB(){

        // testing if was persisted in Data Base
        if(!myList.isEmpty()){
            for(int i = 0; i <= myList.getSize(); i++) {
                People p = myList.get(i).getData();
                peopleRepository.save(p);
            }
        }

        // when
        People found01 = peopleRepository.findById(alex.getId()).orElse(null);

        // then
        assertEquals(found01.getFName(),alex.getFName());
    }

    @Test
    public void deleteDB(){

    }

    @Test
    public void getAllDB(){

    }


}
