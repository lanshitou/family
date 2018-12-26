/*
 * @(#) TblResidentInquiryReply  2017-07-27 11:46:44
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
package com.tiuweb.family.inquiry.domain;

import java.io.Serializable;
import java.util.Date;
import com.tiuweb.climb.framework.commons.domain.AbstractEntity;
  
/**
 * 
 * <p>Title: 居民问诊答复信息</p>
 * <p>Description: TODO 居民问诊答复信息Entity层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-27 11:46:44
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblResidentInquiryReply extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 问诊信息外键
     */
    private String residentinquiryidentity;      
    /**
     * 问诊答复内容
     */
    private String reply;      
    /**
     * 消息发送人外键
     */
    private String replayidentity;      
    /**
     * 消息发送人名称
     */
    private String replayname;      
    /**
     * 消息来源:0居民,1医生
     */
    private Boolean comein;      
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
     * 获取 问诊信息外键
     */
    public String getResidentinquiryidentity()
    {
        return residentinquiryidentity;
    } 
        
    /**
     * 设置 问诊信息外键
     */
    public void setResidentinquiryidentity(String residentinquiryidentity) 
    {
        this.residentinquiryidentity = residentinquiryidentity;
    }   
    
    /**
     * 获取 问诊答复内容
     */
    public String getReply()
    {
        return reply;
    } 
        
    /**
     * 设置 问诊答复内容
     */
    public void setReply(String reply) 
    {
        this.reply = reply;
    }   
    
    /**
     * 获取 消息发送人外键
     */
    public String getReplayidentity()
    {
        return replayidentity;
    } 
        
    /**
     * 设置 消息发送人外键
     */
    public void setReplayidentity(String replayidentity) 
    {
        this.replayidentity = replayidentity;
    }   
    
    /**
     * 获取 消息发送人名称
     */
    public String getReplayname()
    {
        return replayname;
    } 
        
    /**
     * 设置 消息发送人名称
     */
    public void setReplayname(String replayname) 
    {
        this.replayname = replayname;
    }   
    
    /**
     * 获取 消息来源:0居民,1医生
     */
    public Boolean getComein()
    {
        return comein;
    } 
        
    /**
     * 设置 消息来源:0居民,1医生
     */
    public void setComein(Boolean comein) 
    {
        this.comein = comein;
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
    
}