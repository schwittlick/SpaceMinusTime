package mvc.model;

import processing.core.PApplet;
import processing.core.PVector;
import toxi.geom.Vec3D;
import toxi.physics.VerletParticle;
import toxi.physics.VerletPhysics;
import toxi.physics.VerletSpring;
import toxi.physics.behaviors.GravityBehavior;

public class Model {
	
	private final PApplet p;

	private VerletPhysics physics;
	VerletParticle[] particles;
	VerletSpring[] springs;
	private int particleCount = 50;

	public Model(PApplet parent) {
		this.p = parent;

		physics = new VerletPhysics();
		physics.addBehavior(new GravityBehavior(new Vec3D(0, 1, 0)));
		initParticles();
	}

	private void initParticles() {
		particles = new VerletParticle[particleCount];
		springs = new VerletSpring[particleCount - 2];
		for (int i = 0; i < particleCount; i += 2) {
			particles[i] = new VerletParticle(new Vec3D(-100, 30 * i, -50));
			particles[i + 1] = new VerletParticle(new Vec3D(100, 30 * i, -50));
			physics.addParticle(particles[i]);
			physics.addParticle(particles[i + 1]);
		}
		for (int i = 0; i < springs.length; i++) {
			springs[i] = new VerletSpring(particles[i], particles[i + 2], 1,
					0.01f);
			physics.addSpring(springs[i]);
		}
		particles[0].lock();
		particles[1].lock();
	}

	public void update() {
		physics.update();
		setLastParticlesLocation();
	}

	private void setLastParticlesLocation() {
		float mappedPos = p
				.map(p.mouseX, 0, p.width, -p.width / 2, p.width / 2);
		particles[particles.length - 1].x = mappedPos + 100;
		particles[particles.length - 2].x = mappedPos - 100;
	}

	/**
	 * @return PVector array containing the locations of the VerletParticles.
	 */
	public PVector[] getParticleLocations() {
		PVector[] particlesTransformed = new PVector[this.particles.length];
		for (int i = 0; i < particlesTransformed.length; i++) {
			particlesTransformed[i] = new PVector(this.particles[i].x,
					this.particles[i].y, this.particles[i].z);
		}
		return particlesTransformed;
	}
}
