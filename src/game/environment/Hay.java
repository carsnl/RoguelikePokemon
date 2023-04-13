package game.environment;

import engine.positions.Ground;
import game.utility.Element;

/**
 * Hay is one of the available types of Ground in game
 * <p>
 *
 * @author Lai Carson
 * @version 2.0
 * @since 21/09/2022
 */
public class Hay extends Ground {

  /**
   * Constructor.
   */
  public Hay() {
    super(',');
    this.addCapability(Element.GRASS);
  }
}
