package cn.mcsj.sso.entity;

import java.util.Date;

public class SystemConstant {

    private Long id;
    private String code;
    private String value;
    private String remark;
    private Date createTime;

    public Long getId(){
      return id;
    }
    public void setId(Long id){
      this.id = id;
    }
    public String getCode(){
      return code;
    }
    public void setCode(String code){
      this.code = code;
    }
    public String getValue(){
      return value;
    }
    public void setValue(String value){
      this.value = value;
    }
    public String getRemark(){
      return remark;
    }
    public void setRemark(String remark){
      this.remark = remark;
    }
    public Date getCreateTime(){
      return createTime;
    }
    public void setCreateTime(Date createTime){
      this.createTime = createTime;
    }
}