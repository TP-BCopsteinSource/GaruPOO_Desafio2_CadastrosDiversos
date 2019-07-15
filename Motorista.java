public class Motorista {
	private String cpf;
	private String nome;
	private String placaVeiculo;
	private FormaPagamento formaPgto;
	private int pontuacaoAcumulada;
	private int qtdadeAvaliacoes;

	// Versao para carregar do arquivo
	public Motorista(String cpf, String nome, String placaVeiculo, FormaPagamento formaPgto, int pontuacaoAcumulada,
			int qtdadeAvaliacoes) {
		this.cpf = cpf;
		this.nome = nome;
		this.placaVeiculo = placaVeiculo;
		this.formaPgto = formaPgto;
		this.pontuacaoAcumulada = pontuacaoAcumulada;
		this.qtdadeAvaliacoes = qtdadeAvaliacoes;
	}

	// Versao para criar um motorista novo
	public Motorista(String cpf, String nome, String placaVeiculo, FormaPagamento formaPgto) {
		this.cpf = cpf;
		this.nome = nome;
		this.placaVeiculo = placaVeiculo;
		this.formaPgto = formaPgto;
		this.pontuacaoAcumulada = 0;
		this.qtdadeAvaliacoes = 0;
	}

	public String getCPF() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getPlacaVeiculo() {
		return placaVeiculo;
	}

	public FormaPagamento getFormaPgto() {
		return formaPgto;
	}

	public int getPontuacaoAcumulada() {
		return pontuacaoAcumulada;
	}

	public int getQtdadeAvaliacoes() {
		return qtdadeAvaliacoes;
	}

	public int getPontuacaoMedia() {
		return pontuacaoAcumulada / qtdadeAvaliacoes;
	}

	public void infoPontuacao(int pontuacao) {
		if (pontuacao < 1 || pontuacao > 5) {
			throw new IllegalArgumentException("Pontucao invalida ! Deve pertencer ao intervalo: [1;5]");
		}
		pontuacaoAcumulada += pontuacao;
		qtdadeAvaliacoes++;
	}

	@Override
	public String toString() {
		return "Motorista [cpf=" + cpf + ", formaPgto=" + formaPgto + ", nome=" + nome + ", placaVeiculo="
				+ placaVeiculo + ", pontuacaoAcumulada=" + pontuacaoAcumulada + ", qtdadeAvaliacoes=" + qtdadeAvaliacoes
				+ "]";
	}

}
