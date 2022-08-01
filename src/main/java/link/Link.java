package link;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "link")
@Entity
@Data
public class Link {
    @Id
    @Column(name = "short_link")
    private String shortLink;

    @Column(name = "link")
    private String link;

    @Column(name = "open_count")
    private int openCount;
}