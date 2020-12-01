package entidades;
import java.util.ArrayList;

public class Jogador {

	int Pontos;
	ArrayList<Ataque> AtaquesTotais;
	ArrayList<Ataque> AtaquesBemSucedidos;
	ArrayList<Ataque> AtaquesMalSucedidos;
	
	public Jogador() {
		Pontos = 0;
		AtaquesTotais = new ArrayList<Ataque>();
		AtaquesBemSucedidos = new ArrayList<Ataque>();
		AtaquesMalSucedidos = new ArrayList<Ataque>();
	}

	
	public int getPontos() {
		return Pontos;
	}
	
	public void setPontos(int pontos) {
		Pontos = pontos;
	}
	
	public ArrayList<Ataque> getAtaquesTotais() {
		return AtaquesTotais;
	}
	
	public void setAtaquesTotais(ArrayList<Ataque> ataquesTotais) {
		AtaquesTotais = ataquesTotais;
	}

	public ArrayList<Ataque> getAtaquesBemSucedidos() {
		return AtaquesBemSucedidos;
	}

	public void setAtaquesBemSucedidos(ArrayList<Ataque> ataquesBemSucedidos) {
		AtaquesBemSucedidos = ataquesBemSucedidos;
	}

	public ArrayList<Ataque> getAtaquesMalSucedidos() {
		return AtaquesMalSucedidos;
	}

	public void setAtaquesMalSucedidos(ArrayList<Ataque> ataquesMalSucedidos) {
		AtaquesMalSucedidos = ataquesMalSucedidos;
	}

	public boolean ValidarAtaque(Ataque ataque) {
		if(AtaquesTotais.contains(ataque)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void AdicionarAtaqueTotal(Ataque ataque) {
		AtaquesTotais.add(ataque);
	}
	
	public void AdicionarAtaqueBemSucedido(Ataque ataque) {
		AtaquesBemSucedidos.add(ataque);
	}
	
	public void AdicionarAtaqueMalSucedido(Ataque ataque) {
		AtaquesMalSucedidos.add(ataque);
	}
	
	public void AdicionarPonto() {
		Pontos++;
	}
	
}
