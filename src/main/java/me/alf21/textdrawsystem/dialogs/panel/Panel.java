package me.alf21.textdrawsystem.dialogs.panel;

import me.alf21.textdrawsystem.dialogs.Dialog;
import me.alf21.textdrawsystem.dialogs.DialogInterface;
import me.alf21.textdrawsystem.dialogs.styles.DialogStyles;
import net.gtaun.shoebill.object.Player;

/**
 * Created by Alf21 on 26.02.2016.
 */
public class Panel extends Dialog {

	protected Panel(Player player, DialogStyles dialogStyles, DialogInterface dialogInterface) {
		super(player, dialogStyles, dialogInterface);
	}

	public static Panel create(Player player, DialogStyles dialogStyles, DialogInterface dialogInterface) {
		return new Panel(player, dialogStyles, dialogInterface);
	}

	public static Panel create(Player player, DialogInterface dialogInterface) {
		return new Panel(player, null, dialogInterface);
	}

	public static Panel create(Player player) {
		return new Panel(player, null, null);
	}
}
