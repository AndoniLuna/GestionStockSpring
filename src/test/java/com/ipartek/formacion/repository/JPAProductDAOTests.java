package com.ipartek.formacion.repository;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ipartek.formacion.domain.Product;

public class JPAProductDAOTests {
	
	private ApplicationContext context;
    private ProductDAO productDAO;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        productDAO = (ProductDAO) context.getBean("productDAO");
    }

    @Test
    public void testGetProductList() {
        List<Product> products = productDAO.getProductList();
        assertEquals(products.size(), 3, 0);	   
    }

    @Test
    public void testSaveProduct() {
        List<Product> products = productDAO.getProductList();

        Product p = products.get(0);
        Double price = p.getPrice();
        p.setPrice(200.12);
        productDAO.saveProduct(p);

        List<Product> updatedProducts = productDAO.getProductList();
        Product p2 = updatedProducts.get(0);
        assertEquals(p2.getPrice(), 200.12, 0);

        p2.setPrice(price);
        productDAO.saveProduct(p2);
    }
}
