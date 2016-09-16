package com.ipartek.formacion.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ipartek.formacion.domain.Product;

@Repository(value = "productDao")
public class JPAProductDao implements ProductDao {

	private EntityManager em = null;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Product> getProductList() {
		return this.em.createQuery("select p from Product p order by p.id").getResultList();
	}

	@Override
	@Transactional(readOnly = false)
	public void saveProduct(Product prod) {
		this.em.merge(prod);
	}

}
