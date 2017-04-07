package com.bbs.kingdom.procedures;

import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

public class InsertNoteProcedure extends VoltProcedure{
	private final String sql="insert into bbs_note values(?,?,?,?,?,?,?,?)";
	public final SQLStmt sqlStmt=new SQLStmt(sql);
	public VoltTable[] run(long id,long pid,String title,String content,long author_id,int version) throws VoltAbortException{
		voltQueueSQL(sqlStmt,id,pid,title,content,author_id,System.currentTimeMillis(),System.currentTimeMillis(),version);
		return voltExecuteSQL(true);
	}
}


   
   
		   
 	