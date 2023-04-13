package game.actors;

import engine.actions.ActionList;
import engine.actors.Actor;

import engine.positions.GameMap;
import engine.weapons.IntrinsicWeapon;
import engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.affection.AffectionManager;
import game.utility.Element;
import game.utility.Status;
import game.weapons.*;

import java.util.Random;

/**
 * Raichu is one of the available Pokemon in the game. It is an NPC that has actions and
 * behaviours.
 * <p>
 * Created by:
 *
 * @author Anson Wong Sie Yuan
 * @version 2.0
 * @since 15/10/2022
 */

public class Raichu extends Pokemon {

  /**
   * BackUpWeapon to store all the special weapon item for the Pokemon
   */
  private BackupWeapons backUpWeapons = new BackupWeapons();


  public Raichu() {
    super("Raichu", 'R', 180);
    this.addCapability(Element.ELECTRIC);
    AffectionManager.getInstance().registerPokemon(this);
    backUpWeapons.addWeapon(new Thunder());
    backUpWeapons.addWeapon(new ThunderShock());


  }

  /**
   * Returns a list of actions that can be performed by the actor
   *
   * @param otherActor the Actor that might perform an action.
   * @param direction  String representing the direction of the other Actor
   * @param map        current GameMap
   * @return list of actions
   */
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();
    actions.add(super.allowableActions(otherActor, direction, map));

    if (!otherActor.hasCapability(Element.ELECTRIC) && !otherActor.hasCapability(Status.TRAINER)) {
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
    if (isEquipping && this.getInventory().size() <= 1) {
      if (this.getInventory().size() != 0) {
        for (WeaponItem weapon : this.backUpWeapons.getInventoryWeapon()) {
          if (this.getInventory().get(0) == weapon) {
            this.removeItemFromInventory(weapon);
            break;
          }
        }
      }

      Random rand = new Random();
      int int_rand = rand.nextInt(backUpWeapons.getInventoryWeapon().size());
      WeaponItem item = backUpWeapons.getBackUpWeapon(int_rand);
      System.out.println(item);
      this.addItemToInventory(item);
    } else if (!isEquipping && this.getInventory().size() != 0) {
      for (WeaponItem weapon : this.backUpWeapons.getInventoryWeapon()) {
        if (this.getInventory().get(0) == weapon) {
          this.removeItemFromInventory(weapon);
          break;
        }
      }
    }
  }


  /**
   * Intrinsic weapon of the Pokemon
   *
   * @return Intrinsic weapon of the Pokemon
   */
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(10, "body slammed");
  }

  @Override
  /**
   * To check the condition to use the special weapon item for the Pokemon
   * @param currentActor current pokemon
   * @param opposingActor other pokemon
   * @param map the game map
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

  /**
   * @return This Pokemon's evolved form; null if no evolution (or not implemented)
   */
  @Override
  public Pokemon evolve() {
    return null;
  }
}
