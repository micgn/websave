/*
 *  Copyright 2016 Michael Gnatz.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */

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
