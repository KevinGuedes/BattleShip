package entidades;

public class Ataque {
	private int Linha;
	private int Coluna;
	
	public int getLinha() {
		return Linha;
	}
	public void setLinha(int linha) {
		Linha = linha;
	}
	public int getColuna() {
		return Coluna;
	}
	public void setColuna(int coluna) {
		Coluna = coluna;
	}
	
	
	public Ataque(int linha, int coluna) {
		Linha = linha;
		Coluna = coluna;
	}
	
	public String Mostra() {
		return "Ataque: L" + Linha + "-C" + Coluna;
	}
	
	@Override
	public boolean equals(Object object)
	{
	    boolean isEqual = false;

	    if (object != null && object instanceof Ataque)
	    {
	        isEqual = (this.Linha == ((Ataque) object).getLinha()) && (this.Coluna == ((Ataque) object).getColuna());
	    }

	    return isEqual;
	}

	@Override
	public int hashCode() {
	    return this.Linha + this.Coluna;
	}
	
}
