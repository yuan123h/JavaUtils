<%@ page  
  contentType="text/html; charset=UTF-8"  
  isThreadSafe="false"  
  import="java.io.*"  
  import="java.util.*"  
  import="java.lang.reflect.*"  
  import="org.apache.hadoop.hdfs.*"  
  import="org.apache.hadoop.conf.*"  
  import="org.apache.hadoop.hdfs.server.namenode.*"  
  import="org.apache.hadoop.hdfs.server.common.Storage.StorageDirectory"  
  import="org.apache.hadoop.hdfs.server.common.Storage.StorageDirType"  
%>  
  
<%  
   NameNode nn = (NameNode)application.getAttribute("name.node");  
     
   out.println("namenode="+nn.toString());  
   final FSImage nnImage = (FSImage)application.getAttribute("name.system.image");  
   out.println("storagedirs="+nnImage.listStorageDirectories());  
  Method m = FSImage.class.getDeclaredMethod("getFsImageName", null);  
  m.setAccessible(true);  
  out.println("nnImage.getFsImageName()="+m.invoke(nnImage,null));  
      
  out.println("<br/>begin resetStorageDirectories...");  
  Method m1=FSImage.class.getDeclaredMethod("setStorageDirectories", Collection.class,Collection.class);  
  m1.setAccessible(true);  
  Configuration conf = new Configuration();  
  m1.invoke(nnImage,FSNamesystem.getNamespaceDirs(conf),FSNamesystem.getNamespaceEditsDirs(conf));  
  out.println("<br/> resetStorageDirectories success!");  
  out.println("<br/>");  
 out.println("nnImage.getFsImageName()="+m.invoke(nnImage,null));  
  out.println("storagedirs="+nnImage.listStorageDirectories());  
%> 