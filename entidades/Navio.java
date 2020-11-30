package entidades;

public abstract class Navio {
	
	protected int[][] Dimensao;
	
	public int[][] getDimensao() {
		return Dimensao;
	}
	public void setDimensao(int[][] dimensao) {
		Dimensao = dimensao;
	}
	
	public Navio(int[][] dimensao) {
		Dimensao = dimensao;
	}
	
	public abstract int getComprimentoNavio();
}
