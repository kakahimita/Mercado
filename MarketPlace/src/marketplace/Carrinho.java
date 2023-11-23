package marketplace;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author kakah
 */
public class Carrinho {

    public List<Produto> produtos;

    public Carrinho(){
        produtos = new ArrayList<>();
    }
    
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }
    
    public void removerProduto(Produto produto) {
        produtos.remove(produto);
    }
    
    public void mostrarCarinho() {
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }
    
    public void mostrarCarrinhoOrdenadoPorPreco() {
        produtos.stream().sorted(Comparator.comparingDouble(produto -> produto.preco)).forEach(System.out::println);
    }
    
    public double calcularTotal() {
        return produtos.stream().mapToDouble(produto -> produto.preco).sum();
    }
}