package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;
import game.affection.AffectionManager;
import game.items.Candy;
import game.items.Pokeball;
import game.actors.Pokemon;
import game.utility.Status;

public class CatchAction extends Action {

  /**
   * Pokemon instance
   */
  private Pokemon pokemon;
  /**
   * Constant points to decrease when trying to catch a Pokemon below the required affection point
   */
  private final int NOT_ENOUGH_AP_DECREASE_POINT = 10;

  /**
   * Required affection points to capture a Pokemon
   */
  private final int REQUIRED_AFF_POINTS = 50;

  /**
   * Allows the trainer to perform a catch action to catch the Pokemon
   *
   * @param pokemon The Pokemon to be captured
   */
  public CatchAction(Pokemon pokemon) {
    this.pokemon = pokemon;
  }

  @Override
  public String execute(Actor actor, GameMap map) {
    String print;
    if (AffectionManager.getInstance().getAffectionPoint(pokemon) >= REQUIRED_AFF_POINTS
        && pokemon.hasCapability(Status.CATCHABLE)) {

      Pokeball pokeball = new Pokeball();
      pokeball.storePokemon(pokemon);
      actor.addItemToInventory(pokeball);

      for (int i = 0; i < actor.getInventory().size(); i++) {
        System.out.println(actor.getInventory().get(i));
      }

      map.locationOf(actor).addItem(new Candy());
      map.removeActor(pokemon);
      print = pokemon + " has been captured";
    } else {
      print = AffectionManager.getInstance()
          .decreaseAffection(pokemon, NOT_ENOUGH_AP_DECREASE_POINT);
      print += "\n" + pokemon + " is not captured";
    }
    return print;

  }

  /**
   * Prints on the menu description to catch which Pokemon
   *
   * @param actor The actor performing the action.
   * @return String values
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " catch " + pokemon + "?";
  }
}
