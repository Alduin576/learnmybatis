package wq.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;

import java.io.IOException;
import java.io.Reader;

public class BaseMapper {
    /**
     * 其中定义获取sqlSession的方法
     * 将此类作为基类，dao类都需要继承这个类
     * 继承后可以直接调用里面的getSession()方法
     */
    private static SqlSessionFactory sqlSessionFactory;

    /**
     * 先于类执行
     * 执行初始化操作，获取到mybatis配置文件
     * 创建好sqlSessionFactory
     */
    @BeforeClass
    public static void init(){
        try{
            Reader reader= Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        }catch(IOException ignore){
            ignore.printStackTrace();
        }
    }
    //定义getSqlSession()方法
    public SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
