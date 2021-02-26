import java.util.ArrayList;

enum TiposArquivo {
	TEXTO, IMAGEM, PDF;
}

class Arquivo {
	String nome, extensao;
	TiposArquivo tipo;
	float tamanho;
	
	void imprimeNome() {
	    System.out.println(nome+"."+extensao);
	}
}

class Diretorio {
	String nome;
	ArrayList<Arquivo> arquivos;
	ArrayList<Diretorio> subdiretorios;

	void imprimeNome() {
	    System.out.println(nome+"/");
	}
	
	void imprimeConteudo() {
	    System.out.println("# Diretório: " + nome);
	    
	    if (!subdiretorios.isEmpty()) {
	        System.out.println("* Sub-diretórios:");
	        for (Diretorio d : subdiretorios) {
	            d.imprimeNome();
	        }
	    }
	    
	    if (!arquivos.isEmpty()) {
	        System.out.println("* Arquivos:");
	        for (Arquivo a : arquivos) {
	            a.imprimeNome();
	        }
	    }
	}

	void imprimeConteudo(char recursivo) {
		if (recursivo != 'r') {
			imprimeConteudo();
			return;
		}

	    imprimeConteudo();
	    System.out.println();
    
	    if (!subdiretorios.isEmpty()) {
	        for (Diretorio d : subdiretorios) {
	            d.imprimeConteudo('r');
	        }
	    }

	}

	void imprimePorTipo (TiposArquivo tipo) {
		for (Arquivo a : arquivos) {
				if (a.tipo == tipo) {
	            	a.imprimeNome();
				}
	        }
	}

	void imprimePorTipo (TiposArquivo tipo, char recursivo) {
		if (recursivo != 'r') {
			imprimePorTipo(tipo);
			return;
		}

		for (Diretorio d : subdiretorios) {
				d.imprimePorTipo(tipo, 'r');
			//d.imprimePorTipo(tipo);
		}
		imprimePorTipo(tipo);
	}

}

// Classe princial (main)
class Programa {
	public static void main(String[] args) {
		// Declaração dos diretórios
		Diretorio raiz = new Diretorio();
		raiz.arquivos = new ArrayList<Arquivo>();
		raiz.subdiretorios = new ArrayList<Diretorio>();
		raiz.nome = "Raiz";

		Diretorio imagens = new Diretorio();
		imagens.arquivos = new ArrayList<Arquivo>();
		imagens.subdiretorios = new ArrayList<Diretorio>();
		imagens.nome = "Imagens";

		Diretorio relatorios = new Diretorio();
		relatorios.arquivos = new ArrayList<Arquivo>();
		relatorios.subdiretorios = new ArrayList<Diretorio>();
		relatorios.nome = "Relatórios";

		Diretorio livros = new Diretorio();
		livros.arquivos = new ArrayList<Arquivo>();
		livros.subdiretorios = new ArrayList<Diretorio>();
		livros.nome = "Livros";

		// Adiciona todos os diretórios ao diretório "raiz"
		raiz.subdiretorios.add(imagens);
		raiz.subdiretorios.add(relatorios);
		raiz.subdiretorios.add(livros);

		// Declaração de arquivos		
		Arquivo imagem1 = new Arquivo();
		imagem1.nome = "por-do-sol";
		imagem1.extensao = "jpg";
		imagem1.tipo = TiposArquivo.IMAGEM;
		
		Arquivo imagem2 = new Arquivo();
		imagem2.nome = "noite-estrelada";
		imagem2.extensao = "png";
		imagem2.tipo = TiposArquivo.IMAGEM;

		Arquivo leiame = new Arquivo();
		leiame.nome = "leia-me";
		leiame.extensao = "txt";
		leiame.tipo = TiposArquivo.TEXTO;

		Arquivo relatorio1 = new Arquivo();
		relatorio1.nome = "relatório-2019";
		relatorio1.extensao = "doc";
		relatorio1.tipo = TiposArquivo.TEXTO;

		Arquivo relatorio2 = new Arquivo();
		relatorio2.nome = "relatório-2020";
		relatorio2.extensao = "doc";
		relatorio2.tipo = TiposArquivo.TEXTO;

		Arquivo livro1 = new Arquivo();
		livro1.nome = "fluam-minhas-lagrimas-disse-o-policial";
		livro1.extensao = "pdf";
		livro1.tipo = TiposArquivo.PDF;

		// Atribuição dos arquivos aos diretórios
		imagens.arquivos.add(imagem1);
		imagens.arquivos.add(imagem2);
		raiz.arquivos.add(leiame);
		relatorios.arquivos.add(relatorio1);
		relatorios.arquivos.add(relatorio2);
		livros.arquivos.add(livro1);

		// Impressão
		System.out.println("## Impressão das Pastas ##");
		raiz.imprimeConteudo();
		System.out.println();

		System.out.println("## Impressão das Pastas RECURSIVO ##");
		raiz.imprimeConteudo('r');
		System.out.println();

		System.out.println("## Busca por arquivos de TEXTO na RAIZ ##");
		raiz.imprimePorTipo(TiposArquivo.TEXTO);
		System.out.println();

		System.out.println("## Busca por arquivos de TEXTO na RAIZ RECURSIVO ##");
		raiz.imprimePorTipo(TiposArquivo.TEXTO, 'r');
		System.out.println();
	}
}