package models.battleFields;

import models.battleFields.interfaces.Battlefield;
import models.cards.interfaces.Card;
import models.players.Begginer;
import models.players.interfaces.Player;

public class BattleFieldImpl implements Battlefield {
    private static final int BONUS_HEALTH = 40;
    private static final int BONUS_CARD_DAMAGE_POINTS = 30;
    @Override
    public void fight(Player attackPlayer, Player enemyPlayer) {
        if (attackPlayer.isDead() || enemyPlayer.isDead()){
            throw new IllegalArgumentException("Player is dead!");
        }
        preFightPreparation(attackPlayer);
        preFightPreparation(enemyPlayer);

        getHealthPointsFromDeck(attackPlayer);
        getHealthPointsFromDeck(enemyPlayer);

        processFight(attackPlayer, enemyPlayer);
    }

    private void processFight(Player attackPlayer, Player enemyPlayer) {
        while(true) {
            int attackPlayerDamagePoints = attackPlayer.getCardRepository()
                    .getCards()
                    .stream()
                    .mapToInt(Card::getDamagePoints)
                    .sum();

            enemyPlayer.takeDamage(attackPlayerDamagePoints);
            if (enemyPlayer.isDead()) {
                return;
            }
            int enemyPlayerDamagePoints = enemyPlayer.getCardRepository()
                    .getCards()
                    .stream()
                    .mapToInt(Card::getDamagePoints)
                    .sum();
            attackPlayer.takeDamage(enemyPlayerDamagePoints);
            if (attackPlayer.isDead()) {
                return;
            }
        }
    }

    private void getHealthPointsFromDeck(Player player) {
        int healthPoints = player.getCardRepository()
                .getCards()
                .stream()
                .mapToInt(Card::getHealthPoints)
                .sum();

        player.setHealth(player.getHealth() + healthPoints);
    }

    private void preFightPreparation(Player player) {
        if (!Begginer.class.getSimpleName().equals(player.getClass().getSimpleName())){
            return;
        }
        player.setHealth(player.getHealth()+ BONUS_HEALTH);
        player.getCardRepository().getCards()
                .forEach(card ->
                         card.setDamagePoints(card.getDamagePoints() + BONUS_CARD_DAMAGE_POINTS));
    }
}