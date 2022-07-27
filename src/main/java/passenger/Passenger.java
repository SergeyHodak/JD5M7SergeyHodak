package passenger;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "passenger") // На яку таблицю мерджети
@Entity // Entity class, тобто класс з якого буде створюватись таблиця
@Data
public class Passenger {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Помітка що айдішку генерує база данних автоінкрементно
    @Id // Первинний ключ в таблиці
    private long id;

    @Column // Колонка в таблиці
    private String passport;

    @Column
    private String name;
}