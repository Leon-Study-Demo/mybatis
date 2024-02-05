package jp.tokyo.leon.mybatis.web.utils;

import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author leon
 * @date 2024/2/5 11:22
 *
 * 可以动态的生成Dao的实现类
 */
public class GenerateDaoProxy {
    public static Object generate(SqlSession sqlSession, Class<?> daoInterface) {
        // 类池
        ClassPool pool = ClassPool.getDefault();
        // 制造类
        CtClass ctClass = pool.makeClass(daoInterface.getName() + "$$Proxy");
        // 制造接口
        CtClass ctInterface = pool.makeInterface(daoInterface.getName());
        // 实现接口
        ctClass.addInterface(ctInterface);
        // 实现接口的所有方法
        Method[] declaredMethods = daoInterface.getDeclaredMethods();
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
                methodCode.append(")");
                methodCode.append("{");

                methodCode.append("org.apache.ibatis.session.SqlSession sqlSession = jp.tokyo.leon.mybatis.web.utils.SqlSessionUtil.openSession();");
                // 需要知道是什么类型的sql语句
                String sqlId = daoInterface.getName() + "." + method.getName();
                SqlCommandType sqlCommandType = sqlSession.getConfiguration().getMappedStatement(sqlId).getSqlCommandType();
                if (sqlCommandType == SqlCommandType.INSERT) {
                    methodCode.append("sqlSession.insert()");
                }
                if (sqlCommandType == SqlCommandType.DELETE) {

                }
                if (sqlCommandType == SqlCommandType.UPDATE) {
                    methodCode.append("return sqlSession.update(\"" + sqlId + "\", arg0);");
                }
                if (sqlCommandType == SqlCommandType.SELECT) {
                    String s = method.getReturnType().getName();
                    methodCode.append("return (" + s + ")sqlSession.selectOne(\"" + sqlId + "\", arg0);");

                }

                methodCode.append("}");
                System.out.println(methodCode);

                CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
                ctClass.addMethod(ctMethod);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // 创建对象
        Object object = null;
        try {
            Class<?> clazz = ctClass.toClass();
            object = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}
