package br.com.arnia.projetoarnia.sistema.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import br.com.arnia.projetoarnia.faturamento.bean.Pedido;
import br.com.arnia.projetoarnia.sistema.enumerator.TipoEndereco;
import br.com.linkcom.neo.bean.annotation.DescriptionProperty;
import br.com.linkcom.neo.bean.annotation.DisplayName;
import br.com.linkcom.neo.types.Cep;

@Entity
@SequenceGenerator(name = "sq_endereco", sequenceName = "sq_endereco")
public class Endereco {

	private Integer cdendereco;
	private String logradouro;
	private Integer numero;
	private String cep;
	private TipoEndereco tipoendereco;
	private Municipio municipio;
	private Cliente cliente;
	private Uf uf;
	private List<Pedido> pedidos;


	@Id
	@GeneratedValue(generator = "sq_endereco")
	@OneToMany(mappedBy = "cdpedido")
	public Integer getCdendereco() {
		return cdendereco;
	}

	public void setCdendereco(Integer cdendereco) {
		this.cdendereco = cdendereco;
	}
	
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public TipoEndereco getTipoendereco() {
		return tipoendereco;
	}

	public void setTipoendereco(TipoEndereco tipoendereco) {
		this.tipoendereco = tipoendereco;
	}

	@ManyToOne
	@JoinColumn(name = "cdmunicipio")
	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	@ManyToOne
	@JoinColumn(name = "cdcliente")
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@ManyToOne
	@JoinColumn(name = "cduf")
	@DisplayName("UF")
	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	@OneToMany(mappedBy = "cdendereco")
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	

	@Transient
	@DescriptionProperty
	public String getDescricao() {
        String m = municipio != null ? (municipio.getNome() != null ? municipio.getNome() : "") : "";
        String u = uf != null ? (uf.getSigla() != null ? uf.getSigla() : "") : "";
        return (logradouro != null ? logradouro : "")
             + (numero != null ? ", " + numero : "")
             + (!m.isEmpty() ? " - " + m : "")
             + (!u.isEmpty() ? "/" + u : "")
             + (cep != null ? " - CEP: " + cep : "");
    }

	
}
