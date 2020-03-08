package br.unibh.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.unibh.entidades.Aluno;

public class AlunoDAO implements DAO<Aluno, Long> {

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public Aluno find(Long id) {
		try {
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(
					"select * from tb_aluno where id = ?");
			p.setLong(1, id);
			ResultSet r = p.executeQuery();
			if (r.next()) {
				return new Aluno(r.getLong("id"), r.getLong("matricula"),
						r.getString("nome"), r.getString("cpf"),
						r.getString("data_aniversario") == null ? null : df
								.parse(r.getString("data_aniversario")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Aluno find(String cpf) {
		try {
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(
					"select * from tb_aluno where cpf = ?");
			p.setString(1, cpf);
			ResultSet r = p.executeQuery();
			if (r.next()) {
				return new Aluno(r.getLong("id"), r.getLong("matricula"),
						r.getString("nome"), r.getString("cpf"),
						r.getString("data_aniversario") == null ? null : df
								.parse(r.getString("data_aniversario")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insert(Aluno t) {
		try {
			PreparedStatement p = JDBCUtil
					.getConnection()
					.prepareStatement(
							"insert into tb_aluno (matricula, nome, cpf, data_aniversario) values (?,?,?,?)");
			p.setLong(1, t.getMatricula());
			p.setString(2, t.getNome());
			p.setString(3, t.getCpf());
			if (t.getDataAniversario() == null) {
				p.setNull(4, Types.NULL);
			} else {
				p.setString(4, df.format(t.getDataAniversario()));
			}
			p.executeUpdate();
			JDBCUtil.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clean() {
		try {
			JDBCUtil.getConnection().prepareStatement("truncate tb_aluno")
					.executeUpdate();
			JDBCUtil.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void update(Aluno t) {
		try {
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(
					"update tb_aluno set matricula = ?, "
							+ "nome = ?, data_aniversario = ? "
							+ "where id = ?");
			p.setLong(1, t.getMatricula());
			p.setString(2, t.getNome());
			if (t.getDataAniversario() == null) {
				p.setNull(3, Types.NULL);
			} else {
				p.setString(3, df.format(t.getDataAniversario()));
			}
			p.setLong(4, t.getId());
			p.executeUpdate();
			JDBCUtil.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delete(Aluno t) {
		try {
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(
					"delete from tb_aluno where id =?");
			p.setLong(1, t.getId());
			p.executeUpdate();
			JDBCUtil.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public List<Aluno> findAll() {
		ArrayList<Aluno> lista = new ArrayList<Aluno>();
		try {
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(
					"select * from tb_aluno");
			ResultSet r = p.executeQuery();
			while (r.next()) {
				lista.add(new Aluno(
						r.getLong("id"), 
						r.getLong("matricula"), 
						r.getString("nome"), 
						r.getString("cpf"), 
						r.getString("data_aniversario") == null ? 
						null : df.parse(r.getString("data_aniversario"))));
			}
			JDBCUtil.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
