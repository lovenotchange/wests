package com.example.west.jvm.july6th;

import lombok.SneakyThrows;

import java.io.*;

/**
 * @author: chenxu
 * @date: 2020/7/6 21:45
 */
public class MyLoadClass extends ClassLoader {

    private String classLoaderName;

    private String suffix = ".class";

    public MyLoadClass(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    public MyLoadClass(String classLoaderName, ClassLoader parent) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }


    @Override
    protected Class<?> findClass(String className) {
        System.out.println("aaaaaaa");
        try {
            byte[] bytes = getByte(className);
            return defineClass(className, bytes, 0, bytes.length);
        } catch (Exception e) {

        }
        return null;
    }

    private byte[] getByte(String className) throws IOException {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {

           // this.classLoaderName = className.replace(".", "/");
            className = className.replace(".","\\");
            inputStream =
                    new FileInputStream(
                            new File("D:\\"+className + suffix));
            outputStream = new ByteArrayOutputStream();
            int ch = 0;
            while (-1 != (ch = inputStream.read())) {
                outputStream.write(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
            outputStream.close();
        }
        return outputStream.toByteArray();
    }

    public static void test(ClassLoader classLoader) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        Class<?> clazz = classLoader.loadClass("com.example.west.jvm.july6th.Banana");
//        Object o = clazz.newInstance();
//        System.out.println(o);
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        MyLoadClass myLoadClass = new MyLoadClass("myLoadClass",null);
        Class<?> aClass = myLoadClass.loadClass("com.example.west.jvm.july6th.Banana");
        Object o = aClass.newInstance();
        System.out.println(o);
        System.out.println(aClass.getClassLoader());


        MyLoadClass myLoadClass2 = new MyLoadClass("myLoadClass",null);
        Class<?> aClass2 = myLoadClass2.loadClass("com.example.west.jvm.july6th.Banana");


        //test(myLoadClass);
    }
}
