package game.environment;

import engine.positions.Ground;
import engine.positions.Location;
import game.utility.Element;
import game.time.TimePerception;
import game.time.TimePeriodEffect;

/**
 * Lava is one of the available types of Ground in game
 * <p>
 * Created by:
 *
 * @author Riordan D. Alfredo Modified by:
 * @author Lai Carson
 * @version 2.0
 * @since 21/09/2022
 */
public class Lava extends Ground implements TimePerception, ExpandCapable, DestroyCapable,
    CursedCapable {

  private final int CHANCE_EXPAND = 10; //(10/100 == 1/10, see ChanceGenerator)
  private final int CHANCE_DESTROY = 10; //(10/100 == 1/10, see ChanceGenerator)

  /**
   * Constructor.
   */
  public Lava() {
    super('^');
    this.addCapability(Element.FIRE);
    this.registerInstance();
  }

  /**
   * Checks time shift, then performs an action based on current capabilities
   *
   * @param location The location of the Ground
   */
  @Override
  public void tick(Location location) {
    // perform action based on shift
    if (location.getGround().hasCapability(TimePeriodEffect.EXPANDABLE)) {
      expand(this, CHANCE_EXPAND, location);
    } else if (location.getGround().hasCapability(TimePeriodEffect.DESTROYABLE)) {
      destroy(CHANCE_DESTROY, location);
    } else if (location.getGround().hasCapability(TimePeriodEffect.CURSEABLE)) {
      curse(location);
    }
  }

  /**
   * Adjust capabilities during day
   */
  @Override
  public void dayEffect() {
    this.addCapability(TimePeriodEffect.EXPANDABLE);
    this.removeCapability(TimePeriodEffect.DESTROYABLE);
  }

  /**
   * Adjust capabilities during night
   */
  @Override
  public void nightEffect() {
    this.addCapability(TimePeriodEffect.DESTROYABLE);
    this.removeCapability(TimePeriodEffect.EXPANDABLE);
  }

  /**
   * Adjust capabilities during cursedNight
   */
  @Override
  public void cursedNightEffect() {
    this.removeCapability(TimePeriodEffect.DESTROYABLE);
    this.addCapability(TimePeriodEffect.CURSEABLE);
  }
}
