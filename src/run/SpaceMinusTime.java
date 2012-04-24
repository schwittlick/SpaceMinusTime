package run;

import mvc.controller.Controller;
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
	Controller controller;

	public void setup() {
		size(500, 300, P3D);
		
		controller = new Controller(this);
	}

	public void draw() {
		controller.run();
		
	}
}
