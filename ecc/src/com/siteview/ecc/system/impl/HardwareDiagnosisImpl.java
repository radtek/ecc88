package com.siteview.ecc.system.impl;

import java.io.File;
import java.lang.management.ManagementFactory;

import org.zkoss.util.resource.Labels;

import com.siteview.ecc.system.Diagnosis;
import com.sun.management.OperatingSystemMXBean;

public class HardwareDiagnosisImpl extends Diagnosis {

	@Override
	public void execute() throws Exception {
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();  
		
		// 总的物理内存   
		long totalPhysicalMemorySize = osmxb.getTotalPhysicalMemorySize();
		// 剩余的物理内存    
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize(); 
        // 已使用的物理内存    
        long usedPhysicalMemorySize = (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize());

		getResultList().add(Labels.getLabel("CPUType:") + osmxb.getArch());
		getResultList().add(Labels.getLabel("NumberOfProcessors:") + osmxb.getAvailableProcessors());
		getResultList().add(Labels.getLabel("TotalPhysicalMemory:") + totalPhysicalMemorySize / 1024 / 1024 + "M");
		getResultList().add(Labels.getLabel("FreeMemory:") + freePhysicalMemorySize / 1024 / 1024 + "M");
		getResultList().add(Labels.getLabel("PhysicalMemoryUsed:") + usedPhysicalMemorySize / 1024 / 1024 + "M");
		File file = new File("/");
		if("".equals(file.getName())){
			getResultList().add(Labels.getLabel("RunDriveCapacity:") + file.getTotalSpace()/1024/1024/1024 + "G");
			getResultList().add(Labels.getLabel("FreeSpaceOnDriveProgram:") + file.getUsableSpace()/1024/1024/1024 + "G");		

		}else{
			getResultList().add(Labels.getLabel("RunDriver")+(" + file.getName() + ")+Labels.getLabel("Capacity:") + file.getTotalSpace()/1024/1024/1024 + "G");
			getResultList().add(Labels.getLabel("RunDriver")+(" + file.getName() + ")+Labels.getLabel("AvailableSpace:") + file.getUsableSpace()/1024/1024/1024 + "G");		

		}
	
		
		/*
		System.out.println("osm.getArch() "+osmxb.getArch());   
        System.out.println("osm.getAvailableProcessors() "+osmxb.getAvailableProcessors());   
        System.out.println("osm.getCommittedVirtualMemorySize() "+osmxb.getCommittedVirtualMemorySize());   
        System.out.println("osm.getName() "+osmxb.getName());   
        System.out.println("osm.getProcessCpuTime() "+osmxb.getProcessCpuTime());   
        System.out.println("osm.getVersion() "+osmxb.getVersion());
        
        MemoryMXBean mm=(MemoryMXBean)ManagementFactory.getMemoryMXBean();   
        System.out.println("getHeapMemoryUsage "+mm.getHeapMemoryUsage());   
        System.out.println("getNonHeapMemoryUsage "+mm.getNonHeapMemoryUsage());   

        ThreadMXBean tm=(ThreadMXBean)ManagementFactory.getThreadMXBean(); 
		System.out.println("getThreadCount "+tm.getThreadCount());   
        System.out.println("getPeakThreadCount "+tm.getPeakThreadCount());   
        System.out.println("getCurrentThreadCpuTime "+tm.getCurrentThreadCpuTime());   
        System.out.println("getDaemonThreadCount "+tm.getDaemonThreadCount());   
        System.out.println("getCurrentThreadUserTime "+tm.getCurrentThreadUserTime());   

        RuntimeMXBean rmb=(RuntimeMXBean)ManagementFactory.getRuntimeMXBean();   
        System.out.println("getClassPath "+rmb.getClassPath());   
        System.out.println("getLibraryPath "+rmb.getLibraryPath());   
        System.out.println("getVmVersion "+rmb.getVmVersion());  
        */
		
		
		if ((totalPhysicalMemorySize / 1024 / 1024) < 2000)
			throw new Exception(Labels.getLabel("RecommendLeast2GTotalPhysicalMemory"));
		if ((freePhysicalMemorySize / 1024 / 1024) < 200)
			throw new Exception(Labels.getLabel("FreeMemoryLess200M"));
		if ((file.getUsableSpace() / 1024 / 1024) < 500)
			throw new Exception(Labels.getLabel("RunDriver")+(" + file.getName() + ")+Labels.getLabel("NotEnoughFreeSpaceLess500M"));
        
	}

	@Override
	public String getDescription() {
		return Labels.getLabel("HardwareDiagnosticsInfo");
	}

	@Override
	public String getName() {
		return Labels.getLabel("HardwareDiagnostics");
	}

}
