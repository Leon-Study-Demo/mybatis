package jp.tokyo.leon.javassist.test;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import jp.tokyo.leon.javassist.dao.AccountDao;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author leon
 * @date 2024/2/5 10:29
 */
public class TestGenerateFirstClass {

    @Test
    public void testGenerateFirstClass() throws Exception {
        // 获取类池
        ClassPool pool = ClassPool.getDefault();
        // 制造类
        CtClass ctClass = pool.makeClass("jp.tokyo.leon.javassist.dao.impl.AccountDaoImpl");
        // 制造方法
        String methodCode = "public void insert(){System.out.println(123);}";
        CtMethod ctMethod = CtMethod.make(methodCode, ctClass);

        // 方法添加到类中
        ctClass.addMethod(ctMethod);

        // 内存中生成class
        ctClass.toClass();
        // 类加载
        Class<?> aClass = Class.forName("jp.tokyo.leon.javassist.dao.impl.AccountDaoImpl");
        // 创建对象
        Object o = aClass.newInstance();
        Method insert = aClass.getDeclaredMethod("insert");
        insert.invoke(o);

    }
    @Test
    public void testGenerateImpl() throws Exception {
        // 获取类池
        ClassPool classPool = ClassPool.getDefault();
        // 制造类
        CtClass ctClass = classPool.makeClass("jp.tokyo.leon.javassist.dao.AccountDaoImpl");
        // 制造接口
        CtClass ctInterface = classPool.makeInterface("jp.tokyo.leon.javassist.dao.AccountDao");
        // 添加接口到方法中
        ctClass.addInterface(ctInterface);

        // 实现接口中的方法
        // 制造方法
        CtMethod ctMethod = CtMethod.make("public void delete(){System.out.println(\"hello delete!\");}", ctClass);
        ctClass.addMethod(ctMethod);
        // 内存中生成类，同时将生成的类加载到jvm
        Class<?> aClass = ctClass.toClass();
        AccountDao accountDao = (AccountDao) aClass.newInstance();
        accountDao.delete();
    }

    @Test
    public void testGenerateAccountDaoImpl() throws Exception {
        // 获取类池
        ClassPool classPool = ClassPool.getDefault();
        // 制造类
        CtClass ctClass = classPool.makeClass("jp.tokyo.leon.javassist.dao.AccountDaoImpl");
        // 制造接口
        CtClass ctInterface = classPool.makeInterface("jp.tokyo.leon.javassist.dao.AccountDao");
        // 添加接口到方法中
        ctClass.addInterface(ctInterface);

        // 实现接口中的方法
        // 制造方法
        // 获取所有的方法
        Method[] declaredMethods = AccountDao.class.getDeclaredMethods();
        System.out.println(declaredMethods.length);

        Arrays.stream(declaredMethods).forEach(method -> {
            try {
                StringBuilder methodCode = new StringBuilder();

                methodCode.append("public ");
                methodCode.append(method.getReturnType().getName());
                methodCode.append(" ");
                methodCode.append(method.getName());
                methodCode.append("(");
                // 拼接参数
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    Class<?> parameterType = parameterTypes[i];
                    methodCode.append(parameterType.getName());
                    methodCode.append(" ");
                    methodCode.append("arg" + i);
                    if (i != parameterTypes.length - 1) {
                        methodCode.append(",");
                    }

                }
                methodCode.append("){System.out.println(1111);");
                // 添加return 语句
                String simpleName = method.getReturnType().getSimpleName();
                if ("int".equals(simpleName)) {
                    methodCode.append("return 1;");
                } else if ("String".equals(simpleName)) {
                    methodCode.append("return \"hello\";");
                }
                methodCode.append("}");
                System.out.println(methodCode);
                CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
                ctClass.addMethod(ctMethod);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });



        // 内存中生成类，同时将生成的类加载到jvm
        Class<?> aClass = ctClass.toClass();
        AccountDao accountDao = (AccountDao) aClass.newInstance();
        accountDao.insert("222");
        accountDao.delete();
        accountDao.update("dsds", 22.0);
        accountDao.selectByActno("22");
    }


}
