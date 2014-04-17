package com.spidersolitaire.database;

public class DatabaseQueries {
	public static String getMaxDataFromStack ="SELECT card FROM {0} WHERE id = MAX(id)";
	public static String insertCardToStack ="INSERT INTO {0}(card) VALUES ({1})";
	public static String deleteMaxdataFromStack ="DELETE FROM {0} WHERE id = MAX(id)";
	public static String deleteAlldataFromStack ="DELETE FROM {0}";
	public static String getDataFromStack ="SELECT card FROM {0} ORDER BY id ASC";
}
