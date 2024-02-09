package com.lc.base;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.lc.entity.User;
import com.lc.utils.UserContext;

/**
 * 数据实体类基类
 */
public class BaseEntity extends PageEntity {

    private String id;              //主键id
    private String createBy;        //创建人姓名
    private String createDate;      //创建时间
    private String updateBy;        //更新人姓名
    private String updateDate;      //更新时间

    /**
     * 判断是否是新数据，即判断id是否为空
     * @return boolean
     */
    public boolean isNewRecord(){
        return StrUtil.isEmpty(id);
    }

    /**
     * 新增之前调用方法.加入id，创建者，修改者，创建时间，修改时间数据
     * @return
     */
    public void preInsert(){
        this.setId(IdUtil.simpleUUID());
        User currentUser = UserContext.getCurrentUser();
        if(currentUser == null){
            this.setCreateBy("系统");
            this.setUpdateBy("系统");
        }else{
            this.setCreateBy(currentUser.getUserName());
            this.setUpdateBy(currentUser.getUserName());
        }
        this.setCreateDate(DateUtil.now());
        this.setUpdateDate(DateUtil.now());
    }

    /**
     * 修改之前调用方法.更新修改者和修改时间数据
     * @return
     */
    public void preUpdate(){
        User currentUser = UserContext.getCurrentUser();
        if(currentUser == null){
            this.setUpdateBy("系统");
        }else{
            this.setUpdateBy(currentUser.getUserName());
        }
        this.setUpdateDate(DateUtil.now());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

}
