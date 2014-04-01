package com.hbase.pageselect;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.util.Bytes;

//一个hbase 分页例子
public class demo {


/**
	 * 分页查询Trade数据
	 * @param startKey  开始键
	 * @param endKey    结束键
	 * @param pageSize  每页数据数
	 * @param pageNo    页码
	 */
	/*private TradeInfo getTradeInfoByModidied(String startKey, String endKey, int pageSize, int pageNo) throws Exception {
		long totalNums = 0;
		boolean hasNext = false;
		PageScanManager psm = PageScanManager.getInstance();
		PageScan pS = null;
		if(pageNo>=1){
			pS = psm.getPageScan(startKey, endKey, pageSize);
			startKey = pS.getPageStartKey(pageNo);
		}
		ResultScanner rs = HbaseUtils.getTradeByAssistantMTScan(startKey, endKey, pageSize);
		Result lastR = null;
		if(rs != null){
			List<Trade> tradeList = new ArrayList<Trade>();
			for (Result r : rs) {
				Trade trade = this.createTradeByRow(r);
				totalNums++;
				if(pageSize > 0) {
					if(totalNums <= pageSize) {
						if(trade!=null){
							tradeList.add(trade);
						}
					}
				} else {
					if(trade!=null){
						tradeList.add(trade);
					}
				}
				lastR = r;
			}
			rs.close();
			if(pS!=null&&totalNums==pageSize+1){
				hasNext = true;
				String mt = Bytes.toString(lastR.getValue(Bytes.toBytes(Constan.TRADE_FAMILY),Bytes.toBytes("ModifiedTime")));
				String row = Bytes.toString(lastR.getRow());
				String nextPageStartRow = HbaseUtils.getNextScanStartRow(mt, row);
				pS.setPageNoStartKey(pageNo+1, nextPageStartRow);
			}
			if(tradeList.size()>0){
				TradeInfo tradeInfo = new TradeInfo();
				Trades trades = new Trades();
				trades.setTrade(tradeList);
				tradeInfo.setTradeList(trades);
				tradeInfo.setHasNext(hasNext);
				tradeInfo.setPageNo(pageNo);
				return tradeInfo;
			}
		}
		return null;
		
	}*/
}