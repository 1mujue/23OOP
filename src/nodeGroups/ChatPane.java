package nodeGroups;

import Clients.Client;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ChatPane
{
	Client client;

	private HBox chatBox;
	private VBox chatArea;
	private Boolean chatAreaToBottom;

	private static ChatPane chatPaneInstance = new ChatPane();

	public ChatPane()
	{
		chatBox = new HBox();

		ClientPane friendClientPane = new ClientPane();

		VBox messageBox = new VBox(10);
		messageBox.setPrefWidth(650);
		ScrollPane chatAreaScrollPane = new ScrollPane();
		chatAreaScrollPane.setPrefHeight(600);
		chatAreaScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		chatAreaScrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		chatArea = new VBox(10);
		TextField chatInput = new TextField();

		chatAreaScrollPane.setContent(chatArea);
		chatAreaScrollPane.setFitToWidth(true);
		messageBox.getChildren().addAll(chatAreaScrollPane, chatInput);

		chatBox.getChildren().addAll(friendClientPane.getClientListScrollPane(), messageBox);

		chatAreaToBottom = false;
		chatArea.heightProperty().addListener(event ->
		{
			if (chatAreaToBottom)
			{
				chatAreaScrollPane.setVvalue(1);
				chatAreaToBottom = false;
			}
		});

		chatInput.setOnAction(event ->
		{
			Label input = new Label(chatInput.getText());
			input.setStyle("-fx-text-fill: white;" + "-fx-background-radius: 10px;" + "-fx-background-color: green;"
					+ "-fx-border-width: 7px;" + "-fx-border-radius: 5px;" + "-fx-border-color: green;");

//			ClientList clientList;
//			try
//			{
//				clientList = client.getClientList("chat");
//				clientList.setText(chatInput.getText());
//				client.sendObject(clientList);
//			}
//			catch (GetClientListException | SendMessageException e)
//			{
//				e.printStackTrace();
//			}

			HBox chatGroupBox = new HBox();
			chatGroupBox.getChildren().add(input);
			chatGroupBox.setAlignment(Pos.TOP_RIGHT);
			chatArea.getChildren().add(chatGroupBox);
			chatAreaToBottom = true;
			chatInput.clear();

		});
	}

	public static HBox getChatBox()
	{
		return chatPaneInstance.chatBox;
	}

	public static void setClient(Client client)
	{
		chatPaneInstance.client = client;
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
			chatPaneInstance.chatArea.getChildren().add(spt);
			chatPaneInstance.chatAreaToBottom = true;
		});

	}
}
