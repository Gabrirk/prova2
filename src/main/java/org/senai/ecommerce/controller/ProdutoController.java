package org.senai.ecommerce.controller;
import org.senai.ecommerce.entity.Produto;
import org.senai.ecommerce.entity.dto.ProdutoDTO;
import org.senai.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> getProdutos() {
        return new ResponseEntity<>(produtoService.getProdutos(), HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> getProdutoPorCodigo(@PathVariable Long codigo) {
        return new ResponseEntity<>(produtoService.getProdutoPorCodigo(codigo), HttpStatus.OK);
    }

    @PostMapping
    public void criarProduto(@RequestBody ProdutoDTO produto) {
        produtoService.criarProduto(produto);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long codigo, @RequestBody ProdutoDTO productDetails) {
        return new ResponseEntity<>(produtoService.atualizarProduto(codigo, productDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long codigo) {
        produtoService.excluirProduto(codigo);
        return new ResponseEntity<>("Produto Excluído do Sucesso!", HttpStatus.OK);
    }
}
