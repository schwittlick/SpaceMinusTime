package run;

import peasy.PeasyCam;
import processing.core.PApplet;
import toxi.geom.Rect;
import toxi.geom.Vec2D;
import toxi.geom.Vec3D;
import toxi.physics.VerletParticle;
import toxi.physics.VerletPhysics;
import toxi.physics.VerletSpring;
import toxi.physics.behaviors.GravityBehavior;

public class SpaceMinusTime extends PApplet {

	PeasyCam cam;
	VerletPhysics physics;
	int roadLength = 300;
	VerletParticle[] particles;
	VerletSpring[] springs;

	public void setup() {
		size(500, 300, P3D);
		cam = new PeasyCam(this, 200);
		physics = new VerletPhysics();
		physics.addBehavior(new GravityBehavior(new Vec3D(0, 0.5f, 0)));
		initParticles();
	}

	public void draw() {
		background(0);
		strokeWeight(1);
		stroke(170);
		physics.update();
		for(int i=0; i<springs.length; i++){
			line(particles[i].x, particles[i].y, particles[i+2].x, particles[i+2].y);
		}
		strokeWeight(3);
		stroke(255);
		for(int i=0; i<particles.length; i++){
			point(particles[i].x, particles[i].y);
		}
	}

	private void initParticles() {
		particles = new VerletParticle[roadLength]; 
		springs = new VerletSpring[roadLength-2];
		for(int i=0; i<roadLength; i+=2){
			particles[i] = new VerletParticle(new Vec3D(-30, 30*i, 0));
			particles[i+1] = new VerletParticle(new Vec3D(30, 30*i, 0));
			physics.addParticle(particles[i]);
			physics.addParticle(particles[i+1]);
		}
		for(int i=0;i<springs.length; i++){
			springs[i] = new VerletSpring(particles[i], particles[i+2], 1, 0.01f);
			physics.addSpring(springs[i]);
		}
		particles[0].lock();
		particles[1].lock();	
	}
}
