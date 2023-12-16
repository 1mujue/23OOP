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
	private Scene loginScene;
	private Button loginButton;
	private InputField nameInputField;
	private InputField passwordInputField;
	private Text warningText;
	private Button signUpButton;
	private Stage loginStage;
	private Stage primaryStage;

	private static LoginScene loginSceneInstance = new LoginScene();

	private LoginScene()
	{

		nameInputField = new InputField("请输入用户名：", 40, false);
		passwordInputField = new InputField("请输入密码：", 40, true);

		warningText = new Text();
		warningText.setStyle("-fx-fill:red;-fx-font-weight: bold");
		warningText.setWrappingWidth(200);

		HBox loginButtons = new HBox(30);
		loginButtons.setAlignment(Pos.CENTER);
		loginButton = new Button("登录");
		Button resetButton = new Button("重置");
		signUpButton = new Button("注册");
		loginButtons.getChildren().addAll(loginButton, resetButton, signUpButton);

		VBox inputBox = new VBox(15);
		inputBox.setAlignment(Pos.CENTER);
		inputBox.getChildren().addAll(nameInputField.getInputbBox(), passwordInputField.getInputbBox(), warningText,
				loginButtons);

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
			// loginSceneInstance.loginStage.setScene(signUpScene);
			try
			{
				new ClientStart(new ClientInformation(loginSceneInstance.nameInputField.getInput(),
						loginSceneInstance.passwordInputField.getInput(), 1)).executeOrder();
			}
			catch (StartClientException e)
			{
				loginSceneInstance.warningText.setText(e.getMessage());
			}
		});
	}

	public static void changeStage()
	{
		loginSceneInstance.loginStage.close();
		loginSceneInstance.primaryStage.show();
	}

}
