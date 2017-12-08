package me.alf21.textdrawsystem.content.components.list;

import me.alf21.textdrawsystem.container.Container;
import me.alf21.textdrawsystem.TextdrawSystem;
import me.alf21.textdrawsystem.calculations.Calculation;
import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.content.components.ComponentAlignment;
import me.alf21.textdrawsystem.content.components.ComponentData;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.data.Color;
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
	private ArrayList<ListItem> selectedListItems;
	private boolean multiselectionMode;

	private Color unselectedListItemColor;
	private Color unselectedListItemBgColor;
	private Color selectedListItemColor;
	private Color selectedListItemBgColor;

	protected List(Container container, Vector2D position, float maxWidth, float maxHeight, String name) {
		super(container, ComponentAlignment.TOP_LEFT, name);
		this.position = position;
		this.maxHeight = maxHeight;
		this.maxWidth = maxWidth;
		listBar = ListBar.create(this);
		listItems = new ArrayList<>();
		selectedListItems = new ArrayList<>();
		multiselectionMode = false;

		unselectedListItemColor = TextdrawSystem.UNSELECT_COLOR;
		unselectedListItemBgColor = TextdrawSystem.UNSELECT_BG_COLOR;
		selectedListItemColor = TextdrawSystem.SELECT_COLOR;
		selectedListItemBgColor = TextdrawSystem.SELECT_BG_COLOR;
	}

	public static List create(Container container, Vector2D position, float maxWidth, float maxHeight, String name) {
		return new List(container, position, maxWidth, maxHeight, name);
	}

	public ListItem addListItem(String text) {
		ListItem listItem = new ListItem(this, text);
		listItems.add(listItem);
		return listItem;
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
	//	selectedListItems = new ArrayList<>();
	}

	@Override
	public boolean isDestroyed() {
		for (ListItem listItem : listItems)
			if (!listItem.isDestroyed())
				return false;
		return !(!super.isDestroyed() || !listBar.isDestroyed() || !selectedListItems.isEmpty());
	}

	@Override
	public void destroy() {
		super.destroy();
		listItems.forEach(ListItem::destroy);
		listBar.destroy();
		selectedListItems.clear();
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
		playerTextdraws.addAll(listItems.stream().map(ListItem::getPlayerTextdraw).collect(Collectors.toList()));
		playerTextdraws.add(listBar.getPrevious());
		playerTextdraws.add(listBar.getBar());
		playerTextdraws.add(listBar.getBarbg());
		playerTextdraws.add(listBar.getNext());
		return playerTextdraws;
	}

	@Override
	public void onClick(net.gtaun.shoebill.entities.PlayerTextdraw clickedPlayerTextdraw) {
		ListItem listItem = getListItem(clickedPlayerTextdraw);
		if (listItem != null) {
			if (System.currentTimeMillis() - listItem.getClicked() <= 500) {
				listItem.onDoubleClick();
				listItem.onClick();
				listItem.setClicked(System.currentTimeMillis() - 501);
			}
			else {
				listItem.onClick();
				listItem.setClicked(System.currentTimeMillis());
			}

			if (multiselectionMode) {
				if (!selectedListItems.contains(listItem))
					selectListItem(listItem);
				else
					unselectListItem(listItem);
			}
			else {
				if (selectedListItems.isEmpty()){
					selectListItem(listItem);
				}
				else if (selectedListItems.get(0) == listItem) {
					unselectListItem(listItem);
				}
				else {
					unselectListItem(selectedListItems.get(0));
					selectListItem(listItem);
				}
			}
		}
		else {
			if (listBar.getNext().isPlayerTextdraw(clickedPlayerTextdraw))
				moveDown();
			else moveUp();
		}
	}

	public void moveUp() {
		int currentListBarIndex = listBar.getCurrentIndex();
		if (currentListBarIndex > 0) {
			listBar.setCurrentIndex(currentListBarIndex - 1);
			updateListItems();
		}
	}

	public void moveDown() {
		int currentListBarIndex = listBar.getCurrentIndex();
		if (currentListBarIndex < listItems.size() - getAmountVisibleListItems()) {
			listBar.setCurrentIndex(currentListBarIndex + 1);
			updateListItems();
		}
	}

	private ListItem getListItem(net.gtaun.shoebill.entities.PlayerTextdraw playerTextdraw) {
		for (ListItem listItem : listItems) {
			if (listItem.getPlayerTextdraw().isPlayerTextdraw(playerTextdraw))
				return listItem;
		}
		return null;
	}

	private void updateListItems() {
		listItems.stream().filter(ListItem::isDestroyed).forEach(ListItem::recreate);
		listItems.stream().filter(listItem -> listItem.getPlayerTextdraw().isShown()).forEach(ListItem::hide);

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

		listItems.stream().filter(listItem -> !listItem.getPlayerTextdraw().isShown()).forEach(ListItem::destroy);
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

	public ArrayList<ListItem> getSelectedListItems() {
		return selectedListItems;
	}

	public void setSelectedListItems(ArrayList<ListItem> selectedListItem) {
		this.selectedListItems = selectedListItem;
	}

	public void toggleMultiselectionMode(boolean multiselectionMode) {
		this.multiselectionMode = multiselectionMode;
	}

	public boolean isMultiselectionMode() {
		return multiselectionMode;
	}

	private void selectListItem(ListItem listItem) {
		selectedListItems.add(listItem);
		listItem.select();
	}

	private void unselectListItem(ListItem listItem) {
		selectedListItems.remove(listItem);
		listItem.unselect();
	}

	public void changeSelectedTextColor(Color color) {
		selectedListItemColor = color;

		listItems.forEach(listItem -> {
			if (!listItem.isDisableAutoColoring())
				listItem.setSelectedListItemColor(color);
		});
	}

	public void changeSelectedBoxColor(Color color) {
		selectedListItemBgColor = color;

		listItems.forEach(listItem -> {
			if (!listItem.isDisableAutoColoring())
				listItem.setSelectedListItemBgColor(color);
		});
	}

	public void changeUnselectedTextColor(Color color) {
		unselectedListItemColor = color;

		listItems.forEach(listItem -> {
			if (!listItem.isDisableAutoColoring())
				listItem.setUnselectedListItemColor(color);
		});
	}

	public void changeUnselectedBoxColor(Color color) {
		unselectedListItemBgColor = color;

		listItems.forEach(listItem -> {
			if (!listItem.isDisableAutoColoring())
				listItem.setUnselectedListItemBgColor(color);
		});
	}

	public Color getUnselectedListItemColor() {
		return unselectedListItemColor;
	}

	public Color getUnselectedListItemBgColor() {
		return unselectedListItemBgColor;
	}

	public Color getSelectedListItemColor() {
		return selectedListItemColor;
	}

	public Color getSelectedListItemBgColor() {
		return selectedListItemBgColor;
	}

	@Override
	public ComponentData getComponentData() {
		return new ComponentData<>(selectedListItems);
	}
}
