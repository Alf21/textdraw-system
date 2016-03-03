package me.alf21.textdrawsystem.dialogs.panel;

import me.alf21.textdrawsystem.content.Content;
import me.alf21.textdrawsystem.dialogs.interfaces.PanelInterface;
import me.alf21.textdrawsystem.dialogs.panel.layouts.Layout;
import me.alf21.textdrawsystem.dialogs.panel.layouts.PanelLayout;
import me.alf21.textdrawsystem.dialogs.panel.styles.PanelStyle;
import me.alf21.textdrawsystem.dialogs.panel.styles.Process;
import me.alf21.textdrawsystem.dialogs.panel.styles.PanelStyles;
import me.alf21.textdrawsystem.dialogs.panel.styles.normal.Normal;
import me.alf21.textdrawsystem.dialogs.panel.types.PanelType;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.object.Destroyable;
import net.gtaun.shoebill.object.Player;

import java.util.ArrayList;

/**
 * Created by Alf21 on 26.02.2016.
 */
public class Panel implements Destroyable {

	public static final String TEXT_EMPTY = "Click me";

	private Player player;
	private PanelType panelType;
	private PanelStyles panelStyles;
	private PanelLayout panelLayout;
	private Content content;
	private PanelInterface panelInterface;

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

	public Panel(Player player, PanelStyles panelStyles, PanelInterface panelInterface) {
		if(player == null)
			return;
		this.player = player;
		this.panelInterface = panelInterface;
		this.panelInterface.setPanel(this);
		panelLayout = PanelLayout.FREE_STYLE;
		panelType = PanelType.PAGE;
		this.panelStyles = panelStyles;
		try {
			((PanelStyle) panelStyles.getStyleClass().newInstance()).create(this);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		preAddons = new ArrayList<>();
		afterAddons = new ArrayList<>();
	}

	public Panel(Player player, PanelInterface panelInterface) {
		if(player == null)
			return;
		this.player = player;
		this.panelInterface = panelInterface;
		this.panelInterface.setPanel(this);
		panelLayout = PanelLayout.FREE_STYLE;
		panelType = PanelType.PAGE;
		try {
			Normal.class.newInstance().create(this);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		preAddons = new ArrayList<>();
		afterAddons = new ArrayList<>();
	}

	public Panel(Player player) {
		if(player == null)
			return;
		this.player = player;
		this.panelInterface = new PanelInterface(){};
		this.panelInterface.setPanel(this);
		panelLayout = PanelLayout.FREE_STYLE;
		panelType = PanelType.PAGE;
		try {
			Normal.class.newInstance().create(this);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		preAddons = new ArrayList<>();
		afterAddons = new ArrayList<>();
	}

	public void onClickCloseIcon() {
		//TODO if enabled to close
		//hide();
		//destroy();
		panelInterface.onClose();
	}

	public void onClickLeftButton() {
		if (getPanelType() == PanelType.PAGE) {
			Process process = getProcess();
			if ((int) process.getProcess() != 1) {
				content.previousPage();
			}
		}
	}

	public void onClickRightButton() {
		if(getPanelType() == PanelType.PAGE) {
			Process process = getProcess();
			if (getContent().hasMarkedComponents()) {
				content.setText("~r~[ERROR]~w~At first you need to fill all fields");
				return;
			}
			if ((int) process.getProcess() != (int) process.getMaxProcess()) {
				content.setText("_");
				content.nextPage();
			}
			else panelInterface.onFinish();
		}
	}

	public PanelStyles getPanelStyles() {
		return panelStyles;
	}

	public Player getPlayer() {
		return player;
	}

	public PanelType getPanelType() {
		return panelType;
	}

	public void setPanelType(PanelType panelType) {
		this.panelType = panelType;
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

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public void hide() {
		if(player != null) {
			player.cancelSelectTextDraw();

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

	public void show() {
		if(player != null) {
			player.selectTextDraw(hoverColor);

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

	@Override
	public void destroy() {
		hide();
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

	public PanelLayout getPanelLayout() {
		return panelLayout;
	}

	public void setPanelLayout(PanelLayout panelLayout) {
		this.panelLayout = panelLayout;
	}

	public Layout getLayout() {
		try {
			return ((Layout) panelLayout.getLayoutClass().newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
