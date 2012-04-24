package mvc.view;

import mvc.model.Ribbon;
import peasy.PeasyCam;
import processing.core.PApplet;
import processing.core.PVector;

public class View {

	PApplet p;

	PeasyCam cam;

	PVector[] particles;
	
	
	public View(PApplet parent) {

		this.p = parent;

		cam = new PeasyCam(p, 10);
		cam.lookAt(0, 0, 50);
		cam.rotateX(p.radians(90));
		cam.rotateZ(p.radians(180));
		cam.setActive(false);
		
	}

	public void draw() {
		p.background(0);
		drawAxis();

		p.strokeWeight(1);
		p.stroke(170);

		// -2 because there is 2 springs less than there is particles
		// this is drawing the connection of particles -> the springs itself
		for (int i = 0; i < particles.length - 2; i++) {
			p.line(particles[i].x, particles[i].y, particles[i + 2].x,
					particles[i + 2].y);
		}
		
		// drawing the particles
		p.strokeWeight(3);
		p.stroke(255);
		for (int i = 0; i < particles.length; i++) {
			// point(particles[i].x, particles[i].y); // z missing
		}

		// drawing the surface
		p.noStroke();
		p.fill(255, 90);
		p.beginShape(p.TRIANGLE_STRIP);
		for (int i = 0; i < particles.length; i++) {
			p.vertex(particles[i].x, particles[i].y);
		}
		p.endShape();

	}

	private void drawAxis() {
		p.strokeWeight(1);
		p.stroke(255, 0, 0);
		p.line(0, 0, 0, 20, 0, 0);
		p.stroke(0, 255, 0);
		p.line(0, 0, 0, 0, 20, 0);
		p.stroke(0, 0, 255);
		p.line(0, 0, 0, 0, 0, 20);
	}

	public void setParticleLocations(PVector[] particles) {
		this.particles = particles;
	}
}
