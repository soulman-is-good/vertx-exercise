package kz.soulman.firstmaven;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;
import java.io.IOException;

/**
 *
 * @author maxim
 */
public class HttpVerticle extends AbstractVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(HttpVerticle.class);
    
    @Override
    public void start() {
        int port = config().getInteger("http.port", 8080);

        Router router = Router.router(vertx);

        router.get("/test").handler(ctx -> ctx.response().putHeader("content-type", "text/json").end("test"));
        router.errorHandler(404, this::handle404);
        router.route().handler(StaticHandler.create().setCachingEnabled(false));

        vertx.createHttpServer()
            .requestHandler(router)
            .listen(port, result -> {
                if (result.succeeded()) {
                    LOG.info("HttpVerticle started at port " + port);
                } else {
                    LOG.error("Failed to start HttpVerticle: ", result.cause());
                }
            });
    }
    
    @Override
    public void stop() {
        LOG.info("HttpVerticle is going to stop...");
    }
    
    private void handle404(RoutingContext context) {
        ClassLoader loader = getClass().getClassLoader();

        context.response().setStatusCode(404);
        context.response().putHeader("content-type", "text/html");
        context.response().sendFile(loader.getResource("webroot/404.html").getFile());
    }
}
