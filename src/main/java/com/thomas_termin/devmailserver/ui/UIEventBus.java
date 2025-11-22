/*
 * Copyright 2025 Thomas Termin (thomas-termin.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ---------------------------------------------------------------------------
 * This file is based on work from the original "test-mail-server" project
 * by Eugene Khrustalev, licensed under the Apache License 2.0.
 *
 * Modifications made by Thomas Termin (2025):
 *  - Renamed Java package structure to com.thomastermin.devmailserver
 *  - Minor refactoring to support the new namespace
 * ---------------------------------------------------------------------------
 */


package com.thomas_termin.devmailserver.ui;

import com.google.common.eventbus.EventBus;
import javafx.application.Platform;

/**
 * EventBus for UI notifications.
 *
 * @author <a href="mailto:eugene.khrustalev@gmail.com">Eugene Khrustalev</a>
 */
public class UIEventBus {

    private static volatile EventBus INSTANCE;
    private static boolean ENABLED = false;

    private static EventBus getInstance() {
        if (INSTANCE == null) {
            synchronized (UIEventBus.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EventBus();
                }
            }
        }
        return INSTANCE;
    }

    public static boolean isEnabled() {
        return ENABLED;
    }

    public static void setEnabled(boolean enabled) {
        UIEventBus.ENABLED = enabled;
    }

    public static void register(Object subscriber) {
        getInstance().register(subscriber);
    }

    public static void post(final Object event) {
        // Post events only if UI is started
        if (ENABLED) {
            Platform.runLater(() -> getInstance().post(event));
        }
    }
}
