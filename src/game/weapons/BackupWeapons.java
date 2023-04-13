package game.weapons;

import engine.actors.Actor;
import engine.weapons.WeaponItem;
import game.actions.AttackAction;


import java.util.ArrayList;


/**
 * Stores Weapons of a Pokemon Created by:
 *
 * @author Riordan D. Alfredo Modified by: Anson Wong Sie Yuan
 * <p>
 * TODO: Use this class to store game.pokemon.Pokemon's weapons (special attack) permanently.
 * If a game.pokemon.Pokemon needs to use a weapon, put it into that game.pokemon.Pokemon's inventory.
 * @see Actor#getWeapon() method.
 * @see AttackAction uses getWeapon() in the execute() method.
 */
public class BackupWeapons {

  /**
   * HashMap storing special items
   */
  private ArrayList<WeaponItem> inventoryWeapon = new ArrayList<WeaponItem>(); // ArrayList

  /**
   * Adds a special item into inventoryWeapon
   *
   * @param item WeaponItem instance
   */
  public void addWeapon(WeaponItem item) {
    inventoryWeapon.add(item);

  }

  /**
   * Retrieve weapon from the inventoryWeapon
   *
   * @param index of weapon in the lsit
   * @return WeaponItem instance
   */
  public WeaponItem getBackUpWeapon(int index) {
    return inventoryWeapon.get(index);
  }

  public ArrayList<WeaponItem> getInventoryWeapon() {
    return inventoryWeapon;
  }


}
