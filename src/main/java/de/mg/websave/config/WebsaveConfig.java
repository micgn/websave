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

package de.mg.websave.config;


import java.lang.reflect.Field;

public class WebsaveConfig {

    private String basicAuthPassword = "12345";
    private String port = "8888", securePort = "8443";
    private String keystorePath = "/home/mgnatz/git/websave/keystore.jks", keystoreAlias = "self", keystorePassword = "geheim";
    private String datafilePath = "/home/mgnatz/git/websave";


    public WebsaveConfig() {

        for (Field field : WebsaveConfig.class.getDeclaredFields()) {
            String value = System.getProperty(field.getName());
            try {
                if (value != null)
                    field.set(this, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "WebsaveConfig{" +
                "basicAuthPassword='" + basicAuthPassword + '\'' +
                ", port='" + port + '\'' +
                ", securePort='" + securePort + '\'' +
                ", keystorePath='" + keystorePath + '\'' +
                ", keystoreAlias='" + keystoreAlias + '\'' +
                ", keystorePassword='" + keystorePassword + '\'' +
                ", datafilePath='" + datafilePath + '\'' +
                '}';
    }

    public String getSecurePort() {
        return securePort;
    }


    public String getBasicAuthPassword() {
        return basicAuthPassword;
    }

    public String getPort() {
        return port;
    }

    public String getKeystorePath() {
        return keystorePath;
    }

    public String getKeystoreAlias() {
        return keystoreAlias;
    }

    public String getKeystorePassword() {
        return keystorePassword;
    }

    public String getDatafilePath() {
        return datafilePath;
    }
}
