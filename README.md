# Sistema de Gerenciamento de Pedidos

![Java](https://img.shields.io/badge/Java-6-blue)
![Framework](https://img.shields.io/badge/Framework-Neo-lightgrey)
![Persistência](https://img.shields.io/badge/Persistência-Hibernate-orange)
![Servidor](https://img.shields.io/badge/Servidor-JBoss-red)

Um sistema Web completo para gestão de pedidos de venda, construído com Java e Neo Framework, focado em uma interface de usuário dinâmica e uma arquitetura robusta de backend.

---

## 📋 Sobre o Projeto

Este projeto consiste em uma aplicação Web para o gerenciamento completo do ciclo de vida de pedidos de venda. Ele foi desenvolvido como parte de um programa de formação, utilizando uma stack de tecnologias legadas (Java 6 e JBoss) e o framework proprietário Neo, o que trouxe desafios técnicos únicos e soluções específicas.

A aplicação permite o CRUD (Criação, Leitura, Atualização e Exclusão) de pedidos, gerenciando entidades como Clientes, Produtos, Vendedores e Formas de Pagamento, e inclui um módulo para a geração de relatórios dinâmicos em PDF.

## ✨ Funcionalidades Principais

A aplicação é rica em funcionalidades, com grande foco na interatividade e na experiência do usuário.

* **CRUD Completo de Pedidos:**
    * **Listagem com Filtros Avançados:** Tela de listagem (`pedidoListagem.jsp`) que permite filtrar pedidos por Cliente, Vendedor, Período de Datas e, notavelmente, por **Nome de Produto** contido no pedido.
    * **Tela de Entrada Dinâmica:** Uma única tela (`pedidoEntrada.jsp`) para criação e edição de pedidos, com componentes que se adaptam ao contexto.

* **Interface Rica e Assíncrona (AJAX):**
    * **Carregamento em Cascata:** Ao selecionar um Cliente, a lista de Endereços é carregada via AJAX.
    * **Integração com API Externa:** Ao selecionar um Endereço, o sistema busca a previsão do tempo em tempo real na BrasilAPI, utilizando o CEP.
    * **Busca de Dados Sob Demanda:** As listas de Produtos e Formas de Pagamento são carregadas via AJAX apenas quando o usuário interage com os campos, otimizando o carregamento inicial da página.

* **Lógica de Negócio no Frontend:**
    * **Cálculos em Tempo Real:** O total de cada item e o total geral do pedido são calculados instantaneamente via JavaScript (jQuery) conforme o usuário altera preços, quantidades ou descontos.
    * **Geração Dinâmica de Parcelas:** Uma função JavaScript calcula e gera as linhas de parcela com base no valor total, na forma de pagamento e na data do pedido.
    * **Interface Condicional:** O campo "Produto" na grade de itens se comporta de forma inteligente: para itens novos, é um combo que busca os produtos via AJAX; para itens já existentes no modo de edição, o campo é desabilitado para garantir a integridade dos dados.

* **Módulo de Relatórios:**
    * Geração de relatórios de "Produtos Vendidos" em PDF, utilizando iReport/JasperReports.
    * Tela de filtro customizada para os relatórios, permitindo a seleção de um período e de um cliente específico.

## 🛠️ Tecnologias Utilizadas

Este projeto foi construído com as seguintes tecnologias e padrões:

* **Backend:**
    * **Linguagem:** `Java 6`
    * **Framework Principal:** `Neo Framework` (proprietário)
    * **Persistência:** `Hibernate` (como provedor JPA)
    * **Injeção de Dependência:** `Spring Framework` (utilizando injeção via `setter`)
    * **Servidor de Aplicação:** `JBoss 4.0.5.GA`
* **Frontend:**
    * **View:** `JSP (JavaServer Pages)` com Taglibs (JSTL e tags customizadas do Neo)
    * **Scripting:** `JavaScript` e `jQuery`
* **Relatórios:**
    * **Design:** `iReport 2.0.2`
    * **Motor:** `JasperReports`
* **Build & Dependências:** `Maven`

## 🏗️ Arquitetura e Padrões de Projeto

O projeto segue estritamente o padrão de arquitetura em três camadas (Controller-Service-DAO), promovido pelo Neo Framework, garantindo uma clara separação de responsabilidades.

* **Controller (`PedidoCrud.java`):** Responsável por gerenciar o ciclo de vida da requisição web, utilizando os "ganchos" (`hooks`) do `CrudController` do Neo, para orquestrar as ações da tela.
* **Service (`PedidoService.java`):** Contém a lógica de negócio principal e os métodos que orquestram a geração de relatórios.
* **DAO (`PedidoDAO.java`):** Única camada com acesso direto ao banco de dados. Utiliza o `QueryBuilder` do Neo para a construção de consultas de forma segura e programática, abstraindo o HQL.

## ⚙️ Como Executar o Projeto

1.  **Pré-requisitos:**
    * JDK 1.6
    * Servidor de Aplicação JBoss 4.0.5.GA configurado no Eclipse
    * Maven 3.2.5+
    * iReport 2.0.2 para compilação dos relatórios `.jrxml`
2.  **Passos:**
    1.  Importe o projeto no Eclipse como um "Existing Maven Project".
    2.  Garanta que os arquivos `.jrxml` foram compilados para `.jasper` e estão nas pastas corretas (`webapp/WEB-INF/relatorio/...`).
    3.  Execute o projeto no servidor JBoss configurado (`Run As -> Run on Server`).

---
