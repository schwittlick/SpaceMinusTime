package mvc.model;

import processing.core.PApplet;
import processing.core.PVector;
import toxi.geom.Vec3D;
import toxi.physics.VerletParticle;
import toxi.physics.VerletPhysics;
import toxi.physics.VerletSpring;

public class Ribbon {
	PApplet p;
	
	VerletPhysics physics;
	int ribbonLength;
	
	Vec3D startPos;
	
	VerletParticle[] particles;
	VerletSpring[] springs;
	
	public Ribbon(PApplet parent, VerletPhysics physics, int length, Vec3D startPos){
		this.p = parent;
		this.physics = physics;
		this.ribbonLength = length;
		this.startPos = startPos;
		
		initParticles();
	}
	
	private void initParticles() {
		particles = new VerletParticle[ribbonLength];
		springs = new VerletSpring[ribbonLength - 2];
		for (int i = 0; i < ribbonLength; i += 2) {
			particles[i] = new VerletParticle(new Vec3D(-100, 30 * i, -startPos.z));
			particles[i + 1] = new VerletParticle(new Vec3D(100, 30 * i, -startPos.z));
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
	
	public void setLastParticlesLocation() {
		float mappedPos = p
				.map(p.mouseX, 0, p.width, -p.width / 2, p.width / 2);
		particles[particles.length - 1].x = mappedPos + 100;
		particles[particles.length - 2].x = mappedPos - 100;
	}

	public PVector[] getParticleLocations() {
		PVector[] particlesTransformed = new PVector[this.particles.length];
		for (int i = 0; i < particlesTransformed.length; i++) {
			particlesTransformed[i] = new PVector(this.particles[i].x,
					this.particles[i].y, this.particles[i].z);
		}
		return particlesTransformed;
	}
}
