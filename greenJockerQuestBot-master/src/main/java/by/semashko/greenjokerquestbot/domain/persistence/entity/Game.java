package by.semashko.greenjokerquestbot.domain.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Game {

    @Id
    private int id;
    @Column
    private String domain;
    @Column
    private String gameId;
    @OneToOne
    private User user;

}
