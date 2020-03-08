package br.unibh;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import br.unibh.entidades.Aluno;
import br.unibh.entidades.Professor;
import br.unibh.persistencia.JDBCUtil;

public class OlaMundo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			System.out.println(i + 1 + " Olá Mundo");	
		}
		/*int i = 0;
		while (i < 10) {
			System.out.println(i + 1 + " Olá Mundo");
			i++;
		}*/
	
	Aluno a1 = new Aluno(new Long(2), 11316789L, "Solange", "12345678", new Date());
	Aluno a2 = new Aluno(new Long(3), 9813045L, "Solange", "04395485890", new Date());
	
	Professor p1 = new Professor(new Long(5), "Humberto Cruvinel", "982354875", new BigDecimal("9587"));

	Professor p2 = new Professor(new Long(5), "Humberto Cruvinel", "982354875");
	
	System.out.println("CPF ================= " + p1.getCpf());
	System.out.println("BONUS ======== " + Professor.BONUS);
	
	System.out.println(Aluno.verificaMatricula("12345678"));
	
	System.out.println(a1);
	System.out.println(a2);
	System.out.println(p1);
	System.out.println(p2);
	
	try {
		ResultSet res = JDBCUtil.getConnection().prepareStatement("select * from tb_aluno").executeQuery();
		
		while (res.next()){
			System.out.println(res.getLong("id") + "\n" + res.getString("nome"));
		}
		JDBCUtil.closeConnection();

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
}
