package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.Employee;
import cn.wolfcode.edu.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {

    /**
     * 删除员工
     * @param id 待删除员工的id
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 添加员工
     * @param record
     */
    void insert(Employee record);

    /**
     * 查询单个员工
     * @param id  待查询员工的id
     * @return
     */
    Employee selectByPrimaryKey(Long id);

    /**
     * 查询所有的员工
     * @return
     */
    List<Employee> selectAll();

    /**
     * 更新员工
     * @param record
     */
    void updateByPrimaryKey(Employee record);

    /**
     * 分页条数
     * @param qo  封装的查询条件对象
     * @return
     */
    int queryForCount(EmployeeQueryObject qo);

    /**
     * 分页对象集合
     * @param qo  封装的查询条件对象
     * @return
     */
    List<Employee> queryForList(EmployeeQueryObject qo);

    /**
     * 员工的离职或复职
     * @param id
     */
    void changeState(Long id);

    /**
     * 删除员工和角色的关系
     * @param id
     */
    void deleteRelation(Long id);

    /**
     * 维护员工和角色的关系
     * @param empId 员工id
     * @param roleId 角色id
     */
    void insertRelation(@Param("empId") Long empId, @Param("roleId")Long roleId);

    /**
     * 上传图片相对路径的方法
     * @param empId
     * @param imgPath
     */
    void uploadPortrait(@Param("empId") Long empId, @Param("imgPath")String imgPath);

    Employee checkName(String username);
}