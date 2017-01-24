package jogo;


public class Pilha {

	private String[] pilha;
	private int posicao;

	public Pilha(){
		this.posicao = 0;
		pilha = new String[3];
		pilha[0] = "a";
		pilha[1] = "b";
		pilha[2] = "c";

	}

	public void add(String key){
		if(posicao<2){
			pilha[posicao] = key;
			posicao++;
		} else{
			String aux = pilha[1];
			pilha[1] = pilha[2];
			pilha[0] = aux;
			pilha[2] = key;
		}
	}

	public void retira(){
		pilha[0] = "a";
		pilha[1] = "b";
		pilha[2] = "c";
	}

	public boolean checarHurricaneKick(){
		if(pilha[0].equals("x")){//KeyEvent.VK_X
			if(pilha[1].equals("left") ){//KeyEvent.VK_LEFT
				if(pilha[2].equals("down")){//KeyEvent.VK_DOWN
					retira();
					retira();
					retira();
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}

	public boolean checarFireball(){
		if(pilha[0].equals("z")){
			if(pilha[1].equals("right")){
				if(pilha[2].equals("down")){
					retira();
					retira();
					retira();
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}





}
