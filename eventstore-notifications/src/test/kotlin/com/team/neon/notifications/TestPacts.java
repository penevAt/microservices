package com.team.neon.notifications;
import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import eventstore.EventstoreApplication;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

@RunWith(PactRunner.class)
@Provider("external_notifications")
@PactFolder("mypacts")
public class TestPacts {
    private static ConfigurableApplicationContext application;

    @TestTarget
    public final Target target = new HttpTarget(8080);

    @BeforeClass
    public static void startSpring() {
        application = SpringApplication.run(EventstoreApplication.class);
    }

    @State("test GET")
    public void testGet() {
        System.out.println("Testing GET endpoints");
    }

    @AfterClass
    public static void kill() {
        application.stop();
    }
}