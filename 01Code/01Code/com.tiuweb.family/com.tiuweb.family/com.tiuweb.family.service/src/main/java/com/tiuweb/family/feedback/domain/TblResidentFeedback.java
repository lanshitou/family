/*
 * @(#) TblResidentFeedback  2017-07-27 16:51:06
 *
 * Copyright 2003 by TiuWeb Corporation.
 * 51 zhangba six Road, xian, PRC 710065 // Replace with xian’s address
 * 
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * TiuWeb Corporation.  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with TiuWeb.
 */
package com.tiuweb.family.feedback.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.tiuweb.climb.framework.commons.domain.AbstractEntity;
  
/**
 * 
 * <p>Title: 投诉建议信息</p>
 * <p>Description: TODO 投诉建议信息Entity层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-27 16:51:06
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblResidentFeedback extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 类别:1投诉,2建议,3咨询,4其他
     */
    private Short category;      
    /**
     * 状态:0未解决,1已解决,2已评价
     */
    private Short status;      
    /**
     * 内容
     */
    private String content;      
    /**
     * 回复
     */
    private String reply;      
    /**
     * 数据状态:1-true已删除,0-false未删除
     */
    private Boolean deletesign;      
    /**
     * 区域编码
     */
    private String areacode;      
    /**
     * 机构外键
     */
    private String orgid;      
    /**
     * 接收结构名称
     */
    private String orgname;      
    /**
     * 回复人
     */
    private String replyidentify;      
    /**
     * 回复日期
     */
    private Date replydate;      
    
    private List<TblResidentFeedbackEvaluate> evaluates;
    
    public List<TblResidentFeedbackEvaluate> getEvaluates()
   {
      return evaluates;
   }

   public void setEvaluates(List<TblResidentFeedbackEvaluate> evaluates)
   {
      this.evaluates = evaluates;
   }

   /**
     * 获取 类别:1投诉,2建议,3咨询,4其他
     */
    public Short getCategory()
    {
        return category;
    } 
        
    /**
     * 设置 类别:1投诉,2建议,3咨询,4其他
     */
    public void setCategory(Short category) 
    {
        this.category = category;
    }   
    
    /**
     * 获取 状态:0未解决,1已解决,2已评价
     */
    public Short getStatus()
    {
        return status;
    } 
        
    /**
     * 设置 状态:0未解决,1已解决,2已评价
     */
    public void setStatus(Short status) 
    {
        this.status = status;
    }   
    
    /**
     * 获取 内容
     */
    public String getContent()
    {
        return content;
    } 
        
    /**
     * 设置 内容
     */
    public void setContent(String content) 
    {
        this.content = content;
    }   
    
    /**
     * 获取 回复
     */
    public String getReply()
    {
        return reply;
    } 
        
    /**
     * 设置 回复
     */
    public void setReply(String reply) 
    {
        this.reply = reply;
    }   
    
    /**
     * 获取 数据状态:1-true已删除,0-false未删除
     */
    public Boolean getDeletesign()
    {
        return deletesign;
    } 
        
    /**
     * 设置 数据状态:1-true已删除,0-false未删除
     */
    public void setDeletesign(Boolean deletesign) 
    {
        this.deletesign = deletesign;
    }   
    
    /**
     * 获取 区域编码
     */
    public String getAreacode()
    {
        return areacode;
    } 
        
    /**
     * 设置 区域编码
     */
    public void setAreacode(String areacode) 
    {
        this.areacode = areacode;
    }   
    
    /**
     * 获取 机构外键
     */
    public String getOrgid()
    {
        return orgid;
    } 
        
    /**
     * 设置 机构外键
     */
    public void setOrgid(String orgid) 
    {
        this.orgid = orgid;
    }   
    
    /**
     * 获取 接收结构名称
     */
    public String getOrgname()
    {
        return orgname;
    } 
        
    /**
     * 设置 接收结构名称
     */
    public void setOrgname(String orgname) 
    {
        this.orgname = orgname;
    }   
    
    /**
     * 获取 回复人
     */
    public String getReplyidentify()
    {
        return replyidentify;
    } 
        
    /**
     * 设置 回复人
     */
    public void setReplyidentify(String replyidentify) 
    {
        this.replyidentify = replyidentify;
    }   
    
    /**
     * 获取 回复日期
     */
    public Date getReplydate()
    {
        return replydate;
    } 
        
    /**
     * 设置 回复日期
     */
    public void setReplydate(Date replydate) 
    {
        this.replydate = replydate;
    }   
    
}