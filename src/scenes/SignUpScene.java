package scenes;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class SignUpScene
{
	private Scene signUpScene;

	private static SignUpScene signUpSceneInstance = new SignUpScene();

	private SignUpScene()
	{
		Text miao = new Text("注册喵~~");
		Pane g = new Pane();
		g.setLayoutX(100);
		g.setLayoutY(100);
		g.getChildren().add(miao);
		signUpScene = new Scene(g);
	}

	public static Scene getScene()
	{
		return signUpSceneInstance.signUpScene;
	}

}
