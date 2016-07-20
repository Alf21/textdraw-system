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

import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.content.components.ComponentData;
import me.alf21.textdrawsystem.content.components.ComponentDataCollection;
import me.alf21.textdrawsystem.content.components.bar.Bar;
import me.alf21.textdrawsystem.content.components.bar.BarInterface;
import me.alf21.textdrawsystem.content.components.button.Button;
import me.alf21.textdrawsystem.content.components.input.Input;
import me.alf21.textdrawsystem.content.components.input.InputType;
import me.alf21.textdrawsystem.content.components.list.List;
import me.alf21.textdrawsystem.content.components.list.ListItem;
import me.alf21.textdrawsystem.dialogs.layouts.Layout;
import me.alf21.textdrawsystem.dialogs.types.Panel;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.object.Destroyable;

import java.util.ArrayList;

/**
 * @author MK124
 * @author Alf21
 */
public abstract class AbstractPanelDialog implements Destroyable {
    public boolean isProcessing() {
        return processing;
    }

    @SuppressWarnings("unchecked")
    public static abstract class AbstractPanelDialogBuilder
            <PanelDialogType extends AbstractPanelDialog, PanelDialogBuilderType extends AbstractPanelDialogBuilder<PanelDialogType, PanelDialogBuilderType>> {
        protected final PanelDialogType panelDialog;


        protected AbstractPanelDialogBuilder(PanelDialogType panelDialog) {
            this.panelDialog = panelDialog;
        }

        public PanelDialogBuilderType parentPanelDialog(AbstractPanelDialog parent) {
            panelDialog.setParentPanelDialog(parent);
            return (PanelDialogBuilderType) this;
        }

        public PanelDialogBuilderType caption(String caption) {
            panelDialog.setCaption(caption);
            return (PanelDialogBuilderType) this;
        }

        public PanelDialogBuilderType buttonOk(String buttonOk) {
            panelDialog.setButtonOk(buttonOk);
            return (PanelDialogBuilderType) this;
        }

        public PanelDialogBuilderType buttonCancel(String buttonCancel) {
            panelDialog.setButtonCancel(buttonCancel);
            return (PanelDialogBuilderType) this;
        }

        public PanelDialogBuilderType onClickCancel(PanelDialogHandler handler) {
            panelDialog.setClickCancelHandler(handler);
            return (PanelDialogBuilderType) this;
        }

        public PanelDialogBuilderType onClickOk(PanelDialogClickOkHandler handler) {
            panelDialog.setClickOkHandler(handler);
            return (PanelDialogBuilderType) this;
        }

        public PanelDialogBuilderType onShow(PanelDialogHandler handler) {
            panelDialog.setShowHandler(handler);
            return (PanelDialogBuilderType) this;
        }

        public PanelDialogBuilderType onClose(PanelDialogCloseHandler handler) {
            panelDialog.setCloseHandler(handler);
            return (PanelDialogBuilderType) this;
        }

        public PanelDialogType build() {
            return panelDialog;
        }
    }


    @FunctionalInterface
    public interface PanelDialogCloseHandler {
        void onClose(AbstractPanelDialog panelDialog);
    }


    protected final Panel panel;

    private AbstractPanelDialog parentPanelDialog;

    private String caption = "";
    private String buttonOk = "";
    private String buttonCancel = "";

    private PanelDialogHandler showHandler = null;
    private PanelDialogCloseHandler closeHandler = null;
    private PanelDialogHandler clickCancelHandler = null;
    private PanelDialogClickOkHandler clickOkHandler = null;

    private ArrayList<Component> components;

    private boolean showed = false, processing = false;
    private boolean toggleLeftButton, toggleRightButton;


    public AbstractPanelDialog(Panel panel) {
        this.panel = panel;
        toggleLeftButton = toggleRightButton = true;
        components = new ArrayList<>();
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
        super.finalize();
    }

    @Override
    public void destroy() {
        if (this.isShowed())
            this.hide();
        components.forEach(Component::destroy);
    }

    public void recreate() {
        components.forEach(Component::recreate);
    }

    @Override
    public boolean isDestroyed() {
        return true;
    }

    public Panel getPanel() {
        return panel;
    }

    public AbstractPanelDialog getParentPanelDialog() {
        return parentPanelDialog;
    }

    public void setParentPanelDialog(AbstractPanelDialog parentPanelDialog) {
        this.parentPanelDialog = parentPanelDialog;
    }

    public void showParentPanelDialog() {
        if (parentPanelDialog != null) parentPanelDialog.show();
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setButtonOk(String buttonOk) {
        this.buttonOk = buttonOk;
    }

    public void setButtonCancel(String buttonCancel) {
        this.buttonCancel = buttonCancel;
    }

    public String getButtonOk() {
        return buttonOk;
    }

    public String getButtonCancel() {
        return buttonCancel;
    }

    public void setClickOkHandler(PanelDialogClickOkHandler onClickOkHandler) {
        this.clickOkHandler = onClickOkHandler;
    }

    public void setClickCancelHandler(PanelDialogHandler onClickCancelHandler) {
        this.clickCancelHandler = onClickCancelHandler;
    }

    public void setShowHandler(PanelDialogHandler showHandler) {
        this.showHandler = showHandler;
    }

    public void setCloseHandler(PanelDialogCloseHandler closeHandler) {
        this.closeHandler = closeHandler;
    }

    protected void show(String text) {
        if (panel.isShowed())
            panel.hideFromDialog();

        onShow();

        showed = true;

        if (!buttonCancel.replaceAll(" ", "").isEmpty())
            panel.getLeftButton().setText(buttonCancel);
        if (!buttonOk.replaceAll(" ", "").isEmpty())
            panel.getRightButton().setText(buttonOk);
        if (!caption.replaceAll(" ", "").isEmpty())
            panel.getTitle().setText(caption);
        if (!text.replaceAll(" ", "").isEmpty())
            panel.getContent().setText(text);

        panel.showFromDialog(components);

        this.toggleOkButton(toggleRightButton);
        this.toggleCancelButton(toggleLeftButton);

        //components.forEach(Component::show);
    }

    public void hide() {
        hideFromPanel();
        panel.hide();
    }

    public void hideFromPanel() {
        showed = false;

        components.forEach(Component::hide);
    }

    public abstract void show();

    void onShow() {
        if (showHandler != null) showHandler.handle(this);
    }

    void onClose() {
        if (closeHandler != null)
            closeHandler.onClose(this);
        if (!this.isDestroyed())
              this.destroy();
        panel.setPanelDialog(null);
    }

    void onClickOk(ComponentDataCollection componentDataCollection) {
        components.stream().filter(component -> component instanceof List).forEach(component -> {
            for (ListItem listItem : ((List) component).getSelectedListItems()) {
                if (listItem.getSelectHandler() != null) {
                    processing = true;
                    break;
                }
            }
            ((List) component).getSelectedListItems().forEach(ListItem::onSelect);
        });
        if (clickOkHandler != null) clickOkHandler.handle(this, componentDataCollection);
        if (this.isProcessing()) {
            processing = false;
            this.destroy();
        }
    }

    protected void onClickCancel() {
        if (clickCancelHandler != null) clickCancelHandler.handle(this);
    }

    public List addList(String componentName) {
        List list = List.create(panel.getContent(), new Vector2D(126.0f, 147.0f), 388, 139, componentName);
        components.add(list);
        return list;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public ComponentData getComponentData(String name) {
        for(Component component : this.getComponents()) {
            if(component.getName().equals(name))
                return component.getComponentData();
        }
        return null;
    }


    public void addList(List list) {
        if (!components.contains(list))
            components.add(list);
    }

    public void addInput(Input input) {
        if (!components.contains(input))
            components.add(input);
    }

    public void addBar(Bar bar) {
        if (!components.contains(bar))
            components.add(bar);
    }

    public void addButton(Button button) {
        if (!components.contains(button))
            components.add(button);
    }

    public List createList(Vector2D position, float maxWidth, float maxHeight, String name) {
        List list = List.create(panel.getContent(), position, maxWidth, maxHeight, name);
        getComponents().add(list);
        return list;
    }

    public Input createInput(Vector2D position, float width, String placeholder, InputType inputType, String name) {
        Input input = Input.create(panel.getContent(), position, width, placeholder, inputType, name);
        getComponents().add(input);
        return input;
    }

    public Input createInput(Vector2D position, float width, InputType inputType, String name) {
        Input input = Input.create(panel.getContent(), position, width, inputType, name);
        getComponents().add(input);
        return input;
    }

    public Input createInput(Vector2D position, String placeholder, InputType inputType, String name) {
        Input input = Input.create(panel.getContent(), position, placeholder, inputType, name);
        getComponents().add(input);
        return input;
    }

    public Input createInput(Vector2D position, InputType inputType, String name) {
        Input input = Input.create(panel.getContent(), position, inputType, name);
        getComponents().add(input);
        return input;
    }

    public Input createInput(Vector2D position, String name) {
        Input input = Input.create(panel.getContent(), position, name);
        getComponents().add(input);
        return input;
    }

    public Bar createBar(Vector2D position, float minHeight, float maxHeight, float minWidth, float maxWidth, Color minColor, Color maxColor, BarInterface barInterface, String name) {
        Bar bar = Bar.create(panel.getContent(), position, minHeight, maxHeight, minWidth, maxWidth, minColor, maxColor, barInterface, name);
        getComponents().add(bar);
        return bar;
    }

    public Button createButton(float x, float y, String text, String name) {
        Button button = Button.create(panel.getContent(), x, y, text, name);
        getComponents().add(button);
        return button;
    }

    public Button createButton(float x, float y, float width, String text, String name) {
        Button button = Button.create(panel.getContent(), x, y, width, text, name);
        getComponents().add(button);
        return button;
    }

    public Layout getLayout() {
        return panel.getLayout();
    }

    public boolean isShowed() {
        return showed;
    }

    public void toggleOkButton(boolean toggle) {
        if (panel.isShowed()) {
            if (toggle)
                panel.getRightButton().show();
            else panel.getRightButton().hide();
            toggleRightButton = toggle;
        }
    }

    public void toggleCancelButton(boolean toggle) {
        if (panel.isShowed()) {
            if (toggle)
                panel.getLeftButton().show();
            else panel.getLeftButton().hide();
            toggleLeftButton = toggle;
        }
    }

    public boolean isToggleLeftButton() {
        return toggleLeftButton;
    }

    public boolean isToggleRightButton() {
        return toggleRightButton;
    }
}
