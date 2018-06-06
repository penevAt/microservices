//package com.team.neon.notifications
//
//import au.com.dius.pact.provider.junit.PactRunner
//import au.com.dius.pact.provider.junit.Provider
//import au.com.dius.pact.provider.junit.State
//import au.com.dius.pact.provider.junit.loader.PactFolder
//import au.com.dius.pact.provider.junit.target.HttpTarget
//import au.com.dius.pact.provider.junit.target.Target
//import au.com.dius.pact.provider.junit.target.TestTarget
//import eventstore.EventstoreApplication
//import org.junit.AfterClass
//import org.junit.BeforeClass
//import org.junit.runner.RunWith
//import org.springframework.boot.SpringApplication
//import org.springframework.context.ConfigurableApplicationContext
//
//@RunWith(PactRunner::class)
//@Provider("test_provider")
//@PactFolder("mypacts")
//class TestPacts {
//
//    companion object {
//        @JvmStatic
//        var application: ConfigurableApplicationContext? = null
//
//        @JvmStatic
//        @BeforeClass
//        fun startSpring() {
//            application = SpringApplication.run(EventstoreApplication::class.java)
//        }
//
//        @JvmStatic
//        @AfterClass
//        fun kill() {
//            application!!.stop()
//        }
//    }
//
//    @TestTarget
//    val target: Target = HttpTarget(8080)
//
//
//    @State("test GET")
//    fun testGet() {
//        println("Testing GET endpoints")
//    }
//
//}