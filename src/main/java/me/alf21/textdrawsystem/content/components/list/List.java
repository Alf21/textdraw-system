package me.alf21.textdrawsystem.content.components.list;

import me.alf21.textdrawsystem.calculations.Calculation;
import me.alf21.textdrawsystem.content.Content;
import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.content.components.ComponentAlignment;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.data.Vector2D;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Alf21 on 13.03.2016 in the project 'textdraw-system'.
 */
public class List extends Component {
	private Vector2D position;
	private float maxWidth, maxHeight;
	private ListBar listBar;
	private ArrayList<ListItem> listItems;
	private ListItem selectedListItem;

	protected List(Content content, Vector2D position, float maxWidth, float maxHeight, String name) {
		super(content, ComponentAlignment.TOP_LEFT, name);
		this.position = position;
		this.maxHeight = maxHeight;
		this.maxWidth = maxWidth;
		listBar = ListBar.create(this);
		listItems = new ArrayList<>();
	}

	public static List create(Content content, Vector2D position, float maxWidth, float maxHeight, String name) {
		return new List(content, position, maxWidth, maxHeight, name);
	}

	public void addListItem(String text, ListItemInterface listItemInterface) {
		ListItem listItem = new ListItem(this, text, listItemInterface);
		listItems.add(listItem);
	}

	public ArrayList<ListItem> getListItems() {
		return listItems;
	}

	@Override
	public void show() {
		super.show();
		updateListItems();
		listBar.show();
	}

	@Override
	public void hide() {
		super.hide();
		listItems.forEach(ListItem::hide);
		listBar.hide();
	}

	@Override
	public void recreate() {
		super.recreate();
		listItems.forEach(ListItem::recreate);
		listBar.recreate();
	}

	@Override
	public boolean isDestroyed() {
		for (ListItem listItem : listItems)
			if (!listItem.isDestroyed())
				return false;
		return !(!super.isDestroyed() || !listBar.isDestroyed());
	}

	@Override
	public void destroy() {
		super.destroy();
		listItems.forEach(ListItem::destroy);
		listBar.destroy();
		selectedListItem = null;
	}

	public Vector2D getPosition() {
		return position;
	}

	public float getMaxWidth() {
		return maxWidth;
	}

	public float getMaxHeight() {
		return maxHeight;
	}

	public ListBar getListBar() {
		return listBar;
	}

	@Override
	public ArrayList<PlayerTextdraw> getAllPlayerTextdraws() {
		ArrayList<PlayerTextdraw> playerTextdraws = super.getAllPlayerTextdraws();
		playerTextdraws.add(listBar.getPrevious());
		playerTextdraws.add(listBar.getBar());
		playerTextdraws.add(listBar.getBarbg());
		playerTextdraws.add(listBar.getNext());
		playerTextdraws.addAll(listItems.stream().map(ListItem::getPlayerTextdraw).collect(Collectors.toList()));
		return playerTextdraws;
	}

	@Override
	public void onClick(net.gtaun.shoebill.object.PlayerTextdraw clickedPlayerTextdraw) {
		ListItem listItem = getListItem(clickedPlayerTextdraw);
		if (listItem != null) {
			if (System.currentTimeMillis() - listItem.getClicked() <= 500) {
				listItem.getListItemInterface().onDoubleClick(this, listItem);
				listItem.setClicked(System.currentTimeMillis() - 501);
			}
			else {
				listItem.getListItemInterface().onClick(this, listItem);
				listItem.setClicked(System.currentTimeMillis());
			}
			selectedListItem = listItem;
		}
		else {
			int currentListBarIndex = listBar.getCurrentIndex();
			if (listBar.getNext().isPlayerTextdraw(clickedPlayerTextdraw)) {
				if (currentListBarIndex < listItems.size() - getAmountVisibleListItems()) {
					listBar.setCurrentIndex(currentListBarIndex + 1);
					updateListItems();
				}
			}
			else {
				if (currentListBarIndex > 0) {
					listBar.setCurrentIndex(currentListBarIndex - 1);
					updateListItems();
				}
			}
		}
	}

	private ListItem getListItem(net.gtaun.shoebill.object.PlayerTextdraw playerTextdraw) {
		for (ListItem listItem : listItems) {
			if (listItem.getPlayerTextdraw().isPlayerTextdraw(playerTextdraw))
				return listItem;
		}
		return null;
	}

	private void updateListItems() {
		listItems.stream().filter(ListItem::isDestroyed).forEach(ListItem::recreate);
		listItems.stream().filter(listItem -> listItem.getPlayerTextdraw().isShowed()).forEach(ListItem::hide);

		float height = 0.0f;
		int i = 0, currentListBarIndex = listBar.getCurrentIndex();
		if (currentListBarIndex == -1) {
			currentListBarIndex = 0;
			listBar.setCurrentIndex(currentListBarIndex);
		}

		for (ListItem listItem : listItems) {
			float newHeight = height + Calculation.getBoxHeight(listItem.getPlayerTextdraw()) + 6.0f;
			if (i >= currentListBarIndex) {
				if (newHeight <= maxHeight) {
					listItem.update(getPosition(), i - currentListBarIndex);
					listItem.show();
					height = newHeight;
				}
				else break;
			}
			i++;
		}

		listItems.stream().filter(listItem -> !listItem.getPlayerTextdraw().isShowed()).forEach(ListItem::destroy);
	}

	protected int getAmountVisibleListItems() {
		int i = 0;
		float height = 0.0f;
		for (ListItem listItem : listItems) {
			boolean recreated = false;
			if (listItem.isDestroyed()) {
				listItem.recreate();
				recreated = true;
			}
			float newHeight = height + Calculation.getBoxHeight(listItem.getPlayerTextdraw()) + 6f;
			if (recreated) listItem.destroy();
			if (newHeight < maxHeight) {
				height = newHeight;
				i++;
			}
			else return i;
		}
		return i;
	}

	public ListItem getSelectedListItem() {
		return selectedListItem;
	}

	public void setSelectedListItem(ListItem selectedListItem) {
		this.selectedListItem = selectedListItem;
	}
}
