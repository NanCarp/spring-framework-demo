package org.smart4j.framework.bean;

import org.smart4j.framework.util.CastUtil;
import org.smart4j.framework.util.CollectionUtil;

import java.util.Map;

/**
 * 封装表单参数
 * Created by nanca on 12/24/2017.
 */
public class FormParam {

    private String fildName;
    private Object fieldValue;

    public FormParam(String fildName, Object fieldValue) {
        this.fildName = fildName;
        this.fieldValue = fieldValue;
    }

    public String getFildName() {
        return fildName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
