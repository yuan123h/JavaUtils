package com.hadoop.test.ipc;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.ipc.VersionedProtocol;

public interface MyProtocol  extends VersionedProtocol{

		public Text println(Text t);
}
