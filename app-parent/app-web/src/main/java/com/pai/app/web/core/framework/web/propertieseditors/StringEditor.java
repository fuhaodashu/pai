package com.pai.app.web.core.framework.web.propertieseditors;

import org.springframework.beans.propertyeditors.PropertiesEditor;

public class StringEditor extends PropertiesEditor {
    @Override                                                               
    public void setAsText(String text) throws IllegalArgumentException {    
        if (text == null || text.equals("")) {                              
            text = "";                                                     
        }
        setValue(text);
    }                                                                       
                                                                            
    @Override                                                               
    public String getAsText() {
        return getValue().toString();                                       
    }                                                                       
}
