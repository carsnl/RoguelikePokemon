package game.weapons;

import engine.weapons.WeaponItem;
import game.utility.Element;

/**
 * Ember is one of the available Fire-type Weapons in game
 * <p>
 * Created by:
 *
 * @author Anson Wong Sie Yuan
 * @version 1.0
 * @since 16/10/2022
 */

public class Ember extends WeaponItem {


  /**
   * Constructor
   */
  public Ember() {
    super("Ember", 'e', 20, "sparks", 90);
    this.addCapability(Element.FIRE);
  }

}
