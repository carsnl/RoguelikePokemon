package game.environment;

import engine.positions.Location;
import game.utility.ChanceGenerator;

/**
 * Interface for Grounds that are capable of being destroyed
 * <p>
 *
 * @author Lai Carson
 * @version 1.0
 * @since 23/09/2022
 */
public interface DestroyCapable {

  /**
   * Destroys the ground, by setting it to dirt
   *
   * @param chance   chance of destruction
   * @param location where ground is destroyed
   */
  default void destroy(int chance, Location location) {
    ChanceGenerator gen = ChanceGenerator.getInstance();
    // if outcome based on chance is true
    if (gen.chanceOutcome(chance)) {
      // destroy
      location.setGround(new Dirt());
    }
  }
}
