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

public class LoginScene
{
	private Stage loginStage;
	private Stage primaryStage;

	private Scene loginScene;
	private Button loginButton;
	private InputField nameInputField;
	private InputField passwordInputField;
	private Text warningText;
	private Button signUpButton;

	final private static int TitleWidth = 50;
	private static LoginScene loginSceneInstance = new LoginScene();

	private LoginScene()
	{

		nameInputField = new InputField("用户名：", TitleWidth, false);
		passwordInputField = new InputField("密码：", TitleWidth, true);

		warningText = new Text();
		warningText.setStyle("-fx-fill:red;-fx-font-weight: bold");
		warningText.setWrappingWidth(200);

		HBox loginButtonBox = new HBox(30);
		loginButtonBox.setAlignment(Pos.CENTER);
		loginButton = new Button("登录");
		Button resetButton = new Button("重置");
		signUpButton = new Button("注册");
		loginButtonBox.getChildren().addAll(loginButton, resetButton, signUpButton);

		VBox inputBox = new VBox(15);
		inputBox.setAlignment(Pos.CENTER);
		inputBox.getChildren().addAll(nameInputField.getInputbBox(), passwordInputField.getInputbBox(), warningText,
				loginButtonBox);

		loginScene = new Scene(inputBox);

		resetButton.setOnAction(event ->
		{
			nameInputField.clear();
			passwordInputField.clear();
		});
	}

	public static Scene getScene()
	{
		return loginSceneInstance.loginScene;
	}

	public static void initStage(Stage loginStage, Stage primaryStage)
	{
		loginSceneInstance.loginStage = loginStage;
		loginSceneInstance.primaryStage = primaryStage;
		loginSceneInstance.loginButton.setOnAction(event ->
		{
			try
			{
				new ClientStart(new ClientInformation(loginSceneInstance.nameInputField.getInput(),
						loginSceneInstance.passwordInputField.getInput(), 2)).executeOrder();
			}
			catch (StartClientException e)
			{
				loginSceneInstance.warningText.setText(e.getMessage());
			}

		});
	}

	public static void initSignUpBotton(Scene signUpScene)
	{
		loginSceneInstance.signUpButton.setOnAction(event ->
		{
			loginSceneInstance.loginStage.setScene(signUpScene);
		});
	}

	public static void changeStage()
	{
		loginSceneInstance.loginStage.close();
		loginSceneInstance.primaryStage.show();
	}

}
