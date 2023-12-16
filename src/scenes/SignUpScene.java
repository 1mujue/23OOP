package scenes;

import Clients.ClientInformation;
import Exceptions.OrderExceptions.StartClientException;
import Orders.ClientStart;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nodeGroups.InputField;

public class SignUpScene
{
	private Stage loginStage;

	private Scene signUpScene;
	private InputField nameInputField;
	private InputField passwordInputField;
	private InputField passwordReinputField;
	private Text warningText;
	private Button loginButton;

	final private static int TitleWidth = 100;
	private static SignUpScene signUpSceneInstance = new SignUpScene();

	private SignUpScene()
	{
		nameInputField = new InputField("请输入用户名：", TitleWidth, false);
		passwordInputField = new InputField("请输入密码：", TitleWidth, true);
		passwordReinputField = new InputField("请重新输入密码：", TitleWidth, true);

		warningText = new Text();
		warningText.setStyle("-fx-fill:red;-fx-font-weight: bold");
		warningText.setWrappingWidth(200);

		HBox signUpButtonBox = new HBox(30);
		signUpButtonBox.setAlignment(Pos.CENTER);
		loginButton = new Button("登录");
		Button resetButton = new Button("重置");
		Button signUpButton = new Button("注册");
		signUpButtonBox.getChildren().addAll(loginButton, resetButton, signUpButton);

		VBox inputBox = new VBox(15);
		inputBox.setAlignment(Pos.CENTER);
		inputBox.getChildren().addAll(nameInputField.getInputbBox(), passwordInputField.getInputbBox(),
				passwordReinputField.getInputbBox(), signUpButtonBox);

		signUpScene = new Scene(inputBox);

		signUpButton.setOnAction(event ->
		{
			try
			{
				new ClientStart(new ClientInformation(signUpSceneInstance.nameInputField.getInput(),
						signUpSceneInstance.passwordInputField.getInput(), 1)).executeOrder();
			}
			catch (StartClientException e)
			{
				signUpSceneInstance.warningText.setText(e.getMessage());
			}
		});

		resetButton.setOnAction(event ->
		{
			nameInputField.clear();
			passwordInputField.clear();
			passwordReinputField.clear();
		});
	}

	public static Scene getScene()
	{
		return signUpSceneInstance.signUpScene;
	}

	public static void initStage(Stage loginStage)
	{
		signUpSceneInstance.loginStage = loginStage;
	}

	public static void initLoginBotton(Scene loginScene)
	{
		signUpSceneInstance.loginButton.setOnAction(event ->
		{
			signUpSceneInstance.loginStage.setScene(loginScene);
		});
	}

}
