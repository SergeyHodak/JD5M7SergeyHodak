package tests;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "person_info")
@Entity
@Data
public class PersonInfo {
    @Id
    @Column(name = "person_id")
    private long personId;

    @Column(name = "person_name")
    private String name;
}