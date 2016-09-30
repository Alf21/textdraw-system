package me.alf21.textdrawsystem.dialogs;

import me.alf21.textdrawsystem.TextdrawSystem;
import me.alf21.textdrawsystem.content.Content;
import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.content.components.ComponentDataCollection;
import me.alf21.textdrawsystem.dialogs.layouts.DialogLayout;
import me.alf21.textdrawsystem.dialogs.layouts.Layout;
import me.alf21.textdrawsystem.dialogs.styles.DialogStyle;
import me.alf21.textdrawsystem.dialogs.styles.DialogStyles;
import me.alf21.textdrawsystem.dialogs.styles.Process;
import me.alf21.textdrawsystem.dialogs.types.DialogType;
import me.alf21.textdrawsystem.panelDialog.PanelDialog;
import me.alf21.textdrawsystem.player.PlayerData;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.object.Destroyable;
import net.gtaun.shoebill.object.Player;

import java.util.ArrayList;

/**
 * Created by Alf21 on 04.03.2016 in the project 'textdraw-system'.
 */
public abstract class Dialog implements Destroyable {

	public static final String TEXT_EMPTY = "Click me";

	//Colors
	private Color inputColor = Color.GRAY, markerColor = Color.RED, hoverColor = TextdrawSystem.HOVERCOLOR;

	private Player player;
	private Content content;
	private DialogStyles dialogStyles;
	private DialogInterface dialogInterface;
	private DialogType dialogType;
	private DialogLayout dialogLayout;
	private PanelDialog panelDialog;

	private boolean showed = false;

	//Textdraws
	private PlayerTextdraw panelBackground;
	private PlayerTextdraw title;
	private PlayerTextdraw titleBackground;
	private PlayerTextdraw closeIcon;
	private Process process;
	private ArrayList<PlayerTextdraw> preAddons, afterAddons;

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
		if (isDestroyed())
			recreate();

		showed = true;

		if(getPlayer() != null) {
			preAddons.forEach(PlayerTextdraw::show);
			panelBackground.show();
			titleBackground.show();
			title.show();
			content.show();
			closeIcon.show();
			process.show();
			afterAddons.forEach(PlayerTextdraw::show);

			PlayerData playerLifecycle = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);
			playerLifecycle.toggleMouse(true);
		}
	}

	public void show(ArrayList<Component> addons) {
		if (isDestroyed())
			recreate();

		showed = true;

		if(getPlayer() != null) {
			preAddons.forEach(PlayerTextdraw::show);
			panelBackground.show();
			titleBackground.show();
			title.show();
			content.show(addons);
			closeIcon.show();
			process.show();
			afterAddons.forEach(PlayerTextdraw::show);

			PlayerData playerLifecycle = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);
			playerLifecycle.toggleMouse(true);
		}
	}

	public void showFromDialog(ArrayList<Component> addons) {
		if (isDestroyed())
			recreate();

		showed = true;

		if(getPlayer() != null) {
			preAddons.forEach(PlayerTextdraw::show);
			panelBackground.show();
			titleBackground.show();
			title.show();
			content.showFromDialog(addons);
			closeIcon.show();
			process.show();
			afterAddons.forEach(PlayerTextdraw::show);

			PlayerData playerLifecycle = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);
			playerLifecycle.toggleMouse(true);
		}
	}

	public void hide() {
		showed = false;

		if(getPlayer() != null) {
			PlayerData playerLifecycle = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);
			playerLifecycle.toggleMouse(false);

			preAddons.forEach(PlayerTextdraw::hide);
			panelBackground.hide();
			titleBackground.hide();
			title.hide();
			content.hide();
			closeIcon.hide();
			process.hide();
			afterAddons.forEach(PlayerTextdraw::hide);
		}
	}

	public void hideFromDialog() {
		showed = false;

		if(getPlayer() != null) {
			PlayerData playerLifecycle = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);
			playerLifecycle.toggleMouse(false);

			preAddons.forEach(PlayerTextdraw::hide);
			panelBackground.hide();
			titleBackground.hide();
			title.hide();
			content.hideFromDialog();
			closeIcon.hide();
			process.hide();
			afterAddons.forEach(PlayerTextdraw::hide);
		}
	}

	public void recreate() {
		preAddons.forEach(PlayerTextdraw::recreate);
		panelBackground.recreate();
		titleBackground.recreate();
		title.recreate();
		content.recreate();
		closeIcon.recreate();
		process.recreate();
		afterAddons.forEach(PlayerTextdraw::recreate);
	}

	public void recreate(ArrayList<Component> addons) {
		preAddons.forEach(PlayerTextdraw::recreate);
		panelBackground.recreate();
		titleBackground.recreate();
		title.recreate();
		content.recreate(addons);
		closeIcon.recreate();
		process.recreate();
		afterAddons.forEach(PlayerTextdraw::recreate);
	}

	@Override
	public void destroy() {
		if (this.isShowed())
			this.hide();

		preAddons.forEach(PlayerTextdraw::destroy);
		panelBackground.destroy();
		titleBackground.destroy();
		title.destroy();
		content.destroy();
		closeIcon.destroy();
		process.destroy();
		afterAddons.forEach(PlayerTextdraw::destroy);

		preAddons.clear();
		afterAddons.clear();

	//	if (!TextdrawSystem.hasSelectableTextdraw(player)) //TODO
	//		player.cancelSelectTextDraw(); //TODO
	}

	@Override
	public boolean isDestroyed() {
		return !(!preAddons.isEmpty() || !afterAddons.isEmpty()
				|| !panelBackground.isDestroyed()
				|| !titleBackground.isDestroyed()
				|| !title.isDestroyed()
				|| !content.isDestroyed()
				|| !closeIcon.isDestroyed()
				|| !process.isDestroyed());
	}

	public boolean isShowed() {
		return showed;
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

	public Color getHoverColor() {
		return hoverColor;
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

	public void setDialogInterface(DialogInterface dialogInterface) {
		this.dialogInterface = dialogInterface;
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

		if (panelDialog != null)
			panelDialog.onClose();
	}

	public void onClickLeftButton() {
		if (getDialogType() == DialogType.PAGE) {
			Process process = getProcess();
			if ((int) process.getProcess() != 1) {
				getContent().previousPage();
			}
		}

		if (panelDialog != null)
			panelDialog.onClickCancel();
	}

	public void onClickRightButton() {
		if(getDialogType() == DialogType.PAGE) {
			Process process = getProcess();
			if (getContent().hasMarkedComponents()) {
			//	getContent().setText("~r~[ERROR]~w~At first you need to fill all fields");
				return;
			}
			if ((int) process.getProcess() != (int) process.getMaxProcess()) {
			//	getContent().setText("_");
				getContent().nextPage();
			}
			else getDialogInterface().onFinish(this);
		}

		if (panelDialog != null)
			panelDialog.onClickOk(new ComponentDataCollection(panelDialog.getComponents()));
	}

	public PanelDialog getPanelDialog() {
		return panelDialog;
	}

	public void setPanelDialog(PanelDialog panelDialog) {
		this.panelDialog = panelDialog;
	}
}
