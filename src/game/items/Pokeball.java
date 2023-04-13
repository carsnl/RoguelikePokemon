package game.items;

import engine.items.Item;
import game.utility.Status;
import game.actors.Charmander;
import game.actors.Pokemon;
import game.trading.Tradable;

/**
 * A Pokeball object that stores a Pokemon
 *
 * @author Mior Afiq, Anson Wong Sie Yuan
 * @since 16/10/2022
 */
public class Pokeball extends Item implements Tradable {

  private Pokemon pokemon;
  private Charmander charmander;

  /***
   * Constructor.
   */
  public Pokeball() {
    super("Pokeball", '0', false);
  }

  /**
   * Store Pokemon in Pokeball
   *
   * @param pokemon Pokemon to be stored in pokeball
   */
  public void storePokemon(Pokemon pokemon) {
    if (pokemon.hasCapability(Status.CATCHABLE)) {
      this.pokemon = pokemon;
    }
  }

  /**
   * Store Charmander in Pokeball
   *
   * @param charmander Charmander to be stored in pokeball
   */
  public void storeCharmander(Charmander charmander) {
    this.charmander = charmander;
  }

  /**
   * Release Pokemon from Pokeball
   *
   * @return Pokemon stored in pokeball
   */
  public Pokemon releasePokemon() {
    return this.pokemon;
  }

  /**
   * Checks if Pokeball is a tradable item
   *
   * @return true if Pokeball is a tradable item
   */
  @Override
  public boolean isTradable() {
    return true;
  }
}
