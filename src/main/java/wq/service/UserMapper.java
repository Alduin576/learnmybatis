package wq.service;

import org.apache.ibatis.annotations.Param;
import wq.model.User;
import wq.query.UserQuery;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * 查询方法
     * 定义一个方法根据id来查找用户
     * @param userId
     * return
     */
    public User selectById(Long userId);
    /**
     * 查询方法
     * 定义一个方法根据name和password来查找用户
     */
//    public User selectByNameAndPassword(@Param("userName")String userName,@Param("userPassword")String userPassword);
    public User selectByNameAndPassword(Map paramMap);
    /**
     * 查询方法
     * 定义一个方法，根据传入的用户名和邮箱来查找用户
     * 1.只传入用户名，则模糊查询用户
     * 2.只传入邮箱，则精确查询用户
     * 3.传入用户名和邮箱，则需要满足用户名和邮箱的条件
     * @param user
     * @return
     */
    public List<User> selectUserByUserNameAndUserEmail(User user);
    /**
     * 查询方法
     * 1.当传入的id有值时，则优先使用id查询
     * 2.如果id没有值，则判断用户名是否有值，有则使用用户名进行查询，如果没有则sql查询无结果
     * @param user
     * @return
     */
    public User selectUserByIdAndUserName(User user);
    /**
     * 查询方法
     * 使用动态SQL <foreach></foreach>来将id集合作为查询条件加入进去.参数为list类型
     * @param IdList
     * @return
     */
    //经控制变量测试，发现必须需要加上注解@Param("idList") ，不然collection就算="idList也没用"
    //如果不指定@Param的话，参数类型是list集合，则UserMapper.xml中要写的collection值为list
    //@Param("idList")
    public List<User> selectUserByIdList(List<Long> IdList);
    /**
     * 查询方法
     * 使用动态SQL <foreach></foreach>来将id集合作为查询条件加入进去.参数为数组 array类型
     * @param idArray
     * @return
     */
    //不加 @Param注解在list中是没有通过的，现不知道数组可以否
    //经过测试，发现数组类型的参数也需要加上@Param注解
    //如果不指定@Param的话，参数类型是数组，则UserMapper.xml中要写的collection值为array
    //@Param("idArray")
    public List<User> selectUserByIdArray(Long[] idArray);
    /**
     * 查询方法
     * 使用动态sql 将id集合作为查询条件，参数为Map类型
     * @param idMap
     * @return
     */
    //似乎指定collection的值为 Key或者key 无用，需要加上@Param来重命名，在collection中填入重命名的值
    public List<User> selectUserByIdMap(@Param("idMap")Map<String,Object> idMap);
    /**
     * 查询方法
     * 使用动态sql，将id集合中的参数通过foreach标签依次作为查询条件进行查询，参数类型为对象
     * 需要做的事是：1.创建一个java对象，里面的有一个List类型的属性，用于存放到时的参数。2.在接口中声明参数为对象类型，并重命名。
     * 3.映射配置文件中的collection要与存参数的list类型的变量名名字一致,比如你在java对象中定义了一个List userIds,那么collection中填写的即为userIds
     * 不然会报the property (你写的另一个名字)没有找到对应的getter方法的错误
     * 在测试时，先实例化一个list对象，往其中添加参数。
     */
    public List<User> selectUserByObject(UserQuery userQuery);
    /**
     *高级查询方法，关联映射,不带association标签
     * 嵌套结果查询
     */
    public User selectUserRoleById(Long userId);
    /**
     *高级查询方法，关联映射，带association标签
     * 嵌套结果查询
     */
    public User selectUserAndRoleById(Long userId);
    /**
     * 高级查询方法 带association
     * 嵌套查询
     */
    public User selectUserRoleByIdSelect(Long userId);
    /**
     * 高级查询方法
     * collection  一对多映射
     */
    public List<User> selectAllUsersAndRoles();
    /**
     * 高级查询方法
     * 根据用户包含的角色查询角色对应拥有的权限
     * 使用嵌套结果查询
     * 映射文件中select的id为selectAllUserAndRolesWithPrivilege
     */
    public List<User> selectAllUserAndRolesWithPrivilege();

    /**
     * 添加用户方法
     * 定义一个方法来添加用户
     * @param user
     * @return
     */
    public int addUser(User user);
    /**
     * 添加用户方法
     * 定义一个方法来完成批量添加用户，传入的参数为list集合类型
     *
     */
    public int addUserBatch(List<User> users);
    /**
     * 修改方法
     * 修改用户
     * @param user
     * @return
     */
    public int updateUser(User user);

    /**
     * 修改方法
     * 动态有选择性地修改用户
     * @param user
     * @return
     */
    //动态选择性地修改用户
    public int updateUserSelective(User user);

    /**
     * 修改方法
     * 批量修改用户信息
     * @param userMap
     * @return
     */
    public int updateUserBatch(Map<String,Object> userMap);
    /**
     * 删除用户
     * @param userId
     * @return
     */
    public int deleteUserById(Long userId);


    /**
     * 某一天的测试
     */
    public User testTypeHandler(Long id);
    public int testTypeHandler2(User user);
//    public int testTypeHandler002(Date date);
}
