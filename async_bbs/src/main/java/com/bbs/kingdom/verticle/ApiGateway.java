package com.bbs.kingdom.verticle;

import com.bbs.kingdom.web.handler.NoteHandlers;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.TemplateHandler;
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine;

public class ApiGateway extends AbstractVerticle {

  @Override
  public void start() throws Exception {
	Router router =Router.router(vertx);
	router.get("/note/:noteid").handler(NoteHandlers::handlerGet);

//	router.put("/note/:noteid").handler(NoteHandlers::handlerPut);
//	router.delete("/note/:noteid");
//	router.post("/note/:noteid");
//	router.get("/notes");
    vertx.createHttpServer().requestHandler(router::accept).listen(8090);
  }
}
