package game.environment;

import engine.actors.Actor;
import engine.positions.Ground;
import game.utility.Status;

public class Wall extends Ground {

	public Wall() {
		super('#');
		this.addCapability(Status.INDESTRUCTIBLE); // cannot be destroyed
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
