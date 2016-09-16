package com.ipartek.formacion.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ipartek.formacion.domain.Product;

public class JPAProductDaoTest {

	private ApplicationContext context;
	private ProductDao productDao;

	@Before
	public void setUp() throws Exception {
		this.context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
		this.productDao = (ProductDao) this.context.getBean("productDao");
	}

	@Test
	public void testGetProductList() {
		List<Product> products = this.productDao.getProductList();
		assertEquals(products.size(), 3, 0);
	}

	@Test
	public void testSaveProduct() {
		List<Product> products = this.productDao.getProductList();

		Product p = products.get(0);
		Double price = p.getPrice();
		p.setPrice(200.12);
		this.productDao.saveProduct(p);

		List<Product> updatedProducts = this.productDao.getProductList();
		Product p2 = updatedProducts.get(0);
		assertEquals(p2.getPrice(), 200.12, 0);

		p2.setPrice(price);
		this.productDao.saveProduct(p2);
	}

}
