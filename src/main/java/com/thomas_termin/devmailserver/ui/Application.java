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

import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import com.thomas_termin.devmailserver.MailServer;

/**
 * A JavaFX application for MailServer.
 *
 * @author <a href="mailto:eugene.khrustalev@gmail.com">Eugene Khrustalev</a>
 */
public class Application extends javafx.application.Application {

    private final ResourceBundle bundle = ResourceBundle.getBundle(Application.class.getName());

    @Override
    public void start(Stage stage) {
        UIEventBus.setEnabled(true);

        final SMTPPane smtpPane = new SMTPPane();
        final Tab smtpTab = new Tab(bundle.getString("smtp.title"), smtpPane);
        smtpTab.setClosable(false);

        final POP3Pane pop3Pane = new POP3Pane();
        final Tab pop3Tab = new Tab(bundle.getString("pop3.title"), pop3Pane);
        pop3Tab.setClosable(false);

        final MailboxesPane mailboxesPane = new MailboxesPane();
        final Tab mailboxesTab = new Tab(bundle.getString("mailboxes.title"), mailboxesPane);
        mailboxesTab.setClosable(false);

        final TabPane root = new TabPane(smtpTab, pop3Tab, mailboxesTab);
        final Scene scene = new Scene(root, 960, 600);
        scene.getStylesheets().add(Application.class.getResource("/stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle(bundle.getString("app.title"));
        stage.getIcons().add(new Image(Application.class.getResource("/icon.png").toExternalForm()));
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        MailServer.getInstance().shutdown();
        UIEventBus.setEnabled(false);
        super.stop();
    }
}
