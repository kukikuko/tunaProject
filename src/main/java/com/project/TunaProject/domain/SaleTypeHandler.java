package com.project.TunaProject.domain;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class SaleTypeHandler implements TypeHandler<SaleType>{

	@Override
	public void setParameter(PreparedStatement ps, int i, SaleType parameter, JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub
		ps.setString(i, parameter.name());
	}

	@Override
	public SaleType getResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		String code = rs.getString(columnName);
		return getSaleType(code);
	}

	@Override
	public SaleType getResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		String code = rs.getString(columnIndex);
		return getSaleType(code);
	}

	@Override
	public SaleType getResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		String code = cs.getString(columnIndex);
		return getSaleType(code);
	}

	private SaleType getSaleType(String code) {
		switch(code) {
		case "Y":
			return SaleType.Y;
		case "S":
			return SaleType.S;
		case "N":
			return SaleType.N;
		}
		return null;
	}
	
}
