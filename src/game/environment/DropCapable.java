package game.environment;

import engine.items.Item;
import engine.positions.Location;
import game.utility.ChanceGenerator;

/**
 * Interface for Grounds that are capable of dropping items
 * <p>
 *
 * @author Lai Carson
 * @version 1.0
 * @since 23/09/2022
 */
public interface DropCapable {

  /**
   * Drops an item at the location
   *
   * @param item     the item to drop
   * @param chance   the chance of an item dropping
   * @param location the location of the added item
   */
  default void drop(Item item, int chance, Location location) {
    ChanceGenerator gen = ChanceGenerator.getInstance();
    if (gen.chanceOutcome(chance)) {
      location.addItem(item);
    }
  }
}
