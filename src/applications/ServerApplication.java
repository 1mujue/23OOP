package applications;

import Servers.Server;
import javafx.application.Application;
import javafx.stage.Stage;
import scenes.ServerMainScene;

public class ServerApplication extends Application
{

	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		primaryStage.setTitle("服务器控制面板");
		primaryStage.setWidth(900);
		primaryStage.setHeight(600);

		primaryStage.setScene(ServerMainScene.getScene());
		primaryStage.show();

	}

	@Override
	public void stop()
	{
		Server server = Server.getServer();
		server.stopServer();
	}
}
