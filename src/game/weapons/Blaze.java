package game.weapons;

import engine.weapons.WeaponItem;
import game.utility.Element;

/**
 * Blaze is one of the available Fire-type Weapons in game
 * <p>
 * Created by:
 *
 * @author Anson Wong Sie Yuan
 * @version 1.0
 * @since 16/10/2022
 */
public class Blaze extends WeaponItem {

  /**
   * Constructor.
   */

  public Blaze() {
    super("Blaze", 'b', 60, "hits", 90);
    this.addCapability(Element.FIRE);
  }

}
