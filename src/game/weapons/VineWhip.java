package game.weapons;

import engine.weapons.WeaponItem;
import game.utility.Element;

/**
 * VineWhip is one of the available Grass-type Weapons in game
 * <p>
 * Created by:
 *
 * @author Anson Wong Sie Yuan
 * @version 1.0
 * @since 16/10/2022
 */

public class VineWhip extends WeaponItem {

  /**
   * Enum element
   */
  private Element element;

  /**
   * Constructor
   */
  public VineWhip() {
    super("VinWhips", 'v', 30, "whips", 70);
    this.addCapability(Element.GRASS);
  }


}
