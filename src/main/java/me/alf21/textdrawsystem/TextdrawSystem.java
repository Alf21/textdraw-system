/**
 * @author Alf21 on 26.02.2016 in project textdraw-system.
 * Copyright (c) 2016 Alf21. All rights reserved.
 * http://forum.sa-mp.de/index.php?page=VCard&userID=34293
 * 							or
 * search for Alf21 in http://sa-mp.de || Breadfish
 **/

package me.alf21.textdrawsystem;

import me.alf21.textdrawsystem.dialogs.styles.DialogStyles;
import me.alf21.textdrawsystem.dialogs.types.Panel;
import me.alf21.textdrawsystem.panelDialog.PanelDialog;
import me.alf21.textdrawsystem.player.PlayerData;
import me.alf21.textdrawsystem.player.PlayerManager;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import me.alf21.textdrawsystem.utils.PlayersTextdraw;
import net.gtaun.shoebill.common.dialog.DialogHandler;
import net.gtaun.shoebill.common.dialog.InputDialog;
import net.gtaun.shoebill.common.player.PlayerLifecycleHolder;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.resource.Plugin;
import net.gtaun.util.event.EventManager;
import net.gtaun.util.event.EventManagerNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by Alf21 on 26.02.2016 in the project 'textdraw-system'.
 */
public class TextdrawSystem extends Plugin {

	public static final Logger LOGGER = LoggerFactory.getLogger(TextdrawSystem.class);
	private static TextdrawSystem instance;
	private PlayerManager playerManager;
	private PlayerLifecycleHolder playerLifecycleHolder;
    private EventManagerNode eventManagerNode;
	private static ArrayList<PlayersTextdraw> playersTextdraws;
	private static ArrayList<PlayerTextdraw> playerTextdraws;

	public final static Color   HOVERCOLOR = Color.GREEN,
								UNSELECT_COLOR = new Color(255, 255, 255, 255),
								UNSELECT_BG_COLOR = new Color(0, 0, 0, 255),
								SELECT_COLOR = new Color(0, 0, 0, 255),
								SELECT_BG_COLOR = new Color(0, 255, 0, 255);
    
	public static TextdrawSystem getInstance() {
		if (instance == null)
			instance = new TextdrawSystem();
		return instance;
	}
	
	@Override
	protected void onDisable() throws Throwable {
		playerLifecycleHolder.destroy();
        eventManagerNode.destroy();
		playerManager.uninitialize();
		playerManager.destroy();
		playerManager = null;
		System.out.println("[TEXTDRAWSYSTEM] uninitialized");
	}

	@Override
	protected void onEnable() throws Throwable {
		instance = this;
		EventManager eventManager = getEventManager();
        eventManagerNode = eventManager.createChildNode();
        playerLifecycleHolder = new PlayerLifecycleHolder(eventManager);
        playerLifecycleHolder.registerClass(PlayerData.class);
		playerManager = new PlayerManager();
		playersTextdraws = new ArrayList<>();
		playerTextdraws = new ArrayList<>();
		System.out.println("[TEXTDRAWSYSTEM] initialized");
	}

    public Logger getLoggerInstance() {
        return LOGGER;
    }

    public EventManager getEventManagerInstance() {
        return eventManagerNode;
    }
    
    public PlayerLifecycleHolder getPlayerLifecycleHolder() {
        return playerLifecycleHolder;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

	public static ArrayList<PlayersTextdraw> getPlayersTextdraws() {
		return playersTextdraws;
	}

	public static ArrayList<PlayerTextdraw> getPlayerTextdraws() {
		return playerTextdraws;
	}

	// extern functions:
	public static Panel getPanel(Player player) {
		PlayerData playerData = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);
		Panel panel = playerData.getPanel();
		if (panel == null) {
			panel = Panel.create(player);
			setPanel(player, panel);
		}
		return panel;
	}

	public static void setPanel(Player player, Panel panel) {
		PlayerData playerData = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);
		Panel panel1 = playerData.getPanel();
		if (panel1 != null) {
			if (!panel1.isDestroyed()) {
				panel1.hide();
				panel1.destroy();
			}
		}
		playerData.setPanel(panel);
	}

	public static Panel createPanel(Player player) {
		return Panel.create(player);
	}

	public static PanelDialog createPanelDialog(Player player) {
		return getPanel(player).createPanelDialog();
	}

	public static boolean hasSelectableTextdraw(Player player) {
		for (PlayerTextdraw playerTextdraw : playerTextdraws) {
			if (playerTextdraw.isShowed(player) && playerTextdraw.isSelectable())
				return true;
		}
		for (PlayersTextdraw playersTextdraw : playersTextdraws) {
			if (playersTextdraw.isShowed(player) && playersTextdraw.isSelectable())
				return true;
		}
		return false;
	}

	public static InputDialog createInputDialog(Player player, String caption, String message, DialogHandler cancelHandler, InputDialog.ClickOkHandler okHandler) {
		InputDialog inputDialog = InputDialog.create(player, TextdrawSystem.getInstance().getEventManager())
				.caption(caption)
				.message(message)
				.onClickOk(okHandler)
				.onClickCancel(cancelHandler)
				.build();
		inputDialog.show();
		return inputDialog;
	}
}
