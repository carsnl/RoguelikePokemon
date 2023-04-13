package game.environment;

import engine.positions.Ground;
import engine.positions.Location;
import game.utility.ChanceGenerator;

/**
 * Interface for Grounds that are capable of expanding
 * <p>
 *
 * @author Lai Carson
 * @version 1.0
 * @since 23/09/2022
 */
public interface ExpandCapable {

  /**
   * Sets surrounding Locations to a particular ground
   *
   * @param ground   set surround location to this ground
   * @param chance   chance that surrounding Locations are changed
   * @param location centre of the spread
   */
  default void expand(Ground ground, int chance, Location location) {
    Surroundings surroundings = Surroundings.getInstance();
    ChanceGenerator gen = ChanceGenerator.getInstance();
    // if outcome based on chance is true
    if (gen.chanceOutcome(chance)) {
      // set all surrounding ground (if different element)
      surroundings.setSurroundings(ground, location);
    }
  }
}
