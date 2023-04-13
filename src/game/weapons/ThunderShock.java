package game.weapons;

import engine.actors.Actor;
import engine.positions.Exit;
import engine.positions.Location;
import engine.weapons.WeaponItem;
import game.environment.Static;
import game.utility.Element;

/**
 * Thunder Shock is one of the available Thunder-type Weapons in game
 * <p>
 * Created by:
 *
 * @author Lai Carson, Anson Wong Sie Yuan
 * @version 1.0
 * @since 16/10/2022
 */

public class ThunderShock extends WeaponItem {

  public ThunderShock() {
    super("thunder shock", 'T', 60, "shocks", 80);
    this.addCapability(Element.ELECTRIC);
  }

  /**
   * Overridden tick method from GameMap. Ticks through ThunderShock each turn.
   *
   * @param currentLocation The location of the actor carrying this Item.
   * @param actor           The actor carrying this Item.
   */
  @Override
  public void tick(Location currentLocation, Actor actor) {
    for (Exit exit : currentLocation.getExits()) {
      if (exit.getDestination().x() == currentLocation.x()
          || exit.getDestination().y() == currentLocation.y()) {
        exit.getDestination().setGround(new Static(currentLocation.getGround()));
      }
    }
  }
}
