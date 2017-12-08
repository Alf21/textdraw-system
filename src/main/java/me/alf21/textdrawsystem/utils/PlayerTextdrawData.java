package me.alf21.textdrawsystem.utils;

/**
 * Created by Alf21 on 14.06.2016 in the project 'textdraw-system'.
 */
public class PlayerTextdrawData {

	private net.gtaun.shoebill.entities.PlayerTextdraw playerTextdraw;
	private long timestamp;

	public PlayerTextdrawData(net.gtaun.shoebill.entities.PlayerTextdraw playerTextdraw, long timestamp) {
		this.playerTextdraw = playerTextdraw;
		this.timestamp = timestamp;
	}


	public net.gtaun.shoebill.entities.PlayerTextdraw getPlayerTextdraw() {
		return playerTextdraw;
	}

	public long getTimestamp() {
		return timestamp;
	}
}
