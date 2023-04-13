package game.behaviours;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.Exit;
import engine.positions.GameMap;
import game.actions.EvolveAction;
import game.actors.Pokemon;

/**
 * Determines if a Pokemon should evolve based on Actors in its surrounding.
 * <p>
 * Created by:
 *
 * @author Lai Carson
 * @version 1.0
 * @since 15/10/2022
 */

public class EvolveBehaviour implements Behaviour {

  private Pokemon evolveInto; // stores evolved form of a pokemon

  /**
   * Constructor
   *
   * @param evolveInto Stores evolved form of a Pokemon.
   */
  public EvolveBehaviour(Pokemon evolveInto) {
    this.evolveInto = evolveInto;
  }

  /**
   * Checks a Pokemon's surroundings for Pokemon, then decides if it should evolve.
   *
   * @param actor the Pokemon which may evolve.
   * @param map   the GameMap containing the Actor.
   * @return either an EvolveAction or null.
   */
  @Override
  public Action getAction(Actor actor, GameMap map) {
    boolean shouldEvolve = true;
    for (Exit exit : map.locationOf(actor).getExits()) {
      if (exit.getDestination().containsAnActor()) {
        // System.out.println("Do not evolve. Actors nearby.");
        shouldEvolve = false; // there is an actor in immediate surroundings
        break;
      }
    }
    if (shouldEvolve && evolveInto != null) { // if no surrounding actors and has evolved form
      // System.out.println("Can evolve.");
      return new EvolveAction(this.evolveInto); // evolve
    }
    return null; // there are surrounding actors, don't evolve
  }
}
