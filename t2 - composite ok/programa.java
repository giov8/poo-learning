/* TRABALHO 2 - Paradigmas de Programação ERE2 - 2021
Alunos: Giovani Marciniak e Mariana Moreira
Última Modificação: 16/03/21
*/

import java.util.ArrayList;
import java.util.List;

// Interface Composite
interface Diretorio {
	public void imprimeNome();
}

//Composite
class CompositeDiretorio implements Diretorio {
	String nome;
	private List<Diretorio> filho = new ArrayList<Diretorio>();

	public void imprimeNome() {
	    System.out.println(nome+"/");
	}
	
	public void imprimeTudo() {
		for (Diretorio d : filho) {
			d.imprimeNome();
				}
	}
	
	
	public void add (Diretorio diretorio) {
		filho.add(diretorio);
	}
	
	public void remove (Diretorio diretorio) {
		filho.remove(diretorio);
	}
	
	/*void imprimeConteudo() {
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

	// Nessa versão recursiva serão impressas todas as subpastas e arquivos das subpastas também
	@Override
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

	// Na versão recursiva será feita a busca em todas as subpastas também
	@Override
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
*/

}

// Filho Composite, classe abstrata de arquivos
abstract class Arquivo implements Diretorio {
	String nome, extensao;
	TiposArquivo tipo;
	float tamanho;
	
	public void imprimeNome() {
	    System.out.println(nome+"."+extensao);
	}
}

// Classes referentes aos tipos de arquivos
public class ArquivoTexto extends Arquivo {
}

public class ArquivoImagem extends Arquivo {
}

public class ArquivoPDF extends Arquivo {
}

// Classe princial (main)
class Programa {
	public static void main(String[] args) {
		// Declaração dos diretórios
		CompositeDiretorio raiz = new CompositeDiretorio();
		raiz.nome = "Raiz";

		CompositeDiretorio imagens = new CompositeDiretorio();
		imagens.nome = "Imagens";

		CompositeDiretorio relatorios = new CompositeDiretorio();
		relatorios.nome = "Relatórios";

		CompositeDiretorio livros = new CompositeDiretorio();
		livros.nome = "Livros";

		// Adiciona todos os diretórios ao diretório "raiz"
		raiz.add(imagens);
		raiz.add(relatorios);
		raiz.add(livros);

		// Declaração de arquivos		
		ArquivoImagem imagem1 = new Arquivo();
		imagem1.nome = "por-do-sol";
		imagem1.extensao = "jpg";
		imagem1.tipo = TiposArquivo.IMAGEM;
		
		ArquivoImagem imagem2 = new Arquivo();
		imagem2.nome = "noite-estrelada";
		imagem2.extensao = "png";
		imagem2.tipo = TiposArquivo.IMAGEM;

		ArquivoTexto leiame = new Arquivo();
		leiame.nome = "leia-me";
		leiame.extensao = "txt";
		leiame.tipo = TiposArquivo.TEXTO;

		ArquivoTexto relatorio1 = new Arquivo();
		relatorio1.nome = "relatório-2019";
		relatorio1.extensao = "doc";
		relatorio1.tipo = TiposArquivo.TEXTO;

		ArquivoTexto relatorio2 = new Arquivo();
		relatorio2.nome = "relatório-2020";
		relatorio2.extensao = "doc";
		relatorio2.tipo = TiposArquivo.TEXTO;

		ArquivoPDF livro1 = new Arquivo();
		livro1.nome = "fluam-minhas-lagrimas-disse-o-policial";
		livro1.extensao = "pdf";
		livro1.tipo = TiposArquivo.PDF;

		// Atribuição dos filho aos diretórios
		imagens.add(imagem1);
		imagens.add(imagem2);
		raiz.add(leiame);
		relatorios.add(relatorio1);
		relatorios.add(relatorio2);
		livros.add(livro1);

		// Impressão
		/*System.out.println("## Impressão das Pastas ##");
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
		System.out.println();*/
		
		System.out.println("raiz");
		raiz.imprimeTudo();
	
		System.out.println();
		System.out.println("imagens");
		imagens.imprimeTudo();
		
		System.out.println();
		System.out.println("relatorios");
		relatorios.imprimeTudo();
		
		System.out.println();
		System.out.println("livros");
		livros.imprimeTudo();
	}
}