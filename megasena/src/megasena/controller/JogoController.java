package megasena.controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import megasena.model.Jogo;

public class JogoController {
	private String arquivo;
	private ArrayList<Jogo> lista = new ArrayList<Jogo>();
	
	public JogoController() {
	}
		
	public String getArquivo() {
		return this.arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public void lerArquivo() throws IOException, ParseException {
		BufferedReader leitor = new BufferedReader(new FileReader(this.arquivo));
		String linha = leitor.readLine();
		String[] matriz;
		SimpleDateFormat conversor= new SimpleDateFormat("dd/MM/yyyy");
		
		while(linha != null) {
			matriz = linha.split(",");
			Jogo jogo = new Jogo();
			int[] numeros = new int[6];
			
			for(int i=0; i<numeros.length; i++) {
				numeros[i] = Integer.parseInt(matriz[i+2]);
			}
			
			jogo.setSorteio(Integer.parseInt(matriz[0]));
			jogo.setData(conversor.parse(matriz[1]));
			jogo.setNumeros(numeros);
			
			this.lista.add(jogo);
			linha = leitor.readLine();
		}
		leitor.close();
	}
	
	public void escreverArquivo() {
		for(int i=0; i<this.lista.size(); i++) {
			System.out.println(this.lista.get(i));
		}
	}
}
