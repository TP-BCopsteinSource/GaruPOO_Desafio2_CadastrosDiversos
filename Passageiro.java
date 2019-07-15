public class Passageiro {
	private String cpf;
	private String nome;
	private FormaPagamento formaPgto;
	private String nroCartao;
	private int pontuacaoAcumulada;
	private int qtdadeAvaliacoes;

	// Versao para carregar passageiros do arquivo
	public Passageiro(String cpf, String nome, FormaPagamento formaPgto, String nroCartao, int pontuacaoAcumulada,
			int qtdadeAvaliacoes) {
		this.cpf = cpf;
		this.nome = nome;
		this.formaPgto = formaPgto;
		this.nroCartao = nroCartao;
		this.pontuacaoAcumulada = pontuacaoAcumulada;
		this.qtdadeAvaliacoes = qtdadeAvaliacoes;
	}

	// Versao para criar passageiros novos
	public Passageiro(String cpf, String nome, FormaPagamento formaPgto, String nroCartao) {
		this.cpf = cpf;
		this.nome = nome;
		this.formaPgto = formaPgto;
		this.nroCartao = nroCartao;
		this.pontuacaoAcumulada = 0;
		this.qtdadeAvaliacoes = 0;
	}

	
	public String getCPF() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public FormaPagamento getFormaPgto() {
		return formaPgto;
	}

	public String getNroCartao() {
		return nroCartao;
	}

	public int getPontuacaoMedia() {
		return pontuacaoAcumulada/qtdadeAvaliacoes;
	}

	public void infoPontuacao(int pontuacao) {
		if (pontuacao < 1 || pontuacao > 5){
			throw new IllegalArgumentException("Pontucao invalida ! Deve pertencer ao intervalo: [1;5]");
		}
		pontuacaoAcumulada += pontuacao;
		qtdadeAvaliacoes++;
	}

	public int getPontuacaoAcumulada() {
		return pontuacaoAcumulada;
	}

	public int getQtdadeAvaliacoes() {
		return qtdadeAvaliacoes;
	}

	@Override
	public String toString() {
		return "Passageiro [cpf=" + cpf + ", formaPgto=" + formaPgto + ", nome=" + nome + ", nroCartao=" + nroCartao
				+ ", pontuacaoAcumulada=" + pontuacaoAcumulada + ", qtdadeAvaliacoes=" + qtdadeAvaliacoes + "]";
	}
}
