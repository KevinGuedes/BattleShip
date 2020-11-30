package entidades;

public class Contratorpedeiro extends Navio {

	public Contratorpedeiro() {
		super(new int[1][3]);
	}

	@Override
	public int getComprimentoNavio() {
		return this.Dimensao[0].length;
	}

}
