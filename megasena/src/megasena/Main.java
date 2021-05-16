package megasena;

import java.io.IOException;
import java.text.ParseException;

import megasena.controller.JogoController;

public class Main {
	public static void main(String[] args) throws IOException, ParseException {
		JogoController controller = new JogoController();
		controller.setArquivo("mega_sena.csv");
		controller.rodaJogos(4);
	}
}
