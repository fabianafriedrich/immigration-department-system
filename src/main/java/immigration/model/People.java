package immigration.model;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "people")
public class People {

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

    @Column(name ="previous")
    private Long previous;

    @Column(name ="next")
    private Long next;

    public People(){
    }
    public People(Long id, String fName, String lName, Date doa, String nPassport, PriorityLevels priorityL) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.doa = doa;
        this.nPassport = nPassport;
        this.priorityL = priorityL;

    }

}
