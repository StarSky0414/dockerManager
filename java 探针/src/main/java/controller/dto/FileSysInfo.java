package controller.dto;

/**
 * 系统文件状态信息
 */
public class FileSysInfo {

    private String dirName;
    private String sysTypeName;
    long total = 0L;
    long free = 0L;
    long used = 0L;
    long avail = 0L;
    long files = 0L;
    long freeFiles = 0L;
    long diskReads = 0L;
    long diskWrites = 0L;
    long diskReadBytes = 0L;
    long diskWriteBytes = 0L;
    double diskQueue = 0.0D;
    double diskServiceTime = 0.0D;
    double usePercent = 0.0D;

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public String getSysTypeName() {
        return sysTypeName;
    }

    public void setSysTypeName(String sysTypeName) {
        this.sysTypeName = sysTypeName;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getFree() {
        return free;
    }

    public void setFree(long free) {
        this.free = free;
    }

    public long getUsed() {
        return used;
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public long getAvail() {
        return avail;
    }

    public void setAvail(long avail) {
        this.avail = avail;
    }

    public long getFiles() {
        return files;
    }

    public void setFiles(long files) {
        this.files = files;
    }

    public long getFreeFiles() {
        return freeFiles;
    }

    public void setFreeFiles(long freeFiles) {
        this.freeFiles = freeFiles;
    }

    public long getDiskReads() {
        return diskReads;
    }

    public void setDiskReads(long diskReads) {
        this.diskReads = diskReads;
    }

    public long getDiskWrites() {
        return diskWrites;
    }

    public void setDiskWrites(long diskWrites) {
        this.diskWrites = diskWrites;
    }

    public long getDiskReadBytes() {
        return diskReadBytes;
    }

    public void setDiskReadBytes(long diskReadBytes) {
        this.diskReadBytes = diskReadBytes;
    }

    public long getDiskWriteBytes() {
        return diskWriteBytes;
    }

    public void setDiskWriteBytes(long diskWriteBytes) {
        this.diskWriteBytes = diskWriteBytes;
    }

    public double getDiskQueue() {
        return diskQueue;
    }

    public void setDiskQueue(double diskQueue) {
        this.diskQueue = diskQueue;
    }

    public double getDiskServiceTime() {
        return diskServiceTime;
    }

    public void setDiskServiceTime(double diskServiceTime) {
        this.diskServiceTime = diskServiceTime;
    }

    public double getUsePercent() {
        return usePercent;
    }

    public void setUsePercent(double usePercent) {
        this.usePercent = usePercent;
    }
}
