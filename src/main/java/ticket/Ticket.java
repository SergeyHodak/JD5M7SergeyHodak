package ticket;

import jakarta.persistence.*;
import lombok.Data;

@Entity //вказівник що це сутність, і вона має мапитись на якусь таблицю
@Table(name = "ticket") // Але це не потрібно якщо іменування здіснено згідно ковенцій. Але щей може не завжди спрацьовувати.
@Data
public class Ticket { //якщо так то перетвориться в так "SomeTicket => some_ticket"
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "passenger_id") //Вказуємо явно, як називається колонка в таблиці для цього поля
    private long passengerId; //snake_case, це типу все маленькими буквами а слова родліляються нижнім підкесленням "passenger_id"

    @Column(name = "from_planet")
    @Enumerated(EnumType.STRING) //Поле типу енам, зберігай як стрінг
    private Planet from;

    @Column(name = "to_planet")
    @Enumerated(EnumType.STRING)
    private Planet to;
}