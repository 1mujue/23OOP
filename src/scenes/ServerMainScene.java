package scenes;

import Clients.ClientInformation;
import Servers.Server;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nodeGroups.ClientPane;

public class ServerMainScene
{
	private Scene mainScene;
	private Text serverState;
	private Text log;
	private IntegerProperty currentClientCount;
	private Server server;
	private ClientPane currentClientPane;

	private static ServerMainScene serverMainSceneInstance = new ServerMainScene();

	private ServerMainScene()
	{
		server = Server.getServer();
		currentClientPane = new ClientPane();

		BorderPane mainPane = new BorderPane();

		HBox controlButtons = new HBox(40);
		controlButtons.setAlignment(Pos.CENTER);
		controlButtons.setPadding(new Insets(10, 5, 10, 5));
		Button startButton = new Button("启动");
		startButton.setPrefSize(60, 40);
		Button stopButton = new Button("关闭");
		stopButton.setPrefSize(60, 40);
		controlButtons.getChildren().addAll(startButton, stopButton);

		HBox infoList = new HBox(40);
		infoList.setAlignment(Pos.CENTER_LEFT);
		HBox serverStateBox = new HBox(5);
		Text serverStateText = new Text("服务器状态：");
		serverState = new Text("关闭");
		serverState.setStyle("-fx-fill: red");
		serverStateBox.getChildren().addAll(serverStateText, serverState);
		HBox currentClientCountBox = new HBox(5);
		Text currentClientCountText = new Text("当前在线人数：");
		Label currentClientCountLabel = new Label();
		currentClientCount = new SimpleIntegerProperty(0);
		currentClientCountLabel.textProperty().bind(currentClientCount.asString());
		currentClientCountBox.getChildren().addAll(currentClientCountText, currentClientCountLabel);
		infoList.getChildren().addAll(serverStateBox, currentClientCountBox);

		VBox currentClientControllerBox = new VBox();
		HBox clientControlBottonBox = new HBox();
		Button sendButton = new Button("发送");
		Button banButton = new Button("踢出");
		clientControlBottonBox.getChildren().addAll(sendButton, banButton);
		currentClientControllerBox.getChildren().addAll(currentClientPane.getClientListScrollPane(),
				clientControlBottonBox);

		ScrollPane logScrollPane = new ScrollPane();
		log = new Text("服务器日志喵~~~\n");
		logScrollPane.setContent(log);

		mainPane.setCenter(logScrollPane);
		mainPane.setTop(infoList);
		mainPane.setBottom(controlButtons);
		mainPane.setLeft(currentClientControllerBox);

		mainScene = new Scene(mainPane);

		sendButton.setOnAction(event ->
		{

			currentClientPane.getArrayList();

//			Stage messageStage = new Stage();
//			TextField messageField = new TextField();
//			Scene messageScene = new Scene(messageField);
//			messageStage.setScene(messageScene);
//			messageStage.show();
		});
		banButton.setOnAction(event ->
		{
		});
		startButton.setOnAction(event ->
		{
			serverState.setText("开启");
			serverState.setStyle("-fx-fill: green");
			server.startServer();
		});
		stopButton.setOnAction(evene ->
		{
			serverState.setText("关闭");
			serverState.setStyle("-fx-fill: red");
			server.stopServer();
		});
	}

	public static Scene getScene()
	{
		return serverMainSceneInstance.mainScene;
	}

	public static void printMessage(String message)
	{
		if (message.charAt(message.length() - 1) != '\n')
			message += "\n";
		String newMessage = message;
		Platform.runLater(() ->
		{
			serverMainSceneInstance.log.setText(serverMainSceneInstance.log.getText() + newMessage);
		});
	}

	public static void addClient(ClientInformation clientInformation)
	{
		Platform.runLater(() ->
		{
			serverMainSceneInstance.currentClientCount.set(serverMainSceneInstance.currentClientCount.get() + 1);
			serverMainSceneInstance.currentClientPane.addClientBar(clientInformation);
		});
	}
}
