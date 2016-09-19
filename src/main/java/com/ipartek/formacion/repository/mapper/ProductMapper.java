package com.ipartek.formacion.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.domain.Product;

public class ProductMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

		Product p = new Product();
		p.setId(rs.getLong("id"));
		p.setDescription(rs.getString("description"));
		p.setPrice(rs.getDouble("price"));
		return p;
	}

}
