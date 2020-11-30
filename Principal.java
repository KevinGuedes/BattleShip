import entidades.Tabuleiro;
import entidades.Submarino;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import entidades.Navio;
import entidades.NavioTanque;
import entidades.PortaAviao;
import entidades.Ataque;
import entidades.Contratorpedeiro;
import java.util.Random;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
		Random random = new Random();
		int linhas = 10;
		int colunas = 10;
		
		//int w[][] = new int[2][3];
		//System.out.println(w[0].length);
		
		
		//Inânciação dos tabuleiros dos dois jogadores
		
		Tabuleiro tabuleiro1 = new Tabuleiro("Player 1", new int[linhas][colunas]);
		Tabuleiro tabuleiro2 = new Tabuleiro("Player 2", new int[linhas][colunas]);
		
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
			tabuleiro1.posicionarNavio(navio);
			tabuleiro2.posicionarNavio(navio);
		}
	    
	    System.out.println("Tabuleiros pré jogo");
	    tabuleiro1.Mostra();
	    tabuleiro2.Mostra();
	
	
	    //Pontuação
	    int pontosPlayer1 = 0;
	    int pontosParaGanhar = 0;
	    int pontosPlayer2 = 0;
	    for(Navio navio : navios) {
			pontosParaGanhar += navio.getComprimentoNavio();
		}
	    
	    //Ataques
	    ArrayList<Ataque> AtaquesPlayer1 = new ArrayList<Ataque>();
	    ArrayList<Ataque> AtaquesPlayer2 = new ArrayList<Ataque>();
	    
	    int rodadas = 0;
	    
	    boolean fimDeJogo;
	    do {
	    	//Ataques Player 1
		    boolean verificadorPlayer1;
		    Ataque ataquePlayer1;
		    
		    do {
		    	verificadorPlayer1 = false;
		    	ataquePlayer1 = new Ataque(random.nextInt(linhas), random.nextInt(colunas));
			    if(!AtaquesPlayer1.contains(ataquePlayer1)) {
			    	AtaquesPlayer1.add(ataquePlayer1);
			    }
			    else {
			    	verificadorPlayer1 = true;
			    }
		    } while (verificadorPlayer1);
		    if(tabuleiro2.ProcessarAtaque(ataquePlayer1)) {
		    	System.out.println("Player 1: Ataque bem sucedido!");
		    	pontosPlayer1++;
		    }
		    else {
		    	System.out.println("Player 1: Ataque mal sucedido, tente outra posição!");
		    }
		    
		    
		    
		    //Ataques Player 2
		    boolean verificadorPlayer2;
		    Ataque ataquePlayer2;
		    
		    do {
		    	verificadorPlayer2 = false;
		    	ataquePlayer2 = new Ataque(random.nextInt(linhas), random.nextInt(colunas));
			    if(!AtaquesPlayer2.contains(ataquePlayer2)) {
			    	AtaquesPlayer2.add(ataquePlayer2);
			    }
			    else {
			    	verificadorPlayer2 = true;
			    }
		    } while (verificadorPlayer2);
		    if(tabuleiro1.ProcessarAtaque(ataquePlayer2)) {
		    	System.out.println("Player 2: Ataque bem sucedido!");
		    	pontosPlayer2++;
		    }
		    else {
		    	System.out.println("Player 2: Ataque mal sucedido, tente outra posição!");
		    }
		    
		    fimDeJogo = !(pontosPlayer1 == pontosParaGanhar || pontosPlayer2 == pontosParaGanhar);
		    rodadas++;
	    } while(fimDeJogo);
	    
	    
	    //Apresentação dos tabuleiros pós jogo
	    System.out.println("Tabuleiros pós jogo");
	    tabuleiro1.Mostra();
	    tabuleiro2.Mostra();
	    
	    //Quantidade de rodadas executadas
	    System.out.print("O jogo foi encerrado em " + rodadas + " rodadas. ");
	    
	    //Anúncio do vencedor ou do empate
	    String vencedor;
	    if(pontosPlayer1 > pontosPlayer2) {
	    	System.out.println("Player 1 ganhou!");
	    	vencedor = "Player 1 - " + AtaquesPlayer1.size() + " ataques realizados";
	    }
	    else if (pontosPlayer1 == pontosPlayer2) {
	    	System.out.println("Empate");
	    	vencedor = "Empate - " + rodadas + " rodadas foram executadas";
	    }
	    else {
	    	System.out.println("Player 2 ganhou!");
	    	vencedor = "Player 2 - " + AtaquesPlayer2.size() + " ataques realizados";
	    }
	    
	    System.out.println("Ataques realizados pelo Player 1: " + AtaquesPlayer2.size());
	    System.out.println("Ataques realizados pelo Player 2: " + AtaquesPlayer2.size());
	    //Dúvidas / Faltando
	    // Arquivos
	    // Contagem de ataques bem sucedidos ou ataques mesmo?

	    
		
		
		/*
		//Leitura de arquivo
		String fileName = "teste.txt";//ou endereço completo
		File file = new File(fileName);
		//file.
		//file.getAbsolutePath()
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNext()) {
				int a = scanner.nextInt();
				int b = scanner.nextInt();
				String c = scanner.next();
				System.out.println(a + " " + b + " " + " " + c);	
			}
		}
		catch (FileNotFoundException e){
			System.out.println("Arquivo não existe");
			System.exit(1); //break de interromper o sistema todo, quando não sentido em continaur sem o arquivo
		}
		*/
		
		String arquivoAtaquePlayer1 = "ataquesPlayer1.txt";
		String arquivoAtaquePlayer2 = "ataquesPlayer2.txt";
		String arquivoVencedor = "vencedor.txt";
		
		try {
			File filePlayer1 = new File(arquivoAtaquePlayer1);
			File filePlayer2 = new File(arquivoAtaquePlayer2);
			File fileVencedor = new File(arquivoVencedor);
			
			if(filePlayer1.createNewFile() && filePlayer2.createNewFile() && fileVencedor.createNewFile()) {
				
				FileWriter fileWriter1 = new FileWriter(filePlayer1);
				for(Ataque ataque : AtaquesPlayer1) {
					fileWriter1.write(ataque.Mostra() + "\n");
				}
				fileWriter1.close();
				
				FileWriter fileWriter2 = new FileWriter(filePlayer2);
				for(Ataque ataque : AtaquesPlayer2) {
					fileWriter2.write(ataque.Mostra() + "\n");
				}
				fileWriter2.close();
				
				FileWriter fileWriterVencedor = new FileWriter(fileVencedor);
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
		}	
	}
}
