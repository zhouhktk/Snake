package com.snake.cn;

public class Food {
	private int x , y, number;
    public Food() {
    	y = (int)Math.floor(Math.random()*(Confi.rows-2))+1;
		x = (int)Math.floor(Math.random()*(Confi.cols-2))+1;
		number = (int)Math.floor(Math.random()*7+1);
    }
    public Food(int a , int b) {
    	x = a; 
    	y = b;
    	number = (int)Math.floor(Math.random()*7+1);
    }

    int getX() {return x;}
    int getY() {return y;}
    int getNumber(){return number;}
    void setX(int a) {x = a;}
    void setY(int b) {y = b;}
    void setNuber(int number){this.number=number;}
}
