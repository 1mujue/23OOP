//package Clients;
//
//import Exceptions.OrderExceptions.GetClientListException;
//import Exceptions.OrderExceptions.SendMessageException;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
///**
// * @description: this is a temporary GUI for client.
// * @author 赵龙淳
// * @date 2023/12/9 22:46
// * @version 1.0
// */
//public class ClientChatWindow extends JFrame {
//    private JTextArea text_area = new JTextArea(30, 40);
//    private JTextField text_field = new JFormattedTextField(35);
//    private JScrollPane JSP = new JScrollPane(text_area);
//
//    private String text;
//    public Client client;
//    /**
//     * @description: this is a method to creat a client window.(just creat!)
//            * @param: client
//            * @return:
//            * @author 赵龙淳
//            * @date: 2023/12/9 22:47
//     */
//    public ClientChatWindow(Client client){
//        this.client = client;
//    }
//    /**
//     * @description: this method makes the client window visible.
//            * @param:
//            * @return: void
//            * @author 赵龙淳
//            * @date: 2023/12/9 22:47
//     */
//    public void initWindow(){
//        this.setTitle("client chat window");
//        this.setVisible(true);
//
//        //this.add(text_area, BorderLayout.CENTER);
//        this.add(JSP, BorderLayout.CENTER);
//        this.add(text_field,BorderLayout.SOUTH);
//        this.setBounds(500, 150, 500,600);
//
//        text_area.setEditable(false);// Not allowed to write!
//        text_field.requestFocus();
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//
//        text_field.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                text = text_field.getText();
//                if(e.getKeyCode() == KeyEvent.VK_ENTER){
//                    if(!text.trim().isEmpty()){
//                        text += "\n";
//                        text_field.setText("");
//                        text_area.append(text);
//                        try {
//                            //this is where we send message really!
//                            ClientList clientList = client.getClientList("chat");
//                            clientList.setText(text);
//                            client.sendMessage(clientList);
//                        } catch (SendMessageException ex) {
//                            System.out.println("Send message failed in client chat window");
//                            throw new RuntimeException(ex);
//                        } catch (GetClientListException ex) {
//                            System.out.println("get client list failed in client chat window");
//                            throw new RuntimeException(ex);
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });
//    }
//    public void printMessage(Object obj){
//        if(obj instanceof String){
//            String text = (String) obj;
//            int length = text.length();
//            if(text.charAt(length - 1) != '\n')
//                text += "\n";
//            text_area.append(text);
//        }
//    }
//}
