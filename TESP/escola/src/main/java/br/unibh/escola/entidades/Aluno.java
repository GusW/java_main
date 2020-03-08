package br.unibh.escola.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;


@Entity @PrimaryKeyJoinColumn
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "matricula"), name = "TB_ALUNO")
@NamedQueries({ @NamedQuery(name="Aluno.findByName", query = "select a from Aluno a where a.nome like :nome")
})
public class Aluno extends Pessoa {
	
	@Column(unique=true, nullable=false)
	private Long matricula;
	
	@NotNull
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataAniversario;
	
	@ManyToMany(fetch=FetchType.EAGER, mappedBy="alunos")
	private List<Disciplina> disciplinas;
	
	public Aluno() {
	}
	
	public Aluno(Long id, Long matricula, String nome, String cpf, Date dataAniversario) {
		super(id, nome, cpf);
		this.matricula = matricula;
		this.dataAniversario = dataAniversario;
	}
	
	public Aluno(Long id, Long matricula, String nome, String cpf) {
		super(id, nome, cpf);
		this.matricula = matricula;

	}
		
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Aluno(Long id, Long matricula, String nome) {
		super(id, nome, null);
		this.matricula = matricula;
	}
	
	public Long getMatricula() {
		return matricula;
	}
	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public Date getDataAniversario() {
		return dataAniversario;
	}
	public void setDataAniversario(Date dataAniversario) {
		this.dataAniversario = dataAniversario;
	}

	@Override
	public String toString() {
		return "Aluno [dataAniversario=" + dataAniversario + ", matricula="
				+ matricula + "]"
				+ super.toString();
	}


	public static Boolean verificaMatricula(String matricula){
		if (matricula == null){
			return false;
		} else if (matricula.trim().equals("")) {
			return false;
		} else if (matricula.length() != 8){
			return false;
		} else {
			return true;
		}
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dataAniversario == null) ? 0 : dataAniversario.hashCode());
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (dataAniversario == null) {
			if (other.dataAniversario != null)
				return false;
		} else if (!dataAniversario.equals(other.dataAniversario))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}


}
