package br.com.imusica.desafio.rest;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.imusica.desafio.model.Produto;
import br.com.imusica.desafio.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {


	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static final Logger logger = LoggerFactory.getLogger(ProdutoResource.class);

	@PostMapping
	public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto) {
		logger.info(
				"inserindo: {} .",
				produto);
		
		if(campoInvalido(produto)) {
			logger.info(
					"campos invalidos: {} .",
					produto);
			return ResponseEntity.badRequest().build();
		}
		
		final Produto produtoAdicionado = 
					produtoRepository
						.save(produto);
		
		if(produtoAdicionado != null) {

			logger.info(
					"{} inserido com sucesso.",
					produtoAdicionado);
			
			final URI location = 
						ServletUriComponentsBuilder
							.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(
								produtoAdicionado.getId()
							)
							.toUri();
			
			return ResponseEntity
					.created(location)
					.build();
		} else {
			logger.info(
				"{} não inserido com sucesso.",
				produtoAdicionado
			);
			return ResponseEntity
					.noContent()
					.build();
		}
	}
	
	/**
	 * Verifica se um dos campos do produto 
	 * não foi preenchido corretamente.
	 * @param produto
	 * @return
	 */
	private boolean campoInvalido(Produto produto) {
		return 
			StringUtils.isEmpty(produto.getNome())
				||
			StringUtils.isEmpty(produto.getDescricao())
				||
			Objects.isNull(produto.getPreco())
				||
			Objects.isNull(produto.getQuantidade());	
	}

	private ResponseEntity<List<Produto>> listAll() {
		
		logger.info("Listando todos produtos.");
		
		final List<Produto> produtos = 
				produtoRepository.findAll();

		logger.info(
				"resultado consulta de todos produtos: {} .",
				produtos);
		

		if(CollectionUtils.isEmpty(produtos)) {
			return ResponseEntity
					.noContent()
					.build();
		} else {
			return ResponseEntity
					.ok(produtos);
		}
	}
	

	@GetMapping
	public ResponseEntity<List<Produto>> find(@RequestParam(value="nome", required=false) String nome) {
		
		if(StringUtils.isEmpty(nome)) {
			// busca todos
			return listAll();
		}
		
		logger.info(
				"Buscando por nome:{} .",
				nome);
		
		// Search only by name:
		final Produto produto = 
				produtoRepository.findByNome(nome);

		logger.info(
				"{} retornado.",
				produto);
		
		if(Objects.isNull(produto)) {
			return ResponseEntity
					.notFound()
					.build();
		} else {
			
			return ResponseEntity
					.ok(
						Collections
						.singletonList(produto)
					);
		}
	}


	@GetMapping("/{id}")
	public ResponseEntity<Produto> findByid(@PathVariable String id) {
		logger.info(
				"Buscando por id:{} .",
				id);
		
		if(StringUtils.isEmpty(id)) {
			logger.info(
				"id invalid: {} .",
				id
			);
			return ResponseEntity
					.badRequest()
					.build();
		}
		
		final Produto produto = 
				produtoRepository
					.findById(id);
		
		logger.info(
				"{} retornado.",
				produto);

		if(Objects.isNull(produto)) {
			return 
				ResponseEntity
					.notFound()
						.build();
		} else {
			
			return 
				ResponseEntity
					.ok(produto);
		}
		
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> delete(@PathVariable String id) {
		
		if(StringUtils.isEmpty(id)) {
			logger.info(
				"id invalid: {} .",
				id
			);
			return ResponseEntity
					.badRequest()
					.build();
		}
		
		logger.info(
			"deletando produto de id {} .",
			id
		);
		
		final Produto produto = 
				produtoRepository
					.findById(id);

		if(Objects.isNull(produto)) {
			logger.error(
				"Erro ao deletar. Produto de id {} não encontrado.", 
				id
			);
			return 
				ResponseEntity
					.notFound()
					.build();
		}
		
		produtoRepository.deleteById(id);
		
		logger.info("{} deletado.", produto);
		
		return 
			ResponseEntity
				.noContent()
					.build();
	}

}
