package common;

public class ConstantMessages {

    public static final String SUCCESSFULLY_ADDED_PLAYER =
            "Successfully added player of type %s with username: %s";

    public static final String SUCCESSFULLY_ADDED_CARD =
            "Successfully added card of type %sCard with name: %s";

    public static final String SUCCESSFULLY_ADDED_PLAYER_WITH_CARDS
            = "Successfully added card: %s to user: %s";

    public static final String FIGHT_INFO
            = "Attack user health %s - Enemy user health %s";

    public static final String PLAYER_REPORT_INFO
            = "Username: %s - Health: %d - Cards %d";

    public static final String CARD_REPORT_INFO
            = "Card: %s - Damage: %d";

    public static final String DEFAULT_REPORT_SEPARATOR
            = "###";

    public static final String PLAYER_USERNAME_NULL_OR_EMPTY
            = "Player's username cannot be null or an empty string.";

    public static final String PLAYER_HEALTH_LESS_THAN_ZERO
            = "Player's health bonus cannot be less than zero.";

    public static final String DAMAGE_POINTS_LESS_THAN_ZERO
            = "Damage points cannot be less than zero.";

    public static final String CARD_NAME_NULL_OR_EMPTY
            = "Card's name cannot be null or an empty string.";

    public static final String CARD_DAMAGE_POINTS_LESS_THAN_ZERO
            = "Card's damage points cannot be less than zero.";

    public static final String CARD_HP_LESS_THAN_ZERO
            = "Card's HP cannot be less than zero.";
}
