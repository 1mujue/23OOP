package scenes;

import Servers.Server;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ServerMainScene
{
	private Scene mainScene;
	private Text serverState;
	private Server server;
	private Text log;

	private static ServerMainScene serverMainSceneInstance = new ServerMainScene();

	private ServerMainScene()
	{
		server = Server.getServer();

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
		IntegerProperty currentClientCount = new SimpleIntegerProperty(0);
		currentClientCountLabel.textProperty().bind(currentClientCount.asString());
		currentClientCountBox.getChildren().addAll(currentClientCountText, currentClientCountLabel);
		infoList.getChildren().addAll(serverStateBox, currentClientCountBox);

		VBox currentClientControllerBox = new VBox();
		ScrollPane currentClientListScrollPane = new ScrollPane();
		currentClientListScrollPane.setPrefSize(100, 500);
		currentClientListScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		currentClientListScrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		VBox currentClientList = new VBox();
		currentClientListScrollPane.setContent(currentClientList);
		HBox currentClientBottons = new HBox();
		Button sendButton = new Button("发送");
		Button banButton = new Button("踢出");
		currentClientBottons.getChildren().addAll(sendButton, banButton);
		currentClientControllerBox.getChildren().addAll(currentClientListScrollPane, currentClientBottons);

		sendButton.setOnAction(event ->
		{
			Stage messageStage = new Stage();
			TextField messageField = new TextField();
			Scene messageScene = new Scene(messageField);
			messageStage.setScene(messageScene);
			messageStage.show();
		});
		startButton.setOnAction(event ->
		{
			serverState.setText("开启");
			serverState.setStyle("-fx-fill: green");
			server.startServer();

//			currentClientCount.set(currentClientCount.get() + 1);
//			HBox clientBox = new HBox(20);
//			clientBox.setAlignment(Pos.CENTER_RIGHT);
//			Label testClient = new Label("芝士用户");
//			RadioButton selectoRadioButton = new RadioButton();
//			clientBox.getChildren().addAll(testClient, selectoRadioButton);
//			currentClientList.getChildren().add(clientBox);

		});
		stopButton.setOnAction(evene ->
		{
			serverState.setText("关闭");
			serverState.setStyle("-fx-fill: red");
			server.stopServer();
		});

		ScrollPane logScrollPane = new ScrollPane();
		log = new Text("服务器日志喵~~~\n");
		logScrollPane.setContent(log);

		mainPane.setCenter(logScrollPane);
		mainPane.setTop(infoList);
		mainPane.setBottom(controlButtons);
		mainPane.setLeft(currentClientControllerBox);

		mainScene = new Scene(mainPane);
	}

	public static Scene getScene()
	{
		return serverMainSceneInstance.mainScene;
	}

	public static void printMessage(String message)
	{
		if (message.charAt(message.length() - 1) != '\n')
			message += "\n";
		serverMainSceneInstance.log.setText(serverMainSceneInstance.log.getText() + message);
	}
}
