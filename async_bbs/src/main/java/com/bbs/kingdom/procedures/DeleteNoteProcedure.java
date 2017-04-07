package com.bbs.kingdom.procedures;

import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

public class DeleteNoteProcedure extends VoltProcedure{
	private final String sql="delete from bbs_user where id=?";
	public final SQLStmt sqlStmt=new SQLStmt(sql);
	public VoltTable[] run(long id) throws VoltAbortException{
		voltQueueSQL(sqlStmt,id);
		return voltExecuteSQL(true);
	}
}


   
   
		   
 	