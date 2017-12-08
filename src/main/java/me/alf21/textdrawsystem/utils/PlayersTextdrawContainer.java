package me.alf21.textdrawsystem.utils;

import net.gtaun.shoebill.entities.Destroyable;
import net.gtaun.shoebill.entities.Player;

import java.util.ArrayList;

/**
 * Created by Alf21 on 21.07.2016 in the project 'textdraw-system'.
 */
public class PlayersTextdrawContainer implements Destroyable {

	private Player player;
	private ArrayList<PlayersTextdraw> playersTextdraws;

	protected PlayersTextdrawContainer(Player player) {
		this.player = player;
		playersTextdraws = new ArrayList<>();
	}


	public void show() {
		playersTextdraws.forEach(PlayersTextdraw::show);
	}

	public void hide() {
		playersTextdraws.forEach(PlayersTextdraw::hide);
	}

	public void recreate() {
		playersTextdraws.forEach(PlayersTextdraw::recreate);
	}

	@Override
	public void destroy() {
		playersTextdraws.forEach(PlayersTextdraw::destroy);
	}

	@Override
	public boolean isDestroyed() {
		for (PlayersTextdraw playersTextdraw : playersTextdraws)
			if (!playersTextdraw.isDestroyed())
				return false;
		return true;
	}

	public ArrayList<PlayersTextdraw> getPlayersTextdraws() {
		return playersTextdraws;
	}

	public void setPlayerTextdraws(ArrayList<PlayersTextdraw> playersTextdraws) {
		this.playersTextdraws = playersTextdraws;
	}

	public Player getPlayer() {
		return player;
	}
}
