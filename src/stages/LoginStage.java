package stages;

import javafx.stage.Stage;
import scenes.LoginScene;
import scenes.SignUpScene;

public class LoginStage
{
	public static Stage getLoginStage(Stage primaryStage)
	{
		Stage loginStage = new Stage();
		loginStage.setTitle("登录界面");
		loginStage.setWidth(400);
		loginStage.setHeight(200);
		loginStage.setResizable(false);

		LoginScene.initStage(loginStage, primaryStage);
		LoginScene.initSignUpBotton(SignUpScene.getScene());
		SignUpScene.initStage(loginStage);
		SignUpScene.initLoginBotton(LoginScene.getScene());
		loginStage.setScene(LoginScene.getScene());

		return loginStage;
	}
}
