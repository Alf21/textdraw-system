package me.alf21.textdrawsystem.dialogs;

import me.alf21.textdrawsystem.content.Content;
import me.alf21.textdrawsystem.dialogs.layouts.DialogLayout;
import me.alf21.textdrawsystem.dialogs.layouts.Layout;
import me.alf21.textdrawsystem.dialogs.styles.DialogStyle;
import me.alf21.textdrawsystem.dialogs.styles.DialogStyles;
import me.alf21.textdrawsystem.dialogs.styles.Process;
import me.alf21.textdrawsystem.dialogs.types.DialogType;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.object.Destroyable;
import net.gtaun.shoebill.object.Player;

import java.util.ArrayList;

/**
 * Created by Alf21 on 04.03.2016.
 */
public abstract class Dialog implements Destroyable {

	public static final String TEXT_EMPTY = "Click me";

	private Player player;
	private Content content;
	private DialogStyles dialogStyles;
	private DialogInterface dialogInterface;
	private DialogType dialogType;
	private DialogLayout dialogLayout;

	//Textdraws
	private PlayerTextdraw panelBackground;
	private PlayerTextdraw title;
	private PlayerTextdraw titleBackground;
	private PlayerTextdraw leftButtonBackground;
	private PlayerTextdraw rightButtonBackground;
	private PlayerTextdraw leftButton;
	private PlayerTextdraw rightButton;
	private PlayerTextdraw closeIcon;
	private Process process;
	private ArrayList<PlayerTextdraw> preAddons, afterAddons;
	private Color hoverColor = Color.GREEN;

	//Colors
	private Color inputColor, markerColor;

	protected Dialog(Player player, DialogStyles dialogStyles, DialogInterface dialogInterface) {
		this.player = player;
		this.dialogStyles = dialogStyles;
		if(getDialogStyles() == null)
			this.dialogStyles = DialogStyles.NORMAL;
		try {
			((DialogStyle) getDialogStyles().getStyleClass().newInstance()).create(this);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		this.dialogInterface = dialogInterface;
		if(getDialogInterface() == null)
			this.dialogInterface = new DialogInterface(){};
		preAddons = new ArrayList<>();
		afterAddons = new ArrayList<>();
		setDialogLayout(DialogLayout.FREE_STYLE);
		setDialogType(DialogType.PAGE);
	}

	public void show() {
		if(getPlayer() != null) {
			getPlayer().selectTextDraw(hoverColor);

			preAddons.forEach(PlayerTextdraw::show);
			panelBackground.show();
			titleBackground.show();
			title.show();
			leftButtonBackground.show();
			leftButton.show();
			rightButtonBackground.show();
			rightButton.show();
			content.show();
			closeIcon.show();
			process.show();
			afterAddons.forEach(PlayerTextdraw::show);
		}
	}

	public void hide() {
		if(getPlayer() != null) {
			getPlayer().cancelSelectTextDraw();

			preAddons.forEach(PlayerTextdraw::hide);
			panelBackground.hide();
			titleBackground.hide();
			title.hide();
			leftButtonBackground.hide();
			rightButtonBackground.hide();
			leftButton.hide();
			rightButton.hide();
			content.hide();
			closeIcon.hide();
			process.hide();
			afterAddons.forEach(PlayerTextdraw::hide);
		}
	}

	@Override
	public void destroy() {
		preAddons.forEach(PlayerTextdraw::destroy);
		panelBackground.destroy();
		titleBackground.destroy();
		title.destroy();
		leftButtonBackground.destroy();
		leftButton.destroy();
		rightButtonBackground.destroy();
		rightButton.destroy();
		content.destroy();
		closeIcon.destroy();
		process.destroy();
		afterAddons.forEach(PlayerTextdraw::destroy);

		preAddons.clear();
		afterAddons.clear();
	}

	@Override
	public boolean isDestroyed() {
		return !(!preAddons.isEmpty() || !afterAddons.isEmpty()
				|| !panelBackground.isDestroyed()
				|| !titleBackground.isDestroyed()
				|| !title.isDestroyed()
				|| !leftButtonBackground.isDestroyed()
				|| !leftButton.isDestroyed()
				|| !rightButtonBackground.isDestroyed()
				|| !rightButton.isDestroyed()
				|| !content.isDestroyed()
				|| !closeIcon.isDestroyed()
				|| !process.isDestroyed());
	}

	public Player getPlayer() {
		return player;
	}


/*******************************************************************************************************
									 Getting datas from styles
 *******************************************************************************************************/

	public PlayerTextdraw getPanelBackground() {
		return panelBackground;
	}

	public PlayerTextdraw getTitle() {
		return title;
	}

	public PlayerTextdraw getTitleBackground() {
		return titleBackground;
	}

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

	public PlayerTextdraw getCloseIcon() {
		return closeIcon;
	}

	public Process getProcess() {
		return process;
	}

	public void setPanelBackground(PlayerTextdraw panelBackground) {
		this.panelBackground = panelBackground;
	}

	public void setTitle(PlayerTextdraw title) {
		this.title = title;
	}

	public void setTitleBackground(PlayerTextdraw titleBackground) {
		this.titleBackground = titleBackground;
	}

	public void setLeftButtonBackground(PlayerTextdraw leftButtonBackground) { this.leftButtonBackground = leftButtonBackground; }

	public void setRightButtonBackground(PlayerTextdraw rightButtonBackground) { this.rightButtonBackground = rightButtonBackground; }

	public void setLeftButton(PlayerTextdraw leftButton) {
		this.leftButton = leftButton;
	}

	public void setRightButton(PlayerTextdraw rightButton) {
		this.rightButton = rightButton;
	}

	public void setCloseIcon(PlayerTextdraw closeIcon) {
		this.closeIcon = closeIcon;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public void setPreAddons(ArrayList<PlayerTextdraw> preAddons) {
		this.preAddons = preAddons;
	}

	public ArrayList<PlayerTextdraw> getPreAddons() {
		return preAddons;
	}

	public ArrayList<PlayerTextdraw> getAfterAddons() {
		return afterAddons;
	}

	public void setAfterAddons(ArrayList<PlayerTextdraw> afterAddons) {
		this.afterAddons = afterAddons;
	}

	public void setHoverColor(Color hoverColor) {
		this.hoverColor = hoverColor;
	}


	public void setContent(Content content) {
		this.content = content;
	}

	public Content getContent() {
		return content;
	}

	public Color getInputColor() {
		return inputColor;
	}

	public void setInputColor(Color inputColor) {
		this.inputColor = inputColor;
	}

	public Color getMarkerColor() {
		return markerColor;
	}

	public void setMarkerColor(Color markerColor) {
		this.markerColor = markerColor;
	}

	public DialogStyles getDialogStyles() {
		return dialogStyles;
	}

	public DialogInterface getDialogInterface() {
		return dialogInterface;
	}

	public DialogType getDialogType() {
		return dialogType;
	}

	public void setDialogType(DialogType dialogType) {
		this.dialogType = dialogType;
	}

	public DialogLayout getDialogLayout() {
		return dialogLayout;
	}

	public void setDialogLayout(DialogLayout dialogLayout) {
		this.dialogLayout = dialogLayout;
	}

	public Layout getLayout() {
		try {
			return ((Layout) getDialogLayout().getLayoutClass().newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

// Page handling


	public void onClickCloseIcon() {
		getDialogInterface().onClose(this);
	}

	public void onClickLeftButton() {
		if (getDialogType() == DialogType.PAGE) {
			Process process = getProcess();
			if ((int) process.getProcess() != 1) {
				getContent().previousPage();
			}
		}
	}

	public void onClickRightButton() {
		if(getDialogType() == DialogType.PAGE) {
			Process process = getProcess();
			if (getContent().hasMarkedComponents()) {
				getContent().setText("~r~[ERROR]~w~At first you need to fill all fields");
				return;
			}
			if ((int) process.getProcess() != (int) process.getMaxProcess()) {
				getContent().setText("_");
				getContent().nextPage();
			}
			else getDialogInterface().onFinish(this);
		}
	}
}
