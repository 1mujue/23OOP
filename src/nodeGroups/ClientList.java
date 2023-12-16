package nodeGroups;

import java.util.ArrayList;

import Clients.ClientInformation;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;

public class ClientList
{
	private ArrayList<ClientInformation> clientLArrayList;
	private ScrollPane clientListScrollPane;
	private VBox currentClientList;

	public ClientList()
	{
		clientLArrayList = new ArrayList<ClientInformation>();

		clientListScrollPane = new ScrollPane();
		clientListScrollPane.setPrefSize(100, 500);
		clientListScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		clientListScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		currentClientList = new VBox();
		clientListScrollPane.setContent(currentClientList);

	}

	public ScrollPane getClientListScrollPane()
	{
		return clientListScrollPane;
	}

	public void addClientBar(ClientInformation clientInformation)
	{
		ClientBar clientBar = new ClientBar(clientInformation, this);
		currentClientList.getChildren().add(clientBar.getClientBox());
	}

	public void removeClientBar(ClientBar clientBar)
	{
		currentClientList.getChildren().remove(clientBar.getClientBox());
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
