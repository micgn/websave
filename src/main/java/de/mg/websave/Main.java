package de.mg.websave;

import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {

        Env env = new Env();

        Connector httpsConnector = new Connector();
        httpsConnector.setPort(Integer.valueOf(env.getSecurePort()));
        httpsConnector.setSecure(true);
        httpsConnector.setScheme("https");
        httpsConnector.setAttribute("keyAlias", env.getKeystoreAlias());
        httpsConnector.setAttribute("keystorePass", env.getKeystorePassword());
        httpsConnector.setAttribute("keystoreFile", env.getKeystorePath());
        httpsConnector.setAttribute("clientAuth", "false");
        httpsConnector.setAttribute("sslProtocol", "TLS");
        httpsConnector.setAttribute("SSLEnabled", true);

        Tomcat tomcat = new Tomcat();

        Service service = tomcat.getService();
        service.addConnector(httpsConnector);

        Connector defaultConnector = tomcat.getConnector();
        defaultConnector.setPort(Integer.valueOf(env.getPort()));
        defaultConnector.setRedirectPort(Integer.valueOf(env.getSecurePort()));

        tomcat.addRole("save", "save");
        tomcat.addUser("save", env.getBasicAuthPassword());

        final String webappDirLocation = "src/main/webapp/";
        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());


        tomcat.start();
        tomcat.getServer().await();
    }
}
