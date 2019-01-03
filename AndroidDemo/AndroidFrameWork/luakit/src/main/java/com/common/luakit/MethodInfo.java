package com.common.luakit;

import java.lang.reflect.Method;

/**
 *
 * Type Signature	Java Type
 * Z	boolean
 * B	byte
 * C	char
 * S	short
 * I	int
 * J	long
 * F	float
 * D	double
 * L fully-qualified-class ;	fully-qualified-class
 * [ type	type[]
 * ( arg-types ) ret-type	method type
 *
 * Created by xianjiachao on 2019/1/2.
 */
public class MethodInfo {


    Method mMethodRef;
    MethodInfo(Method method) {
        mMethodRef = method;
    }

    public String getClassName() {
        return mMethodRef.getDeclaringClass().getName().replace(".", "/");
    }

    public String getMethodName() {
        return mMethodRef.getName();
    }

    public String getReturnName() {
        return convertJavaToJni(mMethodRef.getReturnType().getName());
    }

    public String getParamsName() {
        StringBuilder params = new StringBuilder("");
        for (Class<?> aClass : mMethodRef.getParameterTypes()) {
            params.append(convertJavaToJni(aClass.getName()));
        }
        return params.toString();
    }

    public String getMethodSignature() {
        return "(" + getParamsName() + ")" + getReturnName();
    }

    private String convertJavaToJni(String type) {
        if ("float".equals(type)) {
            return "F";
        } else if ("int".equals(type)) {
            return "I";
        } else if ("long".equals(type)) {
            return "J";
        } else if ("double".equals(type)) {
            return "D";
        } else if ("boolean".equals(type)) {
            return "Z";
        } else if ("short".equals(type)) {
            return "S";
        } else if ("char".equals(type)) {
            return "C";
        } else if ("byte".equals(type)) {
            return "B";
        } else if ("void".equals(type)) {
            return "V";
        } else {
            return "L" + type.replace(".", "/") + ";";
        }
    }
}
