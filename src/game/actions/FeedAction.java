package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;
import game.affection.AffectionManager;
import game.items.Pokefruit;
import game.actors.Pokemon;


public class FeedAction extends Action {

  private Actor trainer;
  private Pokefruit pokefruit;
  private Pokemon pokemon;
  private final int CORRECT_POKEFRUIT_GIVEN = 20;
  private final int WRONG_POKEFRUIT_GIVEN = 10;

  /**
   * Constructor for feed action
   *
   * @param trainer   the Player on the map
   * @param pokemon   the Pokemon to feed to
   * @param pokefruit The pokefruit to be fed to the Pokemon
   */
  public FeedAction(Actor trainer, Pokemon pokemon, Pokefruit pokefruit) {
    this.trainer = trainer;
    this.pokemon = pokemon;
    this.pokefruit = pokefruit;

  }

  /**
   * Allows the Player to feed a pokefruit to the surrounding Pokemon Can only be called if Player's
   * inventory contains Pokefruit
   *
   * @param actor The actor performing the action.
   * @param map   The map the actor is on.
   * @return String value of the result after performing the feed action
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    String print = null;

    if (pokemon.hasCapability(pokefruit.getElement())) {
      print = AffectionManager.getInstance().increaseAffection(pokemon, CORRECT_POKEFRUIT_GIVEN);
      actor.removeItemFromInventory(pokefruit);
    } else {
      print = AffectionManager.getInstance().decreaseAffection(pokemon, WRONG_POKEFRUIT_GIVEN);
      actor.removeItemFromInventory(pokefruit);
      print += "\n" + pokemon + " disliked the Pokefruit (-10 affection point)";
    }
    return print;
  }

  @Override
  /**
   * Displays on the menu description to feed Pokemons
   * @param actor the Player performing the action.
   *
   */
  public String menuDescription(Actor actor) {
    return actor + " give " + pokefruit.getElement() + " pokefruit to " + pokemon + " "
        + AffectionManager.getInstance().printAp(pokemon);
  }

}

