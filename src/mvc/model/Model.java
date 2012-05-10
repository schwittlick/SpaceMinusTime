package mvc.model;

import processing.core.PApplet;
import processing.core.PVector;
import toxi.geom.AABB;
import toxi.geom.Rect;
import toxi.geom.Vec3D;
import toxi.physics.VerletParticle;
import toxi.physics.VerletPhysics;
import toxi.physics.VerletSpring;
import toxi.physics.behaviors.GravityBehavior;

public class Model {

	private final PApplet p;

	private VerletPhysics physics;

	private Ribbon ribbon;
	private int ribbonLength = 50;

	public Model(PApplet parent) {
		this.p = parent;

		physics = new VerletPhysics();
		physics.addBehavior(new GravityBehavior(new Vec3D(0, 1, 0)));
		//physics.setWorldBounds(Rect.fromCenterExtent(arg0, arg1));

		ribbon = new Ribbon(p, physics, ribbonLength, new Vec3D(0, 0, 400));

	}

	public void update() {
		physics.update();
		ribbon.setLastParticlesLocation();
	}

	/**
	 * @return PVector array containing the locations of the VerletParticles.
	 */
	public PVector[] getParticleLocations() {
		return ribbon.getParticleLocations();
	}
}
