package de.mg.websave.model;

import java.util.List;

public class DataModel {

    private List<EntryModel> entries;

    public List<EntryModel> getEntries() {
        return entries;
    }

    public void setEntries(List<EntryModel> entries) {
        this.entries = entries;
    }

    public EntryModel get(String entryName) {
        for (EntryModel entryModel : entries) {
            if (entryModel.getName().equals(entryName)) {
                return entryModel;
            }
        }
        return null;
    }

    public void delete(String entryName) {
        int i = 0;
        for (EntryModel entryModel : entries) {
            if (entryModel.getName().equals(entryName)) {
                entries.remove(i);
                break;
            }
            i++;
        }
    }

}
