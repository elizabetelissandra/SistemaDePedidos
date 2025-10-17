package br.com.arnia.projetoarnia.report.filter;

import java.util.Date;
import br.com.arnia.projetoarnia.sistema.bean.Cliente;

public class ProdutoVendidoFiltro {

    private Date dataInicial;
    private Date dataFinal;
    private Cliente cliente;

    // Getters e Setters
    public Date getDataInicial() {
        return dataInicial;
    }
    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }
    public Date getDataFinal() {
        return dataFinal;
    }
    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}