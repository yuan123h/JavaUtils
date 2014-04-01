package com.test.java.thread;


import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
 
public abstract class ThreadMonitoring {
 
    private static final ThreadMXBean threadBean = ManagementFactory
            .getThreadMXBean();
    private static final int STACK_DEPTH = 20;
 
    public static ThreadInfo getThreadInfo(Thread t) {
        long tid = t.getId();
        return threadBean.getThreadInfo(tid, STACK_DEPTH);
    }
 
    /**
     * Format the given ThreadInfo object as a String.
     * 
     * @param indent
     *            a prefix for each line, used for nested indentation
     */
    public static String formatThreadInfo(ThreadInfo threadInfo, String indent) {
        StringBuilder sb = new StringBuilder();
        appendThreadInfo(sb, threadInfo, indent);
        return sb.toString();
    }
 
    /**
     * Print all of the thread's information and stack traces.
     * 
     * @param sb
     * @param info
     * @param indent
     */
    public static void appendThreadInfo(StringBuilder sb, ThreadInfo info,
            String indent) {
        boolean contention = threadBean.isThreadContentionMonitoringEnabled();
 
        if (info == null) {
            sb.append(indent).append(
                    "Inactive (perhaps exited while monitoring was done)\n");
            return;
        }
        String taskName = getTaskName(info.getThreadId(), info.getThreadName());
        sb.append(indent).append("Thread ").append(taskName).append(":\n");
 
        Thread.State state = info.getThreadState();
        sb.append(indent).append("  State: ").append(state).append("\n");
        sb.append(indent).append("  Blocked count: ")
                .append(info.getBlockedCount()).append("\n");
        sb.append(indent).append("  Waited count: ")
                .append(info.getWaitedCount()).append("\n");
        if (contention) {
            sb.append(indent)
                    .append("  Blocked time: " + info.getBlockedTime())
                    .append("\n");
            sb.append(indent).append("  Waited time: " + info.getWaitedTime())
                    .append("\n");
        }
        if (state == Thread.State.WAITING) {
            sb.append(indent).append("  Waiting on ")
                    .append(info.getLockName()).append("\n");
        } else if (state == Thread.State.BLOCKED) {
            sb.append(indent).append("  Blocked on ")
                    .append(info.getLockName()).append("\n");
            sb.append(indent)
                    .append("  Blocked by ")
                    .append(getTaskName(info.getLockOwnerId(),
                            info.getLockOwnerName())).append("\n");
        }
        sb.append(indent).append("  Stack:").append("\n");
        for (StackTraceElement frame : info.getStackTrace()) {
            sb.append(indent).append("    ").append(frame.toString())
                    .append("\n");
        }
    }
 
    private static String getTaskName(long id, String name) {
        if (name == null) {
            return Long.toString(id);
        }
        return id + " (" + name + ")";
    }
 
}
 