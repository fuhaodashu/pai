package com.pai.app.web.core.framework.web.propertieseditors;

import org.springframework.beans.propertyeditors.PropertiesEditor;

public class IntegerEditor extends PropertiesEditor {    
    @Override    
    public void setAsText(String text) throws IllegalArgumentException {    
        if (text == null || text.equals("")) {    
            text = "0";    
        }    
        setValue(Integer.parseInt(text));    
        //setValue(Integer.valueOf(text));
    }    
    
    @Override    
    public String getAsText() {    
        return getValue().toString();    
    }    
}

