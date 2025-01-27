package com.openhome.generics;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

public class AnnotationDemo {

    public static void main(String[] args) throws NoSuchMethodException {
        Class<Other> c = Other.class;
        Method method = c.getMethod("doOther");
        if(method.isAnnotationPresent(Debug.class)) {
            System.out.println("setting @Debug anno");
            showDebugAnnotation(method);
        } else {
            System.out.println("no @Debug anno");
        }
        showAllAnnotation(method);
    }

    private static void showDebugAnnotation(Method method) {
        Debug debug = method.getAnnotation(Debug.class);
        System.out.println("name: " + debug.name() + "," + debug.value());
    }

    private static void showAllAnnotation(Method method) {
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation: annotations) {
            System.out.println(annotation.annotationType().getName());
        }
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface Debug {
    String name();
    String value();
}

class Other {

    @Debug(name = "Ku", value = "2024/06/12")
    public void doOther() {

    }
}