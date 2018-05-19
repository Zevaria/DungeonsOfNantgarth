package net.nantgarth.gfx;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWVidMode;

import net.nantgarth.input.Input;

public final class Window {

	private static long window = -1;

	private static GLFWKeyCallback keyCallback;
	private static GLFWMouseButtonCallback mouseButtonCallback; 
	private static GLFWCursorPosCallback cursorPosCallback;
	
	public static void initialize(int width, int height, String title) {
		if(!glfwInit()) {
			throw new RuntimeException("Failed to initialize GLFW.");
		}
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		window = glfwCreateWindow(width, height, title, 0, 0);
		if(window == 0) {
			throw new RuntimeException("Failed to create GLFW window.");
		}
		
		GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vidMode.width() - width) / 2, (vidMode.height() - height) / 2);

		glfwSetKeyCallback(window, keyCallback = new Input.Keyboard());
		glfwSetMouseButtonCallback(window, mouseButtonCallback = new Input.MouseButton());
		glfwSetCursorPosCallback(window, cursorPosCallback = new Input.MousePosition());
		
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
	}
	
	public static void update() {
		glfwSwapBuffers(window);
		glfwPollEvents();
	}
	
	public static boolean open() {
		return !glfwWindowShouldClose(window);
	}
}
