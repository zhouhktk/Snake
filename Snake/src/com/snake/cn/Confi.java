package com.snake.cn;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;


public class Confi {
	public static int FoodHeight=30;
	public static int FoodWidth=30;
	public static int speed = 20;
	public static int WallSize = 30;
	public static int score=0;
	
	public static final int Up = 0 , Down = 1 , Left = 2 , Right = 3;
	
	public static int rows;
	public static int cols;
	
	//蛇和食物的图片
	public static Image wall = new ImageIcon("./res/wall.png").getImage();
	public static Image bg = new ImageIcon("./res/bg.png").getImage();
	public static Image header = new ImageIcon("./res/header.gif").getImage();
	
	public static Image body = new ImageIcon("./res/body1.gif").getImage();
}
