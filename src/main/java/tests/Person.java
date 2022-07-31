package tests;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Table(name = "person")
@Entity
@Data
public class Person {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    //Use separate table
    // Приєднати до ентіті інфу з іншої таблиці
//    @ElementCollection //помітка яка говорить зберігай цю інфу в окремій таблиці
//    @CollectionTable(name = "person_address", joinColumns = @JoinColumn(name = "person_id")) //в цієї додаткової таблиці буде така назва, та така колонка
//    @Column(name = "address")

    //Use the same table
    @Column(name = "addresses")
    @Convert(converter = AddressConverter.class)
    private List<String> addressList;

    @OneToOne(cascade = CascadeType.ALL) //звязок між таблицями один до одного
    @JoinColumn(name = "id", referencedColumnName = "person_id") //зовнішня залежність
    private PersonInfo personInfo;

    @OneToMany(mappedBy = "person")
    private List<Workplace> workplaces;

    @JoinTable(
            name = "person_project",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    @ManyToMany(fetch = FetchType.EAGER) //вказуючий в дужках параметр говорить, що давайте витягувати данні одразу всі
    //якщо вказати параметр FetchType.LAZY то данні будуть витягнуті часткові тільки в тому випадку коли до них звернуться
    // використання лейзи в 90% випадкі, дефолт параметр
    private Set<Project> projects;
}