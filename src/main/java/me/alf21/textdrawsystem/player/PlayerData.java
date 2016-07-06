package me.alf21.textdrawsystem.player;

import me.alf21.textdrawsystem.MsgBox.MsgBox;
import me.alf21.textdrawsystem.dialogs.types.Panel;
import me.alf21.textdrawsystem.utils.PlayerTextdrawData;
import net.gtaun.shoebill.common.player.PlayerLifecycleObject;
import net.gtaun.shoebill.object.Player;
import net.gtaun.util.event.EventManager;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Alf21 on 26.02.2016 in the project 'textdraw-system'.
 */
public class PlayerData extends PlayerLifecycleObject {
	private Player player;
	private Panel panel;
	private PlayerTextdrawData playerTextdrawData;
	private CopyOnWriteArrayList<MsgBox> msgBoxes;
	
	public PlayerData(EventManager manager, Player p) { 
		super(manager, p);
        player = p;
		msgBoxes = new CopyOnWriteArrayList<>();
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
}