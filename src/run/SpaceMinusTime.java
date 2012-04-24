package run;

import peasy.PeasyCam;
import processing.core.PApplet;

public class SpaceMinusTime extends PApplet{
	
	PeasyCam cam;

	public void setup(){
		size(500, 300, P3D);
		cam = new PeasyCam(this, 20);
	}

	public void draw(){
		background(0);
		noStroke();
		fill(200);
		drawPlane();
	}
	
	private void drawPlane(){
		beginShape(TRIANGLE_STRIP);
		for(int i=0;i<100; i++){
			vertex(-10,  i);
			vertex(10, i);
		}
		endShape();
	}
}

