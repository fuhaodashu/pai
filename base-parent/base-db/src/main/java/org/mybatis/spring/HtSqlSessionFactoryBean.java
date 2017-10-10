package org.mybatis.spring;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.pai.base.db.mybatis.mapping.MappingUtil;

/**
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 */
public class HtSqlSessionFactoryBean extends SqlSessionFactoryBean {
	private final Log htLogger = LogFactory.getLog(getClass());

	private Resource[] mappingAllLocation;

	@Override
	protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
		SqlSessionFactory sqlSessionFactory = super.buildSqlSessionFactory();
		Configuration configuration = sqlSessionFactory.getConfiguration();
		if (mappingAllLocation != null && mappingAllLocation.length > 0) {
			try {
				//根据从x5-base-db.xml的配置，通过工具类从x5-mapping-all.xml中获取mapping location的数组
				String[] mappingLocations = MappingUtil
						.getMappingLocations(mappingAllLocation[0].getFile());				
				PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
				//遍历解析
				for(int i=0;i<mappingLocations.length;i++){
					if(mappingLocations[i]==null)
						continue;
					Resource[] mappingAllLocations = resolver.getResources(mappingLocations[i]);
					loadMapping(configuration,mappingAllLocations);
				}				
				SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
				sqlSessionFactory = sqlSessionFactoryBuilder.build(configuration);
			} catch (JAXBException e) {
				htLogger.debug("load mapping for x5-mapping-all.xml failure. msg="+e.getMessage());
			}
		}
		return sqlSessionFactory;
	}

	public void setMappingAllLocation(Resource[] mappingAllLocation) {
		this.mappingAllLocation = mappingAllLocation;
	}

	private void loadMapping(Configuration configuration,
			Resource[] mappingAllLocations) throws NestedIOException {
		if (!isEmpty(mappingAllLocations)) {
			for (Resource mapperLocation : mappingAllLocations) {
				if (mapperLocation == null) {
					continue;
				}

				try {
					XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(
							mapperLocation.getInputStream(), configuration,
							mapperLocation.toString(),
							configuration.getSqlFragments());
					xmlMapperBuilder.parse();
				} catch (Exception e) {
					throw new NestedIOException(
							"Failed to parse mapping resource: '"
									+ mapperLocation + "'", e);
				} finally {
					ErrorContext.instance().reset();
				}

				if (htLogger.isDebugEnabled()) {
					htLogger.debug("Parsed mapper file: '" + mapperLocation
							+ "'");
				}
			}
		} else {
			if (htLogger.isDebugEnabled()) {
				htLogger.debug("Property 'mapperLocations' was not specified or no matching resources found");
			}
		}
	}
}
