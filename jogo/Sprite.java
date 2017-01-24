package jogo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Sprite {

	List<ImageIcon>cenas; 
	private int x;     
 	private int y;     
	int cena = 0;   
	private int controlaVelocidade = 0;
	private int velocidade = 5;

	public Sprite(int x, int y){
		cenas = new ArrayList<>();
		this.x = x;
		this.y = y;
	}

	public void animar(){
		cena += 1;
		if(cena >= cenas.size()){ cena = 0; }
	}

	public void animarSomenteUmaVez(){
		if(cena < cenas.size()-1){
			cena += 1;	
		}else{
			cena = cenas.size()-1;
		}
	}

	public void animarMaisLento(){
		controlaVelocidade+=1;
		if(controlaVelocidade>velocidade){
			cena += 1;
			controlaVelocidade = 0;
			if(cena == cenas.size()){ cena = 0; }
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}