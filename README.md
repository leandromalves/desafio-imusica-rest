# desafio-imusica-rest
# Desafio Api Produtos

### API REST de PRODUTOS

Como executar: 

- Basta executar a classe java "DesafioImusicaRestApplication". 

Automaticamente serão "levantados" o banco MongoDB e o Tom Cat. Ambos embarcados. Não é necessária nenhuma configuração.

URL BASE: http://localhost:8080/produtos

## Serviços: 

### - Listar Todos:

GET: http://localhost:8080/produtos

### - Cadastrar Produto: 

POST: http://localhost:8080/produtos

Exemplo JSON:

{
	"nome": "Celular",
	"descricao": "Smartphone 5 polegadas",
	"preco": 423.98,
	"quantidade": 12
}

### - Buscar por Nome do Produto: 

GET: http://localhost:8080/produtos?nome=nomeDoProduto

{
  "id": "5b37fa462184544ac62d8888"
  "version": 0,
	"nome": "Celular",
	"descricao": "Smartphone 5 polegadas",
	"preco": 423.98,
	"quantidade": 12
}

### - Buscar por id do Produto: 

GET: http://localhost:8080/produtos/{id}

### - Atualizar Produto:

PUT: http://localhost:8080/produtos/

### - Deletar Produto:

DELETE: - Buscar por Nome do Produto: 

GET: http://localhost:8080/produtos/{id}


