package game.trading;

import engine.actions.Action;
import engine.actors.Actor;
import engine.items.Item;
import engine.positions.GameMap;
import game.items.Candy;
import game.items.Pokefruit;
import game.utility.Status;

/**
 * Action for Player to trade items with Nurse Joy
 * <p>
 * Created by:
 *
 * @author Riordan D. Alfredo Modified by:
 * @author Mior Shazryl Afiq
 * @version 2.0
 * @since 15/10/2022
 */
public class TradeAction extends Action {

  // Cost of item to be traded
  private final int cost;

  // Item to be traded
  private final Item item;

  /**
   * Constructor.
   *
   * @param item the item to be traded
   * @param cost the cost of the item
   */
  public TradeAction(Item item, int cost) {
    this.item = item;
    this.cost = cost;
  }

  /**
   * Executes the TradeAction
   *
   * @param actor the Actor performing the action
   * @param map   the GameMap containing the Actor
   * @return a String, e.g. "Player trades 1 Pokefruit for 1 Candy"
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    if (actor.getInventory().contains(new Candy())) {
      actor.removeItemFromInventory(new Candy());
      actor.addItemToInventory(item);
      return menuDescription(actor);
    } else {
      return actor + " does not have enough candies to trade.";
    }
  }

  /**
   * Returns a descriptive String
   *
   * @param actor the Actor performing the action
   * @return a String, e.g. "Player trades Pokefruit with 1 candies"
   */
  @Override
  public String menuDescription(Actor actor) {
    for (int i = 0; i < actor.getInventory().size(); i++) {
      Item item = actor.getInventory().get(i);
      if (item.hasCapability(Status.EDIBLE)) {
        Pokefruit fruit = (Pokefruit) item;
        return actor + " trades " + fruit.getElement() + item + " for " + cost + " candies";
      }
    }
    return actor + " trades " + item + " with " + cost + " candies.";
  }
}
