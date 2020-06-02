package com.lege.peony.common;

/**
 * @Author 了个
 * @date 2020/6/2 13:55
 */
public class RequestMethod {
    private String className;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] parameTypes;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public Class<?>[] getParameTypes() {
        return parameTypes;
    }

    public void setParameTypes(Class<?>[] parameTypes) {
        this.parameTypes = parameTypes;
    }
}
