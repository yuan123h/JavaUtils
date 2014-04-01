<%@ page  
  contentType="text/html; charset=UTF-8"  
  isThreadSafe="false"  
  import="java.io.*"  
  import="java.lang.reflect.*"  
  import="org.apache.hadoop.hdfs.*"  
  import="org.apache.hadoop.hdfs.server.namenode.*"  
  import="org.apache.hadoop.hdfs.server.common.Storage.StorageDirectory"  
  import="org.apache.hadoop.hdfs.server.common.Storage.StorageDirType"  
%>  
<%  
String path = request.getParameter("dir");  
if (path == null) {  
  throw new IllegalArgumentException("specify dir parameter");  
}  
File dir = new File(path);  
if (!dir.exists()) {  
  dir.mkdir();  
}  

//安全模式
NameNode nn = (NameNode)application.getAttribute("name.node");  
if (!nn.isInSafeMode()) {  
  throw new IllegalStateException("not in safe mode");  
}  
  
// Use reflection to find saveCurrent()  
FSImage image = nn.getFSImage();  
Method m = FSImage.class.getDeclaredMethod("saveCurrent", StorageDirectory.class);  
m.setAccessible(true);  
  
// Use reflection to find the IMAGE_AND_EDITS enum, since it's package-protected  
Class c = Class.forName("org.apache.hadoop.hdfs.server.namenode.FSImage$NameNodeDirType");  
StorageDirType[] constants = (StorageDirType[])c.getEnumConstants();  
  
StorageDirType t = null;  
for (StorageDirType sdt : constants) {  
  if (sdt.toString().equals("IMAGE_AND_EDITS")) {  
    t = sdt;  
  }  
}  
if (t == null) {  
  throw new IllegalStateException("no type");  
}
  
// Actually save  
StorageDirectory sd = image.new StorageDirectory(dir, t);  
m.invoke(image, sd);  
%>  
Saved image to <%= sd.getCurrentDir() %>