package game.actions;

import java.util.Random;

import engine.actions.Action;
import engine.actions.ActionList;
import engine.actors.Actor;
import engine.positions.GameMap;
import engine.items.Item;
import engine.weapons.Weapon;
import game.actors.Pokemon;

/**
 * An Action to attack another Actor. Created by:
 *
 * @author Riordan D. Alfredo Modified by: Anson Wong
 */
public class AttackAction extends Action {

  /**
   * The Actor that is to be attacked
   */
  protected Actor target;

  /**
   * The direction of incoming attack.
   */
  protected String direction;

  /**
   * Random number generator
   */
  protected Random rand = new Random();

  /**
   * Constructor.
   *
   * @param target    the Actor to attack
   * @param direction the direction to attack the target
   */
  public AttackAction(Actor target, String direction) {
    this.target = target;
    this.direction = direction;
  }

  /**
   * Allows a pokemon to attack other pokemon and PLayer Will toggle between the weapons in
   * BackUpWeapon if conditions are met Will use the returned weapon to deal damage to other Pokemon
   * or actor if condition not met, will use intrinsic weapon of the pokemon
   *
   * @param actor The actor performing the action.
   * @param map   The map the actor is on.
   * @return String value showing actions in a battles
   */
  @Override
  public String execute(Actor actor, GameMap map) {

    boolean valid = false;
    Pokemon pokemon = (Pokemon) actor;
    valid = pokemon.addSpecialItem(actor, target, map);

    pokemon.toggleWeapon(valid);

    Weapon weapon = actor.getWeapon();

    if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
      return actor + " misses " + target + ".";
    }

    int damage = weapon.damage();
    String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
    target.hurt(damage);
    if (!target.isConscious()) {
      ActionList dropActions = new ActionList();
      // drop all items
        for (Item item : target.getInventory()) {
            dropActions.add(item.getDropAction(actor));
        }
        for (Action drop : dropActions) {
            drop.execute(target, map);
        }
      map.removeActor(target);
      result += System.lineSeparator() + target + " is killed.";
    }

    return result;
  }

  /**
   * Attacking is not shown on the menu description Actions that have been taken will be printed
   * afterwards
   *
   * @param actor The actor performing the action.
   * @return String value
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " attacks " + target + " at " + direction;
  }
}
