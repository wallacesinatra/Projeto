package br.com.wallace.projetocast.request;

public class CursoPostRequest {

	private String descricao;
	private String dataIni;
	private String dataTer;
	private String quantidade;
	private Integer categoria;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataIni() {
		return dataIni;
	}

	public void setDataIni(String dataIni) {
		this.dataIni = dataIni;
	}

	public String getDataTer() {
		return dataTer;
	}

	public void setDataTer(String dataTer) {
		this.dataTer = dataTer;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	

}
