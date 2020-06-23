package client;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.*;

import mdlaf.MaterialLookAndFeel;
import RequestHandler.RequestSender;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import server.ControllerGUI;
import server.demoGUI;
public class LoginGUI {
	int Framewidth = 448;
	int Frameheight = 300;

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

	private static String NAME_FAILED = "CONNECT WITH OTHER NAME";
	private static String NAME_EXSIST = "NAME IS EXSISED";
	private static String SERVER_NOT_START = "SERVER NOT START";

	private JFrame fmLogin;
	private JPanel bodyLogin,headLogin;
	private JLabel lbError;
	private JTextField txtIP, txtPort, txtUsername;
	private JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9;



	public LoginGUI() {
		initializeFrame();
		initializeLabel();
		initializeTextBox();
		initializeButton();
	}


	private void initializeFrame() {
		fmLogin = new JFrame();
		fmLogin.setTitle("Login");
		//ImageIcon image = new ImageIcon("P2P-Chat-Application-hieu/src/main/resources/icons8-login-64.png");
		ImageIcon image = new ImageIcon(URL_DIR + "/P2P-Chat-Application-hieu/src/main/resources/icons8-login-64.png");
		fmLogin.setIconImage(image.getImage());

		fmLogin.setResizable(false);
		fmLogin.setBounds(200, 200, Framewidth, Frameheight);
		fmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fmLogin.getContentPane().setBackground(Color.LIGHT_GRAY);
		fmLogin.getContentPane();



		JLabel label = new JLabel(image);
		fmLogin.add(label);
		fmLogin.getContentPane().setLayout(null);

		headLogin = new JPanel();
		headLogin.setBounds(0,0, Framewidth,Frameheight/6);
		headLogin.setBackground(Color.ORANGE);
		fmLogin.add(headLogin);

		ImageIcon image1 = new ImageIcon(URL_DIR + "/P2P-Chat-Application-hieu/src/main/resources/loginBGnew.jpg");
		JLabel piclabel = new JLabel(image1);
		piclabel.setSize(50,50);
		piclabel.setVisible(true);

		p1 = new JPanel();
		p1.setBounds(100, 60, 60, 20);
		p1.setOpaque(false);
		fmLogin.add(p1);

		p2 = new JPanel();
		p2.setBounds(100, 100, 60, 20);
		p2.setOpaque(false);
		fmLogin.add(p2);

		p3 = new JPanel();
		p3.setBounds(100, 140, 60, 20);
		p3.setOpaque(false);
		fmLogin.add(p3);

		p4 = new JPanel();
		p4.setBounds(200, 60, 152, 30);
		p4.setOpaque(false);
		fmLogin.add(p4);

		p5 = new JPanel();
		p5.setBounds(200, 100, 152, 30);
		p5.setOpaque(false);
		fmLogin.add(p5);

		p6 = new JPanel();
		p6.setBounds(200, 140, 152, 30);
		p6.setOpaque(false);
		fmLogin.add(p6);

		p7 = new JPanel();
		p7.setBounds(120, 180, 100, 50);
		p7.setOpaque(false);
		fmLogin.add(p7);

		p8 = new JPanel();
		p8.setBounds(220, 180, 100, 50);
		p8.setOpaque(false);
		fmLogin.add(p8);

		p9 = new JPanel();
		p9.setBounds(110, 230, 220, 30);
		p9.setOpaque(false);
		fmLogin.add(p9);

		bodyLogin = new JPanel();
		bodyLogin.setBounds(0,Frameheight/6-10, Framewidth,Frameheight-Frameheight/6);
		//bodyLogin.setBackground(Color.pink);
		bodyLogin.add(piclabel);

		//Image scaledImage = piclabel.getScaledInstance(bodyLogin.getWidth(),bodyLogin.getHeight(),Image.SCALE_SMOOTH);
		fmLogin.add(bodyLogin);


	}


	private void initializeLabel() {
		JLabel lbWelcome = new JLabel("LOGIN");
		lbWelcome.setFont(new Font("Verdana",1,30));
		lbWelcome.setForeground(Color.blue);
		lbWelcome.setOpaque(false);
		//lbWelcome.setBounds(100, 40, 180, 40);
		headLogin.add(lbWelcome);


		JLabel lbIP = new JLabel("IP : ");
		lbIP.setFont(new Font("Verdana",1,13));
		lbIP.setForeground(Color.orange);
		lbIP.setBounds(100, 60, 60, 20);
		lbIP.setOpaque(false);
		p1.add(lbIP);
		//fmLogin.getContentPane().add(lbIP);


		JLabel lbPort = new JLabel("Port : ");
		lbPort.setFont(new Font("Verdana",1,13));
		lbPort.setForeground(Color.orange);
		lbPort.setBounds(100, 100, 60, 20);
		lbPort.setOpaque(false);
		p2.add(lbPort);
		//fmLogin.getContentPane().add(lbPort);

		JLabel lbUsername = new JLabel("Name : ");
		lbUsername.setFont(new Font("Verdana",1,13));
		lbUsername.setForeground(Color.orange);
		lbUsername.setBounds(100, 140, 60, 20);
		lbUsername.setOpaque(false);
		p3.add(lbUsername);
		//fmLogin.getContentPane().add(lbUsername);

		lbError = new JLabel("CONNECT WITH OTHER NAME");
		lbError.setForeground(Color.red);
		lbError.setOpaque(false);
		/*
		lbError.setBounds(160, 230, 200, 14);
		lbError.setBackground(Color.BLUE);
		fmLogin.getContentPane().add(lbError);*/
		JPanel errorPanel = new JPanel();
		errorPanel.setBounds(110, 210, 220, 30);
		errorPanel.setOpaque(false);
		errorPanel.add(lbError);

		//fmLogin.getContentPane().add(errorPanel);
		p9.add(lbError);
	}

	private void initializeTextBox() {
		txtIP = new JTextField();
		txtIP.setColumns(10);
		txtIP.setText("localhost");
		txtIP.setBounds(200, 60, 152, 30);
		txtIP.setBackground(Color.WHITE);
		//fmLogin.getContentPane().add(txtIP);
		p4.add(txtIP);

		txtPort = new JTextField();
		txtPort.setColumns(10);
		txtPort.setText("8080");
		txtPort.setBounds(200, 100, 152, 30);
		txtPort.setBackground(Color.white);
		//fmLogin.getContentPane().add(txtPort);
		p5.add(txtPort);

		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setText("type username");
		txtUsername.setBounds(200, 140, 152, 30);
		txtUsername.setBackground(Color.WHITE);
		//fmLogin.getContentPane().add(txtUsername);
		p6.add(txtUsername);
	}

	private void initializeButton() {
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String server_ip = txtIP.getText();
				int server_port = Integer.parseInt(txtPort.getText());
				String username = txtUsername.getText();
				Pattern checkName = Pattern.compile("[a-zA-Z][^<>]*");
				lbError.setVisible(false);

				if (checkName.matcher(username).matches() && !server_ip.equals("")) {
					try {
						// Generate a random number port for peer
						Random rd = new Random();
						int peer_port = 10000 + rd.nextInt() % 1000;

						// Open a socket to connect with the server
						InetAddress server_ip_addr = InetAddress.getByName(server_ip);
						Socket socketClient = new Socket(server_ip_addr, server_port);

						// RequestSender message (user-defined protocol)
						String message = RequestSender.send_acc_req(username,Integer.toString(peer_port));

						// Send message to the server
						ObjectOutputStream sender = new ObjectOutputStream(socketClient.getOutputStream());
						sender.writeObject(message); sender.flush();

						// Get acknowledgment from the server
						ObjectInputStream listener = new ObjectInputStream(socketClient.getInputStream());
						message = (String) listener.readObject();

						// Close socket
						socketClient.close();

						if (message.equals("<SESSION_DENY />")) {
							lbError.setText(NAME_EXSIST);
							lbError.setVisible(true);

						}
						new ControllerGUI(server_ip, server_port, peer_port, username, message);
						fmLogin.dispose();
					} catch (Exception e) {
						lbError.setText(SERVER_NOT_START);
						lbError.setVisible(true);
						e.printStackTrace();
					}
				} else {
					lbError.setText(NAME_FAILED);
					lbError.setVisible(true);

				}
			}
		});
		loginButton.setBounds(120, 180, 90, 29);
		loginButton.setForeground(Color.BLUE);
		//fmLogin.getContentPane().add(loginButton);
		p7.add(loginButton);
		lbError.setVisible(false);

		JButton btnclear = new JButton("Clear");
		btnclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtIP.setText("");
				txtPort.setText("");
				txtUsername.setText("");
				lbError.setText("");
			}
		});
		btnclear.setBounds(220, 180, 90, 29);
		btnclear.setForeground(Color.BLUE);
		p8.add(btnclear);
		//fmLogin.getContentPane().add(btnclear);
		lbError.setVisible(false);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
					window.fmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

