# Sistema de Gerenciamento de Estoque

## Descrição

O Sistema de Gerenciamento de Estoque é uma aplicação Java Swing que permite a gestão de estoques, setores e produtos. Com ele, é possível adicionar, remover e alterar estoques, setores e produtos de forma intuitiva através de uma interface gráfica.

## Funcionalidades

- **Gerenciamento de Estoques**:
  - Adicionar novos estoques.
  - Remover estoques existentes.
  
- **Gerenciamento de Setores**:
  - Adicionar novos setores a um estoque selecionado.
  - Remover setores de um estoque.
  
- **Gerenciamento de Produtos**:
  - Adicionar novos produtos a um setor selecionado.
  - Remover produtos de um setor.
  - Alterar as informações dos produtos (nome, preço e quantidade).

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Swing**: Biblioteca para construção da interface gráfica.
- **IntelliJ IDEA**: IDE para desenvolvimento e gerenciamento do projeto.

## Estrutura do Projeto

- **`src/`**: Contém o código fonte da aplicação.
  - **`Controller/`**: Contém os controladores que gerenciam a lógica de negócios.
  - **`entity/`**: Contém as classes que representam entidades do sistema (Estoque, Setor, Produto).
  - **`EstoqueForm.java`**: Classe principal para a interface gráfica do sistema.
- **`EstoqueForm.form`**: Arquivo de configuração da interface gráfica no IntelliJ IDEA.
- **`README.md`**: Este arquivo de documentação.
