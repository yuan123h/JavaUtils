package com.test.java.jvm;

import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.List;

//JAVA 平台MXBean 是一种托管 Bean，它符合 JMX Instrumentation Specification,
//提供管理接口，用于监视和管理 Java 虚拟机以及 Java 虚拟机在其上运行的操作系统。
public class JvmParas {
	// =======================通过java来获取相关系统状态============================
	// 总的内存量 i is 32576
	// 空闲内存量 j is 32357
	// 最大内存量 is 812928

	/**
	 * @param 直接通过jdk来获取系统相关状态
	 *            ，在1.5.0_10-b03版本以上测试通过
	 */
	public static void main(String[] args) {

		System.out
				.println("=======================通过java来获取相关系统状态============================ ");
		int i = (int) Runtime.getRuntime().totalMemory() / 1024;// Java
																// 虚拟机中的内存总量,以字节为单位
		System.out.println("总的内存量 i is " + i);
		int j = (int) Runtime.getRuntime().freeMemory() / 1024;// Java
																// 虚拟机中的空闲内存量
		System.out.println("空闲内存量 j is " + j);
		System.out.println("最大内存量 is " + Runtime.getRuntime().maxMemory()
				/ 1024);

		System.out
				.println("=======================OperatingSystemMXBean============================ ");
		OperatingSystemMXBean osm = (OperatingSystemMXBean) ManagementFactory
				.getOperatingSystemMXBean();
//		System.out.println(osm.getFreeSwapSpaceSize() / 1024);
//		System.out.println(osm.getFreePhysicalMemorySize() / 1024);
//		System.out.println(osm.getTotalPhysicalMemorySize() / 1024);

		// 获取操作系统相关信息
		System.out.println("osm.getArch() " + osm.getArch());
		System.out.println("osm.getAvailableProcessors() "
				+ osm.getAvailableProcessors());
//		System.out.println("osm.getCommittedVirtualMemorySize() "
//				+ osm.getCommittedVirtualMemorySize());
		System.out.println("osm.getName() " + osm.getName());
//		System.out
//				.println("osm.getProcessCpuTime() " + osm.getProcessCpuTime());
		System.out.println("osm.getVersion() " + osm.getVersion());
		// 获取整个虚拟机内存使用情况
		System.out
				.println("=======================MemoryMXBean============================ ");
		MemoryMXBean mm = (MemoryMXBean) ManagementFactory.getMemoryMXBean();
		System.out.println("getHeapMemoryUsage " + mm.getHeapMemoryUsage());
		System.out.println("getNonHeapMemoryUsage "
				+ mm.getNonHeapMemoryUsage());
		
		// 获取各个线程的各种状态，CPU 占用情况，以及整个系统中的线程状况
		System.out
				.println("=======================ThreadMXBean============================ ");
		ThreadMXBean tm = (ThreadMXBean) ManagementFactory.getThreadMXBean();
		System.out.println("getThreadCount " + tm.getThreadCount());
		System.out.println("getPeakThreadCount " + tm.getPeakThreadCount());
		System.out.println("getCurrentThreadCpuTime "
				+ tm.getCurrentThreadCpuTime());
		System.out.println("getDaemonThreadCount " + tm.getDaemonThreadCount());
		System.out.println("getCurrentThreadUserTime "
				+ tm.getCurrentThreadUserTime());

		// 当前编译器情况
		System.out
				.println("=======================CompilationMXBean============================ ");
		CompilationMXBean gm = (CompilationMXBean) ManagementFactory
				.getCompilationMXBean();
		System.out.println("getName " + gm.getName());
		System.out.println("getTotalCompilationTime "
				+ gm.getTotalCompilationTime());

		// 获取多个内存池的使用情况
		System.out
				.println("=======================MemoryPoolMXBean============================ ");
		List<MemoryPoolMXBean> mpmList = ManagementFactory
				.getMemoryPoolMXBeans();
		for (MemoryPoolMXBean mpm : mpmList) {
			System.out.println("getUsage " + mpm.getUsage());
			System.out.println("getMemoryManagerNames "
					+ mpm.getMemoryManagerNames().toString());
		}
		
		// 获取GC的次数以及花费时间之类的信息
		System.out
				.println("=======================MemoryPoolMXBean============================ ");
		List<GarbageCollectorMXBean> gcmList = ManagementFactory
				.getGarbageCollectorMXBeans();
		for (GarbageCollectorMXBean gcm : gcmList) {
			System.out.println("getName " + gcm.getName());
			System.out
					.println("getMemoryPoolNames " + gcm.getMemoryPoolNames());
		}
		// 获取运行时信息
		System.out
				.println("=======================RuntimeMXBean============================ ");
		RuntimeMXBean rmb = (RuntimeMXBean) ManagementFactory
				.getRuntimeMXBean();
		System.out.println("getClassPath " + rmb.getClassPath());
		System.out.println("getLibraryPath " + rmb.getLibraryPath());
		System.out.println("getVmVersion " + rmb.getVmVersion());
	}

}
