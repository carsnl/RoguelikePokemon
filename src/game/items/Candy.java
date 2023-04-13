package game.items;

import engine.items.Item;
import game.trading.Tradable;

/**
 * Class representing Candy
 * <p>
 * Created by:
 *
 * @author Riordan D. Alfredo Modified by:
 * @author Mior Shazryl Afiq
 * @version 2.0
 * @since 26/09/2022
 */
public class Candy extends Item implements Tradable {

  /***
   * Constructor.
   */
  public Candy() {
    super("Candy", '*', true);
  }

  /**
   * Checks if Candy is a tradable item
   *
   * @return true if Candy is a tradable item
   */
  @Override
  public boolean isTradable() {
    return true;
  }
}
