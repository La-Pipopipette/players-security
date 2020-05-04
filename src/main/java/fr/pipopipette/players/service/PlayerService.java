package fr.pipopipette.players.service;

import fr.pipopipette.players.io.PlayerOutput;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PlayerService {

    private int nextId = 0;

    @Inject JwtService jwtService;

    public Uni<PlayerOutput> create(String name) {
        final int id = getNextId();
        PlayerOutput player = PlayerOutput.builder()
                .id(id)
                .name(name)
                .jwt(jwtService.generateTokenString(id, name))
                .build();

        return Uni.createFrom().item(player);
    }

    private int getNextId() {
        return ++nextId;
    }
}
