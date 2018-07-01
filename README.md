# desafio-imusica-rest
## Desafio API Rest de Produtos

### Decisões

Criei um serviço Rest para realização das operações de CRUD de Produtos. Utilizei banco de dados MongoDB já embutido na aplicação através do Spring Boot.

Como executar: 

- Basta executar a classe java "DesafioImusicaRestApplication". 

Automaticamente serão "levantados" o banco MongoDB e o Tom Cat. Ambos embarcados. Não é necessária nenhuma configuração.


## Serviços: 

URL BASE: http://localhost:8080/produtos

### - Listar Todos:

GET: http://localhost:8080/produtos

### - Cadastrar Produto: 

POST: http://localhost:8080/produtos

Exemplo JSON:

```
{
	"nome": "Celular",
	"descricao": "Smartphone 5 polegadas",
	"preco": 423.98,
	"quantidade": 12
}
```

### - Buscar por Nome do Produto: 

GET: http://localhost:8080/produtos?nome=nomeDoProduto

### - Buscar por id do Produto: 

GET: http://localhost:8080/produtos/{id}

### - Atualizar Produto:

PUT: http://localhost:8080/produtos/

```
{
  "id": "5b37fa462184544ac62d8888"
  "version": 0,
	"nome": "Celular",
	"descricao": "Smartphone 5 polegadas",
	"preco": 423.98,
	"quantidade": 12
}
```

### - Deletar Produto:

DELETE: http://localhost:8080/produtos/{id}


