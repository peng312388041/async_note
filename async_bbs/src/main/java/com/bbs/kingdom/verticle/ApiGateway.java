package com.bbs.kingdom.verticle;

import com.bbs.kingdom.web.api.NoteApi;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;

public class ApiGateway extends AbstractVerticle {

  @Override
  public void start() throws Exception {
	Router router =Router.router(vertx);
	//增
	router.put("/note/:noteid").handler(NoteApi::handlerPut);
	//删
	router.delete("/note/:noteid");
	//改
	router.post("/note/:noteid");
	//查
	router.get("/note/:noteid").handler(NoteApi::handlerGet);
	//list
	router.get("/notes");
	//增加
	router.get("/note");
    vertx.createHttpServer().requestHandler(router::accept).listen(8090);
  }
}
