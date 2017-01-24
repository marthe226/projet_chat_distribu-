package Fenetre;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.SystemDeChat;
import mesobjets.Client;
import mesobjets.Pair;

public class Fenetre {

	public String Message = "Discussion \n";
	JButton send;
	JTextArea textarea = new JTextArea(Message);
	JTextField text = new JTextField("Bonjour \n");
	Client client;

	public Fenetre(Client client) {
		this.client = client;
		// Création et paramétrisation de la fenêtre
		JFrame f = new JFrame("P2P chat");

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500, 200);

		// Création du bouton envoyer
		JButton send = new JButton("Envoyer");
		JButton chat = new JButton("Nouvelle discussion");
		JButton list = new JButton("Voir toutes les conversations");

		// Création de la zone de texte pour taper son message

		text.setPreferredSize(new Dimension(900, 50));

		// Création de la boite horizontale
		Box bottom = Box.createHorizontalBox();

		bottom.add(text);
		bottom.add(send);
		bottom.add(chat);
		bottom.add(list);
		// Création de la zone de texte du haut

		textarea.setPreferredSize(new Dimension(900, 150));
		textarea.setEditable(false);

		// Création de la boite verticale
		Box main = Box.createVerticalBox();

		main.add(textarea);
		main.add(bottom);

		// On met le tout sur la fenêtre et on l'affiche
		Container contentpane = f.getContentPane();
		contentpane.add(main);

		send.addActionListener(new ActionListener() {

			// Definit l'action du bouton
			public void actionPerformed(ActionEvent arg0) {
				Fenetre.this.setTextarea(client.getIdentifiant() + ":" + text.getText() + "\n");
				text.setText("");
			}

		});

		chat.addActionListener(new ActionListener() {

			// Definit l'action du bouton
			public void actionPerformed(ActionEvent arg0) {
				int chatkey = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez votre id: ", " Identifiant",JOptionPane.PLAIN_MESSAGE));
				try {
					SystemDeChat sc = new SystemDeChat(chatkey);
					Fenetre.this.client.JoinChatRoom(chatkey);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		f.setVisible(true);
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public JButton getSend() {
		return send;
	}

	public void setSend(JButton send) {
		this.send = send;
	}

	public String getTextarea() {
		return textarea.getText();
	}

	public void setTextarea(String textarea) {
		this.setMessage(Message + textarea);
		this.textarea.setText(Message);
	}

	public String getText() {
		return text.getText();
	}

	public void setText(String text) {
		this.text.setText(text);
	}

}
