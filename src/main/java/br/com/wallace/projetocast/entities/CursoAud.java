package br.com.wallace.projetocast.entities;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CursoAud {

	@Column(name = "data_inclusao")
	@CreatedDate
	private Instant dataInclusao = Instant.now();

	@Column(name = "data_atualizaçao")
	@LastModifiedDate
	private Instant dataAtualiza = Instant.now();

	@Column(name = "usuario_anterior")
	@LastModifiedBy
	private String usuarioAnt;

	@Column(name = "usuario_atual")
	@CreatedBy
	private String usuarioAtu;

	public Instant getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Instant dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Instant getDataAtualiza() {
		return dataAtualiza;
	}

	public void setDataAtualiza(Instant dataAtualiza) {
		this.dataAtualiza = dataAtualiza;
	}

	public String getUsuarioAnt() {
		return usuarioAnt;
	}

	public void setUsuarioAnt(String usuarioAnt) {
		this.usuarioAnt = usuarioAnt;
	}

	public String getUsuarioAtu() {
		return usuarioAtu;
	}

	public void setUsuarioAtu(String usuarioAtu) {
		this.usuarioAtu = usuarioAtu;
	}

}
