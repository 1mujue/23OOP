package Clients;

import Exceptions.OrderExceptions.*;
import SpecialClass.Message;
import scenes.ClientMainScene;
import scenes.LoginScene;
import scenes.ServerMainScene;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author 赵龙淳
 * @version 1.0
 * @description: the class represents our clients.
 * @date 2023/12/9 22:00
 */
public class Client
{
	private static final InetAddress addr;
	static
	{
		try
		{
			addr = InetAddress.getLocalHost();
		}
		catch (UnknownHostException e)
		{
			throw new RuntimeException(e);
		}
	}
	private static final String CONNECTION_STR = "127.0.0.1";
	private static final String CONNECTION_ADD = addr.getHostAddress();
	private static final int CONNECTION_PORT = 9999;

	private ClientInformation clientInformation;
	private ClientList chatList;
	private ClientList friendsList;
	private ClientList allList;
	private ClientList addList;
	private ClientList deleteList;
	private Socket socket;
	private boolean isConnected = false;
	private ObjectInputStream OIS;
	private ObjectOutputStream OOS;

	public Client(String name, String password)
	{// get started.
		this.clientInformation = new ClientInformation(name, password, 1);
		clientInformation.setClient(this);
		initClientList();
	}

	public Client(ClientInformation clientInformation)
	{// suggest use this.
		this.clientInformation = clientInformation;
		initClientList();
	}

	private void initClientList()
	{
		friendsList = new ClientList(clientInformation, "friend");
		allList = new ClientList(clientInformation, "all");
		chatList = new ClientList(clientInformation, "chat");
		addList = new ClientList(clientInformation, "add");
		deleteList = new ClientList(clientInformation, "delete");
	}

	private void initiateObjectStream() throws InitiateObjectStreamException
	{
		try
		{
			OOS = new ObjectOutputStream(socket.getOutputStream());
			OIS = new ObjectInputStream(socket.getInputStream());
			System.out.println("Initiate object stream success in initiate object stream");
		}
		catch (IOException e)
		{
			throw new InitiateObjectStreamException();
		}
	}

	public boolean getState()
	{
		return this.isConnected;
	}

	public void setState(boolean isConnected)
	{
		this.isConnected = isConnected;
	}

	public String getName()
	{
		return this.clientInformation.getName();
	}

	public void setName(String name)
	{
		this.clientInformation.setName(name);
	}

	public String getPassword()
	{
		return this.clientInformation.getPassword();
	}

	public void setPassword(String password)
	{
		this.clientInformation.setPassword(password);
	}

	public void setClientInformation(String name, String password)
	{
		this.setName(name);
		this.setPassword(password);
	}

	public ClientInformation getClientInformation()
	{
		return this.clientInformation;
	}

	public void setClientInformation(ClientInformation clientInformation)
	{
		this.clientInformation = clientInformation;
	}

	public void setClientList(ClientList clientList) throws SetClientListException
	{
		if (clientList.getType().equals("friend"))
		{
			System.out.println("set friend list success");
			this.friendsList = clientList;
		}
		else if (clientList.getType().equals("all"))
		{
			System.out.println("set all list success");
			this.allList = clientList;
		}
		else if (clientList.getType().equals("chat"))
		{
			System.out.println("set chat list success");
			this.chatList = clientList;
		}
		else
		{
			System.out.println("Illegal list type");
			throw new SetClientListException();
		}
	}

	public ClientList getClientList(String tag) throws GetClientListException
	{
		if (tag.equals("friend"))
		{
			System.out.println("get friend list success");
			return this.friendsList;
		}
		else if (tag.equals("all"))
		{
			System.out.println("get all list success");
			return this.allList;
		}
		else if (tag.equals("chat"))
		{
			System.out.println("get chat list success");
			return this.chatList;
		}
		else
		{
			System.out.println("get illegal list type in get client list");
			throw new GetClientListException();
		}
	}

	public void tryConnect() throws TryConnectException
	{
		System.out.println("Trying to connect");

		try
		{
			this.connectServer();
			System.out.println("Connect success!");
			ClientMainScene.setClient(this);
			LoginScene.changeStage();
			new Thread(this.new ClientThread()).start();// a thread connect only with server!
		}
		catch (ConnectServerException e)
		{
			System.out.println("Connect aborted in try connect");
			throw new TryConnectException();
		}
	}

	public void connectServer() throws ConnectServerException
	{
		try
		{
			if (socket == null)
			{
				socket = new Socket(CONNECTION_STR, CONNECTION_PORT);
			}
			try
			{
				try
				{
					initiateObjectStream();
				}
				catch (InitiateObjectStreamException e)
				{
					System.out.println("Initiate object stream failed in connect server");
					throw new ConnectServerException();
				}
				try
				{
					sendObject(clientInformation);
				}
				catch (SendMessageException e)
				{
					System.out.println("Send message failed in connect server");
					throw new ConnectServerException();
				}

				Object temp;
				try
				{
					temp = getObject();
				}
				catch (GetMessageException e)
				{
					System.out.println("Get message failed in connect server");
					throw new ConnectServerException();
				}

				if (temp instanceof String)
				{
					String text = (String) temp;
					if (text.equals("success"))
					{
						isConnected = true;
					}
					else if (text.equals("failed"))
					{
						isConnected = false;
						System.out.println("Server return information is failed in connect server");
						throw new StartClientException();
					}
				}
			}
			catch (StartClientException e)
			{
				System.out.println("Start client failed in connect server");
				socket.shutdownInput();
				socket.shutdownOutput();
				socket.close();
				throw new ConnectServerException();
			}
		}
		catch (IOException e)
		{
			System.out.println("IO exception in connect server");
		}
	}

	public Object getObject() throws GetMessageException
	{
		Object o = null;
		try
		{
			o = OIS.readObject();
		}
		catch (IOException e)
		{
			System.out.println("OIS exception in get message");
			throw new GetMessageException();
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Class not found in get message");
			throw new GetMessageException();
		}
		if (o instanceof Message)
		{
			Message message = (Message) o;
			if (message.getType().equals("server"))
			{
				System.out.println("Get a message sent by server");
				String temp_text = message.getTag() + message.getSender_clientInformation().getName()
						+ message.getText();
				System.out.println(temp_text);
			}
			else if (message.getType().equals("client"))
			{
				System.out.println("Get a message sent by client");
				printMessage(message.getText());
			}
		}
		else if (o instanceof ClientList)
		{
			ClientList clientList = (ClientList) o;
			try
			{
				if (clientList.getType().equals("friend") || clientList.getType().equals("all"))
				{
					this.setClientList(clientList);
				}
				else
				{
					System.out.println("client gets an llegal client list type");
				}
			}
			catch (SetClientListException e)
			{
				System.out.println("Set client list failed in get message");
			}
		}
		else if (o instanceof String)
		{
			System.out.println("Get a string from server");
			printMessage((String) o);
		}
		return o;
	}

	public void sendObject(Object o) throws SendMessageException
	{
		if (o instanceof ClientList)
		{
			ClientList clientList = (ClientList) o;
			if (clientList.getType().equals("friend"))
			{
				System.out.println("I am sending my friend list");
			}
			else if (clientList.getType().equals("chat"))
			{
				System.out.println("I am sending my chat list");
			}
			else if (clientList.getType().equals("add"))
			{
				System.out.println("I am sending my add request!");// trying to add a friend.
			}
			else if (clientList.getType().equals("all"))
			{
				System.out.println("Try to send illegal type of client list to server in send message");
				throw new SendMessageException();
			}
			try
			{
				OOS.reset();// this is of great important!
				// if you do not do this, something horrible will happen.
				OOS.writeObject(clientList);
			}
			catch (IOException e)
			{
				System.out.println("OOS exception in send message");
				throw new SendMessageException();
			}
		}
		else if (o instanceof ClientInformation)
		{
			System.out.println("I am sending my information");
			try
			{
				OOS.writeObject(o);
			}
			catch (IOException e)
			{
				System.out.println("OOS exception in send message");
				throw new SendMessageException();
			}
		}
	}

	private void printMessage(String text)
	{
		System.out.println(text);
		ClientMainScene.printMessage(text);
	}

	class ClientThread implements Runnable
	{
		@Override
		public void run()
		{
			while (isConnected)
			{
				try
				{
					getObject();
				}
				catch (GetMessageException e)
				{
					try
					{
						socket.close();
					}
					catch (IOException ex)
					{
						System.out.println("IO exception in client thread");
						throw new RuntimeException(ex);
					}
					System.out.println("disconnected from server");
					System.exit(0);
					throw new RuntimeException(e);
				}
			}
		}
	}
}
