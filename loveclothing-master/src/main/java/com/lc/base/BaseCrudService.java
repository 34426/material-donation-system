package com.lc.base;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 增删改查业务层基类
 */
@Service
public abstract class BaseCrudService<D extends BaseCrudDao<T>, T extends BaseEntity>{

    @Autowired
    private D dao;

    /**
     * 保存
     */
    @Transactional(readOnly = false)
    public String save(T entity){
        if(entity.isNewRecord()){
            //新增
            entity.preInsert();
            dao.insert(entity);
        }else{
            //修改
            entity.preUpdate();
            dao.update(entity);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "success");
        return JSON.toJSONString(map);
    }

    /**
     * 物理删除(删除数据库记录)
     * @param id 记录id
     */
    @Transactional(readOnly = false)
    public String deletePhysical(String id){
        dao.deletePhysical(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "success");
        return JSON.toJSONString(map);
    }

    /**
     * 查询所有数据
     * @return List<T> 对应实体类List
     */
    @Transactional(readOnly = true)
    public List<T> selectAllList(){
        return dao.selectAllList();
    }

    /**
     * 根据条件查询
     * @param entity 根据mapper中的sql和entity里的字段进行相应的条件查询
     * @return List<T> 对应实体类List
     */
    @Transactional(readOnly = true)
    public List<T> selectList(T entity){
        return dao.selectList(entity);
    }

    /**
     * 获取数据总数
     * @return Integer 返回数据总数
     */
    @Transactional(readOnly = true)
    public Integer selectAllCount(){
        return dao.selectAllCount();
    }

    /**
     *
     * 获取根据条件查询的数据总数
     * @param entity 根据mapper中的sql和entity里的字段进行相应的条件查询
     * @return Integer 返回数据条数
     */
    @Transactional(readOnly = true)
    public Integer selectCount(T entity){
        return dao.selectCount(entity);
    }

    /**
     * 数据分页查询（主要是配合LayUI的数据表格接口）
     * 不传分页参数的话，默认10条一分页
     */
    @Transactional(readOnly = true)
    public Map<String, Object> selectPage(T entity){
        Map<String, Object> pageDataMap = new HashMap<>(3);
        //默认分页参数
        if(entity.getCurrentPage() == null || entity.getLimitCount() == null){
            entity.setCurrentPage(1);
            entity.setLimitCount(10);
        }
        List<T> dataList = dao.selectList(entity);
        Integer totalCount = dao.selectCount(entity);
        pageDataMap.put("code", 0);
        pageDataMap.put("data", dataList);
        pageDataMap.put("count", totalCount);
        return pageDataMap;
    }

    /**
     * 根据唯一id获取单个实体
     * @param id  数据唯一id
     * @return T 返回单个实体信息
     */
    @Transactional(readOnly = true)
    public T selectEntity(String id){
        return dao.selectEntity(id);
    }


}
