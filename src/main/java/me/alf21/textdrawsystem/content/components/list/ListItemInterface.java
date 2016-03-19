package me.alf21.textdrawsystem.content.components.list;

/**
 * Created by Alf21 on 16.03.2016 in the project 'textdraw-system'.
 */
public interface ListItemInterface {

	default void onClick(List list, ListItem listItem) {

	}

	default void onDoubleClick(List list, ListItem listItem) {

	}

	default void onSelect(List list, ListItem listItem) {

	}
}
