package nodeGroups;

import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class InputField
{
	private HBox inputBox;
	private TextField inputTextField;

	public InputField(String title, int titleWidth, boolean isPassword)
	{
		inputBox = new HBox(5);
		inputBox.setAlignment(Pos.CENTER);
		Text titleText = new Text(title);
		titleText.setWrappingWidth(titleWidth);
		if (isPassword)
			inputTextField = new PasswordField();
		else
			inputTextField = new TextField();
		inputBox.getChildren().addAll(titleText, inputTextField);
	}

	public HBox getInputbBox()
	{
		return inputBox;
	}
	
	public void clear()
	{
		inputTextField.clear();
	}

	public String getInput()
	{
		return inputTextField.getText();
	}
}
