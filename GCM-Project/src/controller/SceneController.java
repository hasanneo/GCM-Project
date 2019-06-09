package controller;

import java.util.ArrayList;

import javafx.scene.Scene;
/**
 * 
 * @author Hasan
 *
 *A controller to manage scenes
 */
public class SceneController {
	private static ArrayList<Scene> sceneList = new ArrayList<>();

	/**
	 * Pop next scene in stack
	 * @return stack top
	 */
	public static Scene pop() {
		Scene scene = sceneList.get(0);
		sceneList.remove(0);
		return scene;
	}

	/**
	 * Push scene to the top of stack
	 * @param scene added to the top of stack
	 */
	public static void push(Scene scene) {
		sceneList.add(0, scene);
	}
}
