package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;
import engine.positions.Location;
import game.actors.Pokemon;

/**
 * Replaces a Pokemon with its evolved form at the same location.
 * <p>
 * Created by:
 *
 * @author Lai Carson
 * @version 1.0
 * @since 15/10/2022
 */

public class EvolveAction extends Action {

  private Pokemon evolveInto; // stores evolved form of a pokemon

  /**
   * Constructor
   *
   * @param evolveInto evolved form of a Pokemons
   */
  public EvolveAction(Pokemon evolveInto) {
    this.evolveInto = evolveInto;
  }

  /**
   * Removes a Pokemon at a location, then adds its evolved form at the same Location
   *
   * @param actor The Pokemon to be replaced.
   * @param map   The map the actor is on.
   * @return Evolution message.
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    Location pokemonLocation = map.locationOf(actor); // initial location of unevolved
    map.removeActor(actor); // remove unevolved
    map.addActor(evolveInto, pokemonLocation); // add evolved to same location
    return actor + " has evolved into a " + evolveInto + "!";
  }

  /**
   * Evolution does not require a menu description.
   *
   * @param actor The actor performing the action.
   * @return
   */
  @Override
  public String menuDescription(Actor actor) {
    return null;
  }
}
