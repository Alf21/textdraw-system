package me.alf21.textdrawsystem.player;

import me.alf21.textdrawsystem.dialogs.panel.Panel;
import net.gtaun.shoebill.common.player.PlayerLifecycleObject;
import net.gtaun.shoebill.object.Player;
import net.gtaun.util.event.EventManager;

public class PlayerData extends PlayerLifecycleObject {
	private Player player;
	private Panel panel;
	
	public PlayerData(EventManager manager, Player p) { 
		super(manager, p);
        player = p;
	}

	/* (non-Javadoc)
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
}