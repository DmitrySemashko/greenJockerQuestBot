package by.semashko.greenjokerquestbot.domain.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class User {
    @Id
    private Long id;
    @Column
    private String telegramId;
    @OneToOne
    private Game game;
}
