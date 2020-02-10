package com.kuzank.autobuildcode.common;

import javassist.*;
import org.junit.Test;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/28
 */
public class JavassistTest {

    @Test
    public void test() throws NotFoundException, CannotCompileException {

        ClassPool classPool = ClassPool.getDefault();

        classPool.appendClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader()));

        CtClass ctClass = classPool.getCtClass("cn.com.Test");

        CtMethod ctMethod = CtNewMethod.make("public void helloWorld(){ System.out.println(\"hello world!\"); }", ctClass);

        ctClass.addMethod(ctMethod);

        Class clazz = ctClass.toClass();
    }

}
