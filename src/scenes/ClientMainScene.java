package scenes;

import Clients.Client;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import nodeGroups.ChatPane;
import nodeGroups.FriendPane;

public class ClientMainScene
{
	private static ClientMainScene mainSceneInstance = new ClientMainScene();
	private Scene mainScene;
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

		mainPane.setLeft(menuButtons);
		mainPane.setCenter(ChatPane.getChatBox());
		mainScene = new Scene(mainPane);

		chatButton.setOnAction(event ->
		{
			mainPane.setCenter(ChatPane.getChatBox());

		});
		friendButton.setOnAction(event ->
		{
			mainPane.setCenter(FriendPane.getFriendBox());
			FriendPane.Refresh();
		});

	}

	public static Scene getScene()
	{
		return mainSceneInstance.mainScene;
	}

	public static void setClient(Client client)
	{
		mainSceneInstance.client = client;
		ChatPane.setClient(mainSceneInstance.client);
		FriendPane.setClient(mainSceneInstance.client);
	}
}
