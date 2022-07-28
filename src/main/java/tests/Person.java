package tests;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "person")
@Entity
public class Person {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    // Приєднати до ентіті інфу з іншої таблиці
    @ElementCollection //помітка яка говорить зберігай цю інфу в окремій таблиці
    @CollectionTable(name = "person_address", joinColumns = @JoinColumn(name = "person_id")) //в цієї додаткової таблиці буде така назва, та така колонка
    @Column(name = "address")
    private List<String> addressList;
}