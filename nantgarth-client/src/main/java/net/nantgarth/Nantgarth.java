package net.nantgarth;

import java.util.Random;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import net.nantgarth.gfx.Window;
import net.nantgarth.input.Input;
import net.nantgarth.input.Input.MouseState;
import net.nantgarth.math.Vector3f;

public class Nantgarth {

	public static void main(String[] args) {
		Window.initialize(1280, 720, "Dungeons of Nantgarth");
		
		GL.createCapabilities();
		
		Random r = new Random();
		Vector3f c1 = new Vector3f(r.nextFloat(), r.nextFloat(), r.nextFloat());
		Vector3f c2 = new Vector3f(r.nextFloat(), r.nextFloat(), r.nextFloat());
		Vector3f c3 = new Vector3f(r.nextFloat(), r.nextFloat(), r.nextFloat());
		
		while(Window.open()) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			
			if(Input.key(GLFW.GLFW_KEY_W).pressed)  System.out.println("W pressed");
			if(Input.key(GLFW.GLFW_KEY_W).released) System.out.println("W released");

			MouseState mb1 = Input.mouse(GLFW.GLFW_MOUSE_BUTTON_1);
			if(mb1.pressed)  System.out.println("Mouse 1 pressed at " + mb1.mx + ", " + mb1.my);
			if(mb1.released) System.out.println("Mouse 1 released at " + mb1.mx + ", " + mb1.my);
			
			if(mb1.pressed) {
				c1 = new Vector3f(r.nextFloat(), r.nextFloat(), r.nextFloat());
				c2 = new Vector3f(r.nextFloat(), r.nextFloat(), r.nextFloat());
				c3 = new Vector3f(r.nextFloat(), r.nextFloat(), r.nextFloat());
			}
			
			GL11.glBegin(GL11.GL_TRIANGLES);
			
			GL11.glColor3f(c1.x, c1.y, c1.z);
			GL11.glVertex2f(-0.5f, -0.5f);

			GL11.glColor3f(c2.x, c2.y, c2.z);
			GL11.glVertex2f( 0.5f, -0.5f);
			
			float x = (((float)mb1.mx / 1280f) * 2f) - 1;
			float y = (((float)mb1.my / 720f)  * 2f) - 1;
			GL11.glColor3f((x + 1f) / 2f, c3.y, (y + 1f) / 2f);
			GL11.glVertex2f(x, -y);
			
			GL11.glEnd();
			
			Input.update();
			Window.update();
		}
	}
}
