package nodeGroups;

import Clients.ClientInformation;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ClientBar
{
	private ClientList clientList;
	private HBox clientBox;
	private CheckBox selectoCheckBox;
	private ClientInformation clientInformation;

	public ClientBar(ClientInformation clientInformation, ClientList clientList)
	{
		this.clientInformation = clientInformation;
		this.clientList = clientList;
		clientBox = new HBox(20);
		clientBox.setAlignment(Pos.CENTER_RIGHT);
		Text nameText = new Text(this.clientInformation.getName());
		selectoCheckBox = new CheckBox();
		clientBox.getChildren().addAll(nameText, selectoCheckBox);

		selectoCheckBox.selectedProperty().addListener((obversable, oldValue, newValue) ->
		{
			if (newValue)
				this.clientList.addClient(this.clientInformation);
			else
				this.clientList.removeClient(this.clientInformation);
		});
	}

	public HBox getClientBox()
	{
		return clientBox;
	}
}
