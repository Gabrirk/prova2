package org.senai.ecommerce.service;

import org.hibernate.engine.jdbc.internal.DDLFormatterImpl;
import org.senai.ecommerce.entity.Produto;
import org.senai.ecommerce.entity.dto.ProdutoDTO;
import org.senai.ecommerce.repository.ProdutoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService{

    private final ProdutoRepo repository;

    public ProdutoService(ProdutoRepo repository) {
        this.repository = repository;
    }

    List<ProdutoDTO> produtos;

    public List<ProdutoDTO> getProdutos() {
        List<Produto> produtos = repository.findAll();
        return produtos.stream().map(dto -> {
            ProdutoDTO produto = new ProdutoDTO();
            produto.setCodigo(dto.getCodigo());
            produto.setPreco(dto.getPreco());
            produto.setEstoque(dto.getEstoque());
            produto.setNome(dto.getNome());
            produto.setDescricao(dto.getDescricao());
            return produto;
        }).collect(Collectors.toList());
    }

    public void criarProduto(ProdutoDTO dto) {
            Produto produto = new Produto();
            produto.setCodigo(dto.getCodigo());
            produto.setPreco(dto.getPreco());
            produto.setEstoque(dto.getEstoque());
            produto.setNome(dto.getNome());
            produto.setDescricao(dto.getDescricao());

            repository.save(produto);
    }


    public ProdutoDTO getProdutoPorCodigo(Long codigo) {
        return produtos.stream().filter(produto -> produto.getCodigo() == codigo).findFirst().get();
    }

    public ProdutoDTO atualizarProduto(Long codigo, ProdutoDTO produtoAlteracao) {
        produtos.forEach(produto -> {
            if (produto.getCodigo() == codigo) {
                produto.setNome(produtoAlteracao.getNome());
                produto.setDescricao(produtoAlteracao.getDescricao());
                produto.setPreco(produtoAlteracao.getPreco());
                produto.setEstoque(produtoAlteracao.getEstoque());
            }
        });
        return produtoAlteracao;
    }

    public void excluirProduto(Long codigo) {
        produtos.removeIf(produto -> produto.getCodigo() == codigo);
    }
}


