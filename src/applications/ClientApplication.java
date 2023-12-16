package applications;

import javafx.application.Application;
import javafx.stage.Stage;
import scenes.ClientMainScene;
import stages.LoginStage;

public class ClientApplication extends Application
{

	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		primaryStage.setTitle("聊天室");

		primaryStage.setWidth(900);
		primaryStage.setHeight(600);

		Stage loginStage = LoginStage.getLoginStage(primaryStage);
		loginStage.show();

		primaryStage.setScene(ClientMainScene.getScene());
	}

	@Override
	public void stop() throws Exception
	{

	}

}