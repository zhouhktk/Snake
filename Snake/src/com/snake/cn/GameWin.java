package com.snake.cn;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GameWin extends JPanel implements ActionListener, KeyListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel gamePanel; 
	private JButton start, pause, quit;
	private JLabel score;
	boolean startFlag = false;
	private Snake snake;
	private Image buf=null;
	private Graphics gbuf=null;
	

	/**
	 * Create the frame.
	 */
	public GameWin() {
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gbl_contentPane);
		
		//游戏区
		gamePanel = new JPanel();
		gamePanel.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_gamePanel = new GridBagConstraints();
		gbc_gamePanel.gridwidth = 2;
		gbc_gamePanel.gridheight = 8;
		gbc_gamePanel.insets = new Insets(0, 0, 0, 5);
		gbc_gamePanel.fill = GridBagConstraints.BOTH;
		gbc_gamePanel.gridx = 0;
		gbc_gamePanel.gridy = 0;
		add(gamePanel, gbc_gamePanel);
		
		//开始按钮
		start = new JButton("\u5F00\u59CB");
		GridBagConstraints gbc_start = new GridBagConstraints();
		gbc_start.insets = new Insets(0, 0, 5, 0);
		gbc_start.gridx = 2;
		gbc_start.gridy = 0;
		add(start, gbc_start);
		
		//暂停按钮
		pause = new JButton("\u6682\u505C");
		GridBagConstraints gbc_pause = new GridBagConstraints();
		gbc_pause.insets = new Insets(0, 0, 5, 0);
		gbc_pause.gridx = 2;
		gbc_pause.gridy = 2;
		add(pause, gbc_pause);
		pause.setEnabled(false);//初始时默认不可用
		
		//退出按钮
		quit = new JButton("\u9000\u51FA");
		GridBagConstraints gbc_quit = new GridBagConstraints();
		gbc_quit.insets = new Insets(0, 0, 5, 0);
		gbc_quit.gridx = 2;
		gbc_quit.gridy = 4;
		add(quit, gbc_quit);
		//计分标签
		score = new JLabel("\u5F97\u5206\uFF1A0");
		GridBagConstraints gbc_score = new GridBagConstraints();
		gbc_score.insets = new Insets(0, 0, 5, 0);
		gbc_score.gridx = 2;
		gbc_score.gridy = 6;
		add(score, gbc_score);
		//注册监听器
		start.addActionListener(this);
		pause.addActionListener(this);
		quit.addActionListener(this);
		this.addKeyListener(this);
		
		snake = new Snake(gamePanel);
		
		new snakeThread().start();
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(!startFlag) return ;
        switch(e.getKeyCode()) {
        case KeyEvent.VK_UP:
            if(snake.getDir() == Confi.Down) break;
            snake.changeDir(Confi.Up);
            break;
        case KeyEvent.VK_DOWN:
            if(snake.getDir() == Confi.Up) break;
            snake.changeDir(Confi.Down);
            break;
        case KeyEvent.VK_LEFT:
            if(snake.getDir() == Confi.Right) break;
            snake.changeDir(Confi.Left);
            break;
        case KeyEvent.VK_RIGHT:
            if(snake.getDir() == Confi.Left) break;
            snake.changeDir(Confi.Right);
            break;
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == start) {
            startFlag = true;
            start.setEnabled(false);
            pause.setEnabled(true);
        }
        if(e.getSource() == pause) {
            startFlag = false;
            start.setEnabled(true);
            pause.setEnabled(false);
        }
        if(e.getSource() == quit) {
            System.exit(0);
        }
        this.requestFocus();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		score.setText("得分："+Confi.score);
		
		int rows = Confi.rows = (int)gamePanel.getHeight()/Confi.FoodHeight;
		int cols = Confi.cols = (int)gamePanel.getWidth()/Confi.FoodWidth;
		
		//建立双缓冲
		buf = createImage(gamePanel.getWidth(), gamePanel.getHeight());
		gbuf=buf.getGraphics();
		gbuf.setColor(gamePanel.getBackground());
		gbuf.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
		
		//绘制墙壁
		gbuf.setColor(Color.gray);
		for(int i=0;i<rows;i++) {
			gbuf.fillRect(0*Confi.WallSize, i*Confi.WallSize, Confi.WallSize, Confi.WallSize);
		}
		for(int i=0;i<rows;i++) {
			gbuf.fillRect((cols-1)*Confi.WallSize, i*Confi.WallSize, Confi.WallSize, Confi.WallSize);
		}
		for(int i=0;i<cols;i++) {
			gbuf.fillRect(i*Confi.WallSize, 0*Confi.WallSize, Confi.WallSize, Confi.WallSize);
		}
		for(int i=0;i<cols;i++) {
			gbuf.fillRect(i*Confi.WallSize, (rows-1)*Confi.WallSize, Confi.WallSize, Confi.WallSize);
		}
				
		snake.draw(gbuf);
		gbuf.dispose();
		g.drawImage(buf, gamePanel.getX(), getY(), null);
	}
	
	
	class snakeThread extends Thread
    {
        public void run() {
            while(true) {
                try {
                    Thread.sleep(200 - Confi.speed >= 0 ? 200 - Confi.speed : 0);
                    repaint();
                    if(startFlag) {
                        snake.move();
                    }
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}