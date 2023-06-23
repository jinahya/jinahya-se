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
package com.github.jinahya.util;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Optional;

/**
 * @author Jin Kwon
 */
class LocaleViewer {

    private enum Column {

        NAME() {
            @Override
            public Object getValueAt(final Locale locale) {
                return locale.toString();
            }
        },
        LANGUAGE() {
            @Override
            public Object getValueAt(final Locale locale) {
                return locale.getDisplayLanguage(locale) + " (" + locale.getDisplayLanguage(Locale.ENGLISH) + ')';
            }
        },
        COUNTRY {
            @Override
            public Object getValueAt(final Locale locale) {
                return Optional.of(locale.getDisplayCountry(locale))
                        .filter(v -> !v.isEmpty())
                        .map(v -> v + Optional.of(locale.getDisplayCountry(Locale.ENGLISH))
                                .filter(v2 -> !v2.isEmpty())
                                .map(v2 -> v + " (" + v2 + ')')
                                .orElse("")
                        )
                        .orElse("");
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

    public static void main(final String[] args) {

        new JFrame("Locale Viewer") {
            @Override
            protected JRootPane createRootPane() {
                return new JRootPane() {

                    @Override
                    protected Container createContentPane() {
                        return new JScrollPane(new JTable() {

                            @Override
                            protected TableModel createDefaultDataModel() {
                                final Locale[] rows = Arrays.stream(Locale.getAvailableLocales())
                                        .skip(1L)
                                        .sorted(Comparator.comparing(Locale::toString))
                                        .toArray(Locale[]::new);
                                final Column[] columns = Column.values();
                                return new AbstractTableModel() {
                                    @Override
                                    public int getRowCount() {
                                        return rows.length;
                                    }

                                    @Override
                                    public int getColumnCount() {
                                        return columns.length;
                                    }

                                    @Override
                                    public String getColumnName(int column) {
                                        return columns[column].name();
                                    }

                                    @Override
                                    public Object getValueAt(int rowIndex, int columnIndex) {
                                        return columns[columnIndex]
                                                .getValueAt(rows[rowIndex]);
                                    }
                                };
                            }
                        });
                    }
                };
            }

            @Override
            protected void processWindowEvent(final WindowEvent we) {
                super.processWindowEvent(we);
                if (we.getID() == WindowEvent.WINDOW_CLOSING) {
                    System.exit(0);
                }
            }

            @Override
            public void frameInit() {
                super.frameInit();
                pack();
            }
        }.setVisible(true);
    }
}
