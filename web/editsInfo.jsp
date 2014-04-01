<%@ page  
  contentType="text/html; charset=UTF-8"  
  isThreadSafe="false"  
  import="java.io.*"  
  import="java.util.*"  
  import="java.lang.reflect.*"  
  import="org.apache.hadoop.hdfs.*"  
  import="org.apache.hadoop.hdfs.server.namenode.*"  
  import="org.apache.hadoop.hdfs.server.common.Storage.StorageDirectory"  
  import="org.apache.hadoop.hdfs.server.common.Storage.StorageDirType"  
%>  
  
<%  
   NameNode nn = (NameNode)application.getAttribute("name.node");  
     
   out.println("namenode="+nn.toString());  
   final FSImage nnImage = (FSImage)application.getAttribute("name.system.image");  
   out.println("storagedirs=" + nnImage.listStorageDirectories());  
  Method m = FSImage.class.getDeclaredMethod("getFsImageName", null);  
  m.setAccessible(true);  
  out.println("nnImage.getFsImageName()="+m.invoke(nnImage,null));  
   
   out.println("httpserver name.system.image="+nnImage.toString());  
   out.println("getFsImage from nn="+nn.getFSImage());  
  out.println("<br/>");  
   File eFile=new File("/data0/hadoop/hdfs/name/current/edits");  
   RandomAccessFile rp = new RandomAccessFile(eFile, "rw");  
   FileOutputStream  fp = new FileOutputStream(rp.getFD());  
  // FSEditLog.EditLogOutputStream eStream = new FSEditLog.EditLogFileOutputStream(eFile);  
   out.println("fileoutputstream="+fp.toString());     
  
   out.println("<br/>");  
  m = FSImage.class.getDeclaredMethod("getRemovedStorageDirs", null);  
  m.setAccessible(true);  
 List<StorageDirectory> list=(List<StorageDirectory>)m.invoke(nnImage,null);  
  out.println("removedStorageDirs.size="+list.size());  
  for(StorageDirectory dir:list)  
   out.println("removeddir="+dir.getRoot().getPath().toString());  
  
out.println("<br/>");  
FSNamesystem fsNamesystem=nn.getNamesystem();  
Method mm = FSNamesystem.class.getDeclaredMethod("getEditLog", null);  
mm.setAccessible(true);  
FSEditLog editlog=(FSEditLog)mm.invoke(fsNamesystem,null);  
out.println("nn's editlog="+editlog.toString());  
  
  
Method mm1 = FSEditLog.class.getDeclaredMethod("getNumEditStreams", null);  
mm1.setAccessible(true);  
out.println("getNumEditStreams="+mm1.invoke(editlog,null));  
%> 