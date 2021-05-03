package megasena.controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JogoController {
	private String arquivo;
	
	public JogoController() {
	}
	
	
	
	public String getArquivo() {
		return this.arquivo;
	}



	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}



	public void lerArquivo() throws IOException {
		BufferedReader leitor = new BufferedReader(new FileReader(this.arquivo));
		String linha = leitor.readLine();
		while(linha != null) {
			System.out.println(linha);
		}
		leitor.close();
	}
}
