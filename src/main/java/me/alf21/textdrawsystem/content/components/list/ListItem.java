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

	private String text;
	private ListItemInterface listItemInterface;
	private PlayerTextdraw playerTextdraw;
	private Long clicked;

	protected ListItem(List list, String text, ListItemInterface listItemInterface) {
		this.text = text;
		this.listItemInterface = listItemInterface;
		clicked = System.currentTimeMillis();

		playerTextdraw = PlayerTextdraw.create(list.getPlayer(), list.getPosition().getX(), list.getPosition().getY() + 15.0f * list.getListItems().size(), getText());
		playerTextdraw.setBackgroundColor(new Color(0, 0, 0, 255));
		playerTextdraw.setFont(TextDrawFont.get(1));
		playerTextdraw.setLetterSize(new Vector2D(0.5f, 1.0f));
		playerTextdraw.setColor(new Color(255, 255, 255, 255));
		playerTextdraw.setOutlineSize(0);
		playerTextdraw.setProportional(true);
		playerTextdraw.setShadowSize(1);
		playerTextdraw.setUseBox(true);
		playerTextdraw.setBoxColor(new Color(255, 255, 255, 50));
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

	public ListItemInterface getListItemInterface() {
		return listItemInterface;
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
}
