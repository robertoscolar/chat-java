package chat.servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import chat.servidor.thread.RecebeMensagemCliente;

public class Servidor {
	
	private List<RecebeMensagemCliente> clientes = new ArrayList<>();

	public static void main(String[] args) {
		
		Servidor servidor = new Servidor();
		servidor.aguardarConexoes();
	}
	
	public void aguardarConexoes() {
		
		try (ServerSocket server = new ServerSocket(3333)) {
			
			while (true) {
				System.out.println("Aguardando conexões...");
				Socket socket = server.accept();
				
				RecebeMensagemCliente recebeMensagem = new RecebeMensagemCliente(socket, this);
				new Thread(recebeMensagem).start();
				
				this.clientes.add(recebeMensagem);
				System.out.println("Novo Cliente Conectado.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enviarMensagemClientes (String mensagem) {
		for (RecebeMensagemCliente cliente : this.clientes) {
			cliente.enviarMensagem(mensagem);
		}
	}
}
