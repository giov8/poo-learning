/* TRABALHO 2 - Paradigmas de Programação ERE2 - 2021
Alunos: Giovani Marciniak e Mariana Moreira
Última Modificação: 14/03/21
*/

import java.util.ArrayList;
import java.util.List;

// Visitors
interface Visitor {
	public void visit(CompositeDiretorio d);
	public void visit(Arquivo a);
}

//public
class ImprimeNomeVisitor implements Visitor {
	
	@Override
	public void visit(CompositeDiretorio d) {
		System.out.println(d.nome+"/");
	}
	
	@Override
	public void visit(Arquivo a) {
		System.out.println(a.nome+"."+a.extensao);
    }
}

//public
class ImprimeNomeArquivoTextoVisitor implements Visitor {
	@Override
	public void visit(CompositeDiretorio d) {}
	
	@Override
	public void visit(Arquivo a) {
		if (a instanceof ArquivoTexto) {
			System.out.println(a.nome+"."+a.extensao);
		}
    }
}

// Fim Visitors

// Interface Composite
interface Diretorio {
	public void accept(Visitor v);
}

//Composite
class CompositeDiretorio implements Diretorio {
	String nome;
	private List<Diretorio> filho = new ArrayList<Diretorio>();
	
	public CompositeDiretorio (String nome) {
		this.nome = nome;
	}

	//(visitor)
	public void accept(Visitor v) {
		v.visit(this);
		for (Diretorio d : filho) {
			d.accept(v);
		}
	}

	
	
	public void add (Diretorio diretorio) {
		filho.add(diretorio);
	}
	
	public void remove (Diretorio diretorio) {
		filho.remove(diretorio);
	}

}

enum EnumTiposArquivo {
	IMAGEM, PDF, TEXTO;
}

// Filho Composite, classe abstrata de arquivos
abstract class Arquivo implements Diretorio {
	String nome, extensao;
	float tamanho;
	
	public void accept(Visitor v) {
		v.visit(this);
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
		FactoryArquivo factoryarquivo = new FactoryArquivo(); //FactoryMethod de tipos de arquivos
		
		// Atribuição dos filho aos diretórios
		imagens.add(factoryarquivo.getArquivo("por-do-sol", "jpg", EnumTiposArquivo.IMAGEM));
		imagens.add(factoryarquivo.getArquivo("noite-estrelada", "png", EnumTiposArquivo.IMAGEM));
		raiz.add(factoryarquivo.getArquivo("leia-me", "txt", EnumTiposArquivo.TEXTO));
		relatorios.add(factoryarquivo.getArquivo("relatório-2019","doc", EnumTiposArquivo.TEXTO));
		relatorios.add(factoryarquivo.getArquivo("relatório-2020", "doc", EnumTiposArquivo.TEXTO));
		livros.add(factoryarquivo.getArquivo("fluam-minhas-lagrimas-disse-o-policial", "pdf", EnumTiposArquivo.PDF));

		// Impressão

		System.out.println("## Impressão das Pastas ##");
		Visitor v = new ImprimeNomeVisitor();
		
		System.out.println("#raiz");
		raiz.accept(v);
	
		System.out.println();
		System.out.println("#imagens");
		imagens.accept(v);
		
		System.out.println();
		System.out.println("#relatorios");
		relatorios.accept(v);
		
		System.out.println();
		System.out.println("#livros");
		livros.accept(v);
		
		System.out.println();
		Visitor vtexto = new ImprimeNomeArquivoTextoVisitor();
		System.out.println("## Busca por arquivos de TEXTO na RAIZ ##");
		System.out.println("#raiz");
		raiz.accept(vtexto);
		
	/*
		System.out.println("## Impressão das Pastas RECURSIVO ##");

		System.out.println("## Busca por arquivos de TEXTO na RAIZ RECURSIVO ##"); */
		
	}
}