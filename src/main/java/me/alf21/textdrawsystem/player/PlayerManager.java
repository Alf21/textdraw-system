package me.alf21.textdrawsystem.player;

import java.io.IOException;

import me.alf21.textdrawsystem.MsgBox.MsgBox;
import me.alf21.textdrawsystem.TextdrawSystem;
import me.alf21.textdrawsystem.dialogs.types.Panel;
import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import me.alf21.textdrawsystem.utils.PlayerTextdrawData;
import net.gtaun.shoebill.event.player.*;
import net.gtaun.shoebill.object.Player;
import net.gtaun.util.event.EventManager;
import net.gtaun.util.event.HandlerPriority;

/**
 * Created by Alf21 on 26.02.2016 in the project 'textdraw-system'.
 */
public class PlayerManager
{	
	public EventManager eventManager;
	public PlayerData playerLifecycle;

	public PlayerManager() throws IOException
	{	
		eventManager = TextdrawSystem.getInstance().getEventManagerInstance();

		eventManager.registerHandler(PlayerClickPlayerTextDrawEvent.class, HandlerPriority.HIGHEST, e -> {
			Player player = e.getPlayer();
			playerLifecycle = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);
			if (playerLifecycle == null)
				return;

			if (!playerLifecycle.getMsgBoxes().isEmpty()) {
				for (MsgBox msgBox : playerLifecycle.getMsgBoxes()) {
					if(msgBox.isShowed()) {
						if (msgBox.getOkButtonText().isPlayerTextdraw(e.getPlayerTextdraw())) {
							msgBox.onClickOk();
							return;
						}
						else if (msgBox.getCancelButtonText().isPlayerTextdraw(e.getPlayerTextdraw())) {
							msgBox.onClickCancel();
							return;
						}
					}
				}
			}

			Panel panel = playerLifecycle.getPanel();
			if (panel == null || panel.isDestroyed() || !panel.isShowed())
				return;

			PlayerTextdraw closeIcon = panel.getCloseIcon();
			PlayerTextdraw leftButton = panel.getLeftButton();
			PlayerTextdraw rightButton = panel.getRightButton();
			if (closeIcon.isPlayerTextdraw(e.getPlayerTextdraw())) {
				panel.onClickCloseIcon();
			} else if (leftButton.isPlayerTextdraw(e.getPlayerTextdraw())) {
				panel.onClickLeftButton();
			} else if (rightButton.isPlayerTextdraw(e.getPlayerTextdraw())) {
				panel.onClickRightButton();
			} else {
				Component component = panel.getContent().getComponent(e.getPlayerTextdraw());
				if (component != null) {
					component.onClick(e.getPlayerTextdraw());
				}
			}

			playerLifecycle.setPlayerTextdrawData(new PlayerTextdrawData(e.getPlayerTextdraw(), System.currentTimeMillis()));
		});

		eventManager.registerHandler(PlayerClickTextDrawEvent.class, HandlerPriority.NORMAL, e -> {
			//TODO add PlayerClickTextDrawEvent.class with same content ?
			Player player = e.getPlayer();
			playerLifecycle = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);
			if (playerLifecycle == null)
				return;
			Panel panel = playerLifecycle.getPanel();
			if (panel == null || panel.isDestroyed() || !panel.isShowed() || playerLifecycle.getPlayerTextdrawData() == null)
				return;

			if (playerLifecycle.getPlayerTextdrawData().getPlayerTextdraw() == null) {
				// ESC pressed
				if (e.getTextdraw() == null) {
					panel.onClickCloseIcon();
				}
			}
		});

		eventManager.registerHandler(PlayerClickPlayerTextDrawEvent.class, HandlerPriority.LOWEST, e -> {
			Player player = e.getPlayer();
			playerLifecycle = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);
			if (playerLifecycle == null || playerLifecycle.getPanel() == null || playerLifecycle.getPanel().isDestroyed() || !playerLifecycle.getPanel().isShowed())
				return;
			playerLifecycle.setPlayerTextdrawData(null);
		});

		eventManager.registerHandler(PlayerDisconnectEvent.class, (e) -> {
			Player player = e.getPlayer();
			playerLifecycle = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);
			if(playerLifecycle.getPanel() != null && !playerLifecycle.getPanel().isDestroyed()) {
				playerLifecycle.getPanel().hide();
				playerLifecycle.getPanel().destroy();
			}
		});

		/* //NOT AVAILABLE TO GET DETECTED :/
		eventManager.registerHandler(PlayerUpdateEvent.class, (e) -> {
			playerLifecycle = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(e.getPlayer(), PlayerData.class);
			Panel panel = playerLifecycle.getPanel();
			if (panel != null && panel.isShowed()) {
				if (e.getPlayer().getKeyState().isKeyPressed(PlayerKey.UP)) {
					panel.getContent().getComponents().stream().filter(component -> component != null && !component.isDestroyed() && component instanceof List).forEach(component -> ((List) component).moveUp());
				} else if (e.getPlayer().getKeyState().isKeyPressed(PlayerKey.DOWN)) {
					panel.getContent().getComponents().stream().filter(component -> component != null && !component.isDestroyed() && component instanceof List).forEach(component -> ((List) component).moveDown());
				} else if (e.getPlayer().getKeyState().isKeyPressed(PlayerKey.SECONDARY_ATTACK)) {
					panel.onClickRightButton();
				}
			}
		});
		*/
	}


	public void uninitialize() {
		
	}


	public void destroy() {
		
	}
}