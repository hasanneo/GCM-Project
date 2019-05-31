package controller;

import java.util.ArrayList;

import client.ClientConnection;

public class DataBaseController {

	private ArrayList[] queryString;
	public static ClientConnection clientCon;
	/**
	 * Setting up the client connection
	 * @param newClientConnection
	 */
	public static void InitiateClient(ClientConnection newClientConnection) {
		clientCon = newClientConnection;
	}

	public static boolean SelectFromTable() {
		boolean result=false;
		clientCon.ExecuteQuery("SELECT * FROM accounts WHERE username = john;");
		System.out.println(clientCon.GetServerObject().toString());
		return result;
	}
}
