package com.marketstocktips.database.lib;

public class DatabaseQueries {
	public static String getDownloadSoftwareCount =" SELECT COUNT( * ) AS total_count FROM  download_software WHERE DATE( date ) = CURRENT_DATE";
	public static String getOpeningBalanceCount =" SELECT COUNT( * ) AS total_count FROM  opening_balance WHERE DATE( date ) = CURRENT_DATE";
	public static String getMarketStockTipsCount =" SELECT COUNT( * ) AS total_count FROM  market_stock_tips WHERE status = 'new'";
	public static String getNewMarketStockTips =" SELECT id,tip FROM  market_stock_tips WHERE status = 'new'";
	public static String updateMarketStockTipsStatus =" UPDATE market_stock_tips SET status = '{2}' WHERE id = {1}";
	public static String insertProcessedTip =" INSERT INTO processed_tip(tip_id, tip_type, trade_type, scrip_name, scrip_amount, status) VALUES ({1},'{2}','{3}','{4}',{5},'new')";
	public static String insertProcessedTipData =" INSERT INTO processed_tip_data(processed_tip_id, tip_data_type, amount) VALUES ({1},'{2}',{3})";
	public static String getProcessedTipID =" SELECT id FROM  processed_tip WHERE creation_ts = (SELECT MAX(creation_ts) FROM processed_tip)";
	public static String getLatestBalance =" SELECT balance FROM opening_balance WHERE DATE( date ) = CURRENT_DATE and id =(SELECT MAX(id) FROM opening_balance)";
	public static String insertPreTradeDetails =" INSERT INTO pre_trade (scrip_name, trade_type, stock_exchange, quantity, trade_amount, stop_loss, status, creation_ts) VALUES ( '{0}','{1}','{2}',{3}, {4}, {5},'{6}', CURRENT_TIMESTAMP)";
	public static String getTradePerfromedCountPerDate =" SELECT COUNT( * ) AS total_count FROM  trade_performed WHERE DATE( date ) = CURRENT_DATE";
	public static String insertTradePerformed = "INSERT INTO trade_performed( date, count) VALUES (CURRENT_DATE,1)";
	public static String updateTradePerformed = "UPDATE trade_performed SET count= count+1 WHERE DATE( date ) = CURRENT_DATE ";
	public static String getNewProcessedTips =" SELECT * FROM  processed_tip WHERE status = 'new'";
	public static String insertMarketStockTips ="INSERT INTO market_stock_tips(tip,tip_date, status) VALUES ('{1}','{2}','new')";
	public static String checkForMarketStockTips = "SELECT COUNT(1) AS total_count FROM market_stock_tips WHERE tip = '{1}' AND  tip_date = '{2}'";
}
