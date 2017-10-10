package org.apache.ibatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 替换源码中的文件,多加一层非空校验
 * @ClassName: CharacterTypeHandler 
 * @Description: TODO
 * @author: fuhao
 * @date: Jul 9, 2017 5:04:25 PM
 */
public class CharacterTypeHandler extends BaseTypeHandler<Character> {

	  @Override
	  public void setNonNullParameter(PreparedStatement ps, int i, Character parameter, JdbcType jdbcType) throws SQLException {
	    ps.setString(i, parameter.toString());
	  }

	  @Override
	  public Character getNullableResult(ResultSet rs, String columnName) throws SQLException {
	    String columnValue = rs.getString(columnName);
	    if (columnValue != null && columnValue.length()>0) {
	      return columnValue.charAt(0);
	    } else {
	      return null;
	    }
	  }

	  @Override
	  public Character getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
	    String columnValue = rs.getString(columnIndex);
	    if (columnValue != null && columnValue.length()>0) {
	      return columnValue.charAt(0);
	    } else {
	      return null;
	    }
	  }

	  @Override
	  public Character getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
	    String columnValue = cs.getString(columnIndex);
	    if (columnValue != null) {
	      return columnValue.charAt(0);
	    } else {
	      return null;
	    }
	  }
	}
