package br.unibh.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.unibh.entidades.Aluno;
import br.unibh.entidades.Professor;

public class ProfessorDAO implements DAO<Professor, Long> {

	public Professor findAll(Long id) {
		
		return null;
	}

	public void insert(Professor t) {
		try {
			PreparedStatement p = JDBCUtil
					.getConnection()
					.prepareStatement(
							"insert into tb_professor (nome, cpf, salario) values (?,?,?)");
			p.setString(1, t.getNome());
			p.setString(2, t.getCpf());
			p.setBigDecimal(3, t.getSalario());
			p.executeUpdate();
			JDBCUtil.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Professor t) {
		try {
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(
					"update tb_professor set "
							+ "nome = ?, salario = ? "
							+ "where id = ?");
			p.setString(1, t.getNome());
			p.setBigDecimal(2, t.getSalario());
			p.setLong(3, t.getId());
			p.executeUpdate();
			JDBCUtil.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(Professor t) {
		try {
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(
					"delete from tb_professor where id =?");
			p.setLong(1, t.getId());
			p.executeUpdate();
			JDBCUtil.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Professor> findAll() {
		ArrayList<Professor> lista = new ArrayList<Professor>();
		try {
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(
					"select * from tb_professor");
			ResultSet r = p.executeQuery();
			while (r.next()) {
				lista.add(new Professor(
						r.getLong("id"),
						r.getString("nome"), 
						r.getString("cpf"), 
						r.getBigDecimal("salario")));
			}
			JDBCUtil.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Professor find(Long id) {
		try {
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(
					"select * from tb_professor where id = ?");
			p.setLong(1, id);
			ResultSet r = p.executeQuery();
			if (r.next()) {
				return new Professor(r.getLong("id"), r.getString("nome"),
						r.getString("cpf"), r.getBigDecimal("salario"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Professor find(String cpf) {
		try {
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(
					"select * from tb_professor where cpf= ?");
			p.setString(1, cpf);
			ResultSet r = p.executeQuery();
			if (r.next()) {
				return new Professor(r.getLong("id"), r.getString("nome"),
						r.getString("cpf"), r.getBigDecimal("salario"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void clean() {
		try {
			JDBCUtil.getConnection().prepareStatement("truncate tb_professor")
					.executeUpdate();
			JDBCUtil.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
