import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.util.Properties;

public class SigarGetCPU {


    public static void main(String[] args) throws SigarException {

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

        String userPers =  "";
        String sysPers =  "";
        String cpuPers = "";
        if(systemName.startsWith("win") || systemName.startsWith("Win")) {
            //Windows系统 Perc * 100
            userPers = String.format("%.1f", userUsedPerc * 100) + "%";
            sysPers = String.format("%.1f", sysUsedPerc * 100) + "%";
            cpuPers = String.format("%.1f", cpuUsedPerc * 100) + "%";
        }else {
            //Linux系统 Perc * 1000
            cpuPers = String.format("%.1f", cpuUsedPerc * 1000) + "%";
            sysPers = String.format("%.1f", sysUsedPerc * 1000) + "%";
            userPers = String.format("%.1f", userUsedPerc * 1000) + "%";
        }

        System.out.println("CPU======="+cpuinfo);
        System.out.println("CPU总数======="+totalCPUs);
        System.out.println("CPU基准速度======="+referenceSpeeds);
        System.out.println("CPU用户利用率======="+userPers);
        System.out.println("CPU系统利用率======="+sysPers);
        System.out.println("CPU利用率======="+cpuPers);

        // 关闭sigar
        sigar.close();

    }
}
