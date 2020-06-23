package server;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import mdlaf.MaterialLookAndFeel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ControllerGUI {
    int Framewidth = 600;
    int Frameheight=600;
    static {
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
            UIManager.put("Button.mouseHoverEnable", true);
            JFrame.setDefaultLookAndFeelDecorated(false);

        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private static String URL_DIR = System.getProperty("user.dir");

    private Controller client_node;
    private String server_ip = "";
    private int server_port = 8080;

    private int peer_port = 0;
    private String username = "";
    private String message = "";

    private JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,bodyCon;
    private JPanel headMenu;
    private JFrame fmMenu;
    private JTextField txtUsername, txtOnlineName, txtFriendName;
    private static JTextArea txtPeerList;

    private static JTextArea txtFriendList;

    private JButton btnChat, btnExit, btnAdd;

    public ControllerGUI(String server_ip, int server_port, int peer_port, String username, String message) throws Exception {
        this.server_ip = server_ip;
        this.server_port = server_port;
        this.peer_port = peer_port;
        this.username = username;
        this.message = message;

        initializeFrame();
        initializeLabel();
        initializeTextBox();
        initializeButton();

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    fmMenu.setVisible(true);
                    client_node = new Controller(server_ip, server_port, peer_port, username, message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ControllerGUI() throws Exception {
        initializeFrame();
        initializeLabel();
        initializeTextBox();
        initializeButton();
        client_node = new Controller(server_ip, server_port, peer_port, username, message);
    }

    private void initializeFrame() {
        fmMenu = new JFrame();
        fmMenu.setTitle("Menu");
        ImageIcon image = new ImageIcon(URL_DIR + "/P2P-Chat-Application-hieu/src/main/resources/icons8-menu-64.png");
        fmMenu.setIconImage(image.getImage());
        fmMenu.setResizable(false);
        fmMenu.setBounds(100, 100, 600, 600);
        //fmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fmMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fmMenu.getContentPane().setBackground(Color.LIGHT_GRAY);
        fmMenu.getContentPane().setLayout(null);

        ImageIcon image1 = new ImageIcon(URL_DIR + "/P2P-Chat-Application-hieu/src/main/resources/ConBG.jpg");
        JLabel piclabel = new JLabel(image1);
        piclabel.setSize(50,50);
        piclabel.setVisible(true);

        p1 = new JPanel(new GridLayout(0,1));
        p1.setBounds(10,100,200,16);
        p1.setOpaque(false);
        fmMenu.add(p1);

        p2 = new JPanel(new GridLayout(0,1));
        p2.setBounds(10, 130, 170, 340);
        p2.setOpaque(false);
        fmMenu.add(p2);

        p3 = new JPanel(new GridLayout(0,1));
        p3.setBounds(205, 100, 200, 16);
        p3.setOpaque(false);
        fmMenu.add(p3);

        p4 = new JPanel(new GridLayout(0,1));
        p4.setBounds(205, 130, 170, 28);
        p4.setOpaque(false);
        fmMenu.add(p4);

        p5 = new JPanel(new GridLayout(0,1));
        p5.setBounds(205, 200, 200, 16);
        p5.setOpaque(false);
        fmMenu.add(p5);

        p6 = new JPanel(new GridLayout(0,1));
        p6.setBounds(205, 230, 170, 28);
        p6.setOpaque(false);
        fmMenu.add(p6);

        p7 = new JPanel(new GridLayout(0,1));
        p7.setBounds(205, 300, 200, 16);
        p7.setOpaque(false);
        fmMenu.add(p7);

        p8 = new JPanel(new GridLayout(0,1));
        p8.setBounds(205, 330, 170, 28);
        p8.setOpaque(false);
        fmMenu.add(p8);

        p9 = new JPanel(new GridLayout(0,1));
        p9.setBounds(400,100,200,16);
        p9.setOpaque(false);
        fmMenu.add(p9);

        p10 = new JPanel(new GridLayout(0,1));
        p10.setBounds(400, 130, 170, 340);
        p10.setOpaque(false);
        fmMenu.add(p10);

        p11 = new JPanel(new GridLayout(0,1));
        p11.setBounds(30, 510, 113, 29);
        p11.setOpaque(false);
        fmMenu.add(p11);

        p12 = new JPanel(new GridLayout(0,1));
        p12.setBounds(230, 510, 113, 29);
        p12.setOpaque(false);
        fmMenu.add(p12);

        p13 = new JPanel(new GridLayout(0,1));
        p13.setBounds(430, 510, 113, 29);
        p13.setOpaque(false);
        fmMenu.add(p13);


        headMenu = new JPanel();
        headMenu.setBounds(0,0, 600,400/6);
        headMenu.setBackground(Color.ORANGE);
        fmMenu.add(headMenu);

        bodyCon = new JPanel();
        bodyCon.setBounds(0,400/6-200, Framewidth,Frameheight+200);
        //bodyCon.setBackground(Color.pink);
        bodyCon.add(piclabel);
        fmMenu.add(bodyCon);

    }

    private void initializeLabel() {
        JLabel lbWelcome = new JLabel("MENU");
        lbWelcome.setFont(new Font("Verdana",1,30));
        lbWelcome.setForeground(Color.blue);
        lbWelcome.setOpaque(false);
        //lbWelcome.setBounds(100, 40, 180, 40);
        headMenu.add(lbWelcome);

        JLabel lbUsername = new JLabel("USERNAME: ");
        lbUsername.setFont(new Font("Verdana",1,15));
        lbUsername.setBounds(205, 100, 200, 16);
        lbUsername.setForeground(Color.orange);
        lbUsername.setOpaque(false);
        p3.add(lbUsername);
        //fmMenu.getContentPane().add(lbUsername);

        JLabel lbOnlineList = new JLabel("ONLINE LIST:");
        lbOnlineList.setFont(new Font("Verdana",1,15));
        lbOnlineList.setBounds(10,100,200,16);
        lbOnlineList.setForeground(Color.orange);
        lbOnlineList.setOpaque(false);
        p1.add(lbOnlineList);
        //fmMenu.getContentPane().add(lbOnlineList);

        JLabel lbFriendList = new JLabel("FRIEND LIST:");
        lbFriendList.setFont(new Font("Verdana",1,15));
        lbFriendList.setBounds(400,100,200,16);
        lbFriendList.setForeground(Color.orange);
        lbFriendList.setOpaque(false);
        p9.add(lbFriendList);
        //fmMenu.getContentPane().add(lbFriendList);

        JLabel lbFriendName = new JLabel("ADD FRIEND: ");
        lbFriendName.setFont(new Font("Verdana",1,15));
        lbFriendName.setBounds(205, 200, 200, 16);
        lbFriendName.setForeground(Color.orange);
        lbFriendName.setOpaque(false);
        p5.add(lbFriendName);
        //fmMenu.getContentPane().add(lbFriendName);

        JLabel lbFriendText = new JLabel("CHAT WITH: ");
        lbFriendText.setFont(new Font("Verdana",1,15));
        lbFriendText.setBounds(205, 300, 200, 16);
        lbFriendText.setForeground(Color.orange);
        lbFriendText.setOpaque(false);
        p7.add(lbFriendText);
        //fmMenu.getContentPane().add(lbFriendText);
    }

    private void initializeTextBox() {
        //User area
        txtUsername = new JTextField(this.username);
        txtUsername.setBackground(Color.WHITE);
        txtUsername.setEditable(false);
        txtUsername.setColumns(10);
        txtUsername.setBounds(205, 130, 170, 28);
        p4.add(txtUsername);
        //fmMenu.getContentPane().add(txtUsername);

        //Online listing area
        txtPeerList = new JTextArea();
        txtPeerList.setBackground(Color.ORANGE);
        txtPeerList.setForeground(Color.BLUE);
        txtPeerList.setText("");
        txtPeerList.setEditable(false);
       // txtPeerList.setBounds(10, 130, 170, 340);
        fmMenu.getContentPane().add(txtPeerList);

        //Friend listing area
        txtFriendList = new JTextArea();
        txtFriendList.setBackground(Color.ORANGE);
        txtFriendList.setForeground(Color.BLUE);
        txtFriendList.setText("");
        txtFriendList.setEditable(false);
        txtFriendList.setBounds(400, 130, 170, 340);
        fmMenu.getContentPane().add(txtFriendList);

        Border orangeBorder = BorderFactory.createLineBorder(Color.WHITE, 10);
        JScrollPane scrollA = new JScrollPane(txtPeerList);
        scrollA.setBounds(10, 130, 170, 340);
        scrollA.setBorder(orangeBorder);
        p2.add(scrollA);
        //fmMenu.getContentPane().add(scrollA);

        JScrollPane scrollB = new JScrollPane(txtFriendList);
        scrollB.setBounds(400, 130, 170, 340);
        scrollB.setBorder(orangeBorder);
        p10.add(scrollB);
        //fmMenu.getContentPane().add(scrollB);

        //Chat with area
        txtFriendName = new JTextField("Type name to add");
        txtFriendName.setBackground(Color.WHITE);
        txtFriendList.setEditable(true);
        txtFriendName.setColumns(10);
        txtFriendName.setBounds(205, 230, 170, 28);
        p6.add(txtFriendName);
        //fmMenu.getContentPane().add(txtFriendName);

        //Add friend area
        txtOnlineName = new JTextField("Type name to chat");
        txtOnlineName.setBackground(Color.WHITE);
        txtPeerList.setEditable(true);
        txtOnlineName.setColumns(10);
        txtOnlineName.setBounds(205, 330, 170, 28);
        //fmMenu.getContentPane().add(txtOnlineName);
        p8.add(txtOnlineName);
    }

    private void initializeButton() {

        btnChat = new JButton("Chat");
        btnChat.setForeground(Color.blue);
        btnChat.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent arg0) {
                //System.out.println("here1");
                String name = txtOnlineName.getText();
                if (name.equals("") || Controller.online_list == null) {
                    JOptionPane.showMessageDialog(fmMenu, "Name 's friend mistake!");
                    return;
                }
                if (name.equals(username)) {
                    JOptionPane.showMessageDialog(fmMenu, "You can't chat with yourself !");
                    return;
                }
                int size = Controller.online_list.size();
                //System.out.println("here2");
                //System.out.println(size);
                //System.out.println(toString(online_list));
                for (int i = 0; i < size; i++) {
                    //System.out.println("here3");
                    //System.out.println(size);
                    if (name.equals(Controller.online_list.get(i).getName())) {
                        //System.out.println("here4");

                        try {
                            System.out.println("here");
                            client_node.chat_request(Controller.online_list.get(i).getHost(),Controller.online_list.get(i).getPort(), name);
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                JOptionPane.showMessageDialog(fmMenu, "Can't found your friend!");
            }
        });
        btnChat.setBounds(230, 510, 113, 29);
        p12.add(btnChat);
        //fmMenu.getContentPane().add(btnChat);

        btnAdd = new JButton("Add");
        btnAdd.setForeground(Color.blue);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Pressed action");
//                int result = JOptionPane.showConfirmDialog(
//                        fmMenu, "Do you want exit now?", null,
//                        JOptionPane.YES_NO_OPTION
//                );
//                if (result == 0) {
//                    try {
//                        client_node.stop_request();
//                        fmMenu.dispose();
//                    } catch (Exception e) {
//                        fmMenu.dispose();
//                    }
//                }
            }
        });
        btnAdd.setBounds(30, 510, 113, 29);
        p11.add(btnAdd);
        //fmMenu.getContentPane().add(btnAdd);



        btnExit = new JButton("Exit");
        btnExit.setForeground(Color.blue);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int result = JOptionPane.showConfirmDialog(
                        fmMenu, "Do you want exit now?", null,
                        JOptionPane.YES_NO_OPTION
                );
                if (result == 0) {
                    try {
                        client_node.stop_request();
                        fmMenu.dispose();
                    } catch (Exception e) {
                        fmMenu.dispose();
                    }
                }
            }
        });
        btnExit.setBounds(430, 510, 113, 29);
        p13.add(btnExit);
        //fmMenu.getContentPane().add(btnExit);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ControllerGUI window = new ControllerGUI();
                    window.fmMenu.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static int showDialog(String msg, boolean type) {
        JFrame frameMessage = new JFrame();
        if(type)
            return JOptionPane.showConfirmDialog(
                    frameMessage, msg, null,
                    JOptionPane.YES_NO_OPTION
            );
        else
            JOptionPane.showMessageDialog(frameMessage, msg);
        return -22;

    }

    public static void update_online_list_UI(String msg) {

        txtPeerList.append(msg + "\n");
    }

//    public static void update_friend_list_UI(String msg) {
//
//        txtFriendList.append(msg + "\n");
//    }


    public static void clear_online_list() {
        txtPeerList.setText("");
        txtPeerList.setText("");
    }

//    public static void clear_friend_list() {
//        txtFriendList.setText("");
//        txtFriendList.setText("");
//    }
}

