package com.bbs.kingdom.web.api;

import org.apache.commons.lang3.StringUtils;

import com.bbs.kingdom.constants.NoteConstants;
import com.bbs.kingdom.model.service.NoteService;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine;

public class NoteApi {
	private static NoteService noteService = new NoteService();

	public static void handlerGet(RoutingContext context) {
		HttpServerResponse response = context.response();
		HttpServerRequest request = context.request();

		String id = request.getParam("noteid");
		int noteId = Integer.parseInt(id);
		noteService.getNote(noteId, resultGet -> {
			if (resultGet.succeeded()) {
				context.put("result", resultGet.result());
				FreeMarkerTemplateEngine engine = FreeMarkerTemplateEngine.create();
				engine.render(context, "templates/note/note_get.ftl", res -> {
					if (res.succeeded()) {
						response.end(res.result());
					} else {
						context.fail(res.cause());
					}
				});
			} else {
				response.end("failed");
			}
		});
	}

	public static void handlerPut(RoutingContext context) {
		HttpServerResponse response = context.response();
		HttpServerRequest request = context.request();
		String noteId = request.getParam("id");
		//if noteId is empty ,return the template
		if (StringUtils.isEmpty(noteId)) {
			
		}
		JsonObject result = new JsonObject();
		result.put(NoteConstants.COLUMN_ID, request.getParam(NoteConstants.COLUMN_ID));
		result.put(NoteConstants.COLUMN_PID, request.getParam(NoteConstants.COLUMN_PID));
		result.put(NoteConstants.COLUMN_TITLE, request.getParam(NoteConstants.COLUMN_TITLE));
		result.put(NoteConstants.COLUMN_CONTENT, request.getParam(NoteConstants.COLUMN_CONTENT));
		result.put(NoteConstants.COLUMN_AUTHOR_ID, request.getParam(NoteConstants.COLUMN_AUTHOR_ID));
		result.put(NoteConstants.COLUMN_PUBLISH_TIME, request.getParam(NoteConstants.COLUMN_PUBLISH_TIME));
		result.put(NoteConstants.COLUMN_LAST_UPDATE_TIME, request.getParam(NoteConstants.COLUMN_LAST_UPDATE_TIME));
		result.put(NoteConstants.COLUMN_VERSION, request.getParam(NoteConstants.COLUMN_VERSION));
		noteService.putNote(result, resultPut -> {
			if (resultPut.succeeded()) {
				response.end();
			} else {
				response.end(resultPut.cause().toString());
			}
		});
	}
}
