package com.snake.cn;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Boot extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Boot frame = new Boot();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Boot() {
		setTitle("贪吃蛇");
		setIconImage(Toolkit.getDefaultToolkit().getImage("./res/snake.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 580, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8D2A\u5403\u86C7");
		label.setBounds(136, 65, 297, 104);
		label.setFont(new Font("华文彩云", Font.PLAIN, 99));
		contentPane.add(label);
		
		JButton button = new JButton("\u8FDB\u5165\u6E38\u620F");
		button.setBounds(176, 204, 220, 51);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainWindow().setVisible(true);
				Boot.this.dispose();
			}
		});
		button.setFont(new Font("华文彩云", Font.PLAIN, 40));
		contentPane.add(button);
		
		JButton btnNewButton = new JButton("   \u9000  \u51FA   ");
		btnNewButton.setBounds(176, 290, 220, 51);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setFont(new Font("华文彩云", Font.PLAIN, 40));
		contentPane.add(btnNewButton);
		
	}
}
