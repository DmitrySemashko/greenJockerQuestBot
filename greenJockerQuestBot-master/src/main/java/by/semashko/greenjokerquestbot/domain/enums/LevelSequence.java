package by.semashko.greenjokerquestbot.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum LevelSequence {

    LINEAR(0),
    SPECIFIED(1),
    ASSAULT(2),
    RANDOM(2);

    @Setter
    @Getter
    private int sequence;
}
