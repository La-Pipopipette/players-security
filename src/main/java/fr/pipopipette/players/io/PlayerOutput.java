package fr.pipopipette.players.io;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlayerOutput {
    private final int id;
    private final String name;
    private final String jwt;
}
