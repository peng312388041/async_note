package com.bbs.kingdom.vertx.event;

import io.vertx.core.AsyncResult;
import io.vertx.core.json.JsonObject;

public class EventManager {
	public static AsyncResult<JsonObject> generateEvent(boolean status,JsonObject result,Throwable cause)
	{
		AsyncResult<JsonObject> event=new AsyncResult<JsonObject>() {

			@Override
			public Throwable cause() {
				return cause;
			}

			@Override
			public boolean failed() {
				return !status;
			}

			@Override
			public JsonObject result() {
				return result;
			}

			@Override
			public boolean succeeded() {
				 return status;
			}
		};
		return event;
	}
	
	public static AsyncResult<Boolean> generateEvent(boolean status,Throwable cause)
	{
		AsyncResult<Boolean> event=new AsyncResult<Boolean>() {

			@Override
			public Throwable cause() {
				return cause;
			}

			@Override
			public boolean failed() {
				return !status;
			}

			@Override
			public Boolean result() {
				return status;
			}

			@Override
			public boolean succeeded() {
				 return status;
			}
		};
		return event;
	}
}
