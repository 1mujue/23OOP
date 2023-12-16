package scenes;

import Clients.Client;
import Clients.ClientList;
import Exceptions.OrderExceptions.GetClientListException;
import Exceptions.OrderExceptions.SendMessageException;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ClientMainScene
{
	private static ClientMainScene mainSceneInstance = new ClientMainScene();
	private Scene mainScene;
	private BooleanProperty chatAreaToBottom;
	private VBox chatArea;
	private Client client;

	private ClientMainScene()
	{
		BorderPane mainPane = new BorderPane();

		VBox menuButtons = new VBox();
		menuButtons.setPrefWidth(70);
		menuButtons.setAlignment(Pos.TOP_CENTER);
		menuButtons.setStyle("-fx-background-color: black");
		Button chatButton = new Button("聊天");
		chatButton.setPrefSize(70, 70);
		chatButton.setStyle("-fx-background-color: black; -fx-text-fill: grey; -fx-font-weight:bold");
		Button friendButton = new Button("好友");
		friendButton.setPrefSize(70, 70);
		friendButton.setStyle("-fx-background-color: black; -fx-text-fill: grey; -fx-font-weight:bold");
		menuButtons.getChildren().addAll(chatButton, friendButton);

		VBox chatBox = new VBox(10);
		ScrollPane chatAreaScrollPane = new ScrollPane();
		chatAreaScrollPane.setPrefHeight(800);
		chatAreaScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		chatAreaScrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		chatArea = new VBox(10);
		chatArea.setAlignment(Pos.CENTER);
		TextField chatInput = new TextField();

		Text friend = new Text("你没有好友捏");

		chatAreaScrollPane.setContent(chatArea);
		chatBox.getChildren().addAll(chatAreaScrollPane, chatInput);
		chatAreaScrollPane.setFitToWidth(true);

		mainPane.setLeft(menuButtons);
		mainPane.setCenter(chatBox);
		mainScene = new Scene(mainPane);

		chatAreaToBottom = new SimpleBooleanProperty(false);
		chatAreaToBottom.addListener(event ->
		{
			if (chatAreaToBottom.get())
			{
				chatAreaScrollPane.setVvalue(1);
				chatAreaToBottom.set(false);
			}
		});

		chatInput.setOnAction(event ->
		{
			Label input = new Label(chatInput.getText());
			input.setStyle("-fx-text-fill: white;" + "-fx-background-radius: 10px;" + "-fx-background-color: green;"
					+ "-fx-border-width: 7px;" + "-fx-border-radius: 5px;" + "-fx-border-color: green;");

			ClientList clientList;
			try
			{
				clientList = client.getClientList("chat");
				clientList.setText(chatInput.getText());
				client.sendObject(clientList);
			}
			catch (GetClientListException | SendMessageException e)
			{
				e.printStackTrace();
			}

			StackPane sp = new StackPane();
			sp.getChildren().add(input);
			sp.setAlignment(Pos.TOP_RIGHT);
			chatArea.getChildren().add(sp);
			chatAreaToBottom.set(true);
			chatInput.clear();

		});

		chatButton.setOnAction(event ->
		{
			mainPane.setCenter(chatBox);

		});
		friendButton.setOnAction(event ->
		{
			mainPane.setCenter(friend);
		});

	}

	public static Scene getScene()
	{
		return mainSceneInstance.mainScene;
	}

	public static void setClient(Client client)
	{
		mainSceneInstance.client = client;
	}

	public static void printMessage(String text)
	{
		Label inputt = new Label(text);
		inputt.setStyle("-fx-text-fill: white;" + "-fx-background-radius: 10px;" + "-fx-background-color: grey;"
				+ "-fx-border-width: 7px;" + "-fx-border-radius: 5px;" + "-fx-border-color: grey;");
		StackPane spt = new StackPane();
		spt.getChildren().add(inputt);
		spt.setAlignment(Pos.TOP_LEFT);
		Platform.runLater(() ->
		{
			mainSceneInstance.chatArea.getChildren().add(spt);
			mainSceneInstance.chatAreaToBottom.set(true);
		});

	}
}
