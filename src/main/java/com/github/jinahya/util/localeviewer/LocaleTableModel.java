/*
 * Copyright 2014 Jin Kwon.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jinahya.util.localeviewer;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jin Kwon
 */
public class LocaleTableModel extends AbstractTableModel {

    private static enum Column {

        NAME() {

            @Override
            public Object getValueAt(final Locale locale) {

                return locale.toString();
            }

        },
        LANGUAGE() {

            @Override
            public Object getValueAt(final Locale locale) {

                return locale.getDisplayLanguage(locale);
            }

        },
        COUNTRY {

            @Override
            public Object getValueAt(final Locale locale) {

                return locale.getDisplayCountry(locale);
            }

        },
        LAN() {

            @Override
            public Object getValueAt(final Locale locale) {

                try {
                    return locale.getISO3Language();
                } catch (final MissingResourceException mre) {
                    return "N/A";
                }
            }

        },
        CON() {

            @Override
            public Object getValueAt(final Locale locale) {

                try {
                    return locale.getISO3Country();
                } catch (final MissingResourceException mre) {
                    return "N/A";
                }
            }

        },
        LN() {

            @Override
            public Object getValueAt(final Locale locale) {

                return locale.getLanguage();
            }

        },
        CO() {

            @Override
            public Object getValueAt(final Locale locale) {

                return locale.getCountry();
            }

        };

        abstract Object getValueAt(Locale locale);

    }

    public LocaleTableModel() {

        super();

        rows = Arrays.asList(Locale.getAvailableLocales());

        columns = Arrays.asList(Column.values());
    }

    @Override
    public int getRowCount() {

        return rows.size();
    }

    @Override
    public int getColumnCount() {

        return columns.size();
    }

    @Override
    public String getColumnName(final int columnIndex) {

        return columns.get(columnIndex).name();
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {

        return columns.get(columnIndex).getValueAt(rows.get(rowIndex));
    }

    private List<Locale> rows;

    private List<Column> columns;

}
