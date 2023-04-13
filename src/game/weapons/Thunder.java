package game.weapons;

import engine.weapons.WeaponItem;
import game.utility.Element;

/**
 * Thunder is one of the available Thunder-type Weapons in game
 * <p>
 * Created by:
 *
 * @author Anson Wong Sie Yuan
 * @version 1.0
 * @since 16/10/2022
 */
public class Thunder extends WeaponItem {

  /**
   * Constructor.
   */
  public Thunder() {
    super("Thunder", 't', 20, "zaps", 90);
    this.addCapability(Element.ELECTRIC);
  }
}
