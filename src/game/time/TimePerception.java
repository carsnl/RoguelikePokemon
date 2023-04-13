package game.time;

/**
 * Created by:
 *
 * @author Riordan D. Alfredo Modified by:
 * @author Lai Carson
 */
public interface TimePerception {

  /**
   * dayEffect of TimePerception object
   */
  void dayEffect();

  /**
   * nightEffect of TimePerception object
   */
  void nightEffect();

  /**
   * cursedNightEffect of a TimePerception object
   */
  void cursedNightEffect();

  /**
   * a default interface method that register current instance to the Singleton manager. It allows
   * corresponding class uses to be affected by global reset
   */
  default void registerInstance() {
    TimePerceptionManager.getInstance().append(this);
  }
}
