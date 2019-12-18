package immigration.model;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


/*Creating class People and Table People in database*/
@Data
@Entity
@Table(name = "people")
public class People {

    private static Long counter = 1L;

    /*@Id auto increment in database*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="first_name")
    private String fName;

    @Column(name ="last_name")
    private String lName;

    @Column(name ="date_of_arrival")
    private Date doa;

    @Column(name ="n_passport")
    private String nPassport;

    @Enumerated(EnumType.STRING)
    @Column(name ="priority_level")
    private PriorityLevels priorityL;

    @Transient
    private Long previous;

    @Transient
    private Long next;

    /*Default constructor*/
    public People(){
    }
    /*Parametrized Constructor initiating variables*/
    public People(Long id, String fName, String lName, Date doa, String nPassport, PriorityLevels priorityL) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.doa = doa;
        this.nPassport = nPassport;
        this.priorityL = priorityL;

    }
    /*Parametrized Constructor initiating variables*/
    public People(String fName, String lName, Date doa, String nPassport, PriorityLevels priorityL) {
        this.id = counter;
        counter++;
        this.fName = fName;
        this.lName = lName;
        this.doa = doa;
        this.nPassport = nPassport;
        this.priorityL = priorityL;

    }
    /*Parametrized Constructor initiating variables*/
    public People(String fName, String lName, Date doa, String nPassport) {
        this.id = counter;
        counter++;
        this.fName = fName;
        this.lName = lName;
        this.doa = doa;
        this.nPassport = nPassport;

    }

}
