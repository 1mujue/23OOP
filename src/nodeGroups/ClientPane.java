package nodeGroups;

import java.util.ArrayList;

import Clients.ClientInformation;
import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;

public class ClientPane
{
	private ArrayList<ClientInformation> clientLArrayList;
	private ScrollPane clientListScrollPane;
	private VBox clientBox;

	public ClientPane()
	{
		clientLArrayList = new ArrayList<ClientInformation>();

		clientListScrollPane = new ScrollPane();
		clientListScrollPane.setMinSize(100, 450);
		clientListScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		clientListScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		clientBox = new VBox();
		clientListScrollPane.setContent(clientBox);

	}

	public ScrollPane getClientListScrollPane()
	{
		return clientListScrollPane;
	}

	public void addClientBar(ClientInformation clientInformation)
	{
		ClientBar clientBar = new ClientBar(clientInformation, this);
		Platform.runLater(() ->
		{
			clientBox.getChildren().add(clientBar.getClientBox());
		});

	}

	public void removeAll()
	{
		Platform.runLater(() ->
		{
			clientBox.getChildren().clear();
		});
		clientLArrayList.clear();
	}

	public void addClient(ClientInformation clientInformation)
	{
		clientLArrayList.add(clientInformation);
	}

	public void removeClient(ClientInformation clientInformation)
	{
		clientLArrayList.remove(clientInformation);
	}

	public ArrayList<ClientInformation> getArrayList()
	{
		return clientLArrayList;
	}
}
