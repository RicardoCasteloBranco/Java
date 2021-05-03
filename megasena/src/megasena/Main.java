package megasena;

import megasena.controller.JogoController;

public class Main {
	public static void main(String[] args) {
		JogoController controller = new JogoController();
		controller.setArquivo("mega_sena.csv");
	}
}
