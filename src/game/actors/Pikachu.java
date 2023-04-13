package game.actors;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.positions.GameMap;
import engine.weapons.IntrinsicWeapon;
import engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.affection.AffectionManager;
import game.behaviours.FollowBehaviour;
import game.utility.Element;
import game.utility.Status;
import game.weapons.BackupWeapons;
import game.weapons.Thunder;

/**
 * Pikachu is one of the available Pokemon in the game. It is an NPC that has actions and
 * behaviours.
 * <p>
 * Created by:
 *
 * @author Anson Wong Sie Yuan
 * @version 2.0
 * @since 16/10/2022
 */

public class Pikachu extends Pokemon {

  private BackupWeapons backUpWeapons = new BackupWeapons();


  /**
   * Constructor.
   */
  public Pikachu(Actor trainer) {
    super("Pikachu", 'p', 100);
    // HINT: add more relevant behaviours here
    this.addCapability(Element.ELECTRIC);
    AffectionManager.getInstance().registerPokemon(this);
    backUpWeapons.addWeapon(new Thunder());
    this.setBehaviours(5, new FollowBehaviour(trainer));
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

    if (!otherActor.hasCapability(Element.ELECTRIC) && !otherActor.hasCapability(Status.TRAINER)) {
      actions.add(new AttackAction(this, direction));
    }
    return actions;
  }

  @Override
  /**
   * @return This Pokemon's evolved form; null if no evolution (or not implemented)
   */
  public Pokemon evolve() {
    return new Raichu();
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
    return new IntrinsicWeapon(10, "tail whipped");
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
        || opposingActor.hasCapability(Element.WATER)) {
      isValid = true;
    }
    return isValid;
  }


}


