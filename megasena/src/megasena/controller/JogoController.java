package megasena.controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import megasena.model.Jogo;

public class JogoController {
	private String arquivo;
	private ArrayList<Jogo> lista = new ArrayList<Jogo>();
	private ArrayList<Jogo> jogos = new ArrayList<Jogo>();
	private ArrayList<Integer> numeros = new ArrayList<Integer>();
	private ArrayList<Integer> resto = new ArrayList<Integer>();
	private static final int[] QUADA = {1,2,3,4,5,11,12,13,14,15,21,22,23,24,25};
	private static final int[] QUADB = {6,7,8,9,10,16,17,18,19,20,26,27,28,29,30};
	private static final int[] QUADC = {31,32,33,34,35,41,42,43,44,45,51,52,53,54,55};
	private static final int[] QUADD = {36,37,38,39,40,46,47,48,49,50,56,57,58,59,60};
	
	
	public JogoController() {
		for(int i = 1; i <= 60; i++) {
			this.resto.add(i);
		}
	}
	
	public void rodaJogos() throws IOException, ParseException {
		this.lerArquivo();
		this.processaLista();
		this.selecionaJogo();
		this.removeResto();
		this.removeRepetido();
		this.imprimeJogos(this.escolheNumeros(JogoController.QUADA,
				JogoController.QUADB, JogoController.QUADC));
		this.imprimeJogos(this.escolheNumeros(JogoController.QUADA,
				JogoController.QUADB, JogoController.QUADD));
		this.imprimeJogos(this.escolheNumeros(JogoController.QUADA,
				JogoController.QUADC, JogoController.QUADD));
		this.imprimeJogos(this.escolheNumeros(JogoController.QUADB,
				JogoController.QUADC, JogoController.QUADD));
	}
		
	public String getArquivo() {
		return this.arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	private void lerArquivo() throws IOException, ParseException {
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
	
	private void processaLista() {
		Jogo ultimo = this.lista.get(0);
		int[] numeros = ultimo.getNumeros();
		
		for(int i = 0; i < numeros.length; i++) {
			for(int j = 1; j < this.lista.size(); j++) {
				if(this.montaJogo(j, numeros[i])) {
					break;
				}
			}
		}
	}
	
	private void imprimeJogos(int[] numeros) {
		for(int i = 0; i < numeros.length; i++) {
			System.out.printf("%d ",numeros[i]);
		}
		System.out.println();
	}
	
	private boolean montaJogo(int id, int numero) {
		Jogo jogo = this.lista.get(id);
		if(jogo.hasNumero(numero)) {
			if(jogo.isLast()) {
				jogo = this.lista.get(id+1);
			}
			this.jogos.add(jogo);
			return true;
		}
		return false;
	}
	
	private void selecionaJogo() {
		Jogo ultimo = this.lista.get(0);
		int[] arrayNumeros = ultimo.getNumeros();
		
		for(int i = 0; i < arrayNumeros.length; i++) {
			Jogo jogo = this.jogos.get(i);
			this.lancaNumero(jogo.getNumeros(), arrayNumeros[i]);
		}
	}
	
	private void lancaNumero(int[] numeros, int numero) {
		boolean verifica = false;
		
		for(int i = 0; i < numeros.length; i++) {
			if(verifica == true) {
				this.numeros.add(numeros[i]);
			}
			if(numeros[i] == numero) {
				verifica = true;
			}
		}
		if(verifica == false) {
			for(int j = 0; j < numeros.length; j++) {
				this.numeros.add(numeros[j]);
			}
		}
	}
	
	private void removeRepetido() {
		this.numeros.sort(null);
		for(int i = 0; i < this.numeros.size(); i++) {
			for(int j = 1; j < this.numeros.size(); j++) {
				if(this.numeros.get(i) == this.numeros.get(j)) {
					this.numeros.remove(j);
				}
			}
		}
	}
	
	private int[] escolheNumeros(int[] m1, int[]m2, int[]m3) {
		int n1 = 0, n2 = 0, n3 = 0, n4 =  0, n5 = 0, n6 = 0;
		
		while(n1 == n2 || n3 == n4 || n5 == n6) {
			n1 = this.previsaoJogos(m1);
			n2 = this.previsaoJogos(m1);
			n3 = this.previsaoJogos(m2);
			n4 = this.previsaoJogos(m2);
			n5 = this.previsaoJogos(m3);
			n6 = this.previsaoJogos(m3);
		}
		int [] numeros = {n1,n2,n3,n4,n5,n6};
		
		return numeros;
	}
	
	
	private void removeResto() {
		for(int i = 0; i < this.numeros.size(); i++) {
			for(int j = 0; j < this.resto.size(); j++) {
				if(this.numeros.get(i) == this.resto.get(j)) {
					this.resto.remove(j);
				}
			}
		}
	}
	
	private int previsaoJogos(int[] quadrante) {
		Random rand = new Random();
		int id = rand.nextInt(this.resto.size());
		int num = this.resto.get(id);
		boolean exist = false;
		
		for(int i = 0; i < quadrante.length; i++) {
			if(num == quadrante[i]) {
				exist = true;
			}
		}
		if(!exist) {
			num = this.previsaoJogos(quadrante);
		}
		return num;
	}
}
