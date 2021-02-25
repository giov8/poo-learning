import java.util.ArrayList;

class Arquivo {
	String nome, extensao, tipo;
	float tamanho;
}

class Diretorio {
	String nome;
	ArrayList<Arquivo> arquivos;
	ArrayList<Diretorio> subdiretorios;

}

class Programa {
	public static void main(String[] args) {
		Diretorio pasta1 = new Diretorio();
		pasta1.nome = "Teste";
	}
}
