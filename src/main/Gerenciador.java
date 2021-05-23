package main;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class Gerenciador {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US); //Comando para o programa aceitar o ponto ao inv�s da virgula 
		Scanner sc = new Scanner(System.in); //Fun��o para leitura de dados  
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        List<Cliente> list = new ArrayList<>();
        GerenciadorDAO gen = new GerenciadorDAO();
        int sair = 0;
        //Abre o arquivo
        String path = "C:\\conversor.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            //Faz a leitura do arquivo
            String line = br.readLine();
            //Adiciona todos os itens do arquivo em uma lista
            while (line != null) {
                String[] fields = line.split(";");
                String nome = fields[0];
                String moedaOrigem = fields[1];
                String moedaDestino = fields[2];
                double valorOriginal = Double.parseDouble(fields[3]);
                double valorConvertido = Double.parseDouble(fields[4]);
                Date data = sdf1.parse(fields[6]);
                list.add(new Cliente(nome, moedaOrigem, moedaDestino, valorOriginal, valorConvertido, data));
                line = br.readLine();
            }
		//menu de op��es ele se repetir� quantas vezes o usu�rio quiser 
        while(sair != 4) {
		String cliente;
		System.out.println("Gerenciador da casa de c�mbio:");
		System.out.println("");
		System.out.println("1- Lista de opera��es");
		System.out.println("2- Valor total das opera��es");
		System.out.println("3- Valor total das taxas cobradas");
		System.out.println("4- Para sair do sistema");
		System.out.println("");
		System.out.print("Digite o n�mero do relat�rio que deseja gerar:  ");
		int escolha = sc.nextInt();
		if(escolha == 4) {
			System.exit(1);
		}
		System.out.println("");
		System.out.println("Deseja determinar um filtro?");
		System.out.println("Digite 1 para N�o / 2 para filtar por nome do cliente / 3 para filtrar por data");
		int escolha2 = sc.nextInt();
		
		//Fun��o para filtar pelo nome do cliente e realizar o relat�rio escolhido de acordo com o filtro designado
		if(escolha2 == 2) {
			System.out.println("Digite o nome do cliente: ");
			cliente = sc.next();
			if(escolha == 1) {
				List<String> cliente1 = list.stream()
						.filter(c -> c.getNome().equals(cliente))
		                .map(p -> "Moda de origem:" + p.getMoedaOrigem() +"/ Valor original: " + p.getValorOriginal() + "/ Moeda de destino:" + p.getMoedaDestino() +"/ Valor convertido: " + p.getValorConvertido() +"R$ \n")
		                .collect(Collectors.toList());
				System.out.println("relat�rio de todas as opera��es que o cliente "+ cliente+ " fez: ");
				cliente1.forEach(System.out::println);
				
			}else if(escolha == 2) {				
				double total1 = list.stream()
						.filter(c -> c.getNome().equals(cliente))
						.map(c -> c.getValorConvertido())
						.reduce(0.0, (c, y) -> c + y);
				System.out.println("Valor total de todas as convers�es que o cliente: "+ cliente+ " fez foi de: R$" + String.format("%.2f",total1));
				
			}else if(escolha == 3) {
			double total1 = list.stream()
					.filter(c -> c.getNome().equals(cliente))
					.map(c -> c.getValorConvertido())
					.reduce(0.0, (c, y) -> c + y );		
			double taxas = total1 * 0.1;
			System.out.println("Total de todas as taxas cobradas do: "+ cliente+ " foi de: R$" + String.format("%.2f",taxas));
			}
         
		}
		//Fun��o para filtar por um intervalo de datas e gerar o relat�rio previamente escolhido
		else if(escolha2 == 3) {
			
			System.out.println("Digite o intervalo de tempo que deseja: ");
			System.out.println("Data 1: ");
			Date data1 = sdf1.parse(sc.next());
			System.out.println("Data 2: ");
			Date data2 = sdf1.parse(sc.next());
			if(escolha == 1) {
				List<String> cliente1 = list.stream()
						.filter(p -> p.getData().after(data1) && p.getData().before(data2))
		                .map(p -> "Moda de origem:" + p.getMoedaOrigem() +"/ Valor original: " + p.getValorOriginal() + "/ Moeda de destino:" + p.getMoedaDestino() +"/ Valor convertido: " + p.getValorConvertido() +"R$ \n")
		                .collect(Collectors.toList());
				System.out.println("relat�rio de todas as opera��es feitas no intervalos das datas solicitadas ");
				cliente1.forEach(System.out::println);
				
			}else if(escolha == 2) {				
				double total1 = list.stream()
						.filter(p -> p.getData().after(data1) && p.getData().before(data2))
						.map(c -> c.getValorConvertido())
						.reduce(0.0, (c, y) -> c + y);
				System.out.println("Valor total de todas as convers�es feitas no intervalos das datas solicitadas foi de: R$" + String.format("%.2f",total1));
				
			}else if(escolha == 3) {
			double total1 = list.stream()
					.filter(p -> p.getData().after(data1) && p.getData().before(data2))
					.map(c -> c.getValorConvertido())
					.reduce(0.0, (c, y) -> c + y );		
			double taxas = total1 * 0.1;
			System.out.println("Total de todas as taxas cobradas no intervalos das datas solicitadas foi de: R$" + String.format("%.2f",taxas));
			}
					
		}else if(escolha2 == 1) {
		// Imprimi na tela todas as opera��es feitas
		if(escolha == 1) {
			List<String> cliente1 = gen.TotalOperacoes(list);
			System.out.println("Todas opera��es realizadas");
			cliente1.forEach(System.out::println);
			
		}
		// Imprimi na tela o total de todas as conver��es feitas
		else if(escolha == 2) {
			double total1 = gen.ValorTotalConversoes(list);			
			System.out.println("Total de todas as convers�es: " + String.format("%.2f",total1) + "R$");
		
		}
		//Imprimi na tela todas as taxas cobradas
		else if(escolha == 3) {
			double taxas = gen.TotalTaxas(list);
			System.out.println("Total de todas as taxas cobradas: " + String.format("%.2f",taxas)+ "R$");
		}
		}
		}
		
	}catch (IOException e) {

        System.out.println("Error ao abrir o arquivo: " + e.getMessage());

    }
}
}
