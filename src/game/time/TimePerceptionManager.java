package game.time;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * A global Singleton manager that gives time perception  on the affected instances.
 * <p>
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Lai Carson
 */
public class TimePerceptionManager {

  /**
   * A list of polymorph instances (any classes that implements TimePerception, such as, a
   * Charmander implements TimePerception, it will be stored in here)
   */
  private final List<TimePerception> timePerceptionList;

  /**
   * A singleton instance
   */
  private static TimePerceptionManager instance = null;

  private int turn;

  private TimePeriod shift; // DAY or NIGHT

  private final int SHIFT_DURATION = 5; // How long a shift lasts

  /**
   * Private constructor
   */
  private TimePerceptionManager() {
    timePerceptionList = new ArrayList<>();
    turn = 0; // start game at turn 0
    this.shift = TimePeriod.DAY;  // turn 0 is day
  }

  /**
   * Get the singleton instance of time perception manager
   *
   * @return TimePerceptionManager singleton instance
   */
  public static TimePerceptionManager getInstance() {
    if (instance == null) {
      instance = new TimePerceptionManager();
    }
    return instance;
  }

  /**
   * Traversing through all instances in the list and execute them By doing this way, it will avoid
   * using `instanceof` all over the place.
   */
  public void run() {
    // determine if current turn is day or night
    if (turn % SHIFT_DURATION == 0 && turn != 0) {
      if (shift == TimePeriod.DAY) {
        shift = TimePeriod.NIGHT;
      } else if (shift == TimePeriod.NIGHT) {
        shift = TimePeriod.CURSED_NIGHT;
      } else if (shift == TimePeriod.CURSED_NIGHT){
        shift = TimePeriod.DAY;
      }
    }

    // print turn and shift to console
    System.out.println(String.format("It is %s. (Turn %d)", this.shift.label, turn));

    // for objects in timePerceptionList, run day or night effects
    for (TimePerception obj : timePerceptionList) {
      if (shift == TimePeriod.DAY) {
        obj.dayEffect();
      } else if (shift == TimePeriod.NIGHT) {
        obj.nightEffect();
      } else if (shift == TimePeriod.CURSED_NIGHT){
        obj.cursedNightEffect();
      }

    }
    turn += 1; // increment turn
  }


  /**
   * Add the TimePerception instance to the list
   *
   * @param objInstance any instance that implements TimePerception
   */
  public void append(TimePerception objInstance) {
    timePerceptionList.add(objInstance);
  }

  /**
   * Remove a TimePerception instance from the list
   * <p>
   * FIXME: [OPTIONAL] run cleanUp once every turn if you don't want to
   *        have too many instances in the list (e.g., memory leak)
   *
   * @param objInstance object instance
   */
  public void cleanUp(TimePerception objInstance) {
    // not implemented
  }
}
