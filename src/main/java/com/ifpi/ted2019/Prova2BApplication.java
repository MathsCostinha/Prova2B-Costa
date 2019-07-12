package com.ifpi.ted2019;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ifpi.ted2019.domain.Pedido;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
public class Prova2BApplication {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		SpringApplication.run(Prova2BApplication.class, args);
		
		Scanner scanner = new Scanner(System.in);
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		System.out.println("Para listar os pedidos cadastrados: Digite 1\n"
				+"Para buscar um pedido pelo ID: Digite 2\n"
				+"Para cadastrar um novo pedido: Digite 3\n"
				+"Para atualizar um pedido: Digite 4\n"
				+"Para remover um pedido: Digite 5\n"
				+"Para sair do Banco de Dados: Digite 0\n");
		int escolha = scanner.nextInt();
		while(escolha != 0) {
			if(escolha==1) {
				String jpql = "SELECT p FROM pedido p";
				List<Pedido>pedidos = entityManager.createQuery(jpql,Pedido.class).getResultList();
				System.out.println(pedidos);
				System.out.println("Se deseja fazer mais alterações no Banco de Dados:\n"
					+"Para listar os pedidos cadastrados: Digite 1\n"
					+"Para buscar um pedido pelo ID: Digite 2\n"
					+"Para cadastrar um novo pedido: Digite 3\n"
					+"Para atualizar um pedido: Digite 4\n"
					+"Para remover um pedido: Digite 5\n"
					+"Para sair do Banco de Dados: Digite 0\n");
				escolha = scanner.nextInt();
			}
			else if(escolha==2) {
				System.out.println("Digite o ID a ser procurado: ");
				int id = scanner.nextInt();
				Pedido pedidoFound= entityManager.find(Pedido.class,id);
				if(pedidoFound != null) {
					System.out.println(pedidoFound);
					System.out.println("Se deseja fazer mais alterações no Banco de Dados:\n"
						+"Para listar os pedidos cadastrados: Digite 1\n"
						+"Para buscar um pedido pelo ID: Digite 2\n"
						+"Para cadastrar um novo pedido: Digite 3\n"
						+"Para atualizar um pedido: Digite 4\n"
						+"Para remover um pedido: Digite 5\n"
						+"Para sair do Banco de Dados: Digite 0\n");
					escolha = scanner.nextInt();
				}
				else if(pedidoFound==null ) {
					System.out.println("ID não encontrado!");
					System.out.println("Se deseja fazer mais alterações no Banco de Dados: \n"
						+"Para listar os pedidos cadastrados: Digite 1\n"
						+"Para buscar um pedido pelo ID: Digite 2\n"
						+"Para cadastrar um novo pedido: Digite 3\n"
						+"Para atualizar um pedido: Digite 4\n"
						+"Para remover um pedido: Digite 5\n"
						+"Para sair do Banco de Dados: Digite 0\n");
					escolha = scanner.nextInt();
				}
			}
			else if (escolha==3) {
				System.out.println("Digite a data: ");
				String dataRecebida = scanner.next();
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
				Date dt = df.parse(dataRecebida);
				System.out.println(dt);
				
				System.out.println("Digite o valor: ");
				float valor = scanner.nextFloat();
				Pedido pedido = new Pedido(null, dt, valor);
				entityManager.getTransaction().begin();
				entityManager.persist(pedido);
				entityManager.getTransaction().commit();
				System.out.println("Cadastro realizado com sucesso!");
				System.out.println("Caso deseje fazer mais alterações no Banco de Dados:\n"
					+"Para listar os pedidos cadastrados: Digite 1\n"
					+"Para buscar um pedido pelo ID: Digite 2\n"
					+"Para cadastrar um novo pedido: Digite 3\n"
					+"Para atualizar um pedido: Digite 4\n"
					+"Para remover um pedido: Digite 5\n"
					+"Para sair do Banco de Dados: Digite 0\n");
				escolha = scanner.nextInt();
			}
			else if(escolha==4) {
				System.out.println("Digite o ID a ser atualizado: ");
				int id = scanner.nextInt();
				Pedido pedidoFound = entityManager.find(Pedido.class,id);
				if(pedidoFound==null) {
					System.out.println("ID não existente!");
					System.out.println("Se deseja fazer mais alterações no Banco de Dados: \n"
						+"Para listar os pedidos cadastrados: Digite 1\n"
						+"Para buscar um pedido pelo ID: Digite 2\n"
						+"Para cadastrar um novo pedido: Digite 3\n"
						+"Para atualizar um pedido: Digite 4\n"
						+"Para remover um pedido: Digite 5\n"
						+"Para sair do Banco de Dados: Digite 0\n");
					escolha = scanner.nextInt();
				}
			else if(pedidoFound!=null) {
				System.out.println("Se deseja atualizar:\n data: Digite 1\n valor: Digite 2\n Os dois anteriores: Digite 3");
				int escolhaDeAlteracao = scanner.nextInt();
				if(escolhaDeAlteracao==1) {
					System.out.println("Alterar data para: ");
					String dataRecebida = scanner.nextLine();
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
					Date dt = df.parse(dataRecebida);
					System.out.println(dt);
					pedidoFound.setData(dt);
					entityManager.getTransaction().begin();
					entityManager.persist(pedidoFound);
					entityManager.getTransaction().commit();
					System.out.println("Alteração realizada com sucesso!");
					System.out.println("Se deseja fazer mais alterações no Banco de Dados: \n"
						+"Para listar os pedidos cadastrados: Digite 1\n"
						+"Para buscar um pedido pelo ID: Digite 2\n"
						+"Para cadastrar um novo pedido: Digite 3\n"
						+"Para atualizar um pedido: Digite 4\n"
						+"Para remover um pedido: Digite 5\n"
						+"Para sair do Banco de Dados: Digite 0\n");
					escolha = scanner.nextInt();
				}
				else if(escolhaDeAlteracao==2) {
					System.out.println("Alterar valor para: ");
					float novovalor = scanner.nextFloat();
					pedidoFound.setValor(novovalor);
					entityManager.getTransaction().begin();
					entityManager.persist(pedidoFound);
					entityManager.getTransaction().commit();
					System.out.println("Alteração realizada com sucesso!");
					System.out.println("Se deseja fazer mais alterações no Banco de Dados: \n"
						+"Para listar os pedidos cadastrados: Digite 1\n"
						+"Para buscar um pedido pelo ID: Digite 2\n"
						+"Para cadastrar um novo pedido: Digite 3\n"
						+"Para atualizar um pedido: Digite 4\n"
						+"Para remover um pedido: Digite 5\n"
						+"Para sair do Banco de Dados: Digite 0\n");
					escolha = scanner.nextInt();
				}
				else if(escolhaDeAlteracao==3) {
					System.out.println("Alterar data para: ");
					String dataRecebida = scanner.nextLine();
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
					Date dt = df.parse(dataRecebida);
					System.out.println(dt);
					pedidoFound.setData(dt);
					System.out.println("Alterar valor para: ");
					float novovalor = scanner.nextFloat();
					pedidoFound.setValor(novovalor);
					entityManager.getTransaction().begin();
					entityManager.persist(pedidoFound);
					entityManager.getTransaction().commit();
					System.out.println("Alteração realizada com sucesso!");
					System.out.println("Se deseja fazer mais alterações no Banco de Dados: \n"
						+"Para listar os pedidos cadastrados: Digite 1\n"
						+"Para buscar um pedido pelo ID: Digite 2\n"
						+"Para cadastrar um novo pedido: Digite 3\n"
						+"Para atualizar um pedido: Digite 4\n"
						+"Para remover um pedido: Digite 5\n"
						+"Para sair do Banco de Dados: Digite 0\n");
					escolha = scanner.nextInt();
				}
			}
			}
			else if(escolha==5) {
				System.out.println("Digite o ID que deseja remover: ");
				int id=scanner.nextInt();
				Pedido pedidoFound= entityManager.find(Pedido.class,id);
				if(pedidoFound!=null) {
					entityManager.getTransaction().begin();
					entityManager.remove(pedidoFound);
					entityManager.getTransaction().commit();
					System.out.println("Removido com sucesso!");
					System.out.println("Se deseja fazer mais alterações no Banco de Dados: \n"
						+"Para listar os pedidos cadastrados: Digite 1\n"
						+"Para buscar um pedido pelo ID: Digite 2\n"
						+"Para cadastrar um novo pedido: Digite 3\n"
						+"Para atualizar um pedido: Digite 4\n"
						+"Para remover um pedido: Digite 5\n"
						+"Para sair do Banco de Dados: Digite 0\n");
					escolha = scanner.nextInt();
				}
				else if(pedidoFound==null) {
					System.out.println("ID não existente!");
					System.out.println("Se deseja fazer mais alterações no Banco de Dados: \n"
						+"Para listar os pedidos cadastrados: Digite 1\n"
						+"Para buscar um pedido pelo ID: Digite 2\n"
						+"Para cadastrar um novo pedido: Digite 3\n"
						+"Para atualizar um pedido: Digite 4\n"
						+"Para remover um pedido: Digite 5\n"
						+"Para sair do Banco de Dados: Digite 0\n");
					escolha = scanner.nextInt();
				}
			}	
		}	
		if(escolha==0) {
			entityManager.close();
			entityManagerFactory.close();
			System.out.println("Não consegui mexer com a data. Goodbye!");
		}	
	}
}