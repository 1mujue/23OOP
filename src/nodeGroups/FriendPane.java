package nodeGroups;

import Clients.Client;
import Clients.ClientInformation;
import Exceptions.OrderExceptions.GetClientListException;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FriendPane
{
	private Client client;

	private ClientPane allClientPane;
	private HBox friendBox;

	private static FriendPane friendPaneInstance = new FriendPane();

	private FriendPane()
	{
		friendBox = new HBox();

		allClientPane = new ClientPane();

		VBox buttonBox = new VBox(10);
		Button addButton = new Button("添加");
		Button refreshButton = new Button("刷新");
		buttonBox.getChildren().addAll(addButton, refreshButton);

		friendBox.getChildren().addAll(allClientPane.getClientListScrollPane(), buttonBox);

		addButton.setOnAction(event ->
		{
			allClientPane.getArrayList();
			Refresh();
		});

		refreshButton.setOnAction(event ->
		{
			Refresh();
		});
	}

	public static void setClient(Client client)
	{
		friendPaneInstance.client = client;
		try
		{
			for (ClientInformation clientInformation : client.getClientList("all").getClientInformations())
				friendPaneInstance.allClientPane.addClientBar(clientInformation);
		}
		catch (GetClientListException e)
		{
			e.printStackTrace();
		}
	}

	public static HBox getFriendBox()
	{
		return friendPaneInstance.friendBox;
	}

	public static void Refresh()
	{
		friendPaneInstance.allClientPane.removeAll();
		try
		{
			for (ClientInformation clientInformation : friendPaneInstance.client.getClientList("all")
					.getClientInformations())
				friendPaneInstance.allClientPane.addClientBar(clientInformation);
		}
		catch (GetClientListException e)
		{
			e.printStackTrace();
		}
	}
}
