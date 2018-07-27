package model.withdraw;

import java.util.Date;

public class Withdraw {

    private Integer id;

    private String ordernum;

    private Integer type;

    private Integer uid;

    private String alipayAccount;

    private String alipayUserid;

    private String realname;

    private Integer bankCardId;

    private String bankname;

    private String bankcardno;

    /**
     * 手续费率
     */
    private Double rate;

    /**
     * 提现金额
     */
    private Double price;

    private Double price1;

    private Date createtime;

    private Integer operationuid;

    private Date operationtime;

    private Date successtime;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum == null ? null : ordernum.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount == null ? null : alipayAccount.trim();
    }

    public String getAlipayUserid() {
        return alipayUserid;
    }

    public void setAlipayUserid(String alipayUserid) {
        this.alipayUserid = alipayUserid == null ? null : alipayUserid.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public Integer getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(Integer bankCardId) {
        this.bankCardId = bankCardId;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getBankcardno() {
        return bankcardno;
    }

    public void setBankcardno(String bankcardno) {
        this.bankcardno = bankcardno == null ? null : bankcardno.trim();
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice1() {
        return price1;
    }

    public void setPrice1(Double price1) {
        this.price1 = price1;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getOperationuid() {
        return operationuid;
    }

    public void setOperationuid(Integer operationuid) {
        this.operationuid = operationuid;
    }

    public Date getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(Date operationtime) {
        this.operationtime = operationtime;
    }

    public Date getSuccesstime() {
        return successtime;
    }

    public void setSuccesstime(Date successtime) {
        this.successtime = successtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
