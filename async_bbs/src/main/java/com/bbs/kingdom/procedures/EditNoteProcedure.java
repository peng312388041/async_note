package com.bbs.kingdom.procedures;

import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

public class EditNoteProcedure extends VoltProcedure{
	private final String sql="update bbs_note set "
			+ " pid=?, "
			+ " title =?, "
			+ " content =?, "
			+ " author_id =?, "
			+ " publish_time=?, "
			+ " last_update_time=?, "
			+ " version =? "
			+ " where id = ?";
	public final SQLStmt sqlStmt=new SQLStmt(sql);
	public VoltTable[] run(long id,long pid,String title,String content,long author_id,int version) throws VoltAbortException{
		voltQueueSQL(sqlStmt,pid,title,content,author_id,System.currentTimeMillis(),System.currentTimeMillis(),version,id);
		return voltExecuteSQL(true);
	}
}


   
   
		   
 	