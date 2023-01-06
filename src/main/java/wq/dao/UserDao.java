package wq.dao;

import wq.model.User;
import org.apache.ibatis.session.SqlSession;
import wq.service.UserMapper;
import wq.utils.BaseMapper;

/**
 * UserDao类完成对用户层的处理与操作
 * 具体为查询，添加，修改，删除
 * 需要继承父类BaseMapper来获取sqlSession
 */
public class UserDao extends BaseMapper {
    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    public User selectById(Long userId){
        SqlSession sqlSession=getSqlSession();
        //获取UserMapper接口
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //调用selectById()方法，其方法位置在接口和xml中
            return userMapper.selectById(userId);
        }finally {
            sqlSession.close();
        }
    }
    /**
     * 添加用户
     * @param user
     */
    public int addUser(User user){
        int result=0;


        return result;
    }
}
