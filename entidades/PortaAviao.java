package entidades;

public class PortaAviao extends Navio {

	public PortaAviao() {
		super(new int[1][5]);
	}

	@Override
	public int getComprimentoNavio() {
		return this.Dimensao[0].length;
	}
}
