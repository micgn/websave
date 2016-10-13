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

package save.service;

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
