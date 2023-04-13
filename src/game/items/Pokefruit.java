package game.items;

import engine.items.Item;
import game.utility.Element;
import game.trading.Tradable;
import game.utility.Status;

/**
 * Class representing Pokefruit
 * <p>
 * Created by:
 *
 * @author Riordan D. Alfredo Modified by:
 * @author Mior Shazryl Afiq
 * @version 2.0
 * @since 26/09/2022
 */
public class Pokefruit extends Item implements Tradable {

  private Element element;

  /***
   * Constructor.
   * @param typeFruit the type of fruit
   */
  public Pokefruit(Element typeFruit) {
    super("Pokefruit", 'f', true);
    this.setElement(typeFruit);
    this.addCapability(Status.EDIBLE);
  }

  /**
   * Sets the element of the Pokefruit
   *
   * @return Charmander stored in pokeball
   */
  public void setElement(Element element) {
    this.element = element;
  }

  /**
   * Gets the element of the Pokefruit
   *
   * @return element of the pokefruit
   */
  public Element getElement() {
    return element;
  }

  /**
   * Checks if Pokefruit is a tradable item
   *
   * @return true if Pokefruit is a tradable item
   */
  public boolean isTradable() {
    return true;
  }
}
