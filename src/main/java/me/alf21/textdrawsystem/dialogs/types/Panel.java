package me.alf21.textdrawsystem.dialogs.types;

import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.content.components.list.List;
import me.alf21.textdrawsystem.dialogs.Dialog;
import me.alf21.textdrawsystem.dialogs.DialogInterface;
import me.alf21.textdrawsystem.dialogs.styles.DialogStyles;
import me.alf21.textdrawsystem.panelDialog.PanelDialog;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.object.Player;

import java.util.ArrayList;

/**
 * Created by Alf21 on 26.02.2016 in the project 'textdraw-system'.
 */
public class Panel extends Dialog {

	private PlayerTextdraw leftButtonBackground;
	private PlayerTextdraw rightButtonBackground;
	private PlayerTextdraw leftButton;
	private PlayerTextdraw rightButton;

	protected Panel(Player player, DialogStyles dialogStyles, DialogInterface dialogInterface) {
		super(player, dialogStyles, dialogInterface);
	}

	public static Panel create(Player player, DialogStyles dialogStyles, DialogInterface dialogInterface) {
		return new Panel(player, dialogStyles, dialogInterface);
	}

	public static Panel create(Player player, DialogInterface dialogInterface) {
		return new Panel(player, null, dialogInterface);
	}

	public static Panel create(Player player) {
		return new Panel(player, null, null);
	}

	@Override
	public void show() {
		super.show();
		initShow();
	}

	@Override
	public void show(ArrayList<Component> addons) {
		super.show(addons);
		initShow();
	}

	@Override
	public void showFromDialog(ArrayList<Component> addons) {
		super.showFromDialog(addons);
		initShow();
	}

	private void initShow() {
		if(super.getPlayer() != null) {
			boolean isLeftButtonToggled = true, isRightButtonToggled = true;

			if (super.getPanelDialog() != null) {
				isLeftButtonToggled = super.getPanelDialog().isToggleLeftButton();
				isRightButtonToggled = super.getPanelDialog().isToggleRightButton();
			}

			if (isLeftButtonToggled) {
				leftButtonBackground.show();
				leftButton.show();
			}
			if (isRightButtonToggled) {
				rightButtonBackground.show();
				rightButton.show();
			}

			super.getAfterAddons().forEach(PlayerTextdraw::hide);
			super.getAfterAddons().forEach(PlayerTextdraw::show);
		}
	}

	@Override
	public void hide() {
		if(super.getPlayer() != null) {
			super.hide();
			leftButtonBackground.hide();
			rightButtonBackground.hide();
			leftButton.hide();
			rightButton.hide();
		}
	}

	@Override
	public void hideFromDialog() {
		this.hide();
	}

	@Override
	public void destroy() {
		super.destroy();
		leftButtonBackground.destroy();
		leftButton.destroy();
		rightButtonBackground.destroy();
		rightButton.destroy();
	}

	@Override
	public boolean isDestroyed() {
		return !(!super.isDestroyed()
				|| !leftButtonBackground.isDestroyed()
				|| !leftButton.isDestroyed()
				|| !rightButtonBackground.isDestroyed()
				|| !rightButton.isDestroyed());
	}


/*******************************************************************************************************
									Getting data from styles
*******************************************************************************************************/

	public PlayerTextdraw getLeftButtonBackground() {
		return leftButtonBackground;
	}

	public PlayerTextdraw getRightButtonBackground() {
		return rightButtonBackground;
	}

	public PlayerTextdraw getLeftButton() {
		return leftButton;
	}

	public PlayerTextdraw getRightButton() {
		return rightButton;
	}

	public void setLeftButtonBackground(PlayerTextdraw leftButtonBackground) { this.leftButtonBackground = leftButtonBackground; }

	public void setRightButtonBackground(PlayerTextdraw rightButtonBackground) { this.rightButtonBackground = rightButtonBackground; }

	public void setLeftButton(PlayerTextdraw leftButton) {
		this.leftButton = leftButton;
	}

	public void setRightButton(PlayerTextdraw rightButton) {
		this.rightButton = rightButton;
	}

	//public PanelDialog createPanelDialog(EventHandler<PanelDialogResponseEvent> onResponse, EventHandler<PanelDialogShowEvent> onShow, EventHandler<PanelDialogCloseEvent> onClose) {
	public PanelDialog createPanelDialog() {
		return PanelDialog.create(this);
	}
}
