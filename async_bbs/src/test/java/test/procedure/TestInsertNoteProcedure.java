package test.procedure;

import com.bbs.kingdom.constants.NoteConstants;
import com.bbs.kingdom.model.service.NoteService;

import io.vertx.core.json.JsonObject;

public class TestInsertNoteProcedure {

	public static void main(String[] args) throws InterruptedException {
		NoteService noteService = new NoteService();
		JsonObject note = new JsonObject();
		note.put(NoteConstants.COLUMN_ID, 1);
		note.put(NoteConstants.COLUMN_PID, 0);
		note.put(NoteConstants.COLUMN_TITLE, "title");
		note.put(NoteConstants.COLUMN_CONTENT, "content");
		note.put(NoteConstants.COLUMN_AUTHOR_ID, 2);
		// note.put(NoteConstants.COLUMN_PUBLISH_TIME,
		// System.currentTimeMillis());
		// note.put(NoteConstants.COLUMN_LAST_UPDATE_TIME,
		// System.currentTimeMillis());
		note.put(NoteConstants.COLUMN_VERSION, 1);
		noteService.putNote(note, res -> {
			if (res.succeeded()) {
				System.out.println("success");
			} else {
				System.out.println(res.cause());
			}
		});
		Thread.sleep(100);
	}
}
