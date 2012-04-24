package run;

import mvc.controller.Controller;
import processing.core.PApplet;

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
