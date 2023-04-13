package game.environment;

import engine.positions.Ground;
import game.utility.Status;

/**
 * A class that represents the floor inside a building.
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Floor extends Ground {
	public Floor() {
		super('_');
		this.addCapability(Status.INDESTRUCTIBLE); // cannot be destroyed
	}
}
