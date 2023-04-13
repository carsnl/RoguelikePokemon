package game.weapons;

import engine.actors.Actor;
import engine.positions.Exit;
import engine.positions.Location;
import engine.weapons.WeaponItem;
import game.environment.Fire;
import game.utility.Element;

/**
 * FireSpin is one of the available Fire-type Weapons in game
 * <p>
 * Created by:
 *
 * @author Lai Carson, Anson Wong Sie Yuan
 * @version 1.0
 * @since 16/10/2022
 */

public class FireSpin extends WeaponItem {

  /**
   * Constructor.
   */
  public FireSpin() {
    super("Firespin", 'F', 70, "boom", 90);
    this.addCapability(Element.FIRE);
  }

  /**
   * Overridden tick method from GameMap. Ticks through FireSpin each turn.
   *
   * @param currentLocation The location of the actor carrying this Item.
   * @param actor           The actor carrying this Item.
   */
  @Override
  public void tick(Location currentLocation, Actor actor) {
    // System.out.println("Ticking FireSpin");
    for (Exit exit : currentLocation.getExits()) {
      exit.getDestination().setGround(new Fire(currentLocation.getGround()));
    }
  }
}


