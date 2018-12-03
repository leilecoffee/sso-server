package cn.mcsj.sso.entity;

import java.util.Date;

public class Permission {

    private Long permissionId;
    private String name;
    private String code;
    private Long parentId;
    private Integer sort;
    private Date createTime;

    public Long getPermissionId(){
      return permissionId;
    }
    public void setPermissionId(Long permissionId){
      this.permissionId = permissionId;
    }
    public String getName(){
      return name;
    }
    public void setName(String name){
      this.name = name;
    }
    public String getCode(){
      return code;
    }
    public void setCode(String code){
      this.code = code;
    }
    public Long getParentId(){
      return parentId;
    }
    public void setParentId(Long parentId){
      this.parentId = parentId;
    }
    public Integer getSort(){
      return sort;
    }
    public void setSort(Integer sort){
      this.sort = sort;
    }
    public Date getCreateTime(){
      return createTime;
    }
    public void setCreateTime(Date createTime){
      this.createTime = createTime;
    }
}