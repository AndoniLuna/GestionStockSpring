package com.ipartek.formacion.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ipartek.formacion.domain.Product;

public class SimpleProductManagerTests {
/*
	private SimpleProductManager productManager;

	private List<Product> products;

	private static int PRODUCT_COUNT = 2;

	private static Double CHAIR_PRICE = new Double(20.50);
	private static String CHAIR_DESCRIPTION = "Chair";

	private static String TABLE_DESCRIPTION = "Table";
	private static Double TABLE_PRICE = new Double(150.10);

	private static int POSITIVE_PRICE_INCREASE = 10;
	private static int NEGATIVE_PRICE_INCREASE = -1;
	private static int OVER_PRICE_INCREASE = 51;

	@Before
	public void setUp() throws Exception {
		this.productManager = new SimpleProductManager();
		this.products = new ArrayList<Product>();

		// stub up a list of products
		Product product = new Product();
		product.setDescription(CHAIR_DESCRIPTION);
		product.setPrice(CHAIR_PRICE);
		this.products.add(product);

		product = new Product();
		product.setDescription(TABLE_DESCRIPTION);
		product.setPrice(TABLE_PRICE);
		this.products.add(product);

		this.productManager.setProducts(this.products);

	}

	@Test
	public void testGetProductsWithNoProducts() {
		this.productManager = new SimpleProductManager();
		assertNull(this.productManager.getProducts());
	}

	@Test
	public void testGetProducts() {
		final List<Product> products = this.productManager.getProducts();
		assertNotNull(products);
		assertEquals(PRODUCT_COUNT, this.productManager.getProducts().size());

		Product product = products.get(0);
		assertEquals(CHAIR_DESCRIPTION, product.getDescription());
		assertEquals(CHAIR_PRICE, product.getPrice());

		product = products.get(1);
		assertEquals(TABLE_DESCRIPTION, product.getDescription());
		assertEquals(TABLE_PRICE, product.getPrice());
	}

	@Test
	public void testIncreasePriceWithNullListOfProducts() {
		try {
			this.productManager = new SimpleProductManager();
			this.productManager.increasePrice(POSITIVE_PRICE_INCREASE);
		} catch (final NullPointerException ex) {
			fail("Products list is null.");
		}
	}

	@Test
	public void testIncreasePriceWithEmptyListOfProducts() {
		try {
			this.productManager = new SimpleProductManager();
			this.productManager.setProducts(new ArrayList<Product>());
			this.productManager.increasePrice(POSITIVE_PRICE_INCREASE);
		} catch (final Exception ex) {
			fail("Products list is empty.");
		}
	}

	@Test
	public void testIncreasePriceWithPositivePercentage() {
		this.productManager.increasePrice(POSITIVE_PRICE_INCREASE);
		final double expectedChairPriceWithIncrease = 22.55;
		final double expectedTablePriceWithIncrease = 165.11;

		final List<Product> products = this.productManager.getProducts();
		Product product = products.get(0);
		assertEquals(expectedChairPriceWithIncrease, product.getPrice(), 0);

		product = products.get(1);
		assertEquals(expectedTablePriceWithIncrease, product.getPrice(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIncreasePriceWithIlegalArgumentNegative() {
		this.productManager.increasePrice(NEGATIVE_PRICE_INCREASE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIncreasePriceWithIlegalArgumentOver() {
		this.productManager.increasePrice(OVER_PRICE_INCREASE);
	}
*/
}
