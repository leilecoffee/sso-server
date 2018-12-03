package cn.mcsj.sso.entity;

import java.util.Date;

public class Menu {

    private Long menuId;
    private String name;
    private Long parentId;
    private String url;
    private Integer sort;
    private Date createTime;

    public Long getMenuId(){
      return menuId;
    }
    public void setMenuId(Long menuId){
      this.menuId = menuId;
    }
    public String getName(){
      return name;
    }
    public void setName(String name){
      this.name = name;
    }
    public Long getParentId(){
      return parentId;
    }
    public void setParentId(Long parentId){
      this.parentId = parentId;
    }
    public String getUrl(){
      return url;
    }
    public void setUrl(String url){
      this.url = url;
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