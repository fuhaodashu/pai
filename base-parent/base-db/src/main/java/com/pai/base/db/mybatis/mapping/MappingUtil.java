package com.pai.base.db.mybatis.mapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pai.base.core.util.JAXBUtil;
import com.pai.base.db.mybatis.mapping.model.Mappings;
import com.pai.base.db.mybatis.mapping.model.ObjectFactory;

public class MappingUtil {
	private final static Log logger = LogFactory.getLog(MappingUtil.class);
	/**
	 * 根据传入的mapping-all配置文件路径，获得该文件里面所有mappingLocation元素的value，返回String[] 数组
	 * @param mappingAllXmlFile
	 * @return
	 * @throws FileNotFoundException
	 * @throws JAXBException 
	 * String[]
	 * @exception 
	 * @since  1.0.0
	 */
	public static String[] getMappingLocations(File mappingAllXmlFile) throws FileNotFoundException, JAXBException {
		InputStream is = new FileInputStream(mappingAllXmlFile);
		Mappings mappings = (Mappings) JAXBUtil.unmarshall(is,ObjectFactory.class);
		if(mappings.getMappingLocation().size()>0){
			String[] locations = new String[mappings.getMappingLocation().size()];
			for(int i=0;i<locations.length;i++){
				if(mappings.getMappingLocation().get(i)!=null)
					locations[i] = mappings.getMappingLocation().get(i).getValue();					
			}			
			return locations;
		}
		logger.error("No found mappingLocation elements, please check 'x5-mapping-all.xml' file");
		return null;
	}
}
