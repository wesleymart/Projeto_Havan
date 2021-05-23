package main;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorDAO {
	
	//Função para imprimir na tela todas as taxas cobradas
	public Double TotalTaxas(List<Cliente> list) {
		double total1 = list.stream()
				.map(c -> c.getValorConvertido())
				.reduce(0.0, (c, y) -> c + y );		
		double taxas = total1 * 0.1;
		return taxas;
	}
	//Função para imprimir na tela o total de todas as converções feitas
	public Double ValorTotalConversoes(List<Cliente> list) {
		double total1 = list.stream()
				.map(c -> c.getValorConvertido())
				.reduce(0.0, (c, y) -> c + y);
		return total1;
	}
	//Função para imprimir na tela todas as operações feitas
	public List TotalOperacoes(List<Cliente> list) {
		List<String> cliente1 = list.stream()
                .map(p -> "Moda de origem:" + p.getMoedaOrigem() +"/ Valor original: " + p.getValorOriginal() + "/ Moeda de destino:" + p.getMoedaDestino() +"/ Valor convertido: " + p.getValorConvertido() +"R$ \n")
                .collect(Collectors.toList());
		return cliente1;
	}
	

	

}
