/**
 * Created by Alf21 on 12.06.2016 in the project 'textdraw-system'.
 */

package me.alf21.textdrawsystem.panelDialog;

@FunctionalInterface
public interface PanelDialogHandler {
    void handle(AbstractPanelDialog panelDialog);
}
