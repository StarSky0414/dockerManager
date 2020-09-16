package controller;

import controller.dto.CPUInfoDTO;
import controller.dto.FileSysInfo;
import org.hyperic.sigar.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Properties;

@RequestMapping(value = "/TestController")
@RestController
public class TestController {

    @RequestMapping("/test")
    public int test() {
        int max = 100, min = 1;
        int ran2 = (int) (Math.random() * (max - min) + min);
        return ran2;
    }

    @RequestMapping("/testCpu")
    public CPUInfoDTO testCpu() throws SigarException {

        CPUInfoDTO cpuInfoDTO = new CPUInfoDTO();

        Properties props = System.getProperties();
        String systemName = props.getProperty("os.name");

        // 使用sigar获取CPU信息及内存
        Sigar sigar = new Sigar();

        // 获取CPU信息
        CpuInfo[] infos = sigar.getCpuInfoList();

        CpuInfo infoss = infos[0];

        CpuPerc cpu = sigar.getCpuPerc();
        // CPU总数
        Integer totalCPUs = infoss.getTotalCores();

        String cpuinfo = infoss.getVendor() + "  " + infoss.getModel();
        // 获取CPU基准速度
        double referenceSpeed = infoss.getMhz();

        String referenceSpeeds = String.format("%.2f", referenceSpeed / 1000) + " GHz";

        // 获取CPU用户利用率
        double userUsedPerc = cpu.getUser();
        // 获取CPU系统利用率
        double sysUsedPerc = cpu.getSys();
        // 获取CPU利用率
        double cpuUsedPerc = cpu.getCombined();

        Integer userPers = 0;
        Integer sysPers = 0;
        Integer cpuPers = 0;
        if (systemName.startsWith("win") || systemName.startsWith("Win")) {
            //Windows系统 Perc * 100
            userPers = (int) (userUsedPerc * 100);
            sysPers = (int) (sysUsedPerc * 100);
            cpuPers = (int) (cpuUsedPerc * 100);
        } else {
            //Linux系统 Perc * 1000
            cpuPers = (int) (cpuUsedPerc * 1000);
            sysPers = (int) (sysUsedPerc * 1000);
            userPers = (int) (userUsedPerc * 1000);
        }

        System.out.println("CPU=======" + cpuinfo);
        System.out.println("CPU总数=======" + totalCPUs);
        System.out.println("CPU基准速度=======" + referenceSpeeds);
        System.out.println("CPU用户利用率=======" + userPers);
        System.out.println("CPU系统利用率=======" + sysPers);
        System.out.println("CPU利用率=======" + cpuPers);


        // 物理内存信息
        Mem mem = sigar.getMem();
// 内存总量
        System.out.println("Total = " + mem.getTotal() / 1024L / 1024 / 1024.0 + "M av");
// 当前内存使用量
        System.out.println("Used = " + mem.getUsed() / 1024L / 1024 / 1024.0 + "M used");


        cpuInfoDTO.setCpuinfo(cpuinfo);
        cpuInfoDTO.setTotalCPUs(totalCPUs);
        cpuInfoDTO.setReferenceSpeeds(referenceSpeeds);
        cpuInfoDTO.setSysPers(sysPers);
        cpuInfoDTO.setUserPers(userPers);
        cpuInfoDTO.setCpuPers(cpuPers);


        cpuInfoDTO.setmAll(String.valueOf(new BigDecimal(mem.getTotal() / 1024L / 1024 / 1024.0).setScale(0, BigDecimal.ROUND_CEILING)));
        cpuInfoDTO.setmUsed(String.valueOf(new BigDecimal(mem.getUsed() / 1024L / 1024 / 1024.0).setScale(2, RoundingMode.HALF_UP)));
        cpuInfoDTO.setmUsedProportion(String.valueOf(new BigDecimal(mem.getUsed() / 1.0 / mem.getTotal()).setScale(2, RoundingMode.HALF_UP)));

        FileSystem[] fileSystemArray = sigar.getFileSystemList();
        ArrayList<FileSysInfo> fileSysInfos = new ArrayList<>();

        for (FileSystem fileSystem : fileSystemArray) {

            FileSysInfo fileSysInfo = new FileSysInfo();
            System.out.println("fileSystem dirName：" + fileSystem.getDirName());//分区的盘符名称
            System.out.println("fileSystem devName：" + fileSystem.getDevName());//分区的盘符名称
            System.out.println("fileSystem typeName：" + fileSystem.getTypeName());// 文件系统类型名，比如本地硬盘、光驱、网络文件系统等
            System.out.println("fileSystem sysTypeName：" + fileSystem.getSysTypeName());//文件系统类型，比如 FAT32、NTFS
            System.out.println("fileSystem options：" + fileSystem.getOptions());
            System.out.println("fileSystem flags：" + fileSystem.getFlags());
            System.out.println("fileSystem type：" + fileSystem.getType());
            fileSysInfo.setDirName(fileSystem.getDirName());
            fileSysInfo.setSysTypeName(fileSystem.getSysTypeName());
            FileSystemUsage fileSystemUsage = null;

            try {
                fileSystemUsage = sigar.getFileSystemUsage(fileSystem.getDirName());
            } catch (SigarException e) {//当fileSystem.getType()为5时会出现该异常——此时文件系统类型为光驱
                continue;
            }
            System.out.println("fileSystemUsage total：" + fileSystemUsage.getTotal() + "KB");// 文件系统总大小
            System.out.println("fileSystemUsage free：" + fileSystemUsage.getFree() + "KB");// 文件系统剩余大小
            System.out.println("fileSystemUsage used：" + fileSystemUsage.getUsed() + "KB");// 文件系统已使用大小
            System.out.println("fileSystemUsage avail：" + fileSystemUsage.getAvail() + "KB");// 文件系统可用大小
            System.out.println("fileSystemUsage files：" + fileSystemUsage.getFiles());
            System.out.println("fileSystemUsage freeFiles：" + fileSystemUsage.getFreeFiles());
            System.out.println("fileSystemUsage diskReadBytes：" + fileSystemUsage.getDiskReadBytes());
            System.out.println("fileSystemUsage diskWriteBytes：" + fileSystemUsage.getDiskWriteBytes());
            System.out.println("fileSystemUsage diskQueue：" + fileSystemUsage.getDiskQueue());
            System.out.println("fileSystemUsage diskServiceTime：" + fileSystemUsage.getDiskServiceTime());
            System.out.println("fileSystemUsage usePercent：" + fileSystemUsage.getUsePercent() * 100 + "%");// 文件系统资源的利用率
            System.out.println("fileSystemUsage diskReads：" + fileSystemUsage.getDiskReads());
            System.out.println("fileSystemUsage diskWrites：" + fileSystemUsage.getDiskWrites());
            System.err.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

            fileSysInfo.setUsePercent(fileSystemUsage.getUsePercent() * 100);
            fileSysInfo.setTotal(fileSystemUsage.getTotal());
            fileSysInfo.setUsed(fileSystemUsage.getUsed());
            fileSysInfos.add(fileSysInfo);
            fileSysInfos.add(fileSysInfo);
        }

        cpuInfoDTO.setSystemName(systemName);

        cpuInfoDTO.setFileSysInfos(fileSysInfos);

        sigar.close();


        return cpuInfoDTO;
    }
}
