package com.alexandrebfreitas.wue;

import com.alexandrebfreitas.wue.config.AsyncSyncConfiguration;
import com.alexandrebfreitas.wue.config.EmbeddedElasticsearch;
import com.alexandrebfreitas.wue.config.EmbeddedSQL;
import com.alexandrebfreitas.wue.config.JacksonConfiguration;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = { WattsUpEnergyApp.class, JacksonConfiguration.class, AsyncSyncConfiguration.class })
@EmbeddedElasticsearch
@EmbeddedSQL
public @interface IntegrationTest {
}
