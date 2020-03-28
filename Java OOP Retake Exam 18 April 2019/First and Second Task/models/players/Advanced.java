package models.players;

import repositories.interfaces.CardRepository;

public class Advanced extends BasePlayer{
    private static final int DEFAULT_HEALTH_POINTS = 250;

    protected Advanced(CardRepository cardRepository, String username, int health) {
        super(cardRepository, username, DEFAULT_HEALTH_POINTS);
    }
}
