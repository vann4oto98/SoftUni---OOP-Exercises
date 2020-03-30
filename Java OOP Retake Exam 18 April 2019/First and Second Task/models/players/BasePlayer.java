package models.players;

import models.players.interfaces.Player;
import repositories.interfaces.CardRepository;

import static common.ConstantMessages.*;

public abstract class BasePlayer implements Player {
    private static final int HEALTH_POINTS_MIN = 0;
    private static final int DAMAGE_POINTS_MIN = 0;

    private String username;
    private int health;
    private CardRepository cardRepository;
    private boolean isDead;


    protected BasePlayer(CardRepository cardRepository, String username, int health){
        this.cardRepository = cardRepository;
        this.setUsername(username);
        this.setHealth(health);
        this.setDead(false);
    }

    @Override
    public CardRepository getCardRepository() {
        return this.cardRepository;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    private void setUsername(String username) {
        if (username == null || username.trim().isEmpty()){
            throw new IllegalArgumentException(PLAYER_USERNAME_NULL_OR_EMPTY);
        }
        this.username = username;
    }

    private void setDead(boolean dead) {
        this.isDead = dead;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int healthPoints) {
        if (healthPoints < HEALTH_POINTS_MIN) {
            throw new IllegalArgumentException(PLAYER_HEALTH_LESS_THAN_ZERO);
        }

        this.health = healthPoints;
    }

    @Override
    public boolean isDead() {
        return this.isDead;
    }

    @Override
    public void takeDamage(int damagePoints) {
        if (damagePoints < DAMAGE_POINTS_MIN) {
            throw new IllegalArgumentException(DAMAGE_POINTS_LESS_THAN_ZERO);
        }
        this.health -= damagePoints;

        if (this.health <= HEALTH_POINTS_MIN){
            this.health = HEALTH_POINTS_MIN;
            setDead(true);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(PLAYER_REPORT_INFO,
                this.getUsername(),
                this.getHealth(),
                this.getCardRepository().getCount()))
                .append(System.lineSeparator());
        this.getCardRepository().getCards().forEach(card -> sb.append(card.toString())
                .append(System.lineSeparator()));
        sb.append(DEFAULT_REPORT_SEPARATOR);

        return sb.toString().trim();
    }
}
