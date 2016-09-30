/**
 * Copyright (C) 2012-2014 MK124
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
 */

package me.alf21.textdrawsystem.panelDialog;

import me.alf21.textdrawsystem.content.components.ComponentDataCollection;
import me.alf21.textdrawsystem.dialogs.types.Panel;

/**
 * @author MK124
 * @author Alf21
 */
public class PanelDialog extends AbstractPanelDialog {
    private String message = "_", caption = "_";

    public PanelDialog(Panel panel) {
        super(panel);
    }

    public PanelDialog(Panel panel, String caption) {
        super(panel);
        setCaption(caption);
    }

    public PanelDialog(Panel panel, String caption, String message) {
        super(panel);
        setCaption(caption);
        setMessage(message);
    }

    public static PanelDialog create(Panel panel) {
        return new PanelDialog(panel);
    }

    public static PanelDialog create(Panel panel, String caption, String message) {
        return new PanelDialog(panel, caption, message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void show() {
        if (panel.getPanelDialog() != this) {
            if (panel.getPanelDialog() != null) {
                panel.getPanelDialog().hide();
                if (!panel.getPanelDialog().isProcessing())
                    panel.getPanelDialog().destroy();
            }
        }

        if (panel.getPanelDialog() != this)
            panel.setPanelDialog(this);

        super.show(message);
    }

    @Override
    public void onClickOk(ComponentDataCollection componentDataCollection) {
        super.onClickOk(componentDataCollection);
    }

    @Override
    public void onClose() {
        super.onClose();
    }

    @Override
    public void onClickCancel() {
        super.onClickCancel();
    }
}
