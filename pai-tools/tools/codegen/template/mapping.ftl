<#import "function.ftl" as func>
<#assign class=model.variables.class>
<#assign po=class + "Po">
<#assign tableName=model.tableName>
<#assign sys=model.variables.sys>
<#assign module=model.variables.module>
<#assign type="com." + sys + ".biz" + "." +  module + ".persistence.entity." +po>
<#assign foreignKey=model.foreignKey>
<#assign sub=model.sub>
<#assign colList=model.columnList>
<#assign commonList=model.commonList>
<#assign pk=func.getPk(model) >
<#assign pkVar=func.getPkVar(model) >
<#-- 模板开始  -->
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${type}">
	<resultMap id="${po}" type="${type}">
		<#list colList as col>
		<#assign colName=func.convertUnderLine(col.columnName)>
		<#if (col.isPK) >
		<id property="${colName}" column="${col.columnName}" jdbcType="${func.getJdbcType(col.colDbType)}"/>
		<#else>
		<result property="${colName}" column="${col.columnName}" jdbcType="${func.getJdbcType(col.colDbType)}"/>
		</#if>
		</#list>
	</resultMap>
	
	<insert id="create" parameterType="${type}">
		INSERT INTO ${tableName}
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
		(<#list colList as col>`${col.columnName}`<#if col_has_next>,</#if></#list>)
		VALUES 
		(<#list colList as col><#assign colName=func.convertUnderLine(col.columnName)><#noparse>#{</#noparse>${colName},jdbcType=${func.getJdbcType(col.colDbType)}<#noparse>}</#noparse><#if col_has_next>, </#if></#list>)
	</insert>
	
	<select id="get"   parameterType="java.lang.String" resultMap="${po}">
		SELECT * FROM ${tableName} 
		WHERE 
		`${pk}`=<#noparse>#{</#noparse>${func.convertUnderLine(pk)}}
		LIMIT 1
	</select>
	
	<select id="getLast" resultMap="${po}">
    	select * from ${tableName} where `${pk}` = (select max(`${pk}`) from ${tableName})
    </select>
	
	<select id="countAll" resultType="int">
    	select count(*) _total from ${tableName}
		<where>
			<if test="whereSql!=null">
				<#noparse>${</#noparse>whereSql}
			</if>
		</where>    	
    </select>	
	
	<select id="find" parameterType="java.util.Map" resultMap="${po}">
		SELECT * FROM ${tableName}
		<where>
			<if test="whereSql!=null">
				<#noparse>${</#noparse>whereSql}
			</if>
		</where>
		<if test="orderBySql!=null">
			ORDER BY <#noparse>${</#noparse>orderBySql}
		</if>
		<if test="orderBySql==null">
			ORDER BY `${pk}` DESC
		</if>
	</select>
	
	<select id="findByIds"   resultMap="${po}">
		SELECT * FROM ${tableName}
			WHERE `${pk}` in 
			<foreach item="id" index="index" collection="ids" open="(" separator="," close=")">  
  				<#noparse>#{id}  </#noparse>
 			</foreach>  		
			ORDER BY `${pk}` 				
	</select>	
	
	<update id="update" parameterType="${type}">
		UPDATE ${tableName} SET
		<#list commonList as col>
		<#assign colName=func.convertUnderLine(col.columnName)>
		`${col.columnName}`=<#noparse>#{</#noparse>${colName},jdbcType=${func.getJdbcType(col.colDbType)}<#noparse>}</#noparse><#if col_has_next>,</#if>
		</#list>
		WHERE
		`${pk}`=<#noparse>#{</#noparse>${func.convertUnderLine(pk)}}
	</update>
	
	<delete id="delete" parameterType="java.lang.String">
		DELETE FROM ${tableName} 
		WHERE
		`${pk}`=<#noparse>#{</#noparse>${func.convertUnderLine(pk)}}
	</delete>
	
	<#if sub?exists && sub==true>
	<#assign foreignKeyVar=func.convertUnderLine(foreignKey)>
	<delete id="delByMainId">
	    DELETE FROM ${tableName}
	    WHERE
	    ${foreignKey}=<#noparse>#{</#noparse>${foreignKeyVar}}
	</delete>    
	
	<select id="get${class}List" resultMap="${po}">
	    SELECT *
	    FROM ${tableName} 
	    WHERE ${foreignKey}=<#noparse>#{</#noparse>${foreignKeyVar}}
	</select>
	</#if>
	
	<update id="updateByExampleSelective" parameterType="java.util.Map">
		UPDATE ${tableName} 
		<set>
			<#list commonList as col>
			<#assign colName=func.convertUnderLine(col.columnName)>
			<if test="entity.${colName}!=null">
				`${col.columnName}`=<#noparse>#{</#noparse>${colName},jdbcType=${func.getJdbcType(col.colDbType)}<#noparse>}</#noparse><#if col_has_next>,</#if>
=======
		(<#list colList as col>${col.columnName}<#if col_has_next>,</#if></#list>)
		VALUES 
		(<#list colList as col><#assign colName=func.convertUnderLine(col.columnName)><#noparse>#{</#noparse>${colName},jdbcType=${func.getJdbcType(col.colDbType)}<#noparse>}</#noparse><#if col_has_next>, </#if></#list>)
	</insert>
	
	<select id="get"   parameterType="java.lang.String" resultMap="${po}">
		SELECT * FROM ${tableName} 
		WHERE 
		${pk}=<#noparse>#{</#noparse>${func.convertUnderLine(pk)}}
		LIMIT 1
	</select>
	
	<select id="getLast" resultMap="${po}">
    	select * from ${tableName} where ${pk} = (select max(${pk}) from ${tableName})
    </select>
	
	<select id="countAll" resultType="int">
    	select count(*) _total from ${tableName}
		<where>
			<if test="whereSql!=null">
				<#noparse>${</#noparse>whereSql}
			</if>
		</where>    	
    </select>	
	
	<select id="find" parameterType="java.util.Map" resultMap="${po}">
		SELECT * FROM ${tableName}
		<where>
			<if test="whereSql!=null">
				<#noparse>${</#noparse>whereSql}
			</if>
		</where>
		<if test="orderBySql!=null">
			ORDER BY <#noparse>${</#noparse>orderBySql}
		</if>
		<if test="orderBySql==null">
			ORDER BY ${pk} DESC
		</if>
	</select>
	
	<select id="findByIds"   resultMap="${po}">
		SELECT * FROM ${tableName}
			WHERE ${pk} in 
			<foreach item="id" index="index" collection="ids" open="(" separator="," close=")">  
  				<#noparse>#{id}  </#noparse>
 			</foreach>  		
			ORDER BY ${pk} 				
	</select>	
	
	<update id="update" parameterType="${type}">
		UPDATE ${tableName} SET
		<#list commonList as col>
		<#assign colName=func.convertUnderLine(col.columnName)>
		${col.columnName}=<#noparse>#{</#noparse>${colName},jdbcType=${func.getJdbcType(col.colDbType)}<#noparse>}</#noparse><#if col_has_next>,</#if>
		</#list>
		WHERE
		${pk}=<#noparse>#{</#noparse>${func.convertUnderLine(pk)}}
	</update>
	
	<delete id="delete" parameterType="java.lang.String">
		DELETE FROM ${tableName} 
		WHERE
		${pk}=<#noparse>#{</#noparse>${func.convertUnderLine(pk)}}
	</delete>
	
	<#if sub?exists && sub==true>
	<#assign foreignKeyVar=func.convertUnderLine(foreignKey)>
	<delete id="delByMainId">
	    DELETE FROM ${tableName}
	    WHERE
	    ${foreignKey}=<#noparse>#{</#noparse>${foreignKeyVar}}
	</delete>    
	
	<select id="get${class}List" resultMap="${po}">
	    SELECT *
	    FROM ${tableName} 
	    WHERE ${foreignKey}=<#noparse>#{</#noparse>${foreignKeyVar}}
	</select>
	</#if>
	
	<update id="updateByExampleSelective" parameterType="java.util.Map">
		UPDATE ${tableName} 
		<set>
			<#list commonList as col>
			<#assign colName=func.convertUnderLine(col.columnName)>
			<if test="entity.${colName}!=null">
				${col.columnName}=<#noparse>#{</#noparse>${colName},jdbcType=${func.getJdbcType(col.colDbType)}<#noparse>}</#noparse><#if col_has_next>,</#if>
>>>>>>> branch 'master' of https://github.com/fuhaodashu/pai.git
			</if>
			</#list>
		</set>
		<where>
			<if test="whereSql!=null">
				<#noparse>${</#noparse>whereSql}
			</if>
		</where>
	</update>
</mapper>
=======
		(<#list colList as col>${col.columnName}<#if col_has_next>,</#if></#list>)
		VALUES 
		(<#list colList as col><#assign colName=func.convertUnderLine(col.columnName)><#noparse>#{</#noparse>${colName},jdbcType=${func.getJdbcType(col.colDbType)}<#noparse>}</#noparse><#if col_has_next>, </#if></#list>)
	</insert>
	
	<select id="get"   parameterType="java.lang.String" resultMap="${po}">
		SELECT * FROM ${tableName} 
		WHERE 
		${pk}=<#noparse>#{</#noparse>${func.convertUnderLine(pk)}}
		LIMIT 1
	</select>
	
	<select id="getLast" resultMap="${po}">
    	select * from ${tableName} where ${pk} = (select max(${pk}) from ${tableName})
    </select>
	
	<select id="countAll" resultType="int">
    	select count(*) _total from ${tableName}
		<where>
			<if test="whereSql!=null">
				<#noparse>${</#noparse>whereSql}
			</if>
		</where>    	
    </select>	
	
	<select id="find" parameterType="java.util.Map" resultMap="${po}">
		SELECT * FROM ${tableName}
		<where>
			<if test="whereSql!=null">
				<#noparse>${</#noparse>whereSql}
			</if>
		</where>
		<if test="orderBySql!=null">
			ORDER BY <#noparse>${</#noparse>orderBySql}
		</if>
		<if test="orderBySql==null">
			ORDER BY ${pk} DESC
		</if>
	</select>
	
	<select id="findByIds"   resultMap="${po}">
		SELECT * FROM ${tableName}
			WHERE ${pk} in 
			<foreach item="id" index="index" collection="ids" open="(" separator="," close=")">  
  				<#noparse>#{id}  </#noparse>
 			</foreach>  		
			ORDER BY ${pk} 				
	</select>	
	
	<update id="update" parameterType="${type}">
		UPDATE ${tableName} SET
		<#list commonList as col>
		<#assign colName=func.convertUnderLine(col.columnName)>
		${col.columnName}=<#noparse>#{</#noparse>${colName},jdbcType=${func.getJdbcType(col.colDbType)}<#noparse>}</#noparse><#if col_has_next>,</#if>
		</#list>
		WHERE
		${pk}=<#noparse>#{</#noparse>${func.convertUnderLine(pk)}}
	</update>
	
	<delete id="delete" parameterType="java.lang.String">
		DELETE FROM ${tableName} 
		WHERE
		${pk}=<#noparse>#{</#noparse>${func.convertUnderLine(pk)}}
	</delete>
	
	<#if sub?exists && sub==true>
	<#assign foreignKeyVar=func.convertUnderLine(foreignKey)>
	<delete id="delByMainId">
	    DELETE FROM ${tableName}
	    WHERE
	    ${foreignKey}=<#noparse>#{</#noparse>${foreignKeyVar}}
	</delete>    
	
	<select id="get${class}List" resultMap="${po}">
	    SELECT *
	    FROM ${tableName} 
	    WHERE ${foreignKey}=<#noparse>#{</#noparse>${foreignKeyVar}}
	</select>
	</#if>
	
	<update id="updateByExampleSelective" parameterType="java.util.Map">
		UPDATE ${tableName} 
		<set>
			<#list commonList as col>
			<#assign colName=func.convertUnderLine(col.columnName)>
			<if test="entity.${colName}!=null">
				${col.columnName}=<#noparse>#{</#noparse>${colName},jdbcType=${func.getJdbcType(col.colDbType)}<#noparse>}</#noparse><#if col_has_next>,</#if>
			</if>
			</#list>
		</set>
		<where>
			<if test="whereSql!=null">
				<#noparse>${</#noparse>whereSql}
			</if>
		</where>
	</update>
</mapper>
>>>>>>> branch 'master' of https://github.com/fuhaodashu/pai.git
=======
		(<#list colList as col>${col.columnName}<#if col_has_next>,</#if></#list>)
		VALUES 
		(<#list colList as col><#assign colName=func.convertUnderLine(col.columnName)><#noparse>#{</#noparse>${colName},jdbcType=${func.getJdbcType(col.colDbType)}<#noparse>}</#noparse><#if col_has_next>, </#if></#list>)
	</insert>
	
	<select id="get"   parameterType="java.lang.String" resultMap="${po}">
		SELECT * FROM ${tableName} 
		WHERE 
		${pk}=<#noparse>#{</#noparse>${func.convertUnderLine(pk)}}
		LIMIT 1
	</select>
	
	<select id="getLast" resultMap="${po}">
    	select * from ${tableName} where ${pk} = (select max(${pk}) from ${tableName})
    </select>
	
	<select id="countAll" resultType="int">
    	select count(*) _total from ${tableName}
		<where>
			<if test="whereSql!=null">
				<#noparse>${</#noparse>whereSql}
			</if>
		</where>    	
    </select>	
	
	<select id="find" parameterType="java.util.Map" resultMap="${po}">
		SELECT * FROM ${tableName}
		<where>
			<if test="whereSql!=null">
				<#noparse>${</#noparse>whereSql}
			</if>
		</where>
		<if test="orderBySql!=null">
			ORDER BY <#noparse>${</#noparse>orderBySql}
		</if>
		<if test="orderBySql==null">
			ORDER BY ${pk} DESC
		</if>
	</select>
	
	<select id="findByIds"   resultMap="${po}">
		SELECT * FROM ${tableName}
			WHERE ${pk} in 
			<foreach item="id" index="index" collection="ids" open="(" separator="," close=")">  
  				<#noparse>#{id}  </#noparse>
 			</foreach>  		
			ORDER BY ${pk} 				
	</select>	
	
	<update id="update" parameterType="${type}">
		UPDATE ${tableName} SET
		<#list commonList as col>
		<#assign colName=func.convertUnderLine(col.columnName)>
		${col.columnName}=<#noparse>#{</#noparse>${colName},jdbcType=${func.getJdbcType(col.colDbType)}<#noparse>}</#noparse><#if col_has_next>,</#if>
		</#list>
		WHERE
		${pk}=<#noparse>#{</#noparse>${func.convertUnderLine(pk)}}
	</update>
	
	<delete id="delete" parameterType="java.lang.String">
		DELETE FROM ${tableName} 
		WHERE
		${pk}=<#noparse>#{</#noparse>${func.convertUnderLine(pk)}}
	</delete>
	
	<#if sub?exists && sub==true>
	<#assign foreignKeyVar=func.convertUnderLine(foreignKey)>
	<delete id="delByMainId">
	    DELETE FROM ${tableName}
	    WHERE
	    ${foreignKey}=<#noparse>#{</#noparse>${foreignKeyVar}}
	</delete>    
	
	<select id="get${class}List" resultMap="${po}">
	    SELECT *
	    FROM ${tableName} 
	    WHERE ${foreignKey}=<#noparse>#{</#noparse>${foreignKeyVar}}
	</select>
	</#if>
	
	<update id="updateByExampleSelective" parameterType="java.util.Map">
		UPDATE ${tableName} 
		<set>
			<#list commonList as col>
			<#assign colName=func.convertUnderLine(col.columnName)>
			<if test="entity.${colName}!=null">
				${col.columnName}=<#noparse>#{</#noparse>${colName},jdbcType=${func.getJdbcType(col.colDbType)}<#noparse>}</#noparse><#if col_has_next>,</#if>
			</if>
			</#list>
		</set>
		<where>
			<if test="whereSql!=null">
				<#noparse>${</#noparse>whereSql}
			</if>
		</where>
	</update>
</mapper>
>>>>>>> branch 'master' of https://github.com/fuhaodashu/pai.git
=======
		(<#list colList as col>${col.columnName}<#if col_has_next>,</#if></#list>)
		VALUES 
		(<#list colList as col><#assign colName=func.convertUnderLine(col.columnName)><#noparse>#{</#noparse>${colName},jdbcType=${func.getJdbcType(col.colDbType)}<#noparse>}</#noparse><#if col_has_next>, </#if></#list>)
	</insert>
	
	<select id="get"   parameterType="java.lang.String" resultMap="${po}">
		SELECT * FROM ${tableName} 
		WHERE 
		${pk}=<#noparse>#{</#noparse>${func.convertUnderLine(pk)}}
		LIMIT 1
	</select>
	
	<select id="getLast" resultMap="${po}">
    	select * from ${tableName} where ${pk} = (select max(${pk}) from ${tableName})
    </select>
	
	<select id="countAll" resultType="int">
    	select count(*) _total from ${tableName}
		<where>
			<if test="whereSql!=null">
				<#noparse>${</#noparse>whereSql}
			</if>
		</where>    	
    </select>	
	
	<select id="find" parameterType="java.util.Map" resultMap="${po}">
		SELECT * FROM ${tableName}
		<where>
			<if test="whereSql!=null">
				<#noparse>${</#noparse>whereSql}
			</if>
		</where>
		<if test="orderBySql!=null">
			ORDER BY <#noparse>${</#noparse>orderBySql}
		</if>
		<if test="orderBySql==null">
			ORDER BY ${pk} DESC
		</if>
	</select>
	
	<select id="findByIds"   resultMap="${po}">
		SELECT * FROM ${tableName}
			WHERE ${pk} in 
			<foreach item="id" index="index" collection="ids" open="(" separator="," close=")">  
  				<#noparse>#{id}  </#noparse>
 			</foreach>  		
			ORDER BY ${pk} 				
	</select>	
	
	<update id="update" parameterType="${type}">
		UPDATE ${tableName} SET
		<#list commonList as col>
		<#assign colName=func.convertUnderLine(col.columnName)>
		${col.columnName}=<#noparse>#{</#noparse>${colName},jdbcType=${func.getJdbcType(col.colDbType)}<#noparse>}</#noparse><#if col_has_next>,</#if>
		</#list>
		WHERE
		${pk}=<#noparse>#{</#noparse>${func.convertUnderLine(pk)}}
	</update>
	
	<delete id="delete" parameterType="java.lang.String">
		DELETE FROM ${tableName} 
		WHERE
		${pk}=<#noparse>#{</#noparse>${func.convertUnderLine(pk)}}
	</delete>
	
	<#if sub?exists && sub==true>
	<#assign foreignKeyVar=func.convertUnderLine(foreignKey)>
	<delete id="delByMainId">
	    DELETE FROM ${tableName}
	    WHERE
	    ${foreignKey}=<#noparse>#{</#noparse>${foreignKeyVar}}
	</delete>    
	
	<select id="get${class}List" resultMap="${po}">
	    SELECT *
	    FROM ${tableName} 
	    WHERE ${foreignKey}=<#noparse>#{</#noparse>${foreignKeyVar}}
	</select>
	</#if>
	
	<update id="updateByExampleSelective" parameterType="java.util.Map">
		UPDATE ${tableName} 
		<set>
			<#list commonList as col>
			<#assign colName=func.convertUnderLine(col.columnName)>
			<if test="entity.${colName}!=null">
				${col.columnName}=<#noparse>#{</#noparse>${colName},jdbcType=${func.getJdbcType(col.colDbType)}<#noparse>}</#noparse><#if col_has_next>,</#if>
			</if>
			</#list>
		</set>
		<where>
			<if test="whereSql!=null">
				<#noparse>${</#noparse>whereSql}
			</if>
		</where>
	</update>
</mapper>
>>>>>>> branch 'master' of https://github.com/fuhaodashu/pai.git
