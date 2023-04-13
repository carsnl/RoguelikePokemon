package game.environment;

import engine.positions.Ground;
import engine.positions.Location;
import game.utility.Element;

/**
 * Static is one of the available types of Ground in game
 * <p>
 * Created by:
 *
 * @author Lai Carson
 * @version 1.0
 * @since 16/10/2022
 */

public class Static extends Ground {

  private Ground ground; // old ground that was converted
  private final int STATIC_DAMAGE = 5; // damage dealt from fire
  private int age; // how many turns this Static has been in the game

  /**
   * Constructor
   *
   * @param ground the Ground before it was turned into Static
   */
  public Static(Ground ground) {
    super('/');
    this.ground = ground;
    this.addCapability(Element.ELECTRIC);
  }

  /**
   * Overridden tick method from Location class, ticks through Static every turn
   *
   * @param location The location of the Ground
   */
  @Override
  public void tick(Location location) {
    this.age += 1; // increment age
    if (age > 3) { // if in game for over 3 turns
      location.setGround(ground); // change back to original ground
    } else if (location.containsAnActor() && !(location.getActor().hasCapability(Element.ELECTRIC)))
    // if location has an actor and is not thunder type
    {
      location.getActor().hurt(STATIC_DAMAGE);
    }
  }
}
