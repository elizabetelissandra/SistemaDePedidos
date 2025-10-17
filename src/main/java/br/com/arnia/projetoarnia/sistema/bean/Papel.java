package br.com.arnia.projetoarnia.sistema.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import br.com.linkcom.neo.authorization.Role;

@Entity
@SequenceGenerator(name = "sq_role", sequenceName = "sq_role")
public class Papel implements Role {

    private Integer cdrole;
    private String nome;

    @Id
    @GeneratedValue(generator = "sq_role")
    public Integer getCdrole() {
        return cdrole;
    }

    public void setCdrole(Integer cdrole) {
        this.cdrole = cdrole;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	@Override
	@Transient
    public String getName() {
        return nome;
    }

    @Override
    @Transient
    public String getDescription() {
        return "Papel do sistema: " + nome;
    }

    @Override
    @Transient
    public Boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(nome);
    }
}
