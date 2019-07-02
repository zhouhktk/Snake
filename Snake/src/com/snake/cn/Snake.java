package com.snake.cn;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.SystemTray;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sun.org.apache.regexp.internal.recompile;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class Snake {
	
	static final int DIR[][] = {{0 , -1} , {0 , 1} , {-1 , 0} , {1 , 0}};
    private List<Food> lt = new ArrayList<Food>();
    private int currentDir;
    private JPanel panel;
    private Food food=null;

    public Snake(JPanel panel) {
    	this.panel = panel;
        currentDir = 3;
                
        lt.add(new Food(12, 15));
        food = new Food(12, 20);        
    }
    
    public Food generateFood() {
		Food temp = new Food();
		while (true) {
			Boolean c = false;
			for(int i=0;i<lt.size();i++) {
				if (temp.getX()==lt.get(i).getX() && temp.getY()==lt.get(i).getY()) {
					temp = new Food();
					c = true;
					break;
				}
			}
			if (!c) {
				break;
			}
		}
		return temp;
		
	}
    
    //获取当前的运动方向
    int getDir() {
    	return currentDir;
    }
    
    //绘制蛇身及食物
    void draw(Graphics g) 
    {
    	g.setColor(Color.RED);
    	g.fillRect(food.getX()*Confi.FoodWidth, food.getY()*Confi.FoodHeight, Confi.FoodWidth, Confi.FoodHeight);
    	//蛇头
    	g.setColor(Color.green);
    	if (lt.size()>0) {
        	g.fillRect(lt.get(0).getX()*Confi.FoodWidth, lt.get(0).getY()*Confi.FoodHeight, Confi.FoodWidth, Confi.FoodHeight);
		}
    	//蛇身
        g.setColor(Color.black);
        for(int i = 1; i < lt.size(); i++) {
            g.fillRect(lt.get(i).getX()*Confi.FoodWidth, lt.get(i).getY()*Confi.FoodHeight, Confi.FoodWidth, Confi.FoodHeight);
        }
    }
    
    //判定死亡
    boolean Dead() {
    	Boolean KnockWall = lt.get(0).getX() <= 0 || lt.get(0).getX() >= Confi.cols-1 || lt.get(0).getY() <= 0 || lt.get(0).getY() >= Confi.rows-1;
    	if (lt.size()==1 && KnockWall) {
				return true;
		}
    	for(int i=1;i<lt.size();i++) {
    		if(KnockWall || (lt.get(0).getX()==lt.get(i).getX()&&lt.get(0).getY()==lt.get(i).getY()))
    			return true;
    	}
        return false;
    }

	public void move() {
		Boolean eated = false;//判断是否吃到了食物
		int tx = lt.get(0).getX() + DIR[currentDir][0];
        int ty = lt.get(0).getY() + DIR[currentDir][1];
        if (tx==food.getX()&&ty==food.getY()) {
			food = generateFood();
			eated=true;
		}
        
        lt.add(0, new Food(tx, ty));
        
        if (!eated) {
        	lt.remove(lt.size()-1);
        	Confi.score=lt.size()-1;
		}
        
        if (Dead()) {
        	JOptionPane.showMessageDialog(null, "GAME OVER", "Message", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
		}
        
	}
	
	void changeDir(int dir) {currentDir = dir;}

}
