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
	
	public CompositeDiretorio (String nome) {
		this.nome = nome;
	}

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

enum EnumTiposArquivo {
	IMAGEM, PDF, TEXTO;
}

// Filho Composite, classe abstrata de arquivos
abstract class Arquivo implements Diretorio {
	String nome, extensao;
	EnumTiposArquivo tipo;
	float tamanho;
	
	public void imprimeNome() {
	    System.out.println(nome+"."+extensao);
	}
}

// Classes referentes aos tipos de arquivos
/*public */
class ArquivoTexto extends Arquivo {
	public ArquivoTexto (String nome, String extensao) {
		this.nome = nome;
		this.extensao = extensao;
	}
}

/* public */
class ArquivoImagem extends Arquivo {
	public ArquivoImagem (String nome, String extensao) {
		this.nome = nome;
		this.extensao = extensao;
	}
}

/* public */
class ArquivoPDF extends Arquivo {
	public ArquivoPDF (String nome, String extensao) {
		this.nome = nome;
		this.extensao = extensao;
	}	
}

class FactoryArquivo {
	
	public Arquivo getArquivo (String nome, String extensao, EnumTiposArquivo tipo){
		if (tipo == EnumTiposArquivo.IMAGEM) {
			return new ArquivoImagem(nome, extensao);
		}
		
		if (tipo == EnumTiposArquivo.PDF) {
			return new ArquivoPDF(nome, extensao);
		}
		
		if (tipo == EnumTiposArquivo.TEXTO) {
			return new ArquivoTexto(nome, extensao);
		}
		
		System.out.println("Erro no getArquivo, Tipo de arquivo inexistente");
		return null;
	}
}

class FactoryDiretorio {
	public CompositeDiretorio getDiretorio(String nome) {
		return new CompositeDiretorio(nome);
	}
}

// Classe princial (main)
class Programa {
	public static void main(String[] args) {
		// Declaração dos diretórios
		FactoryDiretorio factorydiretorio = new FactoryDiretorio();
		
		CompositeDiretorio raiz = factorydiretorio.getDiretorio("Raiz");

		CompositeDiretorio imagens = factorydiretorio.getDiretorio("Imagens");

		CompositeDiretorio relatorios = factorydiretorio.getDiretorio("Relatórios");

		CompositeDiretorio livros = factorydiretorio.getDiretorio("Livros");

		// Adiciona todos os diretórios ao diretório "raiz"
		raiz.add(imagens);
		raiz.add(relatorios);
		raiz.add(livros);

		// Declaração de arquivos		
		FactoryArquivo imagem1 = new FactoryArquivo();
		imagens.add(imagem1.getArquivo("por-do-sol", "jpg", EnumTiposArquivo.IMAGEM));
		
		FactoryArquivo imagem2 = new FactoryArquivo();
		imagens.add(imagem2.getArquivo("noite-estrelada", "png", EnumTiposArquivo.IMAGEM));

		FactoryArquivo leiame = new FactoryArquivo();
		raiz.add(leiame.getArquivo("leia-me", "txt", EnumTiposArquivo.TEXTO));

		FactoryArquivo relatorio1 = new FactoryArquivo();
		relatorios.add(relatorio1.getArquivo("relatório-2019","doc", EnumTiposArquivo.TEXTO));

		FactoryArquivo relatorio2 = new FactoryArquivo();
		relatorios.add(relatorio2.getArquivo("relatório-2020", "doc", EnumTiposArquivo.TEXTO));

		FactoryArquivo livro1 = new FactoryArquivo();
		livros.add(livro1.getArquivo("fluam-minhas-lagrimas-disse-o-policial", "pdf", EnumTiposArquivo.PDF));

		// Atribuição dos filho aos diretórios
		/*imagens.add(Arquivoimagem1);
		imagens.add(imagem2);
		raiz.add(leiame);
		relatorios.add(relatorio1);
		relatorios.add(relatorio2);
		livros.add(livro1);*/

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
		
		System.out.println("#raiz");
		raiz.imprimeTudo();
	
		System.out.println();
		System.out.println("#imagens");
		imagens.imprimeTudo();
		
		System.out.println();
		System.out.println("#relatorios");
		relatorios.imprimeTudo();
		
		System.out.println();
		System.out.println("#livros");
		livros.imprimeTudo();
	}
}