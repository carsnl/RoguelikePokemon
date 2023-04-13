package game.environment;

/**
 * Fire is one of the available types of Ground in game
 * <p>
 * Created by:
 *
 * @author Lai Carson
 * @version 1.0
 * @since 16/10/2022
 */

import engine.positions.Ground;
import engine.positions.Location;
import game.utility.Element;

public class Fire extends Ground {

  private Ground ground; // old ground that was converted
  private final int FIRE_DAMAGE = 10; // damage dealt from fire
  private int age; // how many turns this Fire has been in the game

  /**
   * Constructor
   *
   * @param ground the Ground before it was turned into Fire
   */
  public Fire(Ground ground) {
    super('v');
    this.ground = ground;
    this.addCapability(Element.FIRE);
    this.age = 0;
  }

  /**
   * Overridden tick method from Location class, ticks through Fire every turn
   *
   * @param location The location of the Ground
   */
  @Override
  public void tick(Location location) {
    age += 1; // increment age
    if (age > 2) { // if more than 2 turns in game
      location.setGround(ground); // change back to original Ground
      // System.out.println("Fire removed.");
    } else if (location.containsAnActor() && !(location.getActor().hasCapability(Element.FIRE))) {
      // if location has an actor, and is not fire type
      location.getActor().hurt(FIRE_DAMAGE);
    }
  }
}
