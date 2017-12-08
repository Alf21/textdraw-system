package me.alf21.textdrawsystem.content.components.list;

import me.alf21.textdrawsystem.calculations.Calculation;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.constant.TextDrawAlign;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.entities.Destroyable;
import net.gtaun.shoebill.entities.Player;

/**
 * Created by Alf21 on 17.03.2016 in the project 'textdraw-system'.
 */
public class ListBar implements Destroyable {

	private PlayerTextdraw bar, barbg, next, previous;
	private float width;
	private int currentIndex;
	private List list;

	protected ListBar(List list) {
		this.list = list;
		width = 8.0f;
		Player player = list.getPlayer();
		Vector2D position = list.getPosition();
		float maxWidth = list.getMaxWidth(), maxHeight = list.getMaxHeight();

		previous = PlayerTextdraw.create(player, position.x + maxWidth - width, position.y, "~u~");
		previous.setBackgroundColor(new Color(0, 0, 0, 255));
		previous.setFont(TextDrawFont.get(1));
		previous.setLetterSize(new Vector2D(0.5f, 1.0f));
		previous.setColor(new Color(255, 255, 255, 255));
		previous.setOutlineSize(0);
		previous.setProportional(true);
		previous.setShadowSize(1);
		previous.setUseBox(true);
		previous.setBoxColor(new Color(255, 255, 255, 50));
		previous.setTextSize(new Vector2D(position.x + maxWidth, Calculation.letterHeightToHeight(1.0f)));
		previous.setSelectable(true);

		barbg = PlayerTextdraw.create(player, position.x + maxWidth - width / 2, position.y + 15.0f, "_");
		barbg.setAlignment(TextDrawAlign.get(2));
		barbg.setBackgroundColor(new Color(0, 0, 0, 255));
		barbg.setFont(TextDrawFont.get(1));
		barbg.setLetterSize(new Vector2D(0.5f, 11.4f));
		barbg.setColor(new Color(255, 255, 255, 255));
		barbg.setOutlineSize(0);
		barbg.setProportional(true);
		barbg.setShadowSize(1);
		barbg.setUseBox(true);
		barbg.setBoxColor(new Color(255, 255, 255, 50));
		barbg.setTextSize(new Vector2D(11.4f, width));
		barbg.setSelectable(false);

		bar = PlayerTextdraw.create(player, position.x + maxWidth - width / 2, position.y + 15.0f, "_");
		bar.setAlignment(TextDrawAlign.get(2));
		bar.setBackgroundColor(new Color(0, 0, 0, 255));
		bar.setFont(TextDrawFont.get(1));
		bar.setLetterSize(new Vector2D(0.5f, 11.4f));
		bar.setColor(new Color(255, 255, 255, 255));
		bar.setOutlineSize(0);
		bar.setProportional(true);
		bar.setShadowSize(1);
		bar.setUseBox(true);
		bar.setBoxColor(new Color(255, 255, 255, 50));
		bar.setTextSize(new Vector2D(11.4f, width));
		bar.setSelectable(false);

		next = PlayerTextdraw.create(player, position.x + maxWidth - width, position.y + maxHeight - 15.0f, "~d~");
		next.setBackgroundColor(new Color(0, 0, 0, 255));
		next.setFont(TextDrawFont.get(1));
		next.setLetterSize(new Vector2D(0.5f, 1.0f));
		next.setColor(new Color(255, 255, 255, 255));
		next.setOutlineSize(0);
		next.setProportional(true);
		next.setShadowSize(1);
		next.setUseBox(true);
		next.setBoxColor(new Color(255, 255, 255, 50));
		next.setTextSize(new Vector2D(position.x + maxWidth, Calculation.letterHeightToHeight(1.0f)));
		next.setSelectable(true);

		currentIndex = -1;
	}

	public static ListBar create(List list) {
		return new ListBar(list);
	}

	public void hide() {
		next.hide();
		bar.hide();
		barbg.hide();
		previous.hide();
	}

	public void show() {
		if (getCurrentIndex() == -1)
			setCurrentIndex(0);
		previous.show();
		barbg.show();
		bar.show();
		next.show();
	}

	public void recreate() {
		previous.recreate();
		barbg.recreate();
		next.recreate();
		bar.recreate();
	}

	@Override
	public void destroy() {
		bar.destroy();
		next.destroy();
		barbg.destroy();
		previous.destroy();
	}

	@Override
	public boolean isDestroyed() {
		return !(!bar.isDestroyed() || !next.isDestroyed() || !previous.isDestroyed() || !barbg.isDestroyed());
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
		//TODO reinit the textdraws
	}

	public PlayerTextdraw getBar() {
		return bar;
	}

	public PlayerTextdraw getBarbg() {
		return barbg;
	}

	public PlayerTextdraw getNext() {
		return next;
	}

	public PlayerTextdraw getPrevious() {
		return previous;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;

		int visibleListItems = list.getAmountVisibleListItems();
		if (visibleListItems > 0) {
			if (list.getListItems().size() - visibleListItems > 0) {
				float   height = barbg.getPosition().y,
						boxHeight = Calculation.getBoxHeight(barbg); //TODO calc with -4px ?
				boxHeight -= boxHeight / (float) visibleListItems;

				float barHeight = boxHeight / (float) (list.getListItems().size() - visibleListItems);
				height += barHeight * (float) currentIndex;

				boolean showed = bar.isShown();
				if (!bar.isDestroyed() && showed) bar.hide();

				bar.move(bar.getPosition().x, height);
				bar.setHeight(barHeight);
				bar.setLetterSize(bar.getLetterSize().x, Calculation.heightToLetterHeight(barHeight));

				if (!bar.isDestroyed() && showed) bar.show();
			}
			else {
				boolean showed = bar.isShown();
				if (!bar.isDestroyed() && showed) bar.hide();

				bar.move(barbg.getPosition());
				bar.setTextSize(barbg.getTextSize());
				bar.setLetterSize(barbg.getLetterSize());

				if (!bar.isDestroyed() && showed) bar.show();
			}
		}
	}
}
