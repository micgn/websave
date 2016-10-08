package de.mg.websave.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EntryModel implements Comparable<EntryModel> {

    private String name;
    private String entry;

    public String getName() {
        return name;
    }

    public String getNameEncoded() {
        try {
            return URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw (new RuntimeException(e));
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntry() {
        return entry;
    }

    public String getEntryHtml() {
        return entry.replaceAll("\n", "<br/>");
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public int compareTo(EntryModel m) {
        return name.toLowerCase().compareTo(m.getName().toLowerCase());
    }

}
