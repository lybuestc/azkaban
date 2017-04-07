//package mytest.temp;
//
///**
// * Created by lybuestc on 17/4/7.
// */
//package com.lyb.testReflection;
//
//        import java.lang.annotation.Annotation;
//        import java.lang.reflect.*;
//
///**
// * Created by lybuestc on 17/1/15.
// */
//public class TestReflection {
//    public static void main(String[] args) throws Exception {
//        test13();
//    }
//
//
//    /**
//     * 设置public属性
//     * @throws Exception
//     */
//    static void test1() throws Exception {
//        Class<Person> clazz = Person.class;
//        Person p = clazz.newInstance();
//        System.out.println(p);
//        Field f1 = clazz.getField("name");
//        f1.set(p,"liyabin");
//        System.out.println(p);
//    }
//
//    /**
//     * 设置private属性
//     */
//    static void test2() throws Exception {
//        Class<Person> clazz = Person.class;
//        Person p = clazz.newInstance();
//        System.out.println(p);
//        Field f1 = clazz.getDeclaredField("age");
//        f1.setAccessible(true);
//        f1.set(p,3);
//        System.out.println(p);
//    }
//
//    /**
//     * 调用方法
//     * @throws Exception
//     */
//    static void test3()throws Exception{
//        Class<Person> clazz = Person.class;
//        Person p = clazz.newInstance();
//        Method m1 = clazz.getMethod("show");
//        m1.invoke(p);
//        Method m2 = clazz.getMethod("display",String.class);
//        m2.invoke(p,"liyabin");
//    }
//
//    /**
//     * 获取Class实例
//     */
//    static void test4() throws Exception {
//        //1.调用运行时类的.class属性
//        Class clazz1 = Person.class;
//        System.out.println(clazz1.getName());
//
//        //2.通过运行时类的对象获取
//        Person p = new Person();
//        Class clazz2 = p.getClass();
//        System.out.println(clazz2.getName());
//
//        //3.通过静态方法
//        String className = "com.lyb.testReflection.Person";
//        Class clazz4 = Class.forName(className);
//        System.out.println(clazz4.getName());
//
//    }
//
//    static void test5() throws Exception {
//        String className = "com.lyb.testReflection.Person";
//        Class clazz4 = Class.forName(className);
//        Object obj = clazz4.newInstance();
//        Person p = (Person) obj;
//        System.out.println(p);
//    }
//
//    static void test6() {
//        Class clazz = Person.class;
//        Field[] fields = clazz.getFields();
//        //1.只能获取到public变量
//        for(Field field:fields){
//            System.out.println(field);
//        }
//        System.out.println("===============");
//        //2.
//        Field[] fields2 = clazz.getDeclaredFields();
//        for(Field field:fields2){
//            System.out.println(field);
//        }
//    }
//
//    static void test7(){
//        Class clazz = Person.class;
//        Field[] fields = clazz.getDeclaredFields();
//        for(Field field:fields){
//            System.out.println(field);
//
//            int i = field.getModifiers();
//            String modify = Modifier.toString(i);
//            System.out.println("权限" + modify);
//            System.out.println("返回类型:" + field.getType());
//            System.out.println("变量名:" + field.getName());
//        }
//    }
//
//    static void test8(){
//        Class clazz = Person.class;
//        //获取运行时类及其父类中所有的声明为public的方法
//        Method[] methods = clazz.getMethods();
//        for(Method method:methods){
//            System.out.println(method);
//        }
//        System.out.println("=================");
//        //获取运行时类本身生命的所有的方法
//        Method[] methods2 = clazz.getDeclaredMethods();
//        for(Method method:methods2){
//            System.out.println(method);
//        }
//    }
//
//    static void test9(){
//        Class clazz = Person.class;
//        Method[] methods = clazz.getMethods();
//        for(Method method:methods){
//            System.out.println("==============");
//            //1.方法名
//            System.out.println(method.getName());
//            //1.注解
//            Annotation[] ann =  method.getAnnotations();
//            for(Annotation a : ann){
//                System.out.println(a);
//            }
//            //2.权限修饰符
//            System.out.println(Modifier.toString(method.getModifiers()));
//            //3.返回值类型
//            System.out.println(method.getReturnType().getName());
//
//            //5.形参列表
//            Class[] params = method.getParameterTypes();
//            for(Class p:params){
//                System.out.print(p.getName() + " ");
//            }
//            System.out.println();
//            //6.异常类型
//            Class[] exps = method.getExceptionTypes();
//            for(int i=0;i<exps.length;i++){
//                System.out.println(exps[i].getName());
//            }
//        }
//    }
//
//    static void test10(){
//        Class clazz= Person.class;
//        Constructor[] cons = clazz.getDeclaredConstructors();
//        for(Constructor c:cons){
//            System.out.println(c);
//        }
//    }
//
//    static void test11(){
//        Class clazz= Person.class;
//
//        //获取运行时的父类
//        Class superclass = clazz.getSuperclass();
//        System.out.println(superclass);
//
//        //获取带泛型的父类
//        Type type = clazz.getGenericSuperclass();
//        System.out.println(type);
//
//        //获取父类的泛型
//        ParameterizedType parameter=  (ParameterizedType)type;
//        Type[] args = parameter.getActualTypeArguments();
//        System.out.println(((Class)args[0]).getName());
//
//        //获取实现的接口
//        Class[] interfaces = clazz.getInterfaces();
//        for(Class i :interfaces){
//            System.out.println(i);
//        }
//
//        //获取所在的包
//        Package pack = clazz.getPackage();
//        System.out.println(pack);
//
//        //获取注解
//        Annotation[] anns = clazz.getAnnotations();
//        for(Annotation a:anns){
//            System.out.println(a);
//        }
//    }
//
//    static void test12() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
//        Class clazz = Person.class;
//
//        //getField(String fieldName):获取运行时类中声明为public的指定名为fieldName属性
//        //getDeclaredField:获取运行时类中指定名为fieldName属性
//        Field name = clazz.getField("name");
//        Person p = (Person) clazz.newInstance();
//        name.set(p,"uestc");
//        System.out.println(p);
//
//        //私有变量,getField抛没有属性异常
////        Field age = clazz.getField("age");
////        age.set(p,65);
////        System.out.println(p);
//
//        //私有变量,get到了后set值时抛权限异常IllegalAccessException
////        Field age2 = clazz.getDeclaredField("age");
////        age2.set(p,65);
////        System.out.println(p);
//
//        Field age3 = clazz.getDeclaredField("age");
//        age3.setAccessible(true);
//        age3.set(p,65);
//        System.out.println(p);
//
//    }
//
//    static void test13() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
//        Class clazz = Person.class;
//        Method method = clazz.getMethod("display", String.class);
//        Person p = (Person) clazz.newInstance();
//        method.invoke(p,"china");//返回值为该方法执行的返回值
//
//        //静态方法调用
//        Method method2 = clazz.getDeclaredMethod("info");
//        method2.setAccessible(true);
//        method2.invoke(Person.class);
//
//        //调用构造器
//        Constructor cons = clazz.getDeclaredConstructor(String.class, int.class);
//        cons.setAccessible(true);
//        Object o = cons.newInstance("uestc",65);
//        System.out.println(o);
//    }
//
//}
