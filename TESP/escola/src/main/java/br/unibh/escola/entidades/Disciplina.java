package br.unibh.escola.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "TB_DISCIPLINA")
@NamedQueries({ @NamedQuery(name = "Disciplina.findByNomeECurso", query = "select a from Disciplina a where a.nome like :nome and a.curso like :curso") })
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	@Size(max = 100)
	private String nome;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	@Size(max = 100)
	private String curso;

	@NotNull
	@Column(nullable = false)
	@Max(160)
	private int cargaHoraria;

	@NotNull
	@Column(nullable = false)
	private int tipo;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	@Size(max = 4000)
	private String ementa;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	@Size(max = 4000)
	private String bibliografia;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	@Size(max = 4000)
	private String distribuicaoAvaliacao;

	@Size(max = 4000)
	private String observacao;

	@NotNull
	@ManyToOne
	private Sala sala;

	@NotNull
	@ManyToOne
	private Professor professor;

	@NotNull
	@ManyToMany
	@JoinTable(name = "TB_DISCIPLINA_ALUNO", joinColumns = { @JoinColumn(name = "DISCIPLINA_ID") }, inverseJoinColumns = { @JoinColumn(name = "ALUNO_ID") })
	private List<Aluno> alunos;

	private int versao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getEmenta() {
		return ementa;
	}

	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}

	public String getBibliografia() {
		return bibliografia;
	}

	public void setBibliografia(String bibliografia) {
		this.bibliografia = bibliografia;
	}

	public String getDistribuicaoAvaliacao() {
		return distribuicaoAvaliacao;
	}

	public void setDistribuicaoAvaliacao(String distribuicaoAvaliacao) {
		this.distribuicaoAvaliacao = distribuicaoAvaliacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public int getVersao() {
		return versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}

	public Disciplina(Long id, String nome, String curso, int cargaHoraria,
			int tipo, String ementa, String bibliografia,
			String distribuicaoAvaliacao, String observacao, Sala sala,
			Professor professor, List<Aluno> alunos, int versao) {
		super();
		this.id = id;
		this.nome = nome;
		this.curso = curso;
		this.cargaHoraria = cargaHoraria;
		this.tipo = tipo;
		this.ementa = ementa;
		this.bibliografia = bibliografia;
		this.distribuicaoAvaliacao = distribuicaoAvaliacao;
		this.observacao = observacao;
		this.sala = sala;
		this.professor = professor;
		this.alunos = alunos;
		this.versao = versao;
	}

	public Disciplina() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bibliografia == null) ? 0 : bibliografia.hashCode());
		result = prime * result + cargaHoraria;
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime
				* result
				+ ((distribuicaoAvaliacao == null) ? 0 : distribuicaoAvaliacao
						.hashCode());
		result = prime * result + ((ementa == null) ? 0 : ementa.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + tipo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		if (bibliografia == null) {
			if (other.bibliografia != null)
				return false;
		} else if (!bibliografia.equals(other.bibliografia))
			return false;
		if (cargaHoraria != other.cargaHoraria)
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (distribuicaoAvaliacao == null) {
			if (other.distribuicaoAvaliacao != null)
				return false;
		} else if (!distribuicaoAvaliacao.equals(other.distribuicaoAvaliacao))
			return false;
		if (ementa == null) {
			if (other.ementa != null)
				return false;
		} else if (!ementa.equals(other.ementa))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

	public String toNome() {
		return professor.getNome();
	}
}
