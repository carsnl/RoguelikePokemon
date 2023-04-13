package game.actors;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.positions.GameMap;
import engine.weapons.IntrinsicWeapon;
import engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.affection.AffectionManager;
import game.utility.ChanceGenerator;
import game.utility.Status;
import game.weapons.BackupWeapons;
import game.weapons.Bubble;
import game.time.TimePerception;
import game.utility.Element;

/**
 * Squirtle is one of the available Pokemon in the game. It is an NPC that has actions and
 * behaviours.
 * <p>
 * Created by:
 *
 * @author Lai Carson, Anson Wong Sie Yuan
 * @version 2.0
 * @since 15/10/2022
 */
public class Squirtle extends Pokemon implements TimePerception {

  //FIXME: Change it to a sorted map (is it TreeMap? HashMap? LinkedHashMap?)

  /**
   * BackUpWeapon to store all the special weapon item for the Pokemon
   */
  private BackupWeapons backUpWeapons = new BackupWeapons();

  /**
   * Amount of hp change during the day
   */
  private final int DAY_HP_CHANGE = -10;
  /**
   * Amount of hp change during the night
   */
  private final int NIGHT_HP_CHANGE = 10;
  /**
   * Amount of hp change during cursed night
   */
  private final int CHANCE_CURSE = 2; // chance to halve health (20/100 = 1/5)
  /**
   * Amount of hp change during cursed night
   */
  private final int CURSE_HEALTH_FACTOR = 2; // halve health (1/2) when cursed

  /**
   * Constructor.
   */
  public Squirtle() {
    super("Squirtle", 's', 100);
    // HINT: add more relevant behaviours here
    this.addCapability(Element.WATER);
    this.registerInstance();
    AffectionManager.getInstance().registerPokemon(this);
    backUpWeapons.addWeapon(new Bubble());
  }

  /**
   * Returns a list of actions that can be performed by the actor
   *
   * @param otherActor the Actor that might perform an action.
   * @param direction  String representing the direction of the other Actor
   * @param map        current GameMap
   * @return list of actions
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();
    actions.add(super.allowableActions(otherActor, direction, map));

    if (!otherActor.hasCapability(Element.WATER) && !otherActor.hasCapability(Status.TRAINER)) {
      actions.add(new AttackAction(this, direction));
    }
    return actions;
  }


  /**
   * Randomly picks out a weapon from the BackUpWeapon of the Pokemon is the conditions are met
   *
   * @param isEquipping boolean representing if the condition to equip weapon is met
   */
  public void toggleWeapon(boolean isEquipping) {
    if (isEquipping && this.getInventory().size() == 0) {
      WeaponItem item = backUpWeapons.getBackUpWeapon(0);
      this.addItemToInventory(item);
    } else if (!isEquipping && this.getInventory().size() == 1) {
      WeaponItem item = backUpWeapons.getBackUpWeapon(0);
      this.removeItemFromInventory(item);
    }
  }


  /**
   * Intrinsic weapon of the Pokemon
   *
   * @return Intrinsic weapon of the Pokemon
   */
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(10, "tackle");
  }


  /**
   * To check the condition to use the special weapon item for the Pokemon
   *
   * @param currentActor  current pokemon
   * @param opposingActor other pokemon
   * @param map           the game map
   * @return boolean to indicate whether the condition are met or not
   */
  public boolean addSpecialItem(Actor currentActor, Actor opposingActor, GameMap map) {
    boolean isValid = false;
    if (map.locationOf(currentActor).getGround().hasCapability(Element.WATER)
        || opposingActor.hasCapability(Element.FIRE)) {
      isValid = true;
    }
    return isValid;
  }


  /**
   * Heal or Damage Pokemon during Day (getDayHpChange() can be negative)
   */
  @Override
  public void dayEffect() {
    this.heal(DAY_HP_CHANGE);
  }

  /**
   * Heal or Damage Pokemon during Night (getNightHpChange() can be negative)
   */
  @Override
  public void nightEffect() {
    this.heal(NIGHT_HP_CHANGE);
  }

  /**
   * Change Pokemon's maximum health depending on chance and change factor.
   */
  @Override
  public void cursedNightEffect() {
    ChanceGenerator gen = ChanceGenerator.getInstance();
    if (gen.chanceOutcome(CHANCE_CURSE)) {
      this.resetMaxHp(this.getMaxHp() / CURSE_HEALTH_FACTOR);
      System.out.print(String.format("%s had its maximum health reduced by half. ", this.name));
      System.out.println(String.format("%s %s", this.name, this.printHp()));
    }
  }

  /**
   * @return This Pokemon's evolved form; null if no evolution (or not implemented)
   */
  @Override
  public Pokemon evolve() {
    return null; // evolves but not implemented
  }
}
