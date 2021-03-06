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

package de.mg.websave.service;

import com.thoughtworks.xstream.XStream;
import de.mg.websave.config.WebsaveConfig;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import save.service.DataModel;
import save.service.PasswordModel;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


@Service
public class WebsaveService {

    private static final String DEFAULT_PW = "123";

    private static final String DATA_FILE = "data";
    private static final String PW_FILE = "pw";

    @Autowired
    private EncryptionUtil encryptionUtil;

    private XStream xstream;

    private String dataPath;

    public DataModel getDataModel(String password) {

        File file = new File(dataPath, DATA_FILE);
        if (!file.exists()) {
            DataModel data = new DataModel();
            data.setEntries(new ArrayList<>());
            persist(data, DEFAULT_PW);
            file = new File(dataPath, DATA_FILE);
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            String content = IOUtils.toString(inputStream, "UTF-8");
            String decrypted = encryptionUtil.decrypt(password, content);
            DataModel result = (DataModel) xstream.fromXML(decrypted);
            return result;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public PasswordModel getPasswordModel() {

        File file = new File(dataPath, PW_FILE);
        if (!file.exists()) {
            PasswordModel passwordModel = new PasswordModel();
            passwordModel.setHint(DEFAULT_PW);
            passwordModel.setNewPassword(DEFAULT_PW);
            persist(passwordModel);
            file = new File(dataPath, PW_FILE);
        }

        try {
            PasswordModel passwordModel = (PasswordModel) xstream.fromXML(new FileReader(file));
            return passwordModel;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void persist(DataModel data, String password) {

        copyAway(DATA_FILE);
        FileWriter writer = null;
        try {
            String content = xstream.toXML(data);
            String encrypted = encryptionUtil.encrypt(password, content);
            writer = new FileWriter(new File(dataPath, DATA_FILE));
            writer.write(encrypted);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void persist(PasswordModel passwordModel) {

        copyAway(PW_FILE);
        try {
            FileWriter writer = new FileWriter(new File(dataPath, PW_FILE));
            xstream.toXML(passwordModel, writer);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void copyAway(String filename) {

        if ((new File(dataPath, filename)).exists()) {
            try {
                File inputFile = new File(dataPath, filename);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                File outputFile = new File(dataPath, filename + "-" + df.format(new Date()));

                FileReader in = new FileReader(inputFile);
                FileWriter out = new FileWriter(outputFile);
                int ch;
                while ((ch = in.read()) != -1) {
                    out.write(ch);
                }
                in.close();
                out.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @PostConstruct
    public void init() throws Exception {
        xstream = new XStream();
        dataPath = new WebsaveConfig().getDatafilePath();
    }

}
