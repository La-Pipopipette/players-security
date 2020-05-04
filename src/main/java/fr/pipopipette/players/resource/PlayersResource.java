package fr.pipopipette.players.resource;

import fr.pipopipette.players.io.PlayerOutput;
import fr.pipopipette.players.service.PlayerService;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class PlayersResource {

    @Inject PlayerService playerService;

    @POST
    public Uni<PlayerOutput> newPlayer(String name) {
        return playerService.create(name);
    }
}
