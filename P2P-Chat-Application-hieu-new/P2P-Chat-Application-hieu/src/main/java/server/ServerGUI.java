package server;

import mdlaf.MaterialLookAndFeel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class ServerGUI {
	int Framewidth = 550;
	int Frameheight=442;
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

	private JFrame fmServer;
	private JTextField txtIP, txtPort;
	private static JTextArea txtMessage;
	private static JLabel lbNumber;
	private static int flag =  1;
	private JPanel p1,p2,p3,p4,p5,p6,p7,bodyServer,headGroup;
	Server server;

	public ServerGUI() {
		initializeFrame();
		initializeLabel();
		initializeTextBox();
		initializeButton();
	}


	private void initializeFrame() {
		fmServer = new JFrame();
		fmServer.setTitle("Server Controller");
		ImageIcon image = new ImageIcon(URL_DIR + "/P2P-Chat-Application-hieu/src/main/resources/icons8-server-64.png");
		fmServer.setIconImage(image.getImage());
		fmServer.setResizable(false);
		fmServer.setBounds(200, 200, 550, 442);
		fmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fmServer.getContentPane().setLayout(null);
		fmServer.getContentPane().setBackground(Color.lightGray);

		ImageIcon image1 = new ImageIcon(URL_DIR + "/P2P-Chat-Application-hieu/src/main/resources/loginBG.jpg");
		JLabel piclabel = new JLabel(image1);
		piclabel.setSize(50,50);
		piclabel.setVisible(true);

		headGroup = new JPanel();
		headGroup.setBounds(0,0, 550,55);
		headGroup.setBackground(Color.ORANGE);
		fmServer.add(headGroup);

		p1 = new JPanel();
		p1.setBounds(40, 155-5, 40, 40);
		p1.setOpaque(false);
		fmServer.add(p1);

		p2 = new JPanel();
		p2.setBounds(40, 205-5, 50, 40);
		p2.setOpaque(false);
		fmServer.add(p2);

		p3 = new JPanel();
		p3.setBounds(50, 150-5, 205, 28);
		p3.setOpaque(false);
		fmServer.add(p3);

		p4 = new JPanel();
		p4.setBounds(50, 200-5, 205, 28);
		p4.setOpaque(false);
		fmServer.add(p4);

		p5 = new JPanel(new GridLayout(0,1));
		p5.setBounds(100, 250, 100, 45);
		//p5.setBackground(Color.black);
		p5.setOpaque(false);
		fmServer.add(p5);

		p6 = new JPanel(new GridLayout(0,1));
		p6.setBounds(100, 300, 100, 45);
		//p6.setBackground(Color.black);
		p6.setOpaque(false);
		fmServer.add(p6);

		p7 = new JPanel();
		p7.setBounds(230, 145, 230, 240);
		//p7.setBackground(Color.black);
		p7.setOpaque(false);
		fmServer.add(p7);

		bodyServer = new JPanel();
		bodyServer.setBounds(0,55-15, Framewidth-10,Frameheight-Frameheight/6);
		//bodyServer.setBackground(Color.pink);
		bodyServer.add(piclabel);

		//Image scaledImage = piclabel.getScaledInstance(bodyLogin.getWidth(),bodyLogin.getHeight(),Image.SCALE_SMOOTH);
		fmServer.add(bodyServer);
	}

	private void initializeLabel() {
		JLabel lbIP = new JLabel("IP :");
		lbIP.setOpaque(false);
		lbIP.setForeground(Color.orange);
		lbIP.setFont(new Font("Verdana",1,13));
		lbIP.setBounds(5, 155, 40, 16);
		//fmServer.getContentPane().add(lbIP);
		p1.add(lbIP);

		JLabel lbPort = new JLabel("PORT : ");
		lbPort.setOpaque(false);
		lbPort.setForeground(Color.orange);
		lbPort.setFont(new Font("Verdana",1,13));
		lbPort.setBounds(5, 205, 50, 16);
		p2.add(lbPort);
		//fmServer.getContentPane().add(lbPort);


		JLabel lbGroup = new JLabel("SERVER CONFIGURATION");
		lbGroup.setFont(new Font("Verdana",1,30));
		lbGroup.setForeground(Color.BLUE);
		lbGroup.setBounds(5, 6, 200, 16);
		lbGroup.setOpaque(false);
		headGroup.add(lbGroup);
		//fmServer.getContentPane().add(headGroup);
	}

	private void initializeTextBox() {
		txtIP = new JTextField();
		txtIP.setBounds(55, 150, 205, 28);
		txtIP.setBackground(Color.WHITE);
		p3.add(txtIP);
		//fmServer.getContentPane().add(txtIP);
		txtIP.setColumns(10);
		txtIP.setText("localhost");
		

		txtPort = new JTextField();
		txtPort.setColumns(10);
		txtPort.setBounds(55, 200, 205, 28);
		txtPort.setBackground(Color.WHITE);
		p4.add(txtPort);
		//fmServer.getContentPane().add(txtPort);
		txtPort.setText("8080");

		txtMessage = new JTextArea(13,20);
		txtMessage.setEditable(false);
		txtMessage.setFont(new Font("Hacker",Font.BOLD,10));
		txtMessage.setBackground(Color.ORANGE);
		txtMessage.setForeground(Color.BLUE);

		//Border lineBorder = LineBorder.createBlackLineBorder();
		//Border bevelBorder = BorderFactory.createRaisedBevelBorder();
		Border orangeBorder = BorderFactory.createLineBorder(Color.WHITE, 10);
		//Border bevelLineBorder = new CompoundBorder(lineBorder, orangeBorder);

		JScrollPane scroll = new JScrollPane(txtMessage);
		scroll.setBounds(285, 140, 230, 240);
		scroll.setBorder(orangeBorder);
		p7.add(scroll);
		//fmServer.getContentPane().add(scroll);
		//fmServer.getContentPane().add(txtMessage);
	}

	private void initializeButton() {
		JButton btnStart = new JButton("START");
		//btnStart.setBackground(Color.ORANGE);
		//btnStart.setVisible(true);
		//btnStart.setContentAreaFilled(false);
		//btnStart.setOpaque(true);
		//btnStart.setEnabled(false);
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String ip = txtIP.getText();
					int port = Integer.parseInt(txtPort.getText());
					server = new Server(ip, port);
					flag=1;
					ServerGUI.updateMessage("START SERVER");
				} catch (Exception e) {
					flag=2;
					ServerGUI.updateMessage("START ERROR");
					e.printStackTrace();
				}
			}
		});
		btnStart.setForeground(Color.BLUE);
		//btnStart.setBounds(5, 300, 260, 29);
		p5.add(btnStart);
		//fmServer.getContentPane().add(btnStart);

		JButton btnStop = new JButton("STOP");
		btnStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					server.stop();
					flag=1;
					ServerGUI.updateMessage("STOP SERVER");
				} catch (Exception e) {
					e.printStackTrace();
					flag=1;
					ServerGUI.updateMessage("STOP SERVER");
				}
			}
		});
		btnStop.setForeground(Color.BLUE);
		//btnStop.setBounds(5, 340, 260, 29);
		p6.add(btnStop);
		//fmServer.getContentPane().add(btnStop);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				System.out.println("</PEER_NAME>");
				try {
					ServerGUI window = new ServerGUI();
					window.fmServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void updateMessage(String msg) {
		if(flag==1){
			txtMessage.setForeground(Color.BLUE);
			txtMessage.append(msg + "\n");
		}
		else {
			txtMessage.setForeground(Color.RED);
			txtMessage.append(msg + "\n");
		}
	}

	public static void updateNumberClient() {
		int number = Integer.parseInt(lbNumber.getText());
		lbNumber.setText(Integer.toString(number + 1));
	}
}
