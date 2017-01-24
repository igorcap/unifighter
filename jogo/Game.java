package jogo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;



public class Game extends JPanel implements ActionListener {

	private Image fundo;
	private Image lifebar;
	private Image liuimage;
	private Image jhonyimage;
	private Fighter fighter;
	private Timer timer;
	private int danorecebido;
	private boolean tomandodano = false;
	private boolean fireball = false;
	private boolean morreu = false;

	public Game(){
		this.addKeyListener(new TAdapter());
		this.setFocusable(true);
		this.setDoubleBuffered(true);

		ImageIcon ref = new ImageIcon("background/theamory.png");
		liuimage = new ImageIcon("resources/fighter_pic_1.png").getImage();
		jhonyimage = new ImageIcon("jhonykage/fighter_pic_1.png").getImage();
		fundo = ref.getImage();
		fighter = new Fighter();

		timer = new Timer(60, this);
		timer.start();
	}



	public void paint(Graphics g){
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, this);
		graficos.drawImage(jhonyimage,455,0,this);
		graficos.drawImage(liuimage,0,0,this);
		graficos.drawImage(new ImageIcon("background/lifebar.png").getImage(),42,27,this);
		checarDano();
		graficos.drawImage(lifebar,282,27,this);

		if(tomandodano && !fighter.isDefesa() && !morreu){
			graficos.drawImage(fighter.blood.cenas.get(fighter.blood.cena).getImage(), fighter.getX2(), fighter.getY2(),this);
			graficos.drawImage(fighter.jhonyapanhando.cenas.get(fighter.jhonyapanhando.cena).getImage(), fighter.getX2(), fighter.getY2(),this);
		} else if(!tomandodano && !fighter.isDefesa() && !morreu){
			graficos.drawImage(fighter.jhony.cenas.get(fighter.jhony.cena).getImage(), fighter.getX2(), fighter.getY2(),this);
		} else if(!morreu)
			graficos.drawImage(fighter.jhonydefendendo.cenas.get(fighter.jhonydefendendo.cena).getImage(), fighter.getX2(), fighter.getY2(),this);
		else{
			graficos.drawImage(fighter.jhonymorto.cenas.get(fighter.jhonymorto.cena).getImage(), fighter.getX2(), fighter.getY2(),this);
			graficos.drawImage(fighter.dead.cenas.get(fighter.dead.cena).getImage(), fighter.getX2(), fighter.getY2()+130,this);
			graficos.drawImage(fighter.dead2.cenas.get(fighter.dead2.cena).getImage(), fighter.getX2()+20, fighter.getY2()+77,this);
		}
		if(fighter.p.checarHurricaneKick()){
			graficos.drawImage(fighter.liuhurricane.cenas.get(fighter.liuhurricane.cena).getImage(),fighter.getX(),fighter.getY(),this);
		}else if(fighter.isVai()){
			graficos.drawImage(fighter.liuandando.cenas.get(fighter.liuandando.cena).getImage(),fighter.getX(),fighter.getY(),this);
		} else if(fighter.isVolta()){
			graficos.drawImage(fighter.liuvoltando.cenas.get(fighter.liuvoltando.cena).getImage(),fighter.getX(),fighter.getY(),this);
		} else if(fighter.isPulando()){
			graficos.drawImage(fighter.liupulando.cenas.get(fighter.liupulando.cena).getImage(),fighter.getX(),fighter.getY(),this);
		} else if(fighter.isSoco()){
			graficos.drawImage(fighter.liusocando.cenas.get(fighter.liusocando.cena).getImage(),fighter.getX(),fighter.getY(),this);
		} else if(fighter.isChute()){
			graficos.drawImage(fighter.liuchutando.cenas.get(fighter.liuchutando.cena).getImage(),fighter.getX(),fighter.getY(),this);
		} else if(morreu){
			graficos.drawImage(fighter.liuvencedor.cenas.get(fighter.liuvencedor.cena).getImage(),fighter.getX(),fighter.getY(),this);
			graficos.drawImage(new ImageIcon("background/liukangwins.gif").getImage(),fighter.getX3(),fighter.getY3(),this);
			
		}else
			graficos.drawImage(fighter.liu.cenas.get(fighter.liu.cena).getImage(),fighter.getX(),fighter.getY(),this);

		if(fireball)
			graficos.drawImage(fighter.fireball.cenas.get(fighter.fireball.cena).getImage(),fighter.getX()+43,fighter.getY()+10,this);

		graficos.drawImage(fighter.fight.cenas.get(fighter.fight.cena).getImage(), fighter.getX3(), fighter.getY3(),this);
		fighter.jhony.animar();
		fighter.liu.animar();
		fighter.liuandando.animar();
		fighter.liuvoltando.animar();
		fighter.liuchutando.animar();
		fighter.liusocando.animar();
		fighter.fireball.animar();
		if(tomandodano)
			fighter.jhonydefendendo.animar();
		fighter.liupulando.animar();
		fighter.jhonyapanhando.animar();
		fighter.liuvencedor.animarSomenteUmaVez();
		fighter.fight.animarSomenteUmaVez();
		fighter.jhonymorto.animarSomenteUmaVez();
		fighter.blood.animar();
		fighter.dead.animarMaisLento();
		fighter.dead2.animarMaisLento();

	}

	public void actionPerformed(ActionEvent e){

		if(!fighter.isParado() && fighter.isVai()){
			tomandodano = false;
			fireball = false;

		}
		if(!fighter.isParado() && fighter.isVolta()){
			tomandodano = false;
			fireball = false;

		}
		if(!fighter.isParado() && fighter.isSoco()){			
			fireball = false;
			if(fighter.getX() == 340){
				tomandodano = true;
			}else
				tomandodano = false;
		}


		if(!fighter.isParado() && fighter.isChute()){
			fireball = false;
			if(fighter.getX() > 300){
				tomandodano = true;
			} else
				tomandodano = false;
		}

		if(fighter.isPulando()){
			tomandodano = false;

		}
		if(fighter.p.checarHurricaneKick()){
			fireball = false;
			fighter.liu.cenas.clear();
			for(int i = 1; i<7; i++){
				fighter.liu.cenas.add(new ImageIcon("resources/second_special_attack_"+i+".png"));
				fighter.setDx(12);

			}
			tomandodano = true;	
			if(fighter.isDefesa())
				danorecebido += 25;
			else
				danorecebido += 15;
		}

		if(fighter.p.checarFireball()){
			fireball = true;
			fighter.liu.cenas.clear();
			for(int i = 1; i<4 ; i++){
				fighter.liu.cenas.add(new ImageIcon("resources/fireBall_"+i+".png"));
			}
			
			if(fighter.getX()+170 >= 340)
			tomandodano = true;
			if(fighter.isDefesa())
				danorecebido += 20;
			else
				danorecebido += 10;
		}

		fighter.move();
		repaint();
	}

	public void checarDano(){

		if(danorecebido + fighter.getDanorecebido()==10){
			lifebar = new ImageIcon("background/lifebar10.jpg").getImage();
		} else if(danorecebido + fighter.getDanorecebido() == 15){
			lifebar =new ImageIcon("background/lifebar15.jpg").getImage();
		} else if(danorecebido + fighter.getDanorecebido() == 20){
			lifebar =new ImageIcon("background/lifebar20.jpg").getImage();
		} else if(danorecebido + fighter.getDanorecebido() == 25){
			lifebar =new ImageIcon("background/lifebar25.jpg").getImage();
		} else if(danorecebido + fighter.getDanorecebido() == 30){
			lifebar =new ImageIcon("background/lifebar30.jpg").getImage();
		} else if(danorecebido + fighter.getDanorecebido() == 35){
			lifebar =new ImageIcon("background/lifebar35.jpg").getImage();
		} else if(danorecebido + fighter.getDanorecebido() == 40){
			lifebar =new ImageIcon("background/lifebar40.jpg").getImage();
		} else if(danorecebido + fighter.getDanorecebido() == 45){
			lifebar =new ImageIcon("background/lifebar45.jpg").getImage();
		} else if(danorecebido + fighter.getDanorecebido() == 50){
			lifebar =new ImageIcon("background/lifebar50.jpg").getImage();
		} else if(danorecebido + fighter.getDanorecebido() == 55){
			lifebar =new ImageIcon("background/lifebar55.jpg").getImage();
		} else if(danorecebido + fighter.getDanorecebido() == 60){
			lifebar =new ImageIcon("background/lifebar60.jpg").getImage();
		} else if(danorecebido + fighter.getDanorecebido() == 65){
			lifebar =new ImageIcon("background/lifebar65.jpg").getImage();
		} else if(danorecebido + fighter.getDanorecebido() == 70){
			lifebar =new ImageIcon("background/lifebar70.jpg").getImage();
		} else if(danorecebido + fighter.getDanorecebido() == 75){
			lifebar =new ImageIcon("background/lifebar75.jpg").getImage();
		} else if(danorecebido + fighter.getDanorecebido() == 80){
			lifebar =new ImageIcon("background/lifebar80.jpg").getImage();
		} else if(danorecebido + fighter.getDanorecebido() == 85){
			lifebar =new ImageIcon("background/lifebar85.jpg").getImage();
		} else if(danorecebido + fighter.getDanorecebido() == 90){
			lifebar =new ImageIcon("background/lifebar90.jpg").getImage();
		} else if(danorecebido + fighter.getDanorecebido() == 95){
			lifebar =new ImageIcon("background/lifebar95.jpg").getImage();
		} else if(danorecebido + fighter.getDanorecebido() >= 100){
			lifebar =new ImageIcon("background/lifebar100.jpg").getImage();
			morreu = true;
		} else{
			lifebar =new ImageIcon("background/lifebar.png").getImage();
		}
	}

	private class TAdapter extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			fighter.keyPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			fighter.keyReleased(e);
		}

	}
}
