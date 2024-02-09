package com.lc.base;

import java.util.List;

/**
 * 增删改查持久层基类
 */
public interface BaseCrudDao<T> {

    /**
     * 新增
     * @param entity
     */
    public void insert(T entity);

    /**
     * 物理删除(删除数据库记录)
     * @param id 记录id
     */
    public void deletePhysical(String id);

    /**
     * 修改
     * @param entity
     */
    public void update(T entity);

    /**
     * 查询所有数据
     * @return List<T> 对应实体类List
     */
    public List<T> selectAllList();

    /**
     * 根据条件查询
     * @param entity 根据mapper中的sql和entity里的字段进行相应的条件查询
     * @return List<T> 对应实体类List
     */
    public List<T> selectList(T entity);

    /**
     * 获取数据总数
     * @return Integer 返回数据总数
     */
    public Integer selectAllCount();

    /**
     *
     * 获取根据条件查询的数据总数
     * @param entity 根据mapper中的sql和entity里的字段进行相应的条件查询
     * @return Integer 返回数据条数
     */
    public Integer selectCount(T entity);

    /**
     * 根据唯一id获取单个实体
     * @param id  数据唯一id
     * @return T 返回单个实体信息
     */
    public T selectEntity(String id);




}
