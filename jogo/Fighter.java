package jogo;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;


public class Fighter {

	private int dx;
	private int dy;
	private int x = 20;
	private int y = 115;
	private int x2 = 400;
	private int y2 = 115;
	private int x3 = 160;
	private int y3 = 130;
	private int danorecebido = 0;
	private boolean vai = false;
	private boolean volta = false;
	private boolean parado = true;
	private boolean soco = false;
	private boolean chute = false;
	private boolean pulando = false;
	private boolean hurricanekick = false;
	private boolean defesa = false;
	
	Pilha p;
	Sprite liu = new Sprite(x, y);
	Sprite jhony = new Sprite(x2,y2);
	Sprite fight = new Sprite(x3,y3);
	Sprite jhonyapanhando = new Sprite(x2,y2);
	Sprite jhonydefendendo = new Sprite(x2,y2);
	Sprite jhonymorto = new Sprite(x2,y2);	
	Sprite liuandando = new Sprite(x, y);
	Sprite liuvoltando = new Sprite(x, y);
	Sprite liusocando = new Sprite(x, y);
	Sprite liuchutando = new Sprite(x, y);
	Sprite liuhurricane = new Sprite(x, y);
	Sprite liupulando = new Sprite(x, y);
	Sprite liuvencedor = new Sprite(x,y);
	Sprite fireball = new Sprite(x,y);
	Sprite blood = new Sprite(x2,y2);
	Sprite dead = new Sprite(x2,y2);
	Sprite dead2 = new Sprite(x2,y2);

	public Fighter(){
		posicaoInicial();
		posicaoJhonyInicial();
		p = new Pilha();

		for(int i=1; i<30; i++){
			fight.cenas.add(new ImageIcon("background/fight_"+i+".png"));

		}
		for(int i=1; i<4; i++){
			jhonyapanhando.cenas.add(new ImageIcon("jhonykage/high_hit_"+i+".png"));
		}
		for(int i = 1;i<9;i++){
			liuandando.cenas.add(new ImageIcon("resources/walk_"+i+".png"));
		}
		for(int i = 1;i<9;i++){
			liuvoltando.cenas.add(new ImageIcon("resources/walkBackward_"+i+".png"));
		}
		for(int i=1;i<4;i++){
			liusocando.cenas.add(new ImageIcon("resources/highPunch_"+i+".png"));
		}
		for(int i=1;i<7;i++){
			liuchutando.cenas.add(new ImageIcon("resources/lowKick_"+i+".png"));
		}
		for(int i=1; i < 9 ; i++){
			liupulando.cenas.add(new ImageIcon("resources/direction_jump_"+i+".png"));
		}
		for(int i = 1; i<7; i++){
			liuhurricane.cenas.add(new ImageIcon("resources/second_special_attack_"+i+".png"));
		}
		for(int i = 1; i<4 ; i++){
			jhonydefendendo.cenas.add(new ImageIcon("jhonykage/block_"+i+".png"));
		}
		for(int i = 1; i<4 ; i++){
			fireball.cenas.add(new ImageIcon("fireball/create_"+i+".png"));
		}
		
		for(int i = 4; i<6; i++){
			liuvencedor.cenas.add(new ImageIcon("resources/victory_"+i+".png"));
		}
		for(int i = 1; i<8 ; i++){
			jhonymorto.cenas.add(new ImageIcon("jhonykage/fall_down_"+i+".png"));
		}
		jhonymorto.cenas.add(new ImageIcon("jhonykage/lay_down_1.png"));
		
		for(int i = 1; i<8 ; i++){
			blood.cenas.add(new ImageIcon("effect/high_hit_blood_"+i+".gif"));
		}
		
		for(int i = 19; i<23 ; i++ ){
			dead.cenas.add(new ImageIcon("effect/fatality_blood_"+i+".gif"));
		}
		for(int i = 1; i<10 ; i++){
			dead2.cenas.add(new ImageIcon("effect/fatality_blood_"+i+".gif"));
		}
		
	}

	public void posicaoInicial(){
		liu.cenas.clear();
		for(int i=1; i<8; i++){
			liu.cenas.add(new ImageIcon("resources/stand_"+i+".png"));
		}
		parado = true;
		vai = false;
		volta = false;
		soco = false;
		chute = false;
	}

	public void posicaoJhonyInicial(){
		jhony.cenas.clear();
		for(int i=1; i<8; i++){
			jhony.cenas.add(new ImageIcon("jhonykage/stand_"+i+".png"));
		}
	}

	public void move() {
		x += dx;
		//y += dy;
		liu.setX(x);

		if(x > 340){
			x = 340;
		}
		if(x < 10){
			x = 10;
		}
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			parado = false;
			dx = -3;
			volta = true;
			vai = false;
			chute = false;
			soco = false;

		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 3;
			vai = true;
			parado = false;
			
		}

		if (key == KeyEvent.VK_UP) {
			dy = -1;
			pulando = true;
			parado = false;

		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 1;

		}

		if(key == KeyEvent.VK_Z){
			dx = 0;
			soco = true;
			parado = false;

		}

		if(key == KeyEvent.VK_X){
			dx = 0;
			parado = false;
			chute = true;
		}
		
		if(key == KeyEvent.VK_D){
			defesa = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			p.add("left");
			
			dx = 0;
			parado = true;
			volta = false;
			posicaoInicial();

		}

		if (key == KeyEvent.VK_RIGHT) {
			p.add("right");
			dx = 0;
			parado = true;
			vai = false;
			posicaoInicial();
		}

		if (key == KeyEvent.VK_UP) {
			p.add("up");
			dy = 0;
			parado = true;
			pulando = false;
			posicaoInicial();
		}

		if (key == KeyEvent.VK_DOWN) {
			p.add("down");

			dy = 0;
			parado = true;
			volta = false;
			vai = false;
			chute = false;
			soco = false;
			posicaoInicial();
		}

		
		if(key == KeyEvent.VK_Z){
			p.add("z");
			dx = 0;
			parado = true;
			soco = false;
			if( x > 330 && !defesa)
				danorecebido += 10;
			else if(defesa && x>330)
				danorecebido += 5;
			posicaoInicial();
			posicaoJhonyInicial();

		}
		if(key == KeyEvent.VK_X){
			p.add("x");
			if( x > 310)
				danorecebido += 15;
			else if(defesa && x>310)
				danorecebido += 10;
			dx = 0;
			parado = true;
			chute = false;

			posicaoInicial();
			posicaoJhonyInicial();
		}
		
		if(key == KeyEvent.VK_D){
			defesa = false;
		}

		
	}

	
	///////////GETS E SETS/////////////////	
	
	public boolean isPulando() {
		return pulando;
	}

	public void setPulando(boolean pulando) {
		this.pulando = pulando;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isVai() {
		return vai;
	}

	public void setVai(boolean vai) {
		this.vai = vai;
	}

	public boolean isVolta() {
		return volta;
	}

	public void setVolta(boolean volta) {
		this.volta = volta;
	}

	public boolean isParado() {
		return parado;
	}

	public void setParado(boolean parado) {
		this.parado = parado;
	}

	public boolean isSoco() {
		return soco;
	}

	public void setSoco(boolean soco) {
		this.soco = soco;
	}

	public boolean isChute() {
		return chute;
	}

	public void setChute(boolean chute) {
		this.chute = chute;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public int getX3() {
		return x3;
	}

	public void setX3(int x3) {
		this.x3 = x3;
	}

	public int getY3() {
		return y3;
	}

	public void setY3(int y3) {
		this.y3 = y3;
	}

	public boolean isHurricanekick() {
		return hurricanekick;
	}

	public void setHurricanekick(boolean hurricanekick) {
		this.hurricanekick = hurricanekick;
	}

	public int getDanorecebido() {
		return danorecebido;
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isDefesa() {
		return defesa;
	}

	public void setDefesa(boolean defesa) {
		this.defesa = defesa;
	}
	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

}
