package de.mg.websave;

import de.mg.websave.config.WebsaveConfig;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class TomcatMain {

    public static void main(String[] args) throws Exception {

        WebsaveConfig websaveConfig = new WebsaveConfig();

        Connector httpsConnector = new Connector();
        httpsConnector.setPort(Integer.valueOf(websaveConfig.getSecurePort()));
        httpsConnector.setSecure(true);
        httpsConnector.setScheme("https");
        httpsConnector.setAttribute("keyAlias", websaveConfig.getKeystoreAlias());
        httpsConnector.setAttribute("keystorePass", websaveConfig.getKeystorePassword());
        httpsConnector.setAttribute("keystoreFile", websaveConfig.getKeystorePath());
        httpsConnector.setAttribute("clientAuth", "false");
        httpsConnector.setAttribute("sslProtocol", "TLS");
        httpsConnector.setAttribute("SSLEnabled", true);

        Tomcat tomcat = new Tomcat();

        Service service = tomcat.getService();
        service.addConnector(httpsConnector);

        Connector defaultConnector = tomcat.getConnector();
        defaultConnector.setPort(Integer.valueOf(websaveConfig.getPort()));
        defaultConnector.setRedirectPort(Integer.valueOf(websaveConfig.getSecurePort()));

        tomcat.addRole("save", "save");
        tomcat.addUser("save", websaveConfig.getBasicAuthPassword());

        final String webappDirLocation = "src/main/webapp/";
        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());


        tomcat.start();
        tomcat.getServer().await();
    }
}
