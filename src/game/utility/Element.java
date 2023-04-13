package game.utility;

/**
 * All element types available in the game.
 *
 * @author Riordan D. Alfredo Modified by: Lai Carson, Anson Wong Sie Yuan
 */
public enum Element {
  WATER("Water"),
  FIRE("Fire"),
  GRASS("Grass"),
  ELECTRIC("ELECTRIC"),
  DRAGON("DRAGON");

  private final String label;

  Element(String label) {
    this.label = label;
  }

  /**
   * @return the label text
   */
  @Override
  public String toString() {
    return label;
  }
}
