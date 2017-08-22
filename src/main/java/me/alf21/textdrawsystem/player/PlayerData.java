package me.alf21.textdrawsystem.player;

import me.alf21.textdrawsystem.TextdrawSystem;
import me.alf21.textdrawsystem.container.Container;
import me.alf21.textdrawsystem.dialogs.types.Panel;
import me.alf21.textdrawsystem.msgBox.MsgBox;
import me.alf21.textdrawsystem.utils.PlayerTextdrawData;
import net.gtaun.shoebill.common.player.PlayerLifecycleObject;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.object.Player;
import net.gtaun.util.event.EventManager;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Alf21 on 26.02.2016 in the project 'textdraw-system'.
 */
public class PlayerData extends PlayerLifecycleObject {
	private Player player;
	private Panel panel;
	private PlayerTextdrawData playerTextdrawData;
	private CopyOnWriteArrayList<MsgBox> msgBoxes;
	private CopyOnWriteArrayList<Container> containers;
	
	public PlayerData(EventManager manager, Player p) { 
		super(manager, p);
        player = p;
		msgBoxes = new CopyOnWriteArrayList<>();
		containers = new CopyOnWriteArrayList<>();
	}

	/** (non-Javadoc)
	 * @see net.gtaun.shoebill.common.player.PlayerLifecycleObject#getPlayer()
	 */
	@Override
	public Player getPlayer() {
		return player;
	}

	@Override 
	protected void onInit() { 
		
	} 

	@Override 
	protected void onDestroy() { 
		
	}

	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}

	public PlayerTextdrawData getPlayerTextdrawData() {
		return playerTextdrawData;
	}

	public void setPlayerTextdrawData(PlayerTextdrawData playerTextdrawData) {
		this.playerTextdrawData = playerTextdrawData;
	}

	public CopyOnWriteArrayList<MsgBox> getMsgBoxes() {
		return msgBoxes;
	}

	public void setMsgBoxes(CopyOnWriteArrayList<MsgBox> msgBoxes) {
		this.msgBoxes = msgBoxes;
	}

	public CopyOnWriteArrayList<Container> getContainers() {
		return containers;
	}

	public void setContainers(CopyOnWriteArrayList<Container> containers) {
		this.containers = containers;
	}

	public void toggleMouse() {
		toggleMouse(true);
	}

	public void toggleMouse(boolean toggle) {
		if (TextdrawSystem.hasSelectableTextdraw(player)) {
		//	player.cancelSelectTextDraw();
			if (toggle) {
				Color hoverColor = TextdrawSystem.HOVERCOLOR;
				if (panel != null)
					hoverColor = panel.getHoverColor();
				player.selectTextDraw(hoverColor);
			}
		}
	}
}