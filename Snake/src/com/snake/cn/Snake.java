package com.snake.cn;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Snake {
	
	static final int DIR[][] = {{0 , -1} , {0 , 1} , {-1 , 0} , {1 , 0}};
    private List<Food> lt = new ArrayList<Food>();
    private int currentDir;
    private Food food=null;
    private  AudioClip eatMusic=null, loseMusic=null, bgsound=null;
    
	public Snake() {
    	start();
    }
    
    @SuppressWarnings("deprecation")
	void start(){
    	if (eatMusic==null || loseMusic==null || bgsound==null) {
    		try {
    			eatMusic = Applet.newAudioClip(new java.io.File("./res/score.wav").toURL());
    			loseMusic = Applet.newAudioClip(new java.io.File("./res/loser.wav").toURL());
    			bgsound = Applet.newAudioClip(new java.io.File("./res/bgsound.wav").toURL());
    		} catch (MalformedURLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		}
    	bgsound.loop();
        currentDir = 3;
                
        lt.add(new Food(8, 12));
        food = new Food(12, 12); 
        
    }
    //生成食物，食物不与蛇重合
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
    	//食物
    	Image foodImg = new ImageIcon("./res/food"+ food.getNumber() +".png").getImage();
    	g.drawImage(foodImg, food.getX()*Confi.FoodWidth, food.getY()*Confi.FoodHeight, Confi.FoodWidth, Confi.FoodHeight, null);
    	//蛇头
    	if (lt.size()>0) {
        	g.drawImage(Confi.header, lt.get(0).getX()*Confi.FoodWidth, lt.get(0).getY()*Confi.FoodHeight, Confi.FoodWidth, Confi.FoodHeight, null);
		}
    	//蛇身
        for(int i = 1; i < lt.size(); i++) {
//        	int number = (int)Math.floor(Math.random()*6+1);
//        	Image body = new ImageIcon("./res/body"+ number +".gif").getImage();
        	g.drawImage(Confi.body, lt.get(i).getX()*Confi.FoodWidth, lt.get(i).getY()*Confi.FoodHeight, Confi.FoodWidth, Confi.FoodHeight, null);
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

	public int move() {
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
		}else {
			eatMusic.play();
		}
        
        if (Dead()) {
        	bgsound.stop();
        	loseMusic.play();
        	int i = JOptionPane.showConfirmDialog(null, "再玩一次？", "你挂了！", JOptionPane.YES_NO_OPTION);
        	if (i==1) {
				System.exit(0);
			}else {
				lt.clear();
				start();
				return i;
			}
		}
        return 2;
	}
	
	void changeDir(int dir) {currentDir = dir;}
}
