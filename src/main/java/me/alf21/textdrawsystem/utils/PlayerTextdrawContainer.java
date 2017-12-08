package me.alf21.textdrawsystem.utils;

import net.gtaun.shoebill.entities.Destroyable;
import net.gtaun.shoebill.entities.Player;

import java.util.ArrayList;

/**
 * Created by Alf21 on 21.07.2016 in the project 'textdraw-system'.
 */
public class PlayerTextdrawContainer implements Destroyable {

	private Player player;
	private ArrayList<PlayerTextdraw> playerTextdraws;

	protected PlayerTextdrawContainer(Player player) {
		this.player = player;
		playerTextdraws = new ArrayList<>();
	}


	public void show() {
		playerTextdraws.forEach(PlayerTextdraw::show);
	}

	public void hide() {
		playerTextdraws.forEach(PlayerTextdraw::hide);
	}

	public void recreate() {
		playerTextdraws.forEach(PlayerTextdraw::recreate);
	}

	@Override
	public void destroy() {
		playerTextdraws.forEach(PlayerTextdraw::destroy);
	}

	@Override
	public boolean isDestroyed() {
		for (PlayerTextdraw playerTextdraw : playerTextdraws)
			if (!playerTextdraw.isDestroyed())
				return false;
		return true;
	}

	public ArrayList<PlayerTextdraw> getPlayerTextdraws() {
		return playerTextdraws;
	}

	public void setPlayerTextdraws(ArrayList<PlayerTextdraw> playerTextdraws) {
		this.playerTextdraws = playerTextdraws;
	}

	public Player getPlayer() {
		return player;
	}
}
