package com.snake.cn;

public class Food {
	private int x , y;
    public Food() {
    	y = (int)Math.floor(Math.random()*(Confi.rows-2))+1;
		x = (int)Math.floor(Math.random()*(Confi.cols-2))+1;
    }
    public Food(int a , int b) {x = a; y = b;}
    public Food(Food tmp) {x = tmp.getX(); y = tmp.getY();}
    int getX() {return x;}
    int getY() {return y;}
    void setX(int a) {x = a;}
    void setY(int b) {y = b;}
}
