import mvc.model.Ribbon;
import peasy.PeasyCam;
import processing.core.PApplet;
import processing.core.PVector;
import toxi.geom.Vec3D;
import toxi.physics.VerletPhysics;
import toxi.physics.behaviors.GravityBehavior;


public class SpaceMinusTime extends PApplet{
	
	private VerletPhysics physics;
	private Ribbon ribbon;
	private int ribbonLength = 50;
	
	PeasyCam cam;

	PVector[] particles;
	public void setup() {
		size(500, 300, P3D);
		
		physics = new VerletPhysics();
		physics.addBehavior(new GravityBehavior(new Vec3D(0, 1, 1)));
		
		ribbon = new Ribbon(this, physics, ribbonLength, new Vec3D(0, 0, 400));
		
		cam = new PeasyCam(this, 10);
		cam.lookAt(0, 0, 50);
		cam.rotateX(radians(90));
		cam.rotateZ(radians(180));
		cam.setActive(false);
	}
	
	public void draw(){
		physics.update();
		ribbon.setLastParticlesLocation();
		
		background(0);
		drawAxis();

		strokeWeight(1);
		stroke(170);

		// -2 because there is 2 springs less than there is particles
		// this is drawing the connection of particles -> the springs itself
		for (int i = 0; i < particles.length - 2; i++) {
			line(particles[i].x, particles[i].y, particles[i + 2].x,
					particles[i + 2].y);
		}
		
		// drawing the particles
		strokeWeight(3);
		stroke(255);
		for (int i = 0; i < particles.length; i++) {
			// point(particles[i].x, particles[i].y); // z missing
		}

		// drawing the surface
		noStroke();
		fill(255, 90);
		beginShape(TRIANGLE_STRIP);
		for (int i = 0; i < particles.length; i++) {
			vertex(particles[i].x, particles[i].y);
		}
		endShape();
	}
	
	private void drawAxis() {
		strokeWeight(1);
		stroke(255, 0, 0);
		line(0, 0, 0, 20, 0, 0);
		stroke(0, 255, 0);
		line(0, 0, 0, 0, 20, 0);
		stroke(0, 0, 255);
		line(0, 0, 0, 0, 0, 20);
	}
}
