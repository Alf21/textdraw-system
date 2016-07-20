package me.alf21.textdrawsystem.msgBox;

/**
 * Created by Alf21 on 04.07.2016 in the project 'textdraw-system'.
 */

@FunctionalInterface
public interface MsgBoxHandler {
	void handle(MsgBox msgBox);
}
