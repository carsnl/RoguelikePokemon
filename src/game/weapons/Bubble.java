package game.weapons;

import engine.weapons.WeaponItem;
import game.utility.Element;

/**
 * Bubble is one of the available Water-type Weapons in game
 * <p>
 * Created by:
 *
 * @author Anson Wong Sie Yuan
 * @version 1.0
 * @since 16/10/2022
 */

public class Bubble extends WeaponItem {

  /**
   * Constructor
   */
  public Bubble() {
    super("bubble", 'b', 25, "burbles", 80);
    this.addCapability(Element.WATER);
  }

}
