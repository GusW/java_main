package br.unibh;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.unibh.entidades.Aluno;
import br.unibh.entidades.Professor;
import br.unibh.persistencia.AlunoDAO;
import br.unibh.persistencia.ProfessorDAO;

public class Testes {
	
	@Before
	public void preparaBanco(){
		AlunoDAO dao = new AlunoDAO();
		Aluno a1 = new Aluno(null, new Long(1234), "Joao", "3453453", new Date());
		Aluno a2 = new Aluno(null, new Long(1234), "Maria", "646456456", new Date());
		Aluno a3 = new Aluno(null, new Long(1234), "Jose", "123456789", new Date());
		dao.insert(a1);
		dao.insert(a2);
		dao.insert(a3);
		ProfessorDAO daoP = new ProfessorDAO();
		Professor p1 = new Professor(null, "JoaoP", "475432875", new BigDecimal(2345));
		Professor p2 = new Professor(null, "MariaP", "234432875",new BigDecimal(2345));
		Professor p3 = new Professor(null, "JoseP", "234432564",new BigDecimal(2345));
		daoP.insert(p1);
		daoP.insert(p2);
		daoP.insert(p3);
		
		
		
	}

	@After
	public void limpaBanco(){
		AlunoDAO dao = new AlunoDAO();
		dao.clean();
		ProfessorDAO daoP = new ProfessorDAO();
		daoP.clean();
	}
	
	@Test
	public void testeBuscarAluno() {
		AlunoDAO dao = new AlunoDAO();
		Aluno a = dao.find("646456456");
		Assert.assertEquals(a.getNome(), "Maria");
	}
	
	@Test
	public void testeBuscarProfessor() {
		ProfessorDAO dao = new ProfessorDAO();
		Professor a = dao.find("234432875");
		Assert.assertEquals(a.getNome(), "MariaP");
	}
	
	
	@Test
	public void testeInserirAluno(){
		AlunoDAO dao = new AlunoDAO();
		Aluno a = new Aluno (null, 12321L, "Tomaz Gonzaga", "252525244");
		dao.insert(a);
		Aluno a2 = dao.find(4L);
		Assert.assertEquals(a2.getCpf(), "252525244");
	}

	@Test
	public void testeInserirProfessor(){
		ProfessorDAO dao = new ProfessorDAO();
		Professor p = new Professor(null, "Tomaz Gonzaga", "566728298",new BigDecimal(2345));
		dao.insert(p);
		Professor p2 = dao.find(4L);
		Assert.assertEquals(p2.getCpf(), "566728298");
	}

	
	@Test
	public void testeAtualizaAluno(){
		AlunoDAO dao = new AlunoDAO();
		Aluno a = dao.find("646456456");
		a.setNome("Maria da Silva");
		dao.update(a);
		Aluno a2 = dao.find("646456456");
		Assert.assertEquals(a2.getNome(),"Maria da Silva");
	}
	
	@Test
	public void testeAtualizaProfessor(){
		ProfessorDAO dao = new ProfessorDAO();
		Professor p = dao.find("234432875");
		p.setNome("MariaP da SilvaP");
		p.setSalario(new BigDecimal(2345));
		dao.update(p);
		Professor p2 = dao.find("234432875");
		Assert.assertEquals(p2.getNome(),"MariaP da SilvaP");
	}
	
	@Test
	public void testeExcluirAluno(){
		AlunoDAO dao = new AlunoDAO();
		Aluno a = dao.find("646456456");
		dao.delete(a);
		List<Aluno> lista = dao.findAll();
		Assert.assertEquals(lista.size(),2);
	}
	
	@Test
	public void testeExcluirProfessor(){
		ProfessorDAO dao = new ProfessorDAO();
		Professor a = dao.find("234432875");
		dao.delete(a);
		List<Professor> lista = dao.findAll();
		Assert.assertEquals(lista.size(),2);
	}
}
