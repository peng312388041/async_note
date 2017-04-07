package com.bbs.kingdom.model.service;

import org.voltdb.VoltTableRow;
import org.voltdb.client.ClientResponse;

import com.bbs.kingdom.constants.NoteConstants;
import com.bbs.kingdom.vertx.event.EventManager;
import com.bbs.kingdom.voltdb.VoltdbClient;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

public class NoteService {
	private VoltdbClient client = new VoltdbClient("localhost");

	public void getNote(int noteId, Handler<AsyncResult<JsonObject>> handler) {
		client.callProcedure(res -> {

			if (res.getStatus() == ClientResponse.SUCCESS) {
				VoltTableRow row = res.getResults()[0];
				if (row.advanceRow()) {
					JsonObject result = new JsonObject();
					result.put(NoteConstants.COLUMN_ID, row.getLong(NoteConstants.COLUMN_ID));
					result.put(NoteConstants.COLUMN_PID, row.getLong(NoteConstants.COLUMN_PID));
					result.put(NoteConstants.COLUMN_TITLE, row.getString(NoteConstants.COLUMN_TITLE));
					result.put(NoteConstants.COLUMN_CONTENT, row.getString(NoteConstants.COLUMN_CONTENT));
					result.put(NoteConstants.COLUMN_AUTHOR_ID, row.getLong(NoteConstants.COLUMN_AUTHOR_ID));
					result.put(NoteConstants.COLUMN_PUBLISH_TIME, row.getLong(NoteConstants.COLUMN_PUBLISH_TIME));
					result.put(NoteConstants.COLUMN_LAST_UPDATE_TIME,
							row.getLong(NoteConstants.COLUMN_LAST_UPDATE_TIME));
					result.put(NoteConstants.COLUMN_VERSION, row.getLong(NoteConstants.COLUMN_VERSION));
					handler.handle(EventManager.generateEvent(true, result, null));
				}
				else{
					//TODO logger
				}
			} else {
				handler.handle(EventManager.generateEvent(false, null, new Throwable(res.getStatusString())));
			}
		}, "GetNoteProcedure", noteId);
	}

	public void putNote(JsonObject note, Handler<AsyncResult<Boolean>> handler) {
		client.callProcedure(res -> {
			if (res.getStatus() == ClientResponse.SUCCESS) {
				handler.handle(EventManager.generateEvent(true, null));
			} else {
				handler.handle(EventManager.generateEvent(false, new Throwable(res.getStatusString())));
			}
		}, "InsertNoteProcedure", note.getLong(NoteConstants.COLUMN_ID), note.getLong(NoteConstants.COLUMN_PID),
				note.getString(NoteConstants.COLUMN_TITLE), note.getString(NoteConstants.COLUMN_CONTENT),
				note.getLong(NoteConstants.COLUMN_AUTHOR_ID), note.getLong(NoteConstants.COLUMN_VERSION));
	}
}
