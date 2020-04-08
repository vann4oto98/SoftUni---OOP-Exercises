package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.Collection;

public class GangNeighbourhood implements Neighbourhood {

    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        //main player shooting

        for (Gun currentGun : mainPlayer.getGunRepository().getModels()) {
            for (Player currentCivilPlayer : civilPlayers) {

                while (currentCivilPlayer.isAlive() && currentGun.canFire()) {
                    currentCivilPlayer.takeLifePoints(currentGun.fire());
                }

                if (!currentGun.canFire()){
                    break;
                }
            }
        }

        // civil players shooting

        for (Player currentCivilPlayer : civilPlayers) {
            if (!currentCivilPlayer.isAlive()){
                continue;
            }

            for (Gun currentGun : currentCivilPlayer.getGunRepository().getModels()) {

                while (mainPlayer.isAlive() && currentGun.canFire()) {
                    mainPlayer.takeLifePoints(currentGun.fire());
                }

                if (!mainPlayer.isAlive()){
                    return;
                }
            }
        }
    }
}
