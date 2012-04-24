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

/**
 * 
 * @author Marcel Schwittlick
 * @date 24.04.2012
 * 
 * This is more or less private studies, but feel free to have a look.
 *
 */
public class SpaceMinusTime extends PApplet {

	PeasyCam cam;
	VerletPhysics physics;
	int roadLength = 50;
	VerletParticle[] particles;
	VerletSpring[] springs;

	public void setup() {
		size(500, 300, P3D);
		cam = new PeasyCam(this, 10);
		cam.lookAt(0, 0, 50);
		cam.rotateX(radians(90));
		cam.rotateZ(radians(180));
		cam.setActive(false);
		physics = new VerletPhysics();
		physics.addBehavior(new GravityBehavior(new Vec3D(0, 1, 0)));
		initParticles();
	}

	public void draw() {
		background(0);
		drawAxis();
		
		
		strokeWeight(1);
		stroke(170);
		physics.update();
		for(int i=0; i<springs.length; i++){
			line(particles[i].x, particles[i].y, particles[i+2].x, particles[i+2].y);
		}
		strokeWeight(3);
		stroke(255);
		for(int i=0; i<particles.length; i++){
			//point(particles[i].x, particles[i].y);
		}
		
		noStroke();
		fill(255, 90);
		beginShape(TRIANGLE_STRIP);
		for(int i=0; i<particles.length; i++){
			vertex(particles[i].x, particles[i].y);
		}
		endShape();
		
		setLastParticlesLocation();
	}

	private void setLastParticlesLocation(){
		float mappedPos = map(mouseX, 0, width, -width/2, width/2);
		particles[particles.length-1].x = mappedPos+100;
		particles[particles.length-2].x = mappedPos-100;
	}
	
	private void initParticles() {
		particles = new VerletParticle[roadLength]; 
		springs = new VerletSpring[roadLength-2];
		for(int i=0; i<roadLength; i+=2){
			particles[i] = new VerletParticle(new Vec3D(-100, 30*i, -50));
			particles[i+1] = new VerletParticle(new Vec3D(100, 30*i, -50));
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
	
	private void drawAxis(){
		strokeWeight(1);
		stroke(255, 0, 0);
		line(0, 0, 0, 20, 0, 0);
		stroke(0, 255, 0);
		line(0, 0, 0, 0, 20, 0);
		stroke(0, 0, 255);
		line(0, 0, 0, 0, 0, 20);
	}
}
