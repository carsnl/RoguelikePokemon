package game.utility;

import java.util.Random;

/**
 * An utility class to generate the outcome of a probability.
 * <p>
 *
 * @author Lai Carson
 * @version 1.0
 * @since 21/09/2022
 */
public class ChanceGenerator {

  /**
   * Singleton pattern
   */
  private static ChanceGenerator instance = null;
  private Random random = new Random(); // random object

  /**
   * private constructor
   */
  private ChanceGenerator() {
  }

  /**
   * Singleton pattern
   *
   * @return a new (if instantiating the first time) or existing ChanceGenerator
   */
  public static ChanceGenerator getInstance() {
    if (instance == null) {
      instance = new ChanceGenerator();
    }
    return instance;
  }

  /***
   * Randomly determines if an event should occur based on the probability provided
   * @param chance upper bound of probability (e.g. 20 if probability is 1/20)
   * @return boolean representing outcome of probability
   */
  public boolean chanceOutcome(int chance) {
    int number = random.nextInt(chance) + 1; // a number from 1 to chance
    return number == 1; // returns a boolean if chance succeeds (odds are 1/chance)
  }
}
