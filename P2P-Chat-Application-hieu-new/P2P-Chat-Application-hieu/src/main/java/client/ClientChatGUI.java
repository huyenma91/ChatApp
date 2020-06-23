package client;

import java.awt.*;

import javax.swing.*;

import mdlaf.MaterialLookAndFeel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import protocol.FileData;
import RequestHandler.RequestReceiver;
import RequestHandler.RequestSender;

public class ClientChatGUI {
	int Framewidth = 688;
	int Frameheight=540;
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
	private static String TEMP = "/temp/";

	private JFrame fmChat;
	private JPanel panelMessage, panelFile, headClientCHat;
	private JLabel lbSending, lbReceiving;
	private JTextField textPath, textSend;
	private JTextArea txtDisplayChat;
	private JProgressBar progressSendFile;
	private JPanel p1,p2,p3,p4,p5,p6,bodyCCG;

	private ChatRoom chat;
	private Socket socketChat;
	private String username = "", guest_name = "", file_name = "";
	public boolean isStop = false, isSendFile = false, isReceiveFile = false;
	private int portServer = 0;

	public ClientChatGUI(String user, String guest, Socket socket, int port) {

		username = user;
		guest_name = guest;
		socketChat = socket;
		this.portServer = port;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientChatGUI window = new ClientChatGUI(username, guest_name, socketChat, portServer, 0);
					window.fmChat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ClientChatGUI() {
		initializeFrame();
		initializePanel();
		initializeLabel();
		initializeTextBox();
		initializeButton();
		initializeProgressBar();
	}

	public ClientChatGUI(String user, String guest, Socket socket, int port, int a) throws Exception {
		username = user;
		guest_name = guest;
		socketChat = socket;
		this.portServer = port;

		initializeFrame();
		initializePanel();
		initializeLabel();
		initializeTextBox();
		initializeButton();
		initializeProgressBar();

		chat = new ChatRoom(socketChat, username, guest_name);
		chat.start();
	}

	private void initializeFrame() {
		fmChat = new JFrame();
		fmChat.setTitle("Chat Room");
		ImageIcon image = new ImageIcon(URL_DIR + "/P2P-Chat-Application-hieu/src/main/resources/icons8-chat-64.png");
		fmChat.setIconImage(image.getImage());
		fmChat.setResizable(false);
		fmChat.setBounds(450, 100, 688, 540);
		fmChat.getContentPane().setLayout(null);
		fmChat.getContentPane().setBackground(Color.LIGHT_GRAY);
		//fmChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fmChat.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		ImageIcon image1 = new ImageIcon(URL_DIR + "/P2P-Chat-Application-hieu/src/main/resources/ChatBG.png");
		JLabel piclabel = new JLabel(image1);
		piclabel.setSize(50,50);
		piclabel.setVisible(true);

		p1 = new JPanel();
		p1.setBounds(6, 80-5, 155, 30);
		p1.setOpaque(false);
		fmChat.add(p1);

		p2 = new JPanel(new GridLayout(0,1));
		p2.setBounds(6, 110, 340, 40);
		//p2.setBackground(Color.black);
		p2.setOpaque(false);
		fmChat.add(p2);

		p3 = new JPanel(new GridLayout(0,1));
		p3.setBounds(6, 180-5, 340, 110);
		//p3.setBackground(Color.black);
		p3.setOpaque(false);
		fmChat.add(p3);

		p4 = new JPanel(new GridLayout(0,1));
		p4.setBounds(6, 363, 660, 85);
		//p4.setBackground(Color.black);
		p4.setOpaque(false);
		fmChat.add(p4);

		p5 = new JPanel(new GridLayout(0,1));
		p5.setBounds(8, 460, 650, 30);
		//p5.setBackground(Color.black);
		p5.setOpaque(false);
		fmChat.add(p5);

		p6 = new JPanel(new GridLayout(0,1));
		p6.setBounds(360, 80, 300, 260);
		p6.setBackground(Color.black);
		//p6.setOpaque(false);
		fmChat.add(p6);

		headClientCHat = new JPanel();
		headClientCHat.setBounds(0,0, 688,540/8);
		headClientCHat.setBackground(Color.ORANGE);
		fmChat.add(headClientCHat);

		bodyCCG = new JPanel();
		bodyCCG.setBounds(0,540/8-10, Framewidth-10,Frameheight-540/8);
		//bodyCCG.setBackground(Color.pink);
		bodyCCG.add(piclabel);
		fmChat.add(bodyCCG);


	}
	
	private void initializePanel() {
		//Border orangeBorderC = BorderFactory.createLineBorder(Color.ORANGE, 5);
		panelFile = new JPanel();
		panelFile.setBounds(6, 363, 660, 85);
		panelFile.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "File",0,0,new Font("Verdana",1,15),Color.orange));
		//panelFile.setBorder(orangeBorderC);
		panelFile.setOpaque(false);
		p4.add(panelFile);
		//fmChat.getContentPane().add(panelFile);
		panelFile.setLayout(null);

		panelMessage = new JPanel();
		panelMessage.setBounds(6, 180, 340, 110);
		panelMessage.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Message",1,0,new Font("Verdana",1,15),Color.orange));
		//panelMessage.setBorder(orangeBorderC);
		panelMessage.setOpaque(false);
		p3.add(panelMessage);
		//fmChat.getContentPane().add(panelMessage);
		panelMessage.setLayout(null);
	}

	private void initializeLabel() {
		JLabel lbWelcome = new JLabel("CHAT");
		lbWelcome.setFont(new Font("Verdana",1,30));
		lbWelcome.setForeground(Color.blue);
		lbWelcome.setOpaque(false);
		headClientCHat.add(lbWelcome);

		JLabel lbClientName = new JLabel("CHATTING WITH: ");
		lbClientName.setFont(new Font("Verdana",1,15));
		lbClientName.setForeground(Color.ORANGE);
		lbClientName.setOpaque(false);
		lbClientName.setBounds(6, 80, 155, 20);
		p1.add(lbClientName);
		//fmChat.getContentPane().add(lbClientName);

		JLabel lbAddress = new JLabel("Address: ");
		lbAddress.setFont(new Font("Verdana",1,15));
		lbAddress.setForeground(Color.orange);
		lbAddress.setOpaque(false);
		lbAddress.setBounds(10, 21, 100, 22);
		panelFile.add(lbAddress);

		lbSending = new JLabel("Sending");
		lbAddress.setOpaque(false);
		lbSending.setBounds(10, 53, 81, 22);
		lbSending.setVisible(false);
		panelFile.add(lbSending);

		lbReceiving = new JLabel("Receiving ...");
		lbReceiving.setOpaque(false);
		lbReceiving.setBounds(550, 53, 81, 22);
		lbReceiving.setVisible(false);
		panelFile.add(lbReceiving);
	}
	
	private void initializeTextBox() {
		Border orangeBorderA = BorderFactory.createLineBorder(Color.WHITE, 5);
		JTextField textName = new JTextField(username,30);
		textName.setBorder(orangeBorderA);
		textName.setForeground(Color.blue);
		textName.setBackground(Color.orange);
		textName.setEditable(false);
		textName.setBounds(6, 110, 340, 5);
		p2.add(textName);
		//fmChat.getContentPane().add(textName);
		textName.setText(guest_name);
		//textName.setColumns(30);


		txtDisplayChat = new JTextArea();
		txtDisplayChat.setBackground(Color.orange);
		txtDisplayChat.setForeground(Color.blue);
		txtDisplayChat.setEditable(false);
		txtDisplayChat.setBounds(360, 80, 300, 260);
		//fmChat.getContentPane().add(txtDisplayChat);

		Border orangeBorder = BorderFactory.createLineBorder(Color.WHITE, 10);

		JScrollPane scroll = new JScrollPane(txtDisplayChat);
		scroll.setBounds(360, 80, 300, 260);
		scroll.setBorder(orangeBorder);
		p6.add(scroll);
		//fmChat.getContentPane().add(scroll);

		textPath = new JTextField("");
		textPath.setForeground(Color.blue);
		textPath.setBackground(Color.white);
		textPath.setBounds(100, 21, 280, 25);
		panelFile.add(textPath);
		textPath.setEditable(false);
		textPath.setColumns(10);

		textSend = new JTextField("");
		textSend.setBackground(Color.white);
		textSend.setBounds(10, 21, 320, 39);
		panelMessage.add(textSend);
		textSend.setForeground(Color.blue);
		textSend.setColumns(10);

		textSend.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					String msg = textSend.getText();
					if (isStop) {
						updateChat("[" + username + "] : " + textSend.getText().toString());
						textSend.setText("");
						return;
					}
					if (msg.equals("")) {
						textSend.setText("");
						textSend.setCaretPosition(0);
						return;
					}
					try {
						chat.sendMessage(RequestSender.send_text_content(msg));
						updateChat("["+ username +"] : " + msg);
						textSend.setText("");
						textSend.setCaretPosition(0);
					} catch (Exception e) {
						textSend.setText("");
						textSend.setCaretPosition(0);
					}
				}
			}
		});
	}

	private void initializeButton() {

		File fileTemp = new File(URL_DIR + "/temp");
		if (!fileTemp.exists()) {
			fileTemp.mkdirs();
		}
		
		JButton btnChoose = new JButton("Browse");
		btnChoose.setForeground(Color.blue);
		btnChoose.setBounds(390, 21, 85, 25);
		panelFile.add(btnChoose);
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System
						.getProperty("user.home")));
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int result = fileChooser.showOpenDialog(fmChat);
				if (result == JFileChooser.APPROVE_OPTION) {
					isSendFile = true;
					String path_send = (fileChooser.getSelectedFile().getAbsolutePath()) ;
					file_name = fileChooser.getSelectedFile().getName();
					textPath.setText(path_send);
				}
			}
		});
		
		JButton btnUpload = new JButton("Send");
		btnUpload.setForeground(Color.blue);
		btnUpload.setBounds(475, 21, 85, 25);
		panelFile.add(btnUpload);
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isSendFile)
					try {
						chat.sendMessage(RequestSender.send_file_req(file_name));
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});

		JButton btnDelete = new JButton("Clear");
		btnDelete.setForeground(Color.blue);
		btnDelete.setBounds(560, 21, 85, 25);
		panelFile.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isSendFile = false;
				textSend.setText("");
				textPath.setText("");
			}
		});

		JButton btnSend = new JButton("SEND");
		btnSend.setBounds(10, 70, 320, 30);
		btnSend.setForeground(Color.blue);
		panelMessage.add(btnSend);
		btnSend.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (isStop) {
					updateChat("[" + username +"] : " + textSend.getText().toString());
					textSend.setText("");
					return;
				}
				String msg = textSend.getText();
				if (msg.equals(""))
					return;
				try {
					chat.sendMessage(RequestSender.send_text_content(msg));
					updateChat("["+ username +"] : " + msg);
					textSend.setText("");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		JButton btnDisconnect = new JButton("DISCONNECT");
		btnDisconnect.setForeground(Color.blue);
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Show confirm diaglog
				int result = JOptionPane.showConfirmDialog(
					fmChat, "Do you want close chat with " + guest_name, null, 
					JOptionPane.YES_NO_OPTION
				);
				// Yes Option
				if (result == 0) {
					try {
						isStop = true;
						fmChat.dispose();
						chat.sendMessage("<CHAT_CLOSE />");
						chat.stopChat();
						System.gc();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnDisconnect.setBounds(8, 460, 650, 30);
		p5.add(btnDisconnect);
		//fmChat.getContentPane().add(btnDisconnect);
	}

	private void initializeProgressBar() {
		progressSendFile = new JProgressBar(0, 100);
		progressSendFile.setBounds(93, 60, 388, 14);
		progressSendFile.setStringPainted(true);
		panelFile.add(progressSendFile);
		progressSendFile.setVisible(false);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientChatGUI window = new ClientChatGUI();
					window.fmChat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void updateChat(String msg) {
		txtDisplayChat.append(msg + "\n");
	}

	public void copyFileReceive(
		InputStream inputStr, 
		OutputStream outputStr, 
		String path)
		throws IOException {

			byte[] buffer = new byte[1024];
			int lenght;
			while ((lenght = inputStr.read(buffer)) > 0)
				outputStr.write(buffer, 0, lenght);
	
			inputStr.close();
			outputStr.close();
			File fileTemp = new File(path);
			if (fileTemp.exists()) fileTemp.delete();

	}

	public class ChatRoom extends Thread {

		private Socket connect;
		private ObjectOutputStream outPeer;
		private ObjectInputStream inPeer;
		private boolean continueSendFile = true, finishReceive = false;
		private int sizeOfSend = 0, sizeOfData = 0, sizeFile = 0, sizeReceive = 0;
		private String nameFileReceive = "";
		private InputStream inFileSend;
		private FileData dataFile;

		public ChatRoom(Socket connection, String name, String guest) 
		throws Exception {
			connect = new Socket();
			connect = connection;
			guest_name = guest;
		}

		@Override
		public void run() {
			super.run();
			OutputStream out = null;
			while (!isStop) {
				try {
					// Get data from another peer
					inPeer = new ObjectInputStream(connect.getInputStream());
					Object obj = inPeer.readObject();

					if (obj instanceof String) {
						String msgObj = obj.toString();

						if (msgObj.equals("<CHAT_CLOSE />")) {
							isStop = true;

							JOptionPane.showMessageDialog(
								fmChat, guest_name + " may be close chat with you!");

							connect.close();
							break;
						}

						if (RequestReceiver.get_file(msgObj)) {
							isReceiveFile = true;
							nameFileReceive = msgObj.substring(10,
									msgObj.length() - 11);

							int result = JOptionPane.showConfirmDialog(
								fmChat, guest_name + " send file " + nameFileReceive
								+ " for you", null,  JOptionPane.YES_NO_OPTION
							);
							
							if (result == 0) {
								File fileReceive = new File(URL_DIR + TEMP
										+ "/" + nameFileReceive);
								if (!fileReceive.exists()) {
									fileReceive.createNewFile();
								}
								String msg = "<FILE_REQ_ACK>"
										+ Integer.toBinaryString(portServer)
										+ "</FILE_REQ_ACK>";
								sendMessage(msg);
							} else {
								sendMessage("<FILE_REQ_NOACK />");
							}
						}

						if (RequestReceiver.get_ack(msgObj)) {
							new Thread(new Runnable() {
								public void run() {
									try {
										sendMessage("<FILE_DATA_BEGIN />");
										updateChat("you are sending file:	" + file_name);
										isSendFile = false;
										sendFile(textPath.getText());
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}).start();
						} else if (msgObj.equals("<FILE_REQ_NOACK />")) {
							JOptionPane.showMessageDialog(
								fmChat, guest_name + " wantn't receive file");
						} else if (msgObj.equals("<FILE_DATA_BEGIN />")) {
							finishReceive = false;
							lbReceiving.setVisible(true);
							out = new FileOutputStream(URL_DIR + TEMP
									+ nameFileReceive);
						} else if (msgObj.equals("</FILE_DATA>")) {
							updateChat("You receive file:	" + nameFileReceive + " with size " + sizeReceive + " KB");
							sizeReceive = 0;
							out.flush();
							out.close();
							lbReceiving.setVisible(false);
							new Thread(new Runnable() {

								@Override
								public void run() {
									showSaveFile();
								}
							}).start();
							finishReceive = true;
						} else {
							String message = RequestReceiver.get_text_msg_content(msgObj);
							updateChat("[" + guest_name + "] : " + message);
						}
					} else if (obj instanceof FileData) {
						FileData data = (FileData) obj;
						++sizeReceive;
						out.write(data.data);
					}
				} catch (Exception e) {
					File fileTemp = new File(URL_DIR + TEMP + nameFileReceive);
					if (fileTemp.exists() && !finishReceive) {
						fileTemp.delete();
					}
				}
			}
		}

		private void getData(String path) throws Exception {
			File fileData = new File(path);
			if (fileData.exists()) {
				sizeOfSend = 0;
				dataFile = new FileData();
				sizeFile = (int) fileData.length();
				// sizeOfData = sizeFile % 1024 == 0 ? (int) (fileData.length() / 1024) : (int) (fileData.length() / 1024) + 1;
				sizeOfData =  (int) (fileData.length() / 1024) + 1;
				lbSending.setVisible(true);
				progressSendFile.setVisible(true);
				progressSendFile.setValue(0);
				inFileSend = new FileInputStream(fileData);
			}
		}

		public void sendFile(String path) throws Exception {
			getData(path);
			lbSending.setText("Sending ...");
			do {
				if (continueSendFile) {
					continueSendFile = false;
					new Thread(new Runnable() {

						@Override
						public void run() {
							try {
								inFileSend.read(dataFile.data);
								sendMessage(dataFile);
								sizeOfSend++;
								if (sizeOfSend == sizeOfData - 1) {
									int size = sizeFile - sizeOfSend * 1024;
									dataFile = new FileData(size);
								}
								progressSendFile.setValue((int) (sizeOfSend * 100 / sizeOfData));
								System.out.println(sizeOfSend);
								System.out.println(sizeOfData);
								System.out.println("");
								if (sizeOfSend >= sizeOfData) {
									inFileSend.close();
									isSendFile = true;
									sendMessage("</FILE_DATA>");
									progressSendFile.setVisible(false);
									lbSending.setVisible(false);
									isSendFile = false;
									textPath.setText("");
									updateChat("YOU ARE SEND FILE COMPLETE !!!");
								}
								continueSendFile = true;
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}).start();
					Thread.sleep(10);
				}
			} while (sizeOfSend < sizeOfData);
		}

		private void showSaveFile() {
			while (true) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System
						.getProperty("user.home")));
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = fileChooser.showSaveDialog(fmChat);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = new File(fileChooser.getSelectedFile()
							.getAbsolutePath() + "/" + nameFileReceive );
					if (!file.exists()) {
						try {
							file.createNewFile();
							Thread.sleep(1000);
							InputStream input = new FileInputStream(URL_DIR
									+ TEMP + nameFileReceive);
							OutputStream output = new FileOutputStream(
									file.getAbsolutePath());
							copyFileReceive(input, output, URL_DIR + TEMP
									+ nameFileReceive);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(fmChat, "Your file receive has error!!!");
						}
						break;
					} else {
						int resultContinue = JOptionPane.showConfirmDialog(
							fmChat, "File is exists. You want save file?", null, 
							JOptionPane.YES_NO_OPTION
						);
						if (resultContinue == 0)
							continue;
						else
							break;
					}
				}
			}
		}

		public synchronized void sendMessage(Object obj) throws Exception {
			outPeer = new ObjectOutputStream(connect.getOutputStream());
			if (obj instanceof String) {
				String message = obj.toString();
				outPeer.writeObject(message);
				outPeer.flush();
				if (isReceiveFile)
					isReceiveFile = false;
			} else if (obj instanceof FileData) {
				outPeer.writeObject(obj);
				outPeer.flush();
			}
		}

		public void stopChat() {
			try {
				connect.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
