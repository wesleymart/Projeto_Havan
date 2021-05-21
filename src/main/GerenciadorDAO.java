package main;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorDAO {
	
	
	public Double TotalTaxas(List<Cliente> list) {
		
		double total1 = list.stream()
				.map(c -> c.getValorConvertido())
				.reduce(0.0, (c, y) -> c + y );		
		double taxas = total1 * 0.1;
		return taxas;
	}
	
	public Double ValorTotalConversoes(List<Cliente> list) {
		double total1 = list.stream()
				.map(c -> c.getValorConvertido())
				.reduce(0.0, (c, y) -> c + y);
		return total1;
	}
	
	public List TotalOperacoes(List<Cliente> list) {
		List<String> cliente1 = list.stream()
                .map(p -> "Moda de origem:" + p.getMoedaOrigem() +"/ Valor original: " + p.getValorOriginal() + "/ Moeda de destino:" + p.getMoedaDestino() +"/ Valor convertido: " + p.getValorConvertido() +"R$ \n")
                .collect(Collectors.toList());
		return cliente1;
	}
	

	

}
