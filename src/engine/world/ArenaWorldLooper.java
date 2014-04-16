package engine.world;

import engine.GameLooper;

public class ArenaWorldLooper extends GameLooper {

	ArenaWorld myWorld;
	public ArenaWorldLooper(World currentWorld) {
		super(currentWorld);
		myWorld = (ArenaWorld) getWorld();
	}

	@Override
	public World doLoop() {
		System.out.println("dolooparenaworld");
		return null;
	}

}
