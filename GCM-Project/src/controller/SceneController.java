package controller;

import java.util.ArrayList;

import javafx.scene.Scene;

public class SceneController {
	private static ArrayList<Scene> stageList = new ArrayList<>();

	/**
	 * Pop next scene in stack
	 * @return stack top
	 */
	public static Scene pop() {
		Scene scene = stageList.get(0);
		stageList.remove(0);
		return scene;
	}

	/**
	 * Push scene to the top of stack
	 * @param scene added to the top of stack
	 */
	public static void push(Scene scene) {
		stageList.add(0, scene);
	}
}
