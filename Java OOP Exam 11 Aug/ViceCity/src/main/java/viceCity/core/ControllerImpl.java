package viceCity.core;

import viceCity.common.ConstantMessages;
import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;

import java.util.LinkedList;
import java.util.List;

public class ControllerImpl implements Controller {
    private final Player mainPlayer;
    private final List<Player> civilPlayers;
    private final List<Gun> guns;
    private final Neighbourhood neighbourhood;
    private static final String MAIN_PLAYER_NAME_COMMAND = "Vercetti";
    private static final String MAIN_PLAYER_NAME = "Tommy Vercetti";

    public ControllerImpl(){
        this.mainPlayer = new MainPlayer();
        this.civilPlayers = new LinkedList<>();
        this.guns = new LinkedList<>();
        this.neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        Player player = new CivilPlayer(name);
        this.civilPlayers.add(player);

        return String.format(ConstantMessages.PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun;

        if (Pistol.class.getSimpleName().equals(type)){
            gun = new Pistol(name);
        } else if (Rifle.class.getSimpleName().equals(type)){
            gun = new Rifle(name);
        } else {
            return ConstantMessages.GUN_TYPE_INVALID;
        }

        this.guns.add(gun);
        return String.format(ConstantMessages.GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {

        if (this.guns.isEmpty()){
            return ConstantMessages.GUN_QUEUE_IS_EMPTY;
        }

        String message;
        Gun gun = this.guns.get(0);
        Player civilPlayer = this.civilPlayers.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElse(null);
        if (MAIN_PLAYER_NAME_COMMAND.equals(name)){
            message = String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), MAIN_PLAYER_NAME);
            this.mainPlayer.getGunRepository().add(gun);
            this.guns.remove(gun);
        } else if (civilPlayer != null){
            message = String.format(ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), name);
            civilPlayer.getGunRepository().add(gun);
            this.guns.remove(gun);
        } else {
            message = ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST;
        }

        return message;

    }

    @Override
    public String fight() {
        int mainPlayerLifePoints = this.mainPlayer.getLifePoints();
        long civilPlayersCount = this.civilPlayers.stream().filter(p -> p.isAlive()).count();
        this.neighbourhood.action(this.mainPlayer, this.civilPlayers);

        int mainPlayerLifePointsAfterFight = this.mainPlayer.getLifePoints();
        long civilPlayersCountAfter = this.civilPlayers.stream().filter(p -> p.isAlive()).count();

        String message;
        if (mainPlayerLifePoints == mainPlayerLifePointsAfterFight &&
                civilPlayersCount == civilPlayersCountAfter){
            message = ConstantMessages.FIGHT_HOT_HAPPENED;
        } else {
            message = ConstantMessages.FIGHT_HAPPENED + System.lineSeparator();
            message += String.format(ConstantMessages.MAIN_PLAYER_LIVE_POINTS_MESSAGE, mainPlayerLifePointsAfterFight) + System.lineSeparator();
            message += String.format(ConstantMessages.MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, civilPlayersCount- civilPlayersCountAfter) + System.lineSeparator();
            message += String.format(ConstantMessages.CIVIL_PLAYERS_LEFT_MESSAGE, civilPlayersCountAfter);
        }

        return message;
    }
}
