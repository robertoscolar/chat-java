package chat.cliente.thread;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

import chat.cliente.gui.JanelaGui;

public class RecebeMensagemServidor implements Runnable {

	private Socket socket;
	private JanelaGui janelaGui;
	
	public RecebeMensagemServidor(Socket socket, JanelaGui janelaGui) {
		super();
		this.socket = socket;
		this.janelaGui = janelaGui;
	}

	@Override
	public void run() {
		while (true) {
			try {
				InputStream is = this.socket.getInputStream();
				DataInput dis = new DataInputStream(is);
				String  msgRecebida = dis.readUTF();
				janelaGui.adicionaMensagem(msgRecebida);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
