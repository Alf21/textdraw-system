package me.alf21.textdrawsystem.content.components.list;

import me.alf21.textdrawsystem.calculations.Calculation;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.object.Destroyable;

/**
 * Created by Alf21 on 16.03.2016 in the project 'textdraw-system'.
 */
public class ListItem implements Destroyable {

	// TODO add spacer

	private String text;
	private ListItemHandler selectHandler, clickHandler, doubleClickHandler;
	private PlayerTextdraw playerTextdraw;
	private Long clicked;
	private boolean selected, disableAutoColoring;
	private List list;

	private Color unselectedListItemColor;
	private Color unselectedListItemBgColor;
	private Color selectedListItemColor;
	private Color selectedListItemBgColor;

	protected ListItem(List list, String text) {
		this.list = list;
		this.text = text;

		init();
	}

	private void init() {
		clicked = System.currentTimeMillis();

		unselectedListItemColor = list.getUnselectedListItemColor();
		unselectedListItemBgColor = list.getUnselectedListItemBgColor();
		selectedListItemColor = list.getSelectedListItemColor();
		selectedListItemBgColor = list.getSelectedListItemBgColor();

		selected = disableAutoColoring = false;

		playerTextdraw = PlayerTextdraw.create(list.getPlayer(), list.getPosition().getX(), list.getPosition().getY() + 15.0f * list.getListItems().size(), getText());
		playerTextdraw.setBackgroundColor(new Color(0, 0, 0, 255));
		playerTextdraw.setFont(TextDrawFont.get(1));
		playerTextdraw.setLetterSize(new Vector2D(0.5f, 1.0f));
		playerTextdraw.setColor(unselectedListItemColor);
		playerTextdraw.setOutlineSize(0);
		playerTextdraw.setProportional(true);
		playerTextdraw.setShadowSize(0);
		playerTextdraw.setUseBox(true);
		playerTextdraw.setBoxColor(unselectedListItemBgColor);
		playerTextdraw.setTextSize(new Vector2D(list.getPosition().getX() + list.getMaxWidth() - list.getListBar().getWidth() - 6f, Calculation.letterHeightToHeight(1.0f)));
		playerTextdraw.setSelectable(true);
	}

	public void update(Vector2D position, int index) {
		playerTextdraw.move(position.getX(), position.getY() + 15.0f * index);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ListItemHandler getSelectHandler() {
		return selectHandler;
	}

	public ListItemHandler getClickHandler() {
		return clickHandler;
	}

	public ListItemHandler getDoubleClickHandler() {
		return doubleClickHandler;
	}

	public void setSelectHandler(ListItemHandler selectHandler) {
		this.selectHandler = selectHandler;
	}

	public void setClickHandler(ListItemHandler clickHandler) {
		this.clickHandler = clickHandler;
	}

	public void setDoubleClickHandler(ListItemHandler doubleClickHandler) {
		this.doubleClickHandler = doubleClickHandler;
	}

	public void onClick() {
		if (clickHandler != null) clickHandler.handle(this);
	}

	public void onSelect() {
		if (selectHandler != null) selectHandler.handle(this);
	}

	public void onDoubleClick() {
		if (doubleClickHandler != null) doubleClickHandler.handle(this);
	}

	public PlayerTextdraw getPlayerTextdraw() {
		return playerTextdraw;
	}

	public void hide() {
		playerTextdraw.hide();
	}

	public void show() {
		playerTextdraw.show();
	}

	public void recreate() {
		playerTextdraw.recreate();
	}

	@Override
	public void destroy() {
		playerTextdraw.destroy();
	}

	@Override
	public boolean isDestroyed() {
		return playerTextdraw.isDestroyed();
	}

	public Long getClicked() {
		return clicked;
	}

	public void setClicked(Long clicked) {
		this.clicked = clicked;
	}

	public void select() {
		selected = true;

		if (!list.getSelectedListItems().contains(this))
			list.getSelectedListItems().add(this);

		playerTextdraw.setColor(selectedListItemColor);
		playerTextdraw.setBoxColor(selectedListItemBgColor);
		playerTextdraw.update();
	}

	public void unselect() {
		selected = false;

		if (list.getSelectedListItems().contains(this))
			list.getSelectedListItems().remove(this);

		playerTextdraw.setColor(unselectedListItemColor);
		playerTextdraw.setBoxColor(unselectedListItemBgColor);
		playerTextdraw.update();
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelectedListItemBgColor(Color selectedListItemBgColor) {
		this.selectedListItemBgColor = selectedListItemBgColor;
		if (selected) {
			playerTextdraw.setBoxColor(selectedListItemBgColor);
			playerTextdraw.update();
		}
	}

	public void setSelectedListItemColor(Color selectedListItemColor) {
		this.selectedListItemColor = selectedListItemColor;
		if (selected) {
			playerTextdraw.setColor(selectedListItemColor);
			playerTextdraw.update();
		}
	}

	public void setUnselectedListItemBgColor(Color unselectedListItemBgColor) {
		this.unselectedListItemBgColor = unselectedListItemBgColor;
		if (!selected) {
			playerTextdraw.setBoxColor(unselectedListItemBgColor);
			playerTextdraw.update();
		}
	}

	public void setUnselectedListItemColor(Color unselectedListItemColor) {
		this.unselectedListItemColor = unselectedListItemColor;
		if (!selected) {
			playerTextdraw.setColor(unselectedListItemColor);
			playerTextdraw.update();
		}
	}

	public boolean isDisableAutoColoring() {
		return disableAutoColoring;
	}

	public void toggleDisableAutoColoring(boolean disableAutoColoring) {
		this.disableAutoColoring = disableAutoColoring;
	}
}
