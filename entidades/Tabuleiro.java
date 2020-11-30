package entidades;
import java.util.Random;

public class Tabuleiro {
	
	private int[][] Dimensao;
	private String Nome;
	
	
	public int[][] getDimensao() {
		return Dimensao;
	}
	
	public void setDimensao(int[][] dimensao) {
		Dimensao = dimensao;
	}
	
	
	public Tabuleiro(String nome, int[][] dimensao) {
		Nome = nome;
		Dimensao = dimensao;
		for(int i = 0; i < Dimensao.length; i++){
            for(int j = 0; j < Dimensao[0].length; j++){
           	 Dimensao[i][j] = 0;
            }
		}
	}
	
	
	
	public int getLinhas() {
		return Dimensao.length;
	}
	
	public int getColunas() {
		return Dimensao[0].length;
	}
	
	public int getStatus(int linha, int coluna) {
		return Dimensao[linha][coluna];
	}
	
	public void UpdateStatus(int linha, int coluna, int value) {
		Dimensao[linha][coluna] = value;
	}
	
	public void posicionarNavio(Navio navio) {
		Random random = new Random();
		int linha;
		int coluna;
		int orientacao;
		boolean verificador;
		//0 = horizontal e 1 = vertical
	
		do {
			verificador = false;
			linha = random.nextInt(getLinhas());
			coluna = random.nextInt(getColunas());
			orientacao = random.nextInt(2);
			
			
			if(orientacao == 0) {
				
				int colunaFinal = coluna + navio.getComprimentoNavio();
				if(colunaFinal >= getColunas()) {
					verificador = true;
				}
				else {
					for(int j = coluna; j < colunaFinal; j++) {
						if(getStatus(linha, j) == 1) {
							verificador = true;
						}
					}
				}
				
			}
			
			else {
				
				int linhaFinal = linha + navio.getComprimentoNavio();
				if(linhaFinal >= getLinhas()) {
					verificador = true;
				}
				else {
					for(int i = linha; i < linhaFinal; i++) {
						if(getStatus(i, coluna) == 1) {
							verificador = true;
						}
					}
				}
				
			}
			
		} while(verificador);
		
		
		if(orientacao == 0) {
			for(int j = coluna; j < coluna + navio.getComprimentoNavio(); j ++) {
				Dimensao[linha][j] = 1;
			};
		}
		else {
			for(int i = linha; i < linha + navio.getComprimentoNavio(); i ++) {
				Dimensao[i][coluna] = 1;
			};
		}
		
	}
	
	public boolean ProcessarAtaque(Ataque ataque) {
		if(getStatus(ataque.getLinha(), ataque.getColuna()) == 1) {
			UpdateStatus(ataque.getLinha(), ataque.getColuna(), 2);
			return true;
		}
		else {
			UpdateStatus(ataque.getLinha(), ataque.getColuna(), 3);
			return false;
		}
	}
	
	public void Mostra() {
		String nomeColuna = " ";
		String separador = "  _";
		
		for(int j = 0; j < Dimensao[0].length; j++){
			nomeColuna += "   " + j;
			separador += "____";
		}
	
		System.out.println();
		System.out.println(Nome);
		System.out.println(nomeColuna);
		
		for(int i = 0; i < Dimensao.length; i++){
            System.out.print(i + " ");   
            for(int j = 0; j < Dimensao[0].length; j++){
            	System.out.print("| "+ Dimensao[i][j]+" ");
        	}
        	System.out.println("|");
            System.out.println(separador);
         }
		System.out.println();
		System.out.println();
	}
	
}
