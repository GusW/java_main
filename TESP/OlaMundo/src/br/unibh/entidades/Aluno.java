package br.unibh.entidades;

import java.util.Date;

public class Aluno extends Pessoa {
	
	private Long matricula;
	private Date dataAniversario;
	
	public Aluno(Long id, Long matricula, String nome, String cpf, Date dataAniversario) {
		super(id, nome, cpf);
		this.matricula = matricula;
		this.dataAniversario = dataAniversario;
	}
	
	public Aluno(Long id, Long matricula, String nome, String cpf) {
		super(id, nome, cpf);
		this.matricula = matricula;

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
	

}
