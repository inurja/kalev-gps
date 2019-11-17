package kalev.gps.config

import grails.compiler.GrailsCompileStatic
import grails.config.Config
import grails.core.GrailsApplication
import grails.plugins.GrailsPluginManager
import grails.util.Holders
import org.springframework.context.ApplicationContext

import javax.servlet.ServletContext

//http://burtbeckwith.com/blog/?p=1017
@GrailsCompileStatic
@Singleton
class ApplicationContextHolder {

    private static final Map<String, Object> TEST_BEANS = [:]

    static ApplicationContext getApplicationContext() {
        Holders.applicationContext
    }

    static Object getBean(String name) {
        TEST_BEANS[name] ?: applicationContext.getBean(name)
    }

    static GrailsApplication getGrailsApplication() {
        return Holders.grailsApplication
    }

    static Config getConfig() {
        return Holders.config
    }

    static ServletContext getServletContext() {
        return (ServletContext) Holders.servletContext
    }

    static GrailsPluginManager getPluginManager() {
        return Holders.pluginManager
    }
}
