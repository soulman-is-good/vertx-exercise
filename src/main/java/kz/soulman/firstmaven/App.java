/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.soulman.firstmaven;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 *
 * @author maxim
 */
public class App extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        App app = new App();

        vertx.deployVerticle(app);
    }
    
    @Override
    public void start() {
        LOGGER.info("Verticle up");
    }
    
    @Override
    public void stop() {
        LOGGER.info("Verticle down");
    }
}
