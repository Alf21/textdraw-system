package me.alf21.textdrawsystem.utils;

import me.alf21.textdrawsystem.TextdrawSystem;
import me.alf21.textdrawsystem.calculations.Calculation;
import net.gtaun.shoebill.Shoebill;
import net.gtaun.shoebill.constant.TextDrawAlign;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.entities.Destroyable;
import net.gtaun.shoebill.entities.Player;
import net.gtaun.shoebill.entities.TextdrawBase;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Alf21 on 26.02.2016 in the project 'textdraw-system'.
 */
public class PlayerTextdraw implements Destroyable {

	private static final boolean ENABLE_FRIENDLY_RESOLUTION = true;
	private static final boolean ENABLE_MAX_TEXTDRAW_CHECK = true;

	private net.gtaun.shoebill.entities.PlayerTextdraw playerTextdraw;
	private String text;
	private String allText;
	private Color color, backgroundColor, boxColor;
	private TextDrawAlign alignment;
	private TextDrawFont font;
	private Vector2D position, letterSize, textSize;
	private boolean proportional, useBox, selectable;
	private int outlineSize, shadowSize;
	private Player player;
	private boolean shown;

// custom
	private int boxEnabled;
	private float maxHeight, maxWidth;

	public PlayerTextdraw(Player player) {
		this.player = player;
		playerTextdraw = null;
		text = allText = "";
		color = Color.WHITE;
		backgroundColor = boxColor = Color.BLACK;
		alignment = TextDrawAlign.LEFT;
		font = TextDrawFont.FONT2;
		position = new Vector2D();
		letterSize = new Vector2D(0.5f, 1.0f);
		textSize = null;
		proportional = true;
		useBox = selectable = shown = false;
		outlineSize = 0;
		shadowSize = 1;
		boxEnabled = -1;
		TextdrawSystem.getPlayerTextdraws().add(this);
	}

	public static PlayerTextdraw create(Player player, float x, float y, String text) {
		PlayerTextdraw playerTextdraw = new PlayerTextdraw(player);
		playerTextdraw.setPosition(x, y);
		playerTextdraw.setText(text);
		return playerTextdraw;
	}

	public static PlayerTextdraw create(Player player, float x, float y) {
		return create(player, x, y, "_");
	}

	public static PlayerTextdraw create(Player player, Vector2D vector2d) {
		return create(player, vector2d.x, vector2d.y);
	}

	public static PlayerTextdraw create(Player player, Vector2D vector2d, String text) {
		return create(player, vector2d.x, vector2d.y, text);
	}

	public static PlayerTextdraw create(PlayersTextdraw playersTextdraw, Player player) {
		PlayerTextdraw playerTextdraw = create(player, playersTextdraw.getPosition(), playersTextdraw.getText());
		playerTextdraw.copy(playersTextdraw);
		return playerTextdraw;
	}

	public static PlayerTextdraw get(net.gtaun.shoebill.entities.PlayerTextdraw playerTextdraw) {
		for (PlayerTextdraw playerTextdraw1 : TextdrawSystem.getPlayerTextdraws()) {
			if (playerTextdraw1.isPlayerTextdraw(playerTextdraw))
				return playerTextdraw1;
		}
		return null;
	}

	public void setText(String text) {
		if(playerTextdraw != null && !playerTextdraw.isDestroyed())
			playerTextdraw.setText(text);
		this.text = text;
		if (boxEnabled != -1 && boxEnabled != 0) {
			switch (boxEnabled) {
				case 1:
					enableBox();
					break;
				case 2:
					enableBoxMaxWidth(maxWidth);
					break;
				case 3:
					enableBoxMaxHeight(maxHeight);
					break;
			}
		}
	}

	public String getText() {
		return text;
	}

	public String getAllText() {
		return allText;
	}

	public void setAllText(String allText) {
		this.allText = allText;
		setText(allText);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		if(playerTextdraw != null && !playerTextdraw.isDestroyed())
			playerTextdraw.setColor(color);
		this.color = color;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		if(playerTextdraw != null && !playerTextdraw.isDestroyed())
			playerTextdraw.setBackgroundColor(backgroundColor);
		this.backgroundColor = backgroundColor;
	}

	public Color getBoxColor() {
		return boxColor;
	}

	public void setBoxColor(Color boxColor) {
		if(playerTextdraw != null && !playerTextdraw.isDestroyed())
			playerTextdraw.setBoxColor(boxColor);
		this.boxColor = boxColor;
	}

	public TextDrawAlign getAlignment() {
		return alignment;
	}

	public void setAlignment(TextDrawAlign alignment) {
		if(playerTextdraw != null && !playerTextdraw.isDestroyed())
			playerTextdraw.setAlignment(alignment);
		this.alignment = alignment;
	}

	public TextDrawFont getFont() {
		return font;
	}

	public void setFont(TextDrawFont font) {
		if(playerTextdraw != null && !playerTextdraw.isDestroyed())
			playerTextdraw.setFont(font);
		this.font = font;
	}

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		if (ENABLE_FRIENDLY_RESOLUTION) {
			position.set((float) Math.round(position.x), (float) Math.round(position.y));
		}
		this.position = position;
	}

	public void setPosition(float x, float y) {
		setPosition(new Vector2D(x, y));
	}

	public Vector2D getLetterSize() {
		return letterSize;
	}

	public void setLetterSize(Vector2D letterSize) {
		if(playerTextdraw != null && !playerTextdraw.isDestroyed())
			playerTextdraw.setLetterSize(letterSize);
		this.letterSize = letterSize;
	}

	public void setLetterSize(float x, float y) {
		setLetterSize(new Vector2D(x, y));
	}

	public Vector2D getTextSize() {
		return textSize;
	}

	public void setTextSize(Vector2D textSize) {
		if(playerTextdraw != null && !playerTextdraw.isDestroyed())
			playerTextdraw.setTextSize(textSize);
		this.textSize = textSize;
	}

	public void setTextSize(float x, float y) {
		setTextSize(new Vector2D(x, y));
	}

	public boolean isProportional() {
		return proportional;
	}

	public void setProportional(boolean proportional) {
		if(playerTextdraw != null && !playerTextdraw.isDestroyed())
			playerTextdraw.setProportional(proportional);
		this.proportional = proportional;
	}

	public boolean isUseBox() {
		return useBox;
	}

	public void setUseBox(boolean useBox) {
		if(playerTextdraw != null && !playerTextdraw.isDestroyed())
			playerTextdraw.setUseBox(useBox);
		this.useBox = useBox;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		if(playerTextdraw != null && !playerTextdraw.isDestroyed())
			playerTextdraw.setSelectable(selectable);
		this.selectable = selectable;
	}

	public int getOutlineSize() {
		return outlineSize;
	}

	public void setOutlineSize(int outlineSize) {
		if(playerTextdraw != null && !playerTextdraw.isDestroyed())
			playerTextdraw.setOutlineSize(outlineSize);
		this.outlineSize = outlineSize;
	}

	public int getShadowSize() {
		return shadowSize;
	}

	public void setShadowSize(int shadowSize) {
		if(playerTextdraw != null && !playerTextdraw.isDestroyed())
			playerTextdraw.setShadowSize(shadowSize);
		this.shadowSize = shadowSize;
	}

	public boolean isShown() {
		return (shown && playerTextdraw != null && !playerTextdraw.isDestroyed() && playerTextdraw.isShown()
				|| playerTextdraw != null && !playerTextdraw.isDestroyed() && playerTextdraw.isShown());
	}

	public boolean isShown(Player player) {
		return this.player == player && isShown();
	}

	@Override
	public PlayerTextdraw clone() {
		PlayerTextdraw newPlayerTextdraw = PlayerTextdraw.create(getPlayer(), getPosition(), getText());
		if(getAlignment() != null)          newPlayerTextdraw.setAlignment(getAlignment());
		if(getBackgroundColor() != null)    newPlayerTextdraw.setBackgroundColor(getBackgroundColor());
		if(getBoxColor() != null)           newPlayerTextdraw.setBoxColor(getBoxColor());
		if(getColor() != null)              newPlayerTextdraw.setColor(getColor());
		if(getFont() != null)               newPlayerTextdraw.setFont(getFont());
		if(getLetterSize() != null)         newPlayerTextdraw.setLetterSize(getLetterSize());
		newPlayerTextdraw.setOutlineSize(getOutlineSize());
		newPlayerTextdraw.setProportional(isProportional());
		newPlayerTextdraw.setSelectable(isSelectable());
		newPlayerTextdraw.setShadowSize(getShadowSize());
		if(getTextSize() != null)           newPlayerTextdraw.setTextSize(getTextSize());
		newPlayerTextdraw.setUseBox(isUseBox());
		if(isShown())                      newPlayerTextdraw.show();
		return newPlayerTextdraw;
	}

	public PlayerTextdraw clone(Player player) {
		PlayerTextdraw newPlayerTextdraw = PlayerTextdraw.create(player, getPosition(), getText());
		if(getAlignment() != null)          newPlayerTextdraw.setAlignment(getAlignment());
		if(getBackgroundColor() != null)    newPlayerTextdraw.setBackgroundColor(getBackgroundColor());
		if(getBoxColor() != null)           newPlayerTextdraw.setBoxColor(getBoxColor());
		if(getColor() != null)              newPlayerTextdraw.setColor(getColor());
		if(getFont() != null)               newPlayerTextdraw.setFont(getFont());
		if(getLetterSize() != null)         newPlayerTextdraw.setLetterSize(getLetterSize());
		newPlayerTextdraw.setOutlineSize(getOutlineSize());
		newPlayerTextdraw.setProportional(isProportional());
		newPlayerTextdraw.setSelectable(isSelectable());
		newPlayerTextdraw.setShadowSize(getShadowSize());
		if(getTextSize() != null)           newPlayerTextdraw.setTextSize(getTextSize());
		newPlayerTextdraw.setUseBox(isUseBox());
		if(isShown())                      newPlayerTextdraw.show();
		return newPlayerTextdraw;
	}

	public void copy(PlayerTextdraw playerTextdraw) {
		if(playerTextdraw.getAlignment() != null)       setAlignment(playerTextdraw.getAlignment());
		if(playerTextdraw.getBackgroundColor() != null) setBackgroundColor(playerTextdraw.getBackgroundColor());
		if(playerTextdraw.getBoxColor() != null)        setBoxColor(playerTextdraw.getBoxColor());
		if(playerTextdraw.getColor() != null)           setColor(playerTextdraw.getColor());
		if(playerTextdraw.getFont() != null)            setFont(playerTextdraw.getFont());
		if(playerTextdraw.getLetterSize() != null)      setLetterSize(playerTextdraw.getLetterSize());
		setOutlineSize(playerTextdraw.getOutlineSize());
		setProportional(playerTextdraw.isProportional());
		setSelectable(playerTextdraw.isSelectable());
		setShadowSize(playerTextdraw.getShadowSize());
		setText(playerTextdraw.getText());
		if(playerTextdraw.getTextSize() != null)        setTextSize(playerTextdraw.getTextSize());
		setUseBox(playerTextdraw.isUseBox());
	}

	public void copy(PlayersTextdraw playersTextdraw) {
		if(playersTextdraw.getAlignment() != null)       setAlignment(playersTextdraw.getAlignment());
		if(playersTextdraw.getBackgroundColor() != null) setBackgroundColor(playersTextdraw.getBackgroundColor());
		if(playersTextdraw.getBoxColor() != null)        setBoxColor(playersTextdraw.getBoxColor());
		if(playersTextdraw.getColor() != null)           setColor(playersTextdraw.getColor());
		if(playersTextdraw.getFont() != null)            setFont(playersTextdraw.getFont());
		if(playersTextdraw.getLetterSize() != null)      setLetterSize(playersTextdraw.getLetterSize());
		setOutlineSize(playersTextdraw.getOutlineSize());
		setProportional(playersTextdraw.isProportional());
		setSelectable(playersTextdraw.isSelectable());
		setShadowSize(playersTextdraw.getShadowSize());
		setText(playersTextdraw.getText());
		if(playersTextdraw.getTextSize() != null)        setTextSize(playersTextdraw.getTextSize());
		setUseBox(playersTextdraw.isUseBox());
	}

	public void move(Vector2D vector2d) {
		if(playerTextdraw != null && !playerTextdraw.isDestroyed()) {
			boolean shown = isShown();
			if (ENABLE_FRIENDLY_RESOLUTION) {
				vector2d.set((float) Math.round(vector2d.x), (float) Math.round(vector2d.y));
			}
			if (shown)
				hide();
			playerTextdraw.destroy();
			playerTextdraw = net.gtaun.shoebill.entities.PlayerTextdraw.create(getPlayer(), vector2d);
			setPosition(vector2d);
			synchronize();
			if (shown)
				show();
		}
		else setPosition(vector2d);
	}

	public void move(float x, float y) {
		move(new Vector2D(x, y));
	}

	public void changePlayer(Player player) {
		this.player = player;
		if(playerTextdraw != null && !playerTextdraw.isDestroyed()) {
			boolean shown = isShown();
			if (shown)
				hide();
			playerTextdraw.destroy();
			playerTextdraw = net.gtaun.shoebill.entities.PlayerTextdraw.create(player, getPosition());
			synchronize();
			if (shown)
				show();
		}
	}

	public void synchronize() { //TODO also sync position?
		if(playerTextdraw != null && !isDestroyed()) {
			if(getAlignment() != null)          playerTextdraw.setAlignment(getAlignment());
			if(getBackgroundColor() != null)    playerTextdraw.setBackgroundColor(getBackgroundColor());
			if(getBoxColor() != null)           playerTextdraw.setBoxColor(getBoxColor());
			if(getColor() != null)              playerTextdraw.setColor(getColor());
			if(getFont() != null)               playerTextdraw.setFont(getFont());
			if(getLetterSize() != null)         playerTextdraw.setLetterSize(getLetterSize());
			playerTextdraw.setOutlineSize(getOutlineSize());
			playerTextdraw.setProportional(isProportional());
			playerTextdraw.setSelectable(isSelectable());
			playerTextdraw.setShadowSize(getShadowSize());
			playerTextdraw.setText(getText());
			if(getTextSize() != null)           playerTextdraw.setTextSize(getTextSize());
			playerTextdraw.setUseBox(isUseBox());
		}
	}

	public void clear() {
		if (!isDestroyed()) destroy();
		playerTextdraw = null;
		text = null;
		color = backgroundColor = boxColor = null;
		alignment = null;
		font = null;
		position = letterSize = textSize = null;
		proportional = useBox = selectable = false;
		outlineSize = shadowSize = 0;
		player = null;
	}

	public void hide() {
		if (playerTextdraw != null && !playerTextdraw.isDestroyed())
			playerTextdraw.hide();
		shown = false;
	}

	public void show() {
		if (ENABLE_MAX_TEXTDRAW_CHECK)
			if (Shoebill.get().getSampObjectManager().getPlayerTextdraws(player).size() >= 256)
				return;
		if (playerTextdraw == null || playerTextdraw.isDestroyed())
			recreate();
		playerTextdraw.show();
		shown = true;
	}

	public int getId() {
		if(playerTextdraw != null && !playerTextdraw.isDestroyed())
			return playerTextdraw.getId();
		else return -1;
	}

	public TextdrawBase getPrimitive() {
		if(playerTextdraw != null && !playerTextdraw.isDestroyed())
			return playerTextdraw.getPrimitive();
		else return null;
	}

	public boolean isPlayerTextdraw(net.gtaun.shoebill.entities.PlayerTextdraw playerTextdraw) {
		return this.playerTextdraw == playerTextdraw;
	}

	@Override
	public void destroy() {
		if(playerTextdraw != null && !playerTextdraw.isDestroyed())
			playerTextdraw.destroy();
		TextdrawSystem.getPlayerTextdraws().remove(this);
	}

	@Override
	public boolean isDestroyed() {
		return playerTextdraw == null || playerTextdraw.isDestroyed();
	}

	public void recreate() {
		if(playerTextdraw == null || isDestroyed()) {
			playerTextdraw = net.gtaun.shoebill.entities.PlayerTextdraw.create(player, getPosition());
			synchronize();
		}
	}

	public Player getPlayer() {
		return player;
	}

	public PlayersTextdraw addPlayers(Player... players) {
		ArrayList<Player> tmpPlayers = new ArrayList<>();
		tmpPlayers.add(player);
		Collections.addAll(tmpPlayers, players);
		PlayersTextdraw playersTextdraw = PlayersTextdraw.create(tmpPlayers, getPosition());
		playersTextdraw.copy(this);
		if(isShown()) {
			hide();
			playersTextdraw.show();
		}
		destroy();
		return playersTextdraw;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return super.toString();
	}

//custom functions
	public void enableBox() {
		boxEnabled = 1;
		setUseBox(true);
		setBox(Calculation.getBoxWidth(this) + 8f, Calculation.getBoxHeight(this) + 8f);
		update();
	}

	public void enableBoxMaxWidth(float maxWidth) {
		boxEnabled = 2;
		this.maxWidth = maxWidth;
		setUseBox(true);
		setBox(Calculation.getBoxWidth(this) + 8f, Calculation.getBoxHeight(this, maxWidth) + 8f);
		update();
	}

	public void enableBoxMaxHeight(float maxHeight) {
		boxEnabled = 3;
		this.maxHeight = maxHeight;
		setUseBox(true);
		setBox(Calculation.getBoxWidth(this, maxHeight) + 8f, Calculation.getBoxHeight(this) + 8f);
		update();
	}

	public void setWidth(float width) {
		if(getTextSize() == null)
			setTextSize(0f, 0f);
		switch (getAlignment()) {
			case LEFT:
				setTextSize(getPosition().x - 4f + width, getTextSize().y);
				break;
			case CENTER:
				setTextSize(getTextSize().x, width - 4f); //TODO 3f?
				break;
			case RIGHT:
				setTextSize(getPosition().x - 4f - width, getTextSize().y);
				break;
		}
	}

	public void setHeight(float height) {
		if(getTextSize() == null)
			setTextSize(0f, 0f);
		switch (getAlignment()) {
			case LEFT:
				setTextSize(getTextSize().x, height - 4f);
				break;
			case CENTER:
				setTextSize(height - 4f, getTextSize().y);
				break;
			case RIGHT:
				setTextSize(getTextSize().x, height - 4f);
				break;
		}
	}

	public void setBox(float width, float height) {
		setWidth(width);
		setHeight(height);
	}

	public void update() {
		if (isShown()) {
			hide();
			show();
		}
	}

	public net.gtaun.shoebill.entities.PlayerTextdraw getPlayerTextdraw() {
		return playerTextdraw;
	}
}
