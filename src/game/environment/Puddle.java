package game.environment;

import engine.positions.Ground;
import engine.positions.Location;
import game.utility.Element;
import game.time.TimePerception;
import game.time.TimePeriodEffect;

/**
 * Puddle is one of the available types of Ground in game
 * <p>
 *
 * @author Lai Carson
 * @version 1.0
 * @since 21/09/2022
 */
public class Puddle extends Ground implements TimePerception, ExpandCapable, DestroyCapable,
    CursedCapable {

  private final int CHANCE_EXPAND = 10; //(10/100 == 1/10, see ChanceGenerator)
  private final int CHANCE_DESTROY = 10; //(10/100 == 1/10, see ChanceGenerator)

  /**
   * Constructor.
   */
  public Puddle() {
    super('~');
    this.addCapability(Element.WATER);
    this.registerInstance();
  }

  /**
   * Checks time shift, then performs an action based on current capabilities
   *
   * @param location The location of the Ground
   */
  @Override
  public void tick(Location location) {
    // day night effects
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
    this.addCapability(TimePeriodEffect.DESTROYABLE);
    this.removeCapability(TimePeriodEffect.EXPANDABLE);
  }

  /**
   * Adjust capabilities during night
   */
  @Override
  public void nightEffect() {
    this.addCapability(TimePeriodEffect.EXPANDABLE);
    this.removeCapability(TimePeriodEffect.DESTROYABLE);
  }

  /**
   * Adjust capabilities during cursed night
   */
  @Override
  public void cursedNightEffect() {
    this.addCapability(TimePeriodEffect.CURSEABLE);
    this.removeCapability(TimePeriodEffect.EXPANDABLE);
  }
}
