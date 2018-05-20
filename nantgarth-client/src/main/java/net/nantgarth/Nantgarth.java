package net.nantgarth;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import net.nantgarth.gfx.Shader;
import net.nantgarth.gfx.Window;
import net.nantgarth.input.Input;
import net.nantgarth.input.Input.MouseState;
import net.nantgarth.math.Matrix4f;

public class Nantgarth {

	public static void main(String[] args) {
		Window.initialize(1280, 720, "Dungeons of Nantgarth");
		
		GL.createCapabilities();
		
		Shader shader = Shader.load("res/shaders/test.vs", "res/shaders/test.fs");

		Matrix4f ortho = Matrix4f.ortho(-640, 640, -360, 360, -1, 1);

		float x = 0.0f * 200f;
		float y = 0.5f * 200f;
		
		shader.bind();
		shader.setMatrix4f("projection", ortho);
		while(Window.open()) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			
			if(Input.key(GLFW.GLFW_KEY_W).pressed)  System.out.println("W pressed");
			if(Input.key(GLFW.GLFW_KEY_W).released) System.out.println("W released");

			MouseState mb1 = Input.mouse(GLFW.GLFW_MOUSE_BUTTON_1);
			if(mb1.pressed)  System.out.println("Mouse 1 pressed at " + mb1.mx + ", " + mb1.my);
			if(mb1.released) System.out.println("Mouse 1 released at " + mb1.mx + ", " + mb1.my);
			
			if(mb1.held) {
				x = mb1.mx - 640f;
				y = -(mb1.my - 360f);
			}
			
			GL11.glBegin(GL11.GL_TRIANGLES);
			
			GL11.glVertex2f(-0.5f * 200f, -0.5f * 200f);
			GL11.glVertex2f( 0.5f * 200f, -0.5f * 200f);
			
			GL11.glVertex2f(x, y);
			
			GL11.glEnd();
			
			Input.update();
			Window.update();
		}
	}
}
