package Clients;

import applications.ClientApplication;

/**
 * @author 赵龙淳
 * @version 1.0
 * @description: this is a temporary client test class.
 * @date 2023/12/9 22:55
 */
public class ClientTest
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
			ClientApplication.main(arg);
		}
	}

	public ClientTest()
	{

	}

	public static void main(String[] args)
	{
		ClientTest clientTest = new ClientTest();
		new Thread(clientTest.new WindowLaunch()).start();
//		ClientInformation clientInformation1_1 = new ClientInformation("Haha128", "Ac12_9", 1);// register
//		ClientInformation clientInformation1_2 = new ClientInformation("Haha128", "Ac12_9", 2);// log in
//		ClientInformation clientInformation1_3 = new ClientInformation("Haha128", "Ac12_9", 2);
//		ClientInformation clientInformation2_1 = new ClientInformation("Mujue37", "Aa123_2", 1);
//		ClientInformation clientInformation2_2 = new ClientInformation("Mujue37", "Aa123_2", 2);
//		ClientInformation clientInformation2_3 = new ClientInformation("Mujue37", "Aa123_2", 2);
//
//		try
//		{
//			// new ClientStart(clientInformation1_1).executeOrder();
//			// new ClientStart(clientInformation1_2).executeOrder();
//			// new ClientStart(clientInformation1_3).executeOrder();
//			// new ClientStart(clientInformation2_1).executeOrder();
//			new ClientStart(clientInformation2_2).executeOrder();
//			// new ClientStart(clientInformation2_3).executeOrder();
//		}
//		catch (StartClientException e)
//		{
//			System.out.println(e);
//		}
	}
}