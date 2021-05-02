package megasena.model;

import java.util.Date;

public class Jogo {
	private int[] numeros;
	private int sorteio;
	private Date data;
	
	public Jogo() {
		this.numeros = new int[6];
	}

	public int[] getNumeros() {
		return numeros;
	}

	public void setNumeros(int[] numeros) {
		this.numeros = numeros;
	}

	public int getSorteio() {
		return sorteio;
	}

	public void setSorteio(int sorteio) {
		this.sorteio = sorteio;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
