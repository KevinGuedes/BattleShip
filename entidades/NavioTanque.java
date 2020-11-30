package entidades;

public class NavioTanque extends Navio {

	public NavioTanque() {
		super(new int[1][4]);
	}

	@Override
	public int getComprimentoNavio() {
		return this.Dimensao[0].length;
	}

}
