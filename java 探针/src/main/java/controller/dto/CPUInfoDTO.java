package controller.dto;

/**
 *  CPU信息
 */
public class CPUInfoDTO {

    /** CPU */
    private String cpuinfo;
    /** CPU总数 */
    private Integer totalCPUs;
    /** CPU基准速度 */
    private String referenceSpeeds;
    /** CPU用户利用率 */
    private Integer userPers;
    /** CPU系统利用率 */
    private Integer sysPers;
    /** CPU利用率 */
    private Integer cpuPers;
    /** 内存总量 */
    private String mAll;
    /** 内存总量 */
    private String mUsed;

    public String getCpuinfo() {
        return cpuinfo;
    }

    public void setCpuinfo(String cpuinfo) {
        this.cpuinfo = cpuinfo;
    }

    public Integer getTotalCPUs() {
        return totalCPUs;
    }

    public void setTotalCPUs(Integer totalCPUs) {
        this.totalCPUs = totalCPUs;
    }

    public String getReferenceSpeeds() {
        return referenceSpeeds;
    }

    public void setReferenceSpeeds(String referenceSpeeds) {
        this.referenceSpeeds = referenceSpeeds;
    }

    public Integer getUserPers() {
        return userPers;
    }

    public void setUserPers(Integer userPers) {
        this.userPers = userPers;
    }

    public Integer getSysPers() {
        return sysPers;
    }

    public void setSysPers(Integer sysPers) {
        this.sysPers = sysPers;
    }

    public Integer getCpuPers() {
        return cpuPers;
    }

    public void setCpuPers(Integer cpuPers) {
        this.cpuPers = cpuPers;
    }

    public String getmAll() {
        return mAll;
    }

    public void setmAll(String mAll) {
        this.mAll = mAll;
    }

    public String getmUsed() {
        return mUsed;
    }

    public void setmUsed(String mUsed) {
        this.mUsed = mUsed;
    }
}
