package megasena.model;

import java.util.Date;

public class Jogo {
	private int[] numeros;
	private int sorteio;
	private Date data;
	private boolean isLast;
	
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
	
	
	
	public boolean isLast() {
		return this.isLast;
	}

	public boolean hasNumero(int numero) {
		this.isLast = false;
		
		for(int i = 0; i < this.numeros.length; i++) {
			if(numero == this.numeros[i]) {
				if(i == this.numeros.length-1) {
					this.isLast = true;
				}
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		String texto = "Sorteio: "+this.sorteio+", Data: "+this.data+", números: "+this.numeros[0]+"-";
		texto += this.numeros[1]+"-"+this.numeros[2]+"-"+this.numeros[3]+"-"+this.numeros[4]+"-";
		texto += this.numeros[5];
		return texto;
	}
}
