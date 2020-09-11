package controller;

import controller.dto.CPUInfoDTO;
import org.hyperic.sigar.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

@RequestMapping(value = "/TestController")
@RestController
public class TestController {

    @RequestMapping("/test")
    public int test(){
        int max=100,min=1;
        int ran2 = (int) (Math.random()*(max-min)+min);
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

        Integer userPers =  0;
        Integer sysPers =  0;
        Integer cpuPers = 0;
        if(systemName.startsWith("win") || systemName.startsWith("Win")) {
            //Windows系统 Perc * 100
            userPers =(int) (userUsedPerc * 100) ;
            sysPers =(int) (sysUsedPerc * 100) ;
            cpuPers =(int) (cpuUsedPerc * 100) ;
        }else {
            //Linux系统 Perc * 1000
            cpuPers =(int) (cpuUsedPerc * 1000) ;
            sysPers =(int) (sysUsedPerc * 1000) ;
            userPers =(int) (userUsedPerc * 1000) ;
        }

        System.out.println("CPU======="+cpuinfo);
        System.out.println("CPU总数======="+totalCPUs);
        System.out.println("CPU基准速度======="+referenceSpeeds);
        System.out.println("CPU用户利用率======="+userPers);
        System.out.println("CPU系统利用率======="+sysPers);
        System.out.println("CPU利用率======="+cpuPers);


        // 物理内存信息
        Mem mem = sigar.getMem();
// 内存总量
        System.out.println("Total = " + mem.getTotal() / 1024L / 1024 /1024.0 + "M av");
// 当前内存使用量
        System.out.println("Used = " + mem.getUsed() / 1024L / 1024 /1024.0+ "M used");


        cpuInfoDTO.setCpuinfo(cpuinfo);
        cpuInfoDTO.setTotalCPUs(totalCPUs);
        cpuInfoDTO.setReferenceSpeeds(referenceSpeeds);
        cpuInfoDTO.setSysPers(sysPers);
        cpuInfoDTO.setUserPers(userPers);
        cpuInfoDTO.setCpuPers(cpuPers);


        cpuInfoDTO.setmAll(String.valueOf(new BigDecimal(mem.getTotal() / 1024L / 1024 /1024.0 ).setScale(0, BigDecimal.ROUND_CEILING ) ));
        cpuInfoDTO.setmUsed(String.valueOf(new BigDecimal( mem.getUsed() / 1024L / 1024 /1024.0 ).setScale(2, RoundingMode.HALF_UP)));






        sigar.close();


        return cpuInfoDTO;
    }
}
