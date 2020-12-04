import entidades.Tabuleiro;
import entidades.Submarino;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import entidades.Navio;
import entidades.NavioTanque;
import entidades.PortaAviao;
import entidades.Ataque;
import entidades.Contratorpedeiro;
import entidades.Jogador;
import java.util.Random;

public class Principal {

	public static void main(String[] args) {
		
		Random random = new Random();
		int linhas = 10;
		int colunas = 10;
		
		//int w[][] = new int[2][3];
		//System.out.println(w[0].length);
		
		
		//Inânciação dos tabuleiros dos dois jogadores
		int[][] dimensao1 = new int[linhas][colunas];
		int[][] dimensao2 = new int[linhas][colunas];
		Tabuleiro tabuleiro1 = new Tabuleiro("Player 1", dimensao1);
		Tabuleiro tabuleiro2 = new Tabuleiro("Player 2", dimensao2);
		
		//Povoando ambos os tabuleiros
		ArrayList<Navio> navios = new ArrayList<Navio>();
		navios.add(new Submarino());
	    navios.add(new Submarino());
	    navios.add(new Submarino());
	    navios.add(new Submarino());
	    navios.add(new Contratorpedeiro());
	    navios.add(new Contratorpedeiro());
	    navios.add(new Contratorpedeiro());
	    navios.add(new NavioTanque());
	    navios.add(new NavioTanque());
	    navios.add(new PortaAviao());
	    
	    for(Navio navio : navios) {
			tabuleiro1.PosicionarNavio(navio);
			tabuleiro2.PosicionarNavio(navio);
		}
	    
	    System.out.println("Tabuleiros pré jogo");
	    tabuleiro1.Mostra();
	    tabuleiro2.Mostra();
	
	    //Pontuação
	    int pontosParaGanhar = 0;
	    for(Navio navio : navios) {
			pontosParaGanhar += navio.getComprimentoNavio();
		}
	    
	    //Inicialização do jogo
	    int rodadas = 0;
	    boolean fimDeJogo;
	    Jogador Player1 = new Jogador();
	    Jogador Player2 = new Jogador();

	    do {
	    	rodadas++;
	    	//Ataques Player 1
		    Ataque ataquePlayer1;
		    
		    do {
		    	ataquePlayer1 = new Ataque(random.nextInt(linhas), random.nextInt(colunas));
		    } while (!Player1.ValidarAtaque(ataquePlayer1));
		    
		    
		    Player1.AdicionarAtaqueTotal(ataquePlayer1);
		    
		    if(tabuleiro2.ProcessarAtaque(ataquePlayer1)) {
		    	System.out.println("Player 1: Ataque bem sucedido!");
		    	Player1.AdicionarPonto();
		    	Player1.AdicionarAtaqueBemSucedido(ataquePlayer1);
		    }
		    else {
		    	System.out.println("Player 1: Ataque mal sucedido, tente outra posição!");
		    	Player1.AdicionarAtaqueMalSucedido(ataquePlayer1);
		    }
		    
		    //Ataques Player 2
		    Ataque ataquePlayer2;
		    
		    do {
		    	ataquePlayer2 = new Ataque(random.nextInt(linhas), random.nextInt(colunas));
		    } while (!Player2.ValidarAtaque(ataquePlayer2));
		    
		    Player2.AdicionarAtaqueTotal(ataquePlayer2);
		    
		    if(tabuleiro1.ProcessarAtaque(ataquePlayer2)) {
		    	System.out.println("Player 2: Ataque bem sucedido!");
		    	Player2.AdicionarPonto();
		    	Player2.AdicionarAtaqueBemSucedido(ataquePlayer2);
		    }
		    else {
		    	System.out.println("Player 2: Ataque mal sucedido, tente outra posição!");
		    	Player2.AdicionarAtaqueMalSucedido(ataquePlayer2);
		    }
		    System.out.println(rodadas);
		    fimDeJogo = !(Player1.getPontos() == pontosParaGanhar || Player2.getPontos() == pontosParaGanhar);
		    

	    } while(fimDeJogo);
	    
	    
	    //Apresentação dos tabuleiros pós jogo
	    System.out.println("Tabuleiros pós jogo");
	    tabuleiro1.Mostra();
	    tabuleiro2.Mostra();
	    
	    //Quantidade de rodadas executadas
	    System.out.print("O jogo foi encerrado em " + rodadas + " rodadas. ");
	    
	    //Anúncio do vencedor ou do empate
	    String vencedor;
	    if(Player1.getPontos() > Player2.getPontos()) {
	    	System.out.println("Player 1 ganhou!");
	    	vencedor = "Player 1 - " + Player1.getAtaquesTotais().size() + " ataques realizados";
	    }
	    else if (Player1.getPontos() == Player2.getPontos()) {
	    	System.out.println("Empate");
	    	vencedor = "Empate - " + rodadas + " rodadas foram executadas";
	    }
	    else {
	    	System.out.println("Player 2 ganhou!");
	    	vencedor = "Player 2 - " + Player2.getAtaquesTotais().size() + " ataques realizados";
	    }
	    System.out.println("Ataques bem sucedidos: ");
	    System.out.println("- Player 1: " + Player1.getAtaquesBemSucedidos().size());
	    System.out.println("- Player 2: " + Player2.getAtaquesBemSucedidos().size());
	
		String nomeArquivoAtaquePlayer1 = "ataquesPlayer1.txt";
		String nomeArquivoAtaquePlayer2 = "ataquesPlayer2.txt";
		String nomeArquivoVencedor = "vencedor.txt";
		
		try {
			File arquivoPlayer1 = new File(nomeArquivoAtaquePlayer1);
			File arquivoPlayer2 = new File(nomeArquivoAtaquePlayer2);
			File arquivoVencedor = new File(nomeArquivoVencedor);
			
			if(arquivoPlayer1.createNewFile() && arquivoPlayer2.createNewFile() && arquivoVencedor.createNewFile()) {
				
				FileWriter fileWriter1 = new FileWriter(arquivoPlayer1);
				fileWriter1.write("Ataques Totais\n");
				for(Ataque ataque : Player1.getAtaquesTotais()) {
					fileWriter1.write(ataque.Mostra() + "\n");
				}
				
				fileWriter1.write("\n\n");
				fileWriter1.write("Ataques Bem Sucedidos\n");
				for(Ataque ataque : Player1.getAtaquesBemSucedidos()) {
					fileWriter1.write(ataque.Mostra() + "\n");
				}
				
				fileWriter1.write("\n\n");
				fileWriter1.write("Ataques Mal Sucedidos\n");
				for(Ataque ataque : Player1.getAtaquesMalSucedidos()) {
					fileWriter1.write(ataque.Mostra() + "\n");
				}
				
				fileWriter1.close();
				
				
				FileWriter fileWriter2 = new FileWriter(arquivoPlayer2);
				fileWriter2.write("Ataques Totais\n");
				for(Ataque ataque : Player2.getAtaquesTotais()) {
					fileWriter2.write(ataque.Mostra() + "\n");
				}
				
				fileWriter2.write("\n\n");
				fileWriter2.write("Ataques Bem Sucedidos\n");
				for(Ataque ataque : Player2.getAtaquesBemSucedidos()) {
					fileWriter2.write(ataque.Mostra() + "\n");
				}
				
				fileWriter2.write("\n\n");
				fileWriter2.write("Ataques Mal Sucedidos\n");
				for(Ataque ataque : Player2.getAtaquesMalSucedidos()) {
					fileWriter2.write(ataque.Mostra() + "\n");
				}
		
				fileWriter2.close();
				
				
				FileWriter fileWriterVencedor = new FileWriter(arquivoVencedor);
				fileWriterVencedor.write(vencedor);
				fileWriterVencedor.close();
				
				System.out.println("Arquivos criados.");
			}
			else {
				System.out.println("Algum dos arquivos já existe.");
			}
		} 
		catch (IOException e) {
			System.out.println("Um erro ocorreu.");
			System.out.println(e.getMessage());
		}
		
	}
}
