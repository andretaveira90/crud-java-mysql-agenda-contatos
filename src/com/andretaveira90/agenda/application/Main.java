package com.andretaveira90.agenda.application;

import java.util.Locale;
import java.util.Scanner;

import com.andretaveira90.agenda.dao.ContatoDAO;
import com.andretaveira90.agenda.model.Contato;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		Contato contato = new Contato();
		ContatoDAO contatoDao = new ContatoDAO();
		
		int opcao;
		char confirmacao;
		
		do {
			System.out.println(
					"\nSelecione a opção desejada:\n"
					+ "\n1 - Adicionar contato"
					+ "\n2 - Listar contatos"	
					+ "\n3 - Atualizar contato"
					+ "\n4 - Excluir contato"				
					);
			
			opcao = scanner.nextInt();
			scanner.nextLine();
			
			switch (opcao) {
				case 1:
					System.out.print("Nome: ");
					contato.setNome(scanner.nextLine());
					System.out.print("Email: ");
					contato.setEmail(scanner.nextLine());
					System.out.print("Telefone: ");
					contato.setTelefone(scanner.nextLine());
					System.out.println("Salvar contato? (S/N)");
					confirmacao = scanner.next().charAt(0);
					if (confirmacao == 's' || confirmacao == 'S') {
						contatoDao.salvar(contato);
					}
					break;
					
				case 2:
					System.out.println("\n=== CONTATOS ===");
					for (Contato contatoAtual : contatoDao.listar()) {
						System.out.println("ID: " + contatoAtual.getId());
						System.out.println("Nome: " + contatoAtual.getNome());
						System.out.println("Email: " + contatoAtual.getEmail());
						System.out.println("telefone: " + contatoAtual.getTelefone());
						System.out.println("==========");
					}
					break;
					
				case 3:
					System.out.print("Informe o id do contato: ");
					contato.setId(scanner.nextInt());
					scanner.nextLine();
					System.out.println("Insira os novos dados: ");
					System.out.print("Nome: ");
					contato.setNome(scanner.nextLine());
					System.out.print("Email: ");
					contato.setEmail(scanner.nextLine());
					System.out.print("Telefone: ");
					contato.setTelefone(scanner.nextLine());
					System.out.println("Salvar alterações? (S/N)");
					confirmacao = scanner.next().charAt(0);
					if (confirmacao == 's' || confirmacao == 'S') {
						contatoDao.atualizar(contato);
					} else {
						System.out.println("Alteração descartada");
					}
					break;
				
				case 4:
					System.out.print("Informe o id do contato que deseja excluir: ");
					int id = scanner.nextInt();
					scanner.nextLine();
					System.out.println("Tem certeza que deseja excluir o contato com id = " + id + " (S/N)");
					confirmacao = scanner.next().charAt(0);
					if (confirmacao == 's' || confirmacao == 'S') {
						contatoDao.excluir(id);
					} else {
						System.out.println("Exclusão cancelada");
					}
					break;
					
				default:
					System.out.println("Opção inválida.");
			} 
			
			System.out.println("Deseja fazer outra operação? (S/N)");
			confirmacao = scanner.next().charAt(0);
		
		} while (confirmacao != 'n' && confirmacao != 'N');
		
		System.out.println("Programa encerrado.");	
		scanner.close();
	}
}