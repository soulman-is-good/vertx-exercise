/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.soulman.firstmaven;

import io.vertx.config.ConfigRetriever;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import java.io.IOException;

/**
 *
 * @author maxim
 */
public class App {
    static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        try {
            Vertx vertx = Vertx.vertx();
            ConfigRetriever.create(vertx).getConfig(config -> {
                if (config.succeeded()) {
                    HttpVerticle http = new HttpVerticle();
                    DeploymentOptions options = new DeploymentOptions();
                    
                    options.setConfig(config.result());
                    vertx.deployVerticle(http, options);
                } else {
                    LOG.error("Error processing config file: ", config.cause());
                }
            });
            System.in.read();
        } catch (IOException ex) {
            LOG.fatal("Could not run", ex);
        }
    }
}
