package game.environment;

import engine.actors.Actor;
import engine.positions.Location;
import game.utility.ChanceGenerator;
import game.utility.Status;

/**
 * Interface for Grounds that are capable of being destroyed
 * <p>
 *
 * @author Lai Carson
 * @version 1.0
 * @since 23/09/2022
 */

public interface CursedCapable {

  /**
   * Checks if an actor is at a location. This Ground has a chance to inflict Cursed. Hurts Cursed
   * Pokemon and sets the Ground it steps on to Lava.
   *
   * @param location A particular location on the GameMap.
   */
  default void curse(Location location) {
    ChanceGenerator gen = ChanceGenerator.getInstance();
    // check if there is an actor here
    if (location.containsAnActor()) {
      Actor actor = location.getActor();
      // if not Cursed and chance succeeds
      if (!(actor.hasCapability(Status.CURSED)) && gen.chanceOutcome(2)) {
        actor.addCapability(Status.CURSED); // actor is Cursed
        System.out.println(String.format("%s is Cursed!", actor));
      }
      // if actor is Cursed
      if (actor.hasCapability(Status.CURSED)) {
        actor.hurt(3); // reduce health by 3
        System.out.print(String.format("%s is hurt by Cursed! ", actor));
        location.setGround(new Lava()); // the Ground it steps on turns to Lava
        System.out.println(
            String.format("%s leaves a trail of fire at (%d, %d)", actor, location.x(),
                location.y()));
      }
    }
  }
}
