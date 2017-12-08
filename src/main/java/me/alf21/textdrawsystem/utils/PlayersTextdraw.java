package me.alf21.textdrawsystem.utils;

import me.alf21.textdrawsystem.TextdrawSystem;
import me.alf21.textdrawsystem.calculations.Calculation;
import net.gtaun.shoebill.constant.TextDrawAlign;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.entities.Destroyable;
import net.gtaun.shoebill.entities.Player;
import net.gtaun.shoebill.entities.Textdraw;
import net.gtaun.shoebill.entities.TextdrawBase;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Alf21 on 26.02.2016 in the project 'textdraw-system'.
 */
public class PlayersTextdraw implements Destroyable {

	private static final boolean ENABLE_FRIENDLY_RESOLUTION = true;

	private Textdraw playersTextdraw;
	private String text;
	private String allText;
	private Color color, backgroundColor, boxColor;
	private TextDrawAlign alignment;
	private TextDrawFont font;
	private Vector2D position, letterSize, textSize;
	private boolean proportional, useBox, selectable;
	private int outlineSize, shadowSize;
	private ArrayList<Player> players;
	private boolean shown;

	public PlayersTextdraw(ArrayList<Player> players) {
		this.players = players;
		playersTextdraw = null;
		text = allText = "";
		color = Color.WHITE;
		backgroundColor = boxColor = Color.BLACK;
		alignment = TextDrawAlign.LEFT;
		font = TextDrawFont.FONT2;
		position = letterSize = textSize = new Vector2D();
		proportional = true;
		useBox = selectable = shown = false;
		outlineSize = 0;
		shadowSize = 1;
		TextdrawSystem.getPlayersTextdraws().add(this); //TODO
	}

	public static PlayersTextdraw create(ArrayList<Player> players, float x, float y, String text) {
		PlayersTextdraw playersTextdraw = new PlayersTextdraw(players);
		playersTextdraw.setPosition(x, y);
		playersTextdraw.setText(text);
		return playersTextdraw;
	}

	public static PlayersTextdraw create(ArrayList<Player> players, float x, float y) {
		return create(players, x, y, "_");
	}

	public static PlayersTextdraw create(ArrayList<Player> players, Vector2D vector2d) {
		return create(players, vector2d.x, vector2d.y);
	}

	public static PlayersTextdraw create(ArrayList<Player> players, Vector2D vector2d, String text) {
		return create(players, vector2d.x, vector2d.y, text);
	}

	public static PlayersTextdraw create(float x, float y, Player... players) {
		ArrayList<Player> tmpPlayers = new ArrayList<>();
		Collections.addAll(tmpPlayers, players);
		return create(tmpPlayers, x, y);
	}

	public static PlayersTextdraw create(float x, float y, String text, Player... players) {
		ArrayList<Player> tmpPlayers = new ArrayList<>();
		Collections.addAll(tmpPlayers, players);
		return create(tmpPlayers, x, y, text);
	}

	public static PlayersTextdraw create(Vector2D vector2D, Player... players) {
		ArrayList<Player> tmpPlayers = new ArrayList<>();
		Collections.addAll(tmpPlayers, players);
		return create(tmpPlayers, vector2D);
	}

	public static PlayersTextdraw create(Vector2D vector2D, String text, Player... players) {
		ArrayList<Player> tmpPlayers = new ArrayList<>();
		Collections.addAll(tmpPlayers, players);
		return create(tmpPlayers, vector2D, text);
	}

	public static PlayersTextdraw create(PlayerTextdraw playerTextdraw) {
		PlayersTextdraw playersTextdraw = create(playerTextdraw.getPosition(), playerTextdraw.getText(), playerTextdraw.getPlayer());
		playersTextdraw.copy(playerTextdraw);
		return playersTextdraw;
	}

	public static PlayersTextdraw get(net.gtaun.shoebill.entities.Textdraw textdraw) {
		for (PlayersTextdraw playersTextdraw1 : TextdrawSystem.getPlayersTextdraws()) {
			if (playersTextdraw1.isPlayerTextdraw(textdraw))
				return playersTextdraw1;
		}
		return null;
	}

	public void setText(String text) {
		if(playersTextdraw != null && !playersTextdraw.isDestroyed())
			playersTextdraw.setText(text);
		this.text = text;
	/* TODO
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
	*/
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
		if(playersTextdraw != null && !playersTextdraw.isDestroyed())
			playersTextdraw.setColor(color);
		this.color = color;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		if(playersTextdraw != null && !playersTextdraw.isDestroyed())
			playersTextdraw.setBackgroundColor(backgroundColor);
		this.backgroundColor = backgroundColor;
	}

	public Color getBoxColor() {
		return boxColor;
	}

	public void setBoxColor(Color boxColor) {
		if(playersTextdraw != null && !playersTextdraw.isDestroyed())
			playersTextdraw.setBoxColor(boxColor);
		this.boxColor = boxColor;
	}

	public TextDrawAlign getAlignment() {
		return alignment;
	}

	public void setAlignment(TextDrawAlign alignment) {
		if(playersTextdraw != null && !playersTextdraw.isDestroyed())
			playersTextdraw.setAlignment(alignment);
		this.alignment = alignment;
	}

	public TextDrawFont getFont() {
		return font;
	}

	public void setFont(TextDrawFont font) {
		if(playersTextdraw != null && !playersTextdraw.isDestroyed())
			playersTextdraw.setFont(font);
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
		if(playersTextdraw != null && !playersTextdraw.isDestroyed())
			playersTextdraw.setLetterSize(letterSize);
		this.letterSize = letterSize;
	}

	public void setLetterSize(float x, float y) {
		setLetterSize(new Vector2D(x, y));
	}

	public Vector2D getTextSize() {
		return textSize;
	}

	public void setTextSize(Vector2D textSize) {
		if(playersTextdraw != null && !playersTextdraw.isDestroyed())
			playersTextdraw.setTextSize(textSize);
		this.textSize = textSize;
	}

	public void setTextSize(float x, float y) {
		setTextSize(new Vector2D(x, y));
	}

	public boolean isProportional() {
		return proportional;
	}

	public void setProportional(boolean proportional) {
		if(playersTextdraw != null && !playersTextdraw.isDestroyed())
			playersTextdraw.setProportional(proportional);
		this.proportional = proportional;
	}

	public boolean isUseBox() {
		return useBox;
	}

	public void setUseBox(boolean useBox) {
		if(playersTextdraw != null && !playersTextdraw.isDestroyed())
			playersTextdraw.setUseBox(useBox);
		this.useBox = useBox;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		if(playersTextdraw != null && !playersTextdraw.isDestroyed())
			playersTextdraw.setSelectable(selectable);
		this.selectable = selectable;
	}

	public int getOutlineSize() {
		return outlineSize;
	}

	public void setOutlineSize(int outlineSize) {
		if(playersTextdraw != null && !playersTextdraw.isDestroyed())
			playersTextdraw.setOutlineSize(outlineSize);
		this.outlineSize = outlineSize;
	}

	public int getShadowSize() {
		return shadowSize;
	}

	public void setShadowSize(int shadowSize) {
		if(playersTextdraw != null && !playersTextdraw.isDestroyed())
			playersTextdraw.setShadowSize(shadowSize);
		this.shadowSize = shadowSize;
	}

	public boolean isShown(Player player) {
		return (shown && playersTextdraw != null && !playersTextdraw.isDestroyed() && playersTextdraw.isShownForPlayer(player)
				|| playersTextdraw != null && !playersTextdraw.isDestroyed() && playersTextdraw.isShownForPlayer(player));
	}

	public boolean isShown() {
		for(Player player : getPlayers()) {
			if(isShown(player))
				return true;
		}
		return false;
	}

	@Override
	public PlayersTextdraw clone() {
		PlayersTextdraw newPlayersTextdraw = PlayersTextdraw.create(getPosition(), getText());
		if(getAlignment() != null)          newPlayersTextdraw.setAlignment(getAlignment());
		if(getBackgroundColor() != null)    newPlayersTextdraw.setBackgroundColor(getBackgroundColor());
		if(getBoxColor() != null)           newPlayersTextdraw.setBoxColor(getBoxColor());
		if(getColor() != null)              newPlayersTextdraw.setColor(getColor());
		if(getFont() != null)               newPlayersTextdraw.setFont(getFont());
		if(getLetterSize() != null)         newPlayersTextdraw.setLetterSize(getLetterSize());
		newPlayersTextdraw.setOutlineSize(getOutlineSize());
		newPlayersTextdraw.setProportional(isProportional());
		newPlayersTextdraw.setSelectable(isSelectable());
		newPlayersTextdraw.setShadowSize(getShadowSize());
		if(getTextSize() != null)           newPlayersTextdraw.setTextSize(getTextSize());
		newPlayersTextdraw.setUseBox(isUseBox());
		if(isShown())                      newPlayersTextdraw.show();
		players.forEach(newPlayersTextdraw::addPlayer);
		return newPlayersTextdraw;
	}

	public PlayersTextdraw clone(Player... players) {
		PlayersTextdraw newPlayersTextdraw = PlayersTextdraw.create(getPosition(), getText());
		if(getAlignment() != null)          newPlayersTextdraw.setAlignment(getAlignment());
		if(getBackgroundColor() != null)    newPlayersTextdraw.setBackgroundColor(getBackgroundColor());
		if(getBoxColor() != null)           newPlayersTextdraw.setBoxColor(getBoxColor());
		if(getColor() != null)              newPlayersTextdraw.setColor(getColor());
		if(getFont() != null)               newPlayersTextdraw.setFont(getFont());
		if(getLetterSize() != null)         newPlayersTextdraw.setLetterSize(getLetterSize());
		newPlayersTextdraw.setOutlineSize(getOutlineSize());
		newPlayersTextdraw.setProportional(isProportional());
		newPlayersTextdraw.setSelectable(isSelectable());
		newPlayersTextdraw.setShadowSize(getShadowSize());
		if(getTextSize() != null)           newPlayersTextdraw.setTextSize(getTextSize());
		newPlayersTextdraw.setUseBox(isUseBox());
		if(isShown())                      newPlayersTextdraw.show();
		for (Player player : players) {
			newPlayersTextdraw.addPlayer(player);
		}
		return newPlayersTextdraw;
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

	public void move(Vector2D vector2d) {
		if (playersTextdraw != null && !playersTextdraw.isDestroyed()) {
			boolean showed = isShown();
			if (ENABLE_FRIENDLY_RESOLUTION) {
				vector2d.set((float) Math.round(vector2d.x), (float) Math.round(vector2d.y));
			}
			if (showed)
				hide();
			playersTextdraw.destroy();
			playersTextdraw = Textdraw.create(vector2d);
			setPosition(vector2d);
			synchronize();
			if (showed) {
				show();
			}
		}
		else setPosition(vector2d);
	}

	public void move(float x, float y) {
		move(new Vector2D(x, y));
	}

	public void synchronize() { //TODO also sync position?
		if(playersTextdraw != null && !isDestroyed()) {
			if(getAlignment() != null)          playersTextdraw.setAlignment(getAlignment());
			if(getBackgroundColor() != null)    playersTextdraw.setBackgroundColor(getBackgroundColor());
			if(getBoxColor() != null)           playersTextdraw.setBoxColor(getBoxColor());
			if(getColor() != null)              playersTextdraw.setColor(getColor());
			if(getFont() != null)               playersTextdraw.setFont(getFont());
			if(getLetterSize() != null)         playersTextdraw.setLetterSize(getLetterSize());
			playersTextdraw.setOutlineSize(getOutlineSize());
			playersTextdraw.setProportional(isProportional());
			playersTextdraw.setSelectable(isSelectable());
			playersTextdraw.setShadowSize(getShadowSize());
			playersTextdraw.setText(getText());
			if(getTextSize() != null)           playersTextdraw.setTextSize(getTextSize());
			playersTextdraw.setUseBox(isUseBox());
		}
	}

	public void clear() {
		if (!isDestroyed()) destroy();
		playersTextdraw = null;
		text = null;
		color = backgroundColor = boxColor = null;
		alignment = null;
		font = null;
		position = letterSize = textSize = null;
		proportional = useBox = selectable = false;
		outlineSize = shadowSize = 0;
		players = null;
	}

	public void hide() {
		getPlayers().forEach(this::hide);
	}

	public void hide(Player player) {
		if (playersTextdraw != null && !playersTextdraw.isDestroyed())
			playersTextdraw.hide(player);
		shown = false;
	}

	public void show() {
		getPlayers().forEach(this::show);
	}

	public void show(Player player) {
		if (playersTextdraw == null || playersTextdraw.isDestroyed())
			recreate();
		playersTextdraw.show(player);
		shown = true;
	}

	public int getId() {
		if (playersTextdraw != null && !playersTextdraw.isDestroyed())
			return playersTextdraw.getId();
		else return -1;
	}

	public TextdrawBase getPrimitive() {
		if (playersTextdraw != null && !playersTextdraw.isDestroyed())
			return playersTextdraw.getPrimitive();
		else return null;
	}

	public boolean isPlayerTextdraw(Textdraw textdraw) {
		return this.playersTextdraw == textdraw;
	}

	@Override
	public void destroy() {
		if (playersTextdraw != null && !playersTextdraw.isDestroyed())
			playersTextdraw.destroy();
		TextdrawSystem.getPlayersTextdraws().remove(this);
	}

	@Override
	public boolean isDestroyed() {
		return (playersTextdraw == null || playersTextdraw.isDestroyed());
	}

	public void recreate() {
		if(playersTextdraw == null || isDestroyed()) {
			playersTextdraw = Textdraw.create(getPosition());
			synchronize();
		}
	}

	public ArrayList<Player> getPlayers() { return players; }

	public void addPlayer(Player player) {
		getPlayers().add(player);
		if(isShown())
			show(player);
	}

	public void removePlayer(Player player) {
		getPlayers().remove(player);
		if(isShown())
			hide(player);
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
		setUseBox(true);
		setBox(Calculation.getBoxWidth(this) + 8f, Calculation.getBoxHeight(this) + 8f);
		update();
	}

	public void setWidth(float width) {
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

	public Textdraw getPlayersTextdraw() {
		return playersTextdraw;
	}
}
