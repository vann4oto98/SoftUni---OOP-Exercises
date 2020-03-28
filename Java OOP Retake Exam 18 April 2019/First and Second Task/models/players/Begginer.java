package models.players;

import repositories.interfaces.CardRepository;

public class Begginer extends BasePlayer {
    private static final int DEFAULT_HEALTH_POINTS = 50;

    public Begginer(CardRepository cardRepository, String username) {
        super(cardRepository, username, DEFAULT_HEALTH_POINTS);
    }
}
