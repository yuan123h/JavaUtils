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
FSNamesystem fsNamesystem=nn.getNamesystem();  
Method mm = FSNamesystem.class.getDeclaredMethod("getEditLog", null);  
mm.setAccessible(true);  
FSEditLog editlog=(FSEditLog)mm.invoke(fsNamesystem,null);  
out.println("nn's editlog="+editlog.toString());  
  
  
Method mm1 = FSEditLog.class.getDeclaredMethod("getNumEditStreams", null);  
mm1.setAccessible(true);  
out.println("getNumEditStreams="+mm1.invoke(editlog,null));  
  
  
 Field field=FSEditLog.class.getDeclaredField("editStreams");  
 field.setAccessible(true);  
 ArrayList editStreams=(ArrayList)field.get(editlog);  
 out.println(editStreams.size());  
   
 out.println("<br/>begin to reset editStreams...");  
 editStreams.clear();  
 Class c = Class.forName("org.apache.hadoop.hdfs.server.namenode.FSEditLog$EditLogFileOutputStream");  
 Constructor constructor=c.getDeclaredConstructor(File.class);  
 constructor.setAccessible(true);  
 File f=new File("/analyser/hdfs/dfs/name/current/edits");  
 editStreams.add(constructor.newInstance(f));  
 //f=new File("/data0/hadoop/aernfs/name/current/edits");  
 //editStreams.add(constructor.newInstance(f));  
out.println("<br/> reset editStreams success!");  
 out.println("editStreams.size()="+editStreams.size());  
 out.println("getNumEditStreams="+mm1.invoke(editlog,null));  
  
  
  
  
  
  
  
%>