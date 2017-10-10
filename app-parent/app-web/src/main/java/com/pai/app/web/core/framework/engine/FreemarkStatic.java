package com.pai.app.web.core.framework.engine;

import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;

public class FreemarkStatic extends HashMap<Object, Object>{
	private static final long serialVersionUID = -285284523359715528L;
	private static FreemarkStatic FREEMARKER_STATIC_MODELS;

	private FreemarkStatic() {

	}

	public static FreemarkStatic getInstance() {
		if (FREEMARKER_STATIC_MODELS == null) {
			FREEMARKER_STATIC_MODELS = new FreemarkStatic();
		}
		return FREEMARKER_STATIC_MODELS;
	}

	public static TemplateHashModel useStaticPackage(String packageName) {
		try {
			BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
			TemplateHashModel staticModels = wrapper.getStaticModels();
			TemplateHashModel fileStatics = (TemplateHashModel) staticModels.get(packageName);
			return fileStatics;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
