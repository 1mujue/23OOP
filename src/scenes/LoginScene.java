package scenes;

import Clients.ClientInformation;
import Exceptions.OrderExceptions.StartClientException;
import Orders.ClientStart;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginScene
{
	private Scene loginScene;
	private Button loginButton;
	private TextField nameInput;
	private PasswordField passwordInput;
	private Text warningText;
	private Button signUpButton;
	private Stage loginStage;
	private Stage primaryStage;

	private static LoginScene loginSceneInstance = new LoginScene();

	private LoginScene()
	{
		HBox nameHBox = new HBox(10);
		nameHBox.setAlignment(Pos.CENTER);
		Text nameText = new Text("用户名:");
		nameText.setWrappingWidth(40);
		nameInput = new TextField();

		nameHBox.getChildren().addAll(nameText, nameInput);
		HBox passwordBox = new HBox(10);
		passwordBox.setAlignment(Pos.CENTER);
		Text passwordText = new Text("密码:");
		passwordText.setWrappingWidth(40);
		passwordInput = new PasswordField();
		passwordBox.getChildren().addAll(passwordText, passwordInput);

		warningText = new Text();
		warningText.setStyle("-fx-fill:red;-fx-font-weight: bold");
		warningText.setWrappingWidth(200);

		HBox loginButtons = new HBox(30);
		loginButtons.setAlignment(Pos.CENTER);
		loginButton = new Button("登录");
		Button resetButton = new Button("重置");
		signUpButton = new Button("注册");
		loginButtons.getChildren().addAll(loginButton, resetButton, signUpButton);

		VBox inputVBox = new VBox(15);
		inputVBox.setAlignment(Pos.CENTER);
		inputVBox.getChildren().addAll(nameHBox, passwordBox, warningText, loginButtons);

		loginScene = new Scene(inputVBox);


		resetButton.setOnAction(event ->
		{
			nameInput.clear();
			passwordInput.clear();
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
				new ClientStart(new ClientInformation(loginSceneInstance.nameInput.getText(),
						loginSceneInstance.passwordInput.getText(), 2)).executeOrder();
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
			//loginSceneInstance.loginStage.setScene(signUpScene);
			try
			{
				new ClientStart(new ClientInformation(loginSceneInstance.nameInput.getText(),
						loginSceneInstance.passwordInput.getText(), 1)).executeOrder();
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
