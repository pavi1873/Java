package com.sharekhan.database.lib;

public class DatabaseQueries {
	public static String getDownloadSoftwareCount =" SELECT COUNT( * ) AS total_count FROM  download_software WHERE DATE( date ) = CURRENT_DATE";
	public static String insertDownloadSoftware ="INSERT INTO download_software(date) VALUES (CURRENT_TIMESTAMP) ";
	public static String getBalanceCount =" SELECT COUNT( * ) AS total_count FROM  balance WHERE DATE( date ) = CURRENT_DATE";
	public static String insertBalanceAmount ="INSERT INTO balance(balance) VALUES ({1}) ";
	public static String getMarketStockTipsCount =" SELECT COUNT( * ) AS total_count FROM  market_stock_tips WHERE status = 'new'";
	public static String getNewMarketStockTips =" SELECT id,tip FROM  market_stock_tips WHERE status = 'new'";
	public static String updateMarketStockTipsAsProcessed =" UPDATE market_stock_tips SET status = 'processed' WHERE id = {0}";
	public static String insertProcessedTip =" INSERT INTO processed_tip('tip_id', 'tip_type', 'trade_type', 'scrip_name', 'scrip_amount', 'creation_ts','status') VALUES ({1},'{2}','{3}','{4}',{5},CURRENT_DATE,'new')";
	public static String insertProcessedTipData =" INSERT INTO processed_tip_data('processed_tip_id', 'tip_data_type', 'amount', 'creation_ts') VALUES ({1},'{2}',{3},CURRENT_DATE)";
	public static String getProcessedTipID =" SELECT id FROM  processed_tip WHERE creation_ts = MAX(creation_ts)";
	public static String getLatestBalance =" SELECT balance FROM balance WHERE DATE( date ) = CURRENT_DATE and id = MAX(id)";
	public static String insertPreTradeDetails =" INSERT INTO pre_trade ('scrip_name', 'trade_type', 'stock_exchange', 'quantity', 'trade_amount', 'stop_loss', 'status', 'creation_ts') VALUES ( '{0}','{1}','{2}',{3}, {4}, {5},'{6}', CURRENT_TIMESTAMP)";
	public static String getTradePerfromedCountPerDate =" SELECT COUNT( * ) AS total_count FROM  trade_performed WHERE DATE( date ) = CURRENT_DATE";
	public static String insertTradePerformed = "INSERT INTO trade_performed( 'date', 'count') VALUES (CURRENT_DATE,1)";
	public static String updateTradePerformed = "UPDATE trade_performed SET count= count+1 WHERE DATE( date ) = CURRENT_DATE ";
	public static String getNewProcessedTips =" SELECT * FROM  processed_tip WHERE status = 'new'";
	public static String getProcessedTipsCount =" SELECT COUNT( * ) AS total_count FROM  processed_tip	 WHERE status = 'new'";
 
}
