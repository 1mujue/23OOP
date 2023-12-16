package Servers;

import Clients.ClientInformation;
import Clients.ClientList;
import SpecialClass.InformationChange;
import Exceptions.OrderExceptions.*;
import Lock.ClientListLock;
import Orders.*;
import SpecialClass.Message;
import Tool.AddOrderTool;
import Tool.DeserializeTool;
import Tool.InitiateFileTool;
import Tool.SerializeTool;
import scenes.ServerMainScene;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author 赵龙淳
 * @version 1.0
 * @description: this is the server.
 * @date 2023/12/9 23:11
 */
public class Server
{
	private static final Server server = new Server();
	public final int max_order_num = 10;
	private final int PORT = 9999;
	public int order_num = 0;
	private boolean isStart = false;
	private ServerSocket serverSocket;
	private Socket temp_socket;
	private ArrayList<ClientThread> ClientThreads = new ArrayList<>();
	private ArrayList<IOrder> orders = new ArrayList<>();
	private ClientList dynamic_client_informations;// online client information.
	private ClientList static_client_informations;// all client information.
	private ArrayList<ClientList> clientLists;// the list of all clients' friend list.
	private ObjectInputStream OIS;// temporary
	private ObjectOutputStream OOS;// temporary

	private Server()
	{
		dynamic_client_informations = new ClientList("all");
		clientLists = new ArrayList<>();
		new InitiateFileTool(clientLists, "./FriendLists.txt").executeTool();
		clientLists = (ArrayList<ClientList>) new DeserializeTool("./FriendLists.txt").executeTool();
		if (clientLists == null)
		{
			clientLists = new ArrayList<>();
		}
		static_client_informations = new ClientList("all");
		new InitiateFileTool(static_client_informations, "./Server.txt").executeTool();
		static_client_informations = (ClientList) new DeserializeTool("./Server.txt").executeTool();
		if (static_client_informations == null)
		{
			static_client_informations = new ClientList("static");
		}
	}

	public static Server getServer()
	{
		return server;
	}

	public boolean getState()
	{
		return this.isStart;
	}

	public void addStaticClientInformation(ClientInformation clientInformation)
	{
		static_client_informations.getClientInformations().add(clientInformation);
	}

	public void removeStaticClientInformation(ClientInformation clientInformation)
	{
		static_client_informations.deleteClientInformation(clientInformation);
	}

	public void addDynamicClientInformation(ClientInformation clientInformation)
	{
		dynamic_client_informations.getClientInformations().add(clientInformation);
	}

	public void removeDynamicClientInformation(ClientInformation clientInformation)
	{
		dynamic_client_informations.deleteClientInformation(clientInformation);
	}

	public void addClientList(ClientList clientList)
	{
		this.clientLists.add(clientList);
	}

	public ClientList getStatic_client_informations()
	{
		return static_client_informations;
	}

	public ClientList getDynamic_client_informations()
	{
		return this.dynamic_client_informations;
	}

	public ClientList getClientList(ClientInformation clientInformation, String type) throws GetClientListException
	{
		for (ClientList clientList : clientLists)
		{
			if (clientList.getType().equals(type)
					&& clientList.getOwner_client_information().getName().equals(clientInformation.getName()))
			{
				return clientList;
			}
		}
		throw new GetClientListException();
	}

	public void addOrder(IOrder order)
	{
		orders.add(order);
		order_num++;
	}// do not use it directly!

	public void printMessage(String text)
	{
		System.out.println(text);
		ServerMainScene.printMessage(text);
	}

	public void initServer()
	{
		String text;
		text = "initiate server success!";
		printMessage(text);
	}

	public void waitStart()
	{
		Server.CheckServer checkServer = this.new CheckServer();
		checkServer.run();
		this.establishConnection();
	}

	public void startServer()
	{
		try
		{
			String text;
			if (serverSocket == null)
			{
				serverSocket = new ServerSocket(PORT);
				isStart = true;
				new Thread(new ServerThread()).start();
				text = "Start server success!";
				printMessage(text);
			}
		}
		catch (IOException e)
		{
			System.out.println("Socket exception in start server");
			throw new RuntimeException(e);
		}
	}

	public void stopServer()
	{
		isStart = false;
		try
		{
			String text;
			if (serverSocket != null)
			{
				serverSocket.close();
			}
			text = "server disconnect";
			printMessage(text);
			new SerializeTool(Server.server.static_client_informations, "Server.txt").executeTool();
		}
		catch (IOException e)
		{
			System.out.println("Socket exception in stop server");
			throw new RuntimeException(e);
		}
	}

	public void endServer()
	{
		isStart = false;
		try
		{
			String text;
			if (serverSocket != null)
			{
				serverSocket.close();
			}
			text = "server end";
			printMessage(text);
			new SerializeTool(Server.server.static_client_informations, "Server.txt").executeTool();
			System.exit(0);
		}
		catch (IOException e)
		{
			System.out.println("Socket exception in stop server");
			throw new RuntimeException(e);
		}
	}

	public void establishConnection()
	{
		try
		{
			String text;
			while (isStart)
			{
				// try to connect with a client.
				temp_socket = serverSocket.accept();
				text = "Establish connection!";
				printMessage(text);

				// here, we are going to get the information of client to register or log in.
				OIS = new ObjectInputStream(temp_socket.getInputStream());
				OOS = new ObjectOutputStream(temp_socket.getOutputStream());
				Object temp;

				ClientInformation clientInformation = null;
				try
				{
					temp = OIS.readObject();
				}
				catch (ClassNotFoundException e)
				{
					System.out.println("Get client information from client to server failed");
					throw new RuntimeException(e);
				}
				if (temp instanceof ClientInformation)
				{
					clientInformation = (ClientInformation) temp;
				}
				try
				{
					if (clientInformation != null)
					{
						if (clientInformation.getTag() == 1)
						{
							new RegisterClient(clientInformation).executeOrder();// judge whether can we register
							Server.getServer().addStaticClientInformation(clientInformation);
							Server.getServer().addDynamicClientInformation(clientInformation);
							new SerializeTool(static_client_informations, "./Server.txt").executeTool();// a new client!
						}
						else if (clientInformation.getTag() == 2)
						{
							new Login(clientInformation).executeOrder();// judge whether can we log in
							Server.getServer().addDynamicClientInformation(clientInformation);
						}

						OOS.writeObject("success");// caution: we have not established client socket yet,
						// so we can not use the sendMessage() method!

						ClientList friend_client_list = null;// get friend list.
						try
						{
							friend_client_list = getClientList(clientInformation, "friend");
						}
						catch (GetClientListException e)
						{
							System.out.println("The new client does not have a client list");
							friend_client_list = new ClientList(clientInformation, "friend");
							addClientList(friend_client_list);
							new SerializeTool(clientLists, "./FriendLists.txt").executeTool();// make a record.
						}

						ClientSocket clientSocket = new ClientSocket(clientInformation, temp_socket, OIS, OOS);
						this.sendObject(clientSocket, friend_client_list);// send friend client list to client.
						this.sendObject(clientSocket, dynamic_client_informations);// send all online client list to
																					// client.

						connectClient(clientSocket);// connect

					}
				}
				catch (CreatClientException e)
				{
					OOS.writeObject("failed");
					System.out.println("Register client failed in establish connection");
					temp_socket.shutdownOutput();
					temp_socket.shutdownInput();
					temp_socket.close();
				}
				catch (LoginException e)
				{
					OOS.writeObject("failed");
					System.out.println("Log in failed in establish connection");
					temp_socket.shutdownOutput();
					temp_socket.shutdownInput();
					temp_socket.close();
				}
			}
		}
		catch (SocketException e)
		{
			System.out.println("Server is disconnected");
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	public void connectClient(ClientSocket clientSocket)
	{
		String text;
		// show the information of the client.
		text = " " + temp_socket.getInetAddress() + " " + temp_socket.getPort();
		text += " " + clientSocket.getClientInformation().getName();
		printMessage(text);
		ClientThreads.add(new ClientThread(clientSocket));
		ServerMainScene.addClient(clientSocket.getClientInformation());
		PrintMessageInGroup printMessageInGroup = new PrintMessageInGroup("hello, my clients!", ClientThreads);
		new AddOrderTool(printMessageInGroup).executeTool();
	}

	public Object getObject(ClientSocket clientSocket) throws IOException
	{
		ObjectInputStream OIS = clientSocket.getOIS();
		Object o = null;
		try
		{
			o = OIS.readObject();
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}

		if (o instanceof ClientList)
		{
			ClientList clientList = (ClientList) o;
			if (clientList.getType().equals("chat"))
			{
				System.out.println("get a chat list");
			}
			else if (clientList.getType().equals("add"))
			{
				System.out.println("get an add list");
			}
			else if (clientList.getType().equals("delete"))
			{
				System.out.println("get a delete list");
			}
		}
		else if (o instanceof InformationChange)
		{
			System.out.println("a client wants to change his client information");
		}
		return o;
	}

	public void sendObject(ClientSocket clientSocket, Object o)
	{
		ObjectOutputStream OOS = clientSocket.getOOS();
		try
		{
			OOS.reset();
			OOS.writeObject(o);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * @author 赵龙淳
	 * @version 1.0
	 * @description: this class is very important,it supports the running of our
	 *               server.
	 * @date 2023/12/10 20:00
	 */
	class ServerThread implements Runnable
	{
		private final Object lock = Lock.ServerLock.getLock();
		private String temp_text;

		public ServerThread()
		{
		}

		@Override
		public void run()
		{

			while (true)
			{
				synchronized (lock)
				{
					if (order_num == 0)
					{
						try
						{
							lock.wait();
						}
						catch (InterruptedException e)
						{
							throw new RuntimeException(e);
						}
					}

					for (IOrder order : Server.getServer().orders)
					{
						try
						{
							order.executeOrder();
						}
						catch (Exception e)
						{
							if (e instanceof PrintInGroupExcption)
							{
								System.out.println("there is something wrong with PrintInGroup!");
							}
							else if (e instanceof DisconnectClientException)
							{
								System.out.println("there is something wrong with DisconnectClient!");
							}
						}
					}
					server.orders.clear();
					order_num = 0;
					lock.notify();
				}
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * @author 赵龙淳
	 * @version 1.0
	 * @description: caution: only when a chat window is started can we get this
	 *               thread start.
	 * @date 2023/12/10 20:09
	 */
	public class ClientThread implements Runnable
	{// caution: a client thread belongs only to one client.
		public ClientSocket clientSocket;
		private Object o;
		private ClientList clientList;

		public ClientThread(ClientSocket clientSocket)
		{// this is a thread only connected with server.
			this.clientSocket = clientSocket;
			new Thread(this).start();
		}

		private void sendMessageToOthers() throws SendMessageToOthersException
		{
			String tag;
			String text;
			int size = -1;
			Iterator<ClientThread> ITCSS = ClientThreads.iterator();// this is online client list with socket and Object
																	// Stream.
			ClientThread temp_CS;
			ArrayList<ClientInformation> chat_client_informations = null;
			if (clientList == null)
			{// we do not choose who we want to send message to,so we send the message
				// globally.
				size = 0;
			}
			else if (!clientList.getType().equals("chat"))
			{
				System.out.println("Not chat client list when sending message in server");
				throw new SendMessageToOthersException();
			}
			text = clientList.getText();
			if (size == -1)
			{// that means, the client list is not null.
				chat_client_informations = clientList.getClientInformations();
				if (chat_client_informations == null)
				{// although it is impossible to happen if everything works correctly.
					size = 0;
				}
				else
				{
					size = chat_client_informations.size();
				}
			}
			if (size == 0 || size == dynamic_client_informations.getClientInformations().size())
			{
				tag = "Global:";
			}
			else if (size != 1)
			{
				tag = "Group:";
			}
			else
			{
				tag = "Private:";
			}
			String temp_text = tag + clientSocket.getClientInformation().getName();
			printMessage(temp_text + " " + text);
			Message message = new Message(text, tag, "client", this.clientSocket.getClientInformation());
			while (ITCSS.hasNext())
			{
				temp_CS = ITCSS.next();
				if (!temp_CS.clientSocket.getClientInformation().getName()
						.equals(this.clientSocket.getClientInformation().getName()))
				{
					if (tag.equals("Global:"))
					{
						chat_client_informations = dynamic_client_informations.getClientInformations();// all online
																										// clients.
						if (chat_client_informations == null)
						{// although it is impossible to happen if everything works correctly.
							System.out.println("no chat client list when sending message in server");
							throw new SendMessageToOthersException();
						}
					}
					for (ClientInformation clientInformation : chat_client_informations)
					{
						if (!temp_CS.clientSocket.getClientInformation().getName().equals(clientInformation.getName()))
						{
							sendObject(temp_CS.clientSocket, message);
							break;
						}
					}
				}
			}
		}

		private void addFriends() throws AddFriendsException
		{
			if (!clientList.getType().equals("add"))
			{
				System.out.println("not add client list when adding friends");
				throw new AddFriendsException();
			}
			boolean addSuccess = false;
			ArrayList<ClientInformation> add_clientInformations = clientList.getClientInformations();// these are who we
																										// want to add.
			ClientInformation owner_client_information = this.clientSocket.getClientInformation();// The applicant.
			ClientList owner_friend_clientList = null; // get applicant's friend list.
			try
			{
				owner_friend_clientList = getClientList(owner_client_information, "friend");
			}
			catch (GetClientListException e)
			{
				System.out.println("There is no friend list of the client in server");
				throw new AddFriendsException();
			}
			for (ClientInformation clientInformation_in_add : add_clientInformations)
			{// who we want to add.
				for (ClientThread clientThread : ClientThreads)
				{
					ClientInformation clientInformation_in_client_thread = clientThread.clientSocket
							.getClientInformation();
					if (clientInformation_in_client_thread.getName().equals(clientInformation_in_add.getName()))
					{
						try
						{
							ClientList friend_client_list_of_adder = getClientList(clientInformation_in_add, "friend");
							friend_client_list_of_adder.addClientInformation(owner_client_information);
							sendObject(clientThread.clientSocket,
									clientInformation_in_client_thread.getClient().getClientList("friend"));
							addSuccess = true;
							break;
							// adders' friend list get refreshed.
						}
						catch (GetClientListException e)
						{
							System.out.println("Get an adder's friend list failed");
							break;
						}
					}
				}
				if (addSuccess)
				{
					owner_friend_clientList.addClientInformation(clientInformation_in_add);
					addSuccess = false;
				}
			}
			sendObject(this.clientSocket, owner_friend_clientList);// applicant's friend list get refreshed.
		}

		private void deleteFriends() throws DeleteFriendsException
		{
			boolean deleteSuccess = false;
			if (!clientList.getType().equals("delete"))
			{
				throw new DeleteFriendsException();
			}
			ClientInformation owner_client_information = this.clientSocket.getClientInformation();// who wants to
																									// delete？
			ArrayList<ClientInformation> delete_clientInformations = clientList.getClientInformations();// delete who?
			ClientList owner_friend_list = null;
			try
			{
				owner_friend_list = getClientList(owner_client_information, "friend");
			}
			catch (GetClientListException e)
			{
				System.out.println("There is no friend list of the client in server");
				throw new DeleteFriendsException();
			}
			for (ClientInformation client_information_in_delete : delete_clientInformations)
			{// delete the who from the list of whom he wants to delete.
				for (ClientThread clientThread : ClientThreads)
				{
					ClientInformation clientInformation_in_client_thread = clientThread.clientSocket
							.getClientInformation();
					if (clientInformation_in_client_thread.getName().equals(client_information_in_delete.getName()))
					{
						try
						{
							ClientList friend_client_list_of_deleter = getClientList(client_information_in_delete,
									"friend");
							friend_client_list_of_deleter.deleteClientInformation(owner_client_information);
							sendObject(clientThread.clientSocket, friend_client_list_of_deleter);
							deleteSuccess = true;
						}
						catch (GetClientListException e)
						{
							System.out.println("Get an adder's friend list failed");
							break;
						}
					}
					if (deleteSuccess)
					{
						owner_friend_list.deleteClientInformation(client_information_in_delete);
						deleteSuccess = false;
					}
				}
			}
			sendObject(this.clientSocket, owner_friend_list);
		}

		private void ChangeInformation(InformationChange informationChange)
		{
			ClientInformation old_client_information = informationChange.getOld_client_information();
			ClientInformation new_client_information = informationChange.getNew_client_information();
			this.clientSocket.setClientInformation(new_client_information);
			dynamic_client_informations.refreshClientInformation(old_client_information, new_client_information);
			static_client_informations.refreshClientInformation(old_client_information, new_client_information);
			new SerializeTool(static_client_informations, "./Server.txt");
			for (ClientList friend_list : clientLists)
			{
				friend_list.refreshClientInformation(old_client_information, new_client_information);
			}
			for (ClientThread clientThread : ClientThreads)
			{
				ClientInformation clientInformation_in_client_thread = clientThread.clientSocket.getClientInformation();
				try
				{
					ClientList friend_client_list = getClientList(clientInformation_in_client_thread, "friend");
					sendObject(clientThread.clientSocket,
							clientInformation_in_client_thread.getClient().getClientList("friend"));
					// other's friend list get refreshed.
				}
				catch (GetClientListException e)
				{
					System.out.println("Get an adder's friend list failed");
					break;
				}
			}
		}

		@Override
		public void run()
		{
			String text;
			try
			{
				while (isStart)
				{
					o = getObject(clientSocket);
					if (o instanceof ClientList)
					{
						ClientList clientList = (ClientList) o;
						this.clientList = clientList;

						if (clientList.getType().equals("chat"))
						{
							try
							{
								sendMessageToOthers();
							}
							catch (SendMessageToOthersException e)
							{
								System.out.println("We have trouble when sending message to others in client thread");
								// throw new RuntimeException(e);
							}
						}
						else if (clientList.getType().equals("add"))
						{// an add request from client.
							Object client_list_lock = ClientListLock.getLock();// get locked.
							synchronized (client_list_lock)
							{
								try
								{
									addFriends();
								}
								catch (AddFriendsException e)
								{
									System.out.println("We have trouble when adding friends");
									// throw new RuntimeException(e);
								}
							}
						}
						else if (clientList.getType().equals("delete"))
						{
							Object client_list_lock = ClientListLock.getLock();
							// we need to delete friend
							synchronized (client_list_lock)
							{
								try
								{
									deleteFriends();
								}
								catch (DeleteFriendsException e)
								{
									System.out.println("We have trouble when deleting friends");
								}
							}
						}
					}
					else if (o instanceof InformationChange)
					{
						ChangeInformation((InformationChange) o);
					}
				}
			}
			catch (IOException e)
			{
				text = "a client is disconnected";
				printMessage(text);
			}
		}
	}

	/**
	 * @author 赵龙淳
	 * @version 1.0
	 * @description: check whether the server is started.
	 * @date 2023/12/10 20:10
	 */
	class CheckServer implements Runnable
	{

		@Override
		public void run()
		{
			String text;
			text = "Server doesn't get started";
			while (!isStart)
			{
				try
				{
					printMessage(text);
					Thread.sleep(3000);
				}
				catch (InterruptedException e)
				{
					throw new RuntimeException(e);
				}
			}
		}
	}
}
