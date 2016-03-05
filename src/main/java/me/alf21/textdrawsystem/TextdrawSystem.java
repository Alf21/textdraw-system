/**
 * @author Alf21 on 26.02.2016 in project textdraw-system.
 * Copyright (c) 2016 Alf21. All rights reserved.
 * http://forum.sa-mp.de/index.php?page=VCard&userID=34293
 * 							or
 * search for Alf21 in http://sa-mp.de || Breadfish
 **/

package me.alf21.textdrawsystem;

import me.alf21.textdrawsystem.player.PlayerData;
import me.alf21.textdrawsystem.player.PlayerManager;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import me.alf21.textdrawsystem.utils.PlayersTextdraw;
import net.gtaun.shoebill.common.player.PlayerLifecycleHolder;
import net.gtaun.shoebill.resource.Plugin;
import net.gtaun.util.event.EventManager;
import net.gtaun.util.event.EventManagerNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class TextdrawSystem extends Plugin {

	public static final Logger LOGGER = LoggerFactory.getLogger(TextdrawSystem.class);
	private static TextdrawSystem instance;
	private PlayerManager playerManager;
	private EventManager eventManager;
    private PlayerLifecycleHolder playerLifecycleHolder;
    private EventManagerNode eventManagerNode;
	private static ArrayList<PlayersTextdraw> playersTextdraws;
	private static ArrayList<PlayerTextdraw> playerTextdraws;
    
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
		eventManager = getEventManager();
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
}
