package jogo;


import javax.swing.JFrame;

public class Start extends JFrame {

	public Start(){
		
		add(new Game());
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setTitle("UniFighter - Alfa");
        setResizable(false);
        setVisible(true);
	}
	
	public static void main(String[] args) {
		new Start();
	}
	
}
