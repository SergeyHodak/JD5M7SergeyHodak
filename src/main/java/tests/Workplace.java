package tests;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Table(name = "workplace")
@Entity
@Data
public class Workplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ToString.Exclude //Виключити тут туСтрінг
    @ManyToOne
    private Person person;

    private String place;
}