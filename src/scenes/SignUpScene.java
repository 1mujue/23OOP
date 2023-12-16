package scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import nodeGroups.InputField;

public class SignUpScene
{
	private Scene signUpScene;

	private static SignUpScene signUpSceneInstance = new SignUpScene();

	private SignUpScene()
	{
		InputField nameInputField = new InputField("请输入用户名：", 40, false);
		InputField passwordInputField = new InputField("请输入密码：", 40, true);
		InputField passwordReinputField = new InputField("请重新输入密码：", 40, true);

		VBox inputBox = new VBox(15);
		inputBox.setAlignment(Pos.CENTER);
		inputBox.getChildren().addAll(nameInputField.getInputbBox(), passwordInputField.getInputbBox(),
				passwordReinputField.getInputbBox());

		signUpScene = new Scene(inputBox);
	}

	public static Scene getScene()
	{
		return signUpSceneInstance.signUpScene;
	}

}
