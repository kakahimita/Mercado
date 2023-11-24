package marketplace;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author kakah
 */
public class Menu {

    private Scanner scanner;
    private Carrinho carrinho;
    private List<Produto> listaProdutos;

    public Menu(Scanner scanner, Carrinho carrinho, List<Produto> listaProdutos) {
        this.scanner = scanner;
        this.carrinho = carrinho;
        this.listaProdutos = listaProdutos;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("Menu Cliente:");
            System.out.println("1. Lista de Produtos");
            System.out.println("2. Carrinho");
            System.out.println("3. Sair");
            System.out.print("Digite uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("");
                    exibirProdutos();
                    System.out.println("");
                    exibirMenuProdutos();
                    break;
                case 2:
                    exibirMenuCarrinho();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println("");
        } while (opcao != 3);
    }

    private void exibirMenuProdutos() {
        int opcaoProdutos;
        do {
            System.out.println("Menu de Produtos:");
            System.out.println("1. Adicionar item a Lista");
            System.out.println("2. Listar todos os produtos");
            System.out.println("3. Listar todos os produtos em ordem alfabética");
            System.out.println("4. Lista produtos por Preço");
            System.out.println("0. Voltar ao menu principal");
            System.out.println("");
            System.out.print("Digite uma opção: ");
            opcaoProdutos = scanner.nextInt();

            switch (opcaoProdutos) {
                case 1:
                    System.out.println("");
                    adicionarItemALista();
                    break;
                case 2:
                    exibirProdutos();
                    break;
                case 3:
                    System.out.println("");
                    System.out.println("== Produtos em ordem Alfabética ==");
                    exibirProdutosEmOrdemAlfabetica();
                    break;
                case 4:
                    System.out.println("");
                    System.out.println("== Produtos por Preço ==");
                    exibirProdutosEmOrdemDePreco();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println("");
        } while (opcaoProdutos != 0);
    }

    private void adicionarItemALista() {
        System.out.print("Digite o nome do produto que você deseja adicionar à lista: ");
        scanner.nextLine(); // Consume newline left-over
        String nome = scanner.nextLine();
        for (Produto produto : listaProdutos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                exibirProdutos();
                System.out.println("");
                System.out.println("Produto já existe na lista.");
                return;
            }
        }
        System.out.print("Digite o preço do produto: ");
        double preco = scanner.nextDouble();
        listaProdutos.add(new Produto(nome, preco));
        System.out.println("Produto adicionado à lista.");
    }

    private void adicionarItemAoCarrinhoPorId() {
        int id;
        do {
            System.out.print("Número do produto que você deseja adicionar ao carrinho (ou 0 para voltar): ");
            id = scanner.nextInt();
            if (id > 0 && id <= listaProdutos.size()) {
                Produto produto = listaProdutos.get(id - 1);
                carrinho.adicionarProduto(produto);
                System.out.println(produto.getNome() + " foi adicionado ao carrinho.");
                System.out.println("");
            } else if (id != 0) {
                System.out.println("Número de produto inválido.");
            }
        } while (id != 0);
    }

    private void listarTodosOsProdutos() {
        System.out.println("Lista de Produtos:");
        for (Produto produto : listaProdutos) {
            System.out.println(produto);
        }
    }

    private void listarProdutosNoCarrinho() {
        System.out.println("== Produtos no Carrinho ==");
        carrinho.mostrarCarinho();
    }

    private void exibirProdutos() {
        for (int i = 0; i < listaProdutos.size(); i++) {
            Produto produto = listaProdutos.get(i);
            System.out.println((i + 1) + ". " + produto.getNome() + ", Preço: " + produto.getPreco());
        }
    }

    private void exibirMenuCarrinho() {
        int opcaoCarrinho;
        do {
            System.out.println("");
            System.out.println("Menu de Carrinho:");
            System.out.println("1. Adicionar item ao carrinho");
            System.out.println("2. Listar Produtos dentro do carrinho");
            System.out.println("3. Valor total da compra");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Digite uma opção: ");
            opcaoCarrinho = scanner.nextInt();

            switch (opcaoCarrinho) {
                case 1:
                    System.out.println("");
                    exibirProdutos();
                    adicionarItemAoCarrinhoPorId();
                    break;
                case 2:
                    System.out.println("");
                    listarProdutosNoCarrinho();
                    break;
                case 3:
                    System.out.println("");
                    System.out.println("Valor total da compra:");
                    System.out.println(carrinho.calcularTotal());
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println("");
        } while (opcaoCarrinho != 0);
    }

    private void exibirProdutosEmOrdemAlfabetica() {
        List<Produto> produtosOrdenados = new ArrayList<>(listaProdutos);
        produtosOrdenados.sort(Comparator.comparing(Produto::getNome));
        for (int i = 0; i < produtosOrdenados.size(); i++) {
            Produto produto = produtosOrdenados.get(i);
            System.out.println((i + 1) + ". " + produto.getNome() + ", Preço: " + produto.getPreco());
        }
    }

    private void exibirProdutosEmOrdemDePreco() {
        List<Produto> produtosOrdenados = new ArrayList<>(listaProdutos);
        produtosOrdenados.sort(Comparator.comparingDouble(Produto::getPreco));
        for (int i = 0; i < produtosOrdenados.size(); i++) {
            Produto produto = produtosOrdenados.get(i);
            System.out.println((i + 1) + ". " + produto.getNome() + ", Preço: " + produto.getPreco());
        }
    }

}
