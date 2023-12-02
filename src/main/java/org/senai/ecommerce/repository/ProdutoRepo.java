package org.senai.ecommerce.repository;

import org.senai.ecommerce.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProdutoRepo extends JpaRepository<Produto, Long> {
}
