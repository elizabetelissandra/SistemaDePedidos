package br.com.arnia.projetoarnia.api.dto;

import java.util.List;

public class ClimaResponse {
	private String cidade;
	private String estado;
	private String atualizado_em;
	private List<Previsao> clima;

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	public String getAtualizado_em() {
		return atualizado_em;
	}

	public List<Previsao> getClima() {
		return clima;
	}

	public class Previsao {
		private String data;
		private String condicao;
		private double min;
		private double max;
		private Integer indice_uv;
		private String condicao_desc;

		public String getData() {
			return data;
		}

		public String getCondicao() {
			return condicao;
		}

		public double getMin() {
			return min;
		}

		public double getMax() {
			return max;
		}

		public Integer getIndice_uv() {
			return indice_uv;
		}

		public String getCondicao_desc() {
			return condicao_desc;
		}
	}
}