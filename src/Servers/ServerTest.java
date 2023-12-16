package Servers;

import applications.ServerApplication;

/**
 * @description: this is a temporary test for server.
 * @author 赵龙淳
 * @date 2023/12/9 23:11
 * @version 1.0
 */
public class ServerTest
{
	public class WindowLaunch implements Runnable
	{
		public WindowLaunch()
		{

		}

		@Override
		public void run()
		{
			String[] arg = new String[1];
			ServerApplication.main(arg);
		}
	}

	public ServerTest()
	{

	}

	public static void main(String[] args)
	{
		Server server = Server.getServer();
		if (!server.getState())
		{
			ServerTest serverTest = new ServerTest();
			new Thread(serverTest.new WindowLaunch()).start();
			server.initServer();
			server.waitStart();
		}
		System.exit(0);

	}

}
