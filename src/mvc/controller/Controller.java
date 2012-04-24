package mvc.controller;

import processing.core.PApplet;
import mvc.model.Model;
import mvc.view.View;

public class Controller {

	Model model;
	View view;

	public Controller(PApplet parent) {
		model = new Model(parent);
		view = new View(parent);
	}

	public void run() {
		model.update();
		view.setParticleLocations(model.getParticleLocations());
		view.draw();
	}
}
