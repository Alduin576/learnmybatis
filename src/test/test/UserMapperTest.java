package test;

import wq.model.Privilege;
import wq.model.Role;
import wq.model.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import wq.query.UserQuery;
import wq.service.UserMapper;
import wq.utils.BaseMapper;

import java.util.*;

public class UserMapperTest extends BaseMapper {
    void printUsers(List<User> users){
        for(User user:users){
            System.out.println(user);
        }
    }
    /**查询方法测试
     * 根据id查询用户
     * @param
     * @return
     */
    @Test
    public void selectByIdTest(){
        SqlSession sqlSession=getSqlSession();
        //获取UserMapper接口
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //调用selectById()方法，其方法位置在接口和xml中
            User queryUser=userMapper.selectById(1l);
            System.out.println(queryUser);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 查询方法测试
     * 根据用户名和密码查询用户
     */
    @Test
    public void selectUserByNamePasswordTest(){
        SqlSession sqlSession=getSqlSession();
        //获取userMapper接口
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            Map queryMap=new HashMap();
            queryMap.put("userName","wq");
            queryMap.put("userPassword","game");
//            User user=userMapper.selectByNameAndPassword("test1","12345");
            User user=userMapper.selectByNameAndPassword(queryMap);
        }finally {
            sqlSession.close();
        }
    }

    /**
     *查询方法测试
     * 根据用户名和邮箱查询用户
     * 测试动态sql中的if
     */
    @Test
    public void selectUserByNameAndEmailTest(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            User user=new User();
            user.setUserName("w");
            user.setUserEmail("wq@code.com");
            List<User> users=userMapper.selectUserByUserNameAndUserEmail(user);
            printUsers(users);
        }finally {
            sqlSession.close();
        }
    }
    /**
     * 查询方法测试
     * 根据用户id和用户名来查询用户
     * 测试动态sql中的choose
     */
    @Test
    public void selectUserByIdAndNameTest(){
        SqlSession sqlSession=getSqlSession();
        try{
            //获取接口
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //创建pojo对象用于传值
            User user=new User();
            user.setUserId(4l);//因为有id，则会优先查询id，不会去把用户名作为参数放入其中
            user.setUserName("xwx");
            //执行
            user=userMapper.selectUserByIdAndUserName(user);
            System.out.println(user);
        }finally{
            sqlSession.close();
        }
    }
    /**
     * 查询方法测试
     * foreach实现sql in集合测试
     * 根据多个id，一个id集合，来查询多条与id相关的记录
     * 定义参数类型为集合list
     */
    @Test
    public void selectUserByIdListTest(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //定义一个集合，存放要查找的id
            List<Long> idList=new ArrayList<>();
            idList.add(1l);
            idList.add(6l);
            idList.add(7l);
            List<User> users=userMapper.selectUserByIdList(idList);
        }finally {
            sqlSession.close();
        }
    }
    /**
     * 查询方法测试
     * foreach实现sql in集合测试
     * 根据多个id，一个id集合，来查询多条与id相关的记录
     * 定义参数类型为数组Array
     * xml映射文件 UserMapper.xml需要另外编写
     * 映射接口 中定义方法的参数要变为 数组类型的参数
     */
    @Test
    public void selectUserByIdArrayTest(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //创建数组
            Long[] idArray=new Long[]{1l,4l};
            //执行
            List<User> users=userMapper.selectUserByIdArray(idArray);
        }finally {
            sqlSession.close();
        }
    }
    /**
     * 查询方法测试
     * foreach实现 动态sql in集合测试
     * 定义参数类型为Map
     */
    @Test
    public void selectUserByIdMapTest(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //创建传入的map类型 参数
            Map<String,Object> idMap=new HashMap<>();
            idMap.put("id1",1l);
            idMap.put("id2",4l);
            idMap.put("id3",7l);
            //执行
            List<User> users=userMapper.selectUserByIdMap(idMap);
        }finally{
            sqlSession.close();
        }
    }
    /**
     * 查询方法测试
     * foreach实现sql in集合测试
     * 根据多个id，一个id集合，来查询多条与id相关的记录
     * 定义参数类型为对象类型，UserQuery
     * xml映射文件 UserMapper.xml需要另外编写
     * 映射接口 中定义方法的参数要为 对象类型的参数
     */
    @Test
    public void selectUserByObjectTest(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //定义在UserQuery中存放参数的List
            List<Long> ids=new ArrayList<>();
            ids.add(1l);
            ids.add(6l);
            ids.add(4l);
            UserQuery userQuery=new UserQuery();
            userQuery.setUserIds(ids);
            List<User> user=userMapper.selectUserByObject(userQuery);
        }finally {
            sqlSession.close();
        }
    }
    /**
     * 高级查询方法测试
     * resultMap标签中不带association标签
     */
    @Test
    public void selectUserRoleByIdTest(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            User user=userMapper.selectUserRoleById(6l);
        }finally{
            sqlSession.close();
        }

    }
    /**
     * 高级查询方法测试
     * resultMap标签中带association标签
     * 嵌套结果查询
     */
    @Test
    public void selectUserRoleByIdTest2(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            User user=userMapper.selectUserAndRoleById(6l);
        }finally{
            sqlSession.close();
        }

    }
    /**
     * 高级查询方法测试
     * 会执行两条sql语句，第一条查询的结果中有一个是第二条sql语句中的参数。第二条sql语句查询出来的结果是最终想要的结果。
     * 下方方法中的参数是第一条sql查询语句中的参数。 where user_id=#{userId}
     */
    @Test
    public void selectUserAndRoleByIdSelectTest(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            User user=userMapper.selectUserRoleByIdSelect(1l);
            /*
            如果不执行user.getRole();关联的第二条查询语句就不会被执行，因为设置fetchType为lazy，当需要去获取相关的role时，也就是执行user.getRole()后，
            会执行关联的第二条关联语句  selectRoleByRoleId
             */
            System.out.println("调用user.getRole()");
            user.getRole();
        }finally{
            sqlSession.close();
        }
    }
    /**
     * 高级查询方法测试，实现查询所有用户对应拥有的角色
     * 测试 一对多映射
     */
    @Test
    public void selectAllUsersAndRolesTest(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<User> usersRoles=userMapper.selectAllUsersAndRoles();
            System.out.println("用户数:"+usersRoles.size());
            for(User user:usersRoles){
                System.out.println("用户名:"+user.getUserName());
                for(Role role:user.getRoleList()){
                    System.out.println("角色名:"+role.getRoleName());
                }
            }
        }finally{
            sqlSession.close();
        }
    }
    /**
     * 高级查询方法测试
     * 查询结果：查询所有用户包含角色所拥有的权限
     * 查询方式：嵌套结果查询
     */
    @Test
    public void selectAllUserAndRolesWithPrivilegeTest(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //执行
            List<User> users=new ArrayList<>();
            users=userMapper.selectAllUserAndRolesWithPrivilege();
            System.out.println("user count:"+users.size());
            for(User user:users){
                System.out.println("用户名:"+user.getUserName());
                for(Role role:user.getRoleList()){
                    System.out.println("角色名:"+role.getRoleName());
                    for(Privilege privilege:role.getPrivilegeList()){
                        System.out.println("权限名:"+privilege.getPrivilegeName());
                    }
                }
            }

        }finally {
            sqlSession.close();
        }
    }
    /**
     * 添加方法测试
     * 添加用户
     * 使用动态sql中的if来动态插入用户
     */
    @Test
    //添加用户
    public void addUserTest(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //创建持久化pojo对象
            User user =new User();
            user.setUserName("lw");
            user.setUserPassword("123");
            user.setUserInfo("无");
            int result=userMapper.addUser(user);
            //查询之前添加进去的数据
            System.out.println("影响了记录的条数："+result);
            user=userMapper.selectById(user.getUserId());
            System.out.println(user);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
    /**
     * 添加方法测试
     * 测试批量添加用户
     *
     */
    @Test
    public void testAddUserBatchTest(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //user对象不可以放于此处
//            User user=new User();
            List<User> users=new ArrayList<>();
            //如果输入用户自定义的名字，那该如何批量写入？  存疑
            for(int i=0;i<2;i++){
                User user=new User();
                user.setUserName("test"+i);
                user.setUserPassword("12345_"+i);
                user.setUserInfo("测试用户"+i);
                user.setUserEmail("test"+i+"@code.com");
                users.add(user);
            }
            int result=userMapper.addUserBatch(users);
        }finally{
            sqlSession.rollback();
            sqlSession.close();
        }
    }
    /**
     * 修改用户方法测试
     *
     */
    //修改用户
    @Test
    public void updateUserTest(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //定义持久化pojo对象，里面存放 要修改的信息
            User user=new User();
            user.setUserName("lw");
            user.setUserPassword("12345");
            user.setUserInfo("做视频很擅长");
            user.setUserEmail("lw@code.com");
            user.setUserId(1l);
            //执行接口中和xml中的语句
            int result =userMapper.updateUser(user);
            System.out.println(result);
            user=userMapper.selectById(user.getUserId());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    /**
     * 修改用户方法测试
     */
    //动态选择性地修改用户，使用动态SQL中的<set>
    @Test
    public void updateUserSelectiveTest(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            User user=new User();
            user.setUserName("hh");
            user.setUserPassword("1235");
            user.setUserInfo("自信");
            user.setUserEmail("hh@code.com");
            user.setUserId(4l);
            //执行
            int result=userMapper.updateUserSelective(user);
        }
        finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
    /**
     * 修改用户方法测试
     * 批量修改用户信息
     */
    @Test
    public void updateUserBatchTest(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //创建list集合用于存放要修改的信息
            Map userMap=new HashMap<String,Object>();
            userMap.put("user_id",4l);
            userMap.put("user_name","test");
            userMap.put("user_email","test@code.com");

            int result=userMapper.updateUserBatch(userMap);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
    /**
     * 删除用户测试
     */
    //删除用户
    @Test
    public void deleteUserTest(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            int result=userMapper.deleteUserById(1l);
            System.out.println(result);
        }finally{
            sqlSession.rollback();
            sqlSession.close();
        }
    }
    //测试类型转换
    @Test
    public void testTypeHandler(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            User user=new User();

            user=userMapper.testTypeHandler(10l);
            System.out.println(user.getUserBirthday());
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testTypeHandler2(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            User user=new User();
            user.setUserBirthday(new Date());
            int result=userMapper.testTypeHandler2(user);
        }finally{
            sqlSession.commit();
            sqlSession.close();

        }
    }
}
