package entidades;

public class Submarino extends Navio {
	
	public Submarino() {
		super(new int[1][2]);
	}

	@Override
	public int getComprimentoNavio() {
		return this.Dimensao[0].length;
	}

}
