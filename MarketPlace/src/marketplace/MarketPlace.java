package marketplace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author kakah
 */
public class MarketPlace {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Carrinho carrinho = new Carrinho();
        List<Produto> listaProdutos = new ArrayList<>(
                Arrays.asList(new Produto("Arroz", 5.0), new Produto("Feijão", 9.0), new Produto("Macarrão", 3.50),
                        new Produto("Leite", 4.50), new Produto("Ovos", 18.0), new Produto("Carne", 53.0),
                        new Produto("Cenoura", 3.50), new Produto("Tomate", 4.0), new Produto("Cebola", 7.0)));

        Menu menu = new Menu(scanner, carrinho, listaProdutos);
        menu.exibirMenu();
    }
}
