package me.alf21.textdrawsystem.utils;

import me.alf21.textdrawsystem.TextdrawSystem;
import net.gtaun.shoebill.constant.TextDrawAlign;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.object.Destroyable;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.Textdraw;
import net.gtaun.shoebill.object.TextdrawBase;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Alf21 on 26.02.2016.
 */
public class PlayersTextdraw implements Destroyable {

	private Textdraw playersTextdraw;
	private String text;
	private Color color, backgroundColor, boxColor;
	private TextDrawAlign alignment;
	private TextDrawFont font;
	private Vector2D position, letterSize, textSize;
	private boolean proportional, useBox, selectable;
	private int outlineSize, shadowSize;
	private ArrayList<Player> players;
	//private boolean showed TODO

	public PlayersTextdraw(ArrayList<Player> players, float x, float y) {
		this.players = players;
		playersTextdraw = Textdraw.create(x, y);
		TextdrawSystem.getPlayersTextdraws().add(this); //TODO
	}

	public static PlayersTextdraw create(ArrayList<Player> players, float x, float y) {
		PlayersTextdraw playersTextdraw = new PlayersTextdraw(players, x, y);
		playersTextdraw.setPosition(x, y);
		return playersTextdraw;
	}

	public static PlayersTextdraw create(ArrayList<Player> players, float x, float y, String text) {
		PlayersTextdraw playersTextdraw = create(players, x, y);
		playersTextdraw.setText(text);
		return playersTextdraw;
	}

	public static PlayersTextdraw create(ArrayList<Player> players, Vector2D vector2d) {
		return create(players, vector2d.getX(), vector2d.getY());
	}

	public static PlayersTextdraw create(ArrayList<Player> players, Vector2D vector2d, String text) {
		return create(players, vector2d.getX(), vector2d.getY(), text);
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

	public void setText(String text) {
		playersTextdraw.setText(text);
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		playersTextdraw.setColor(color);
		this.color = color;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		playersTextdraw.setBackgroundColor(backgroundColor);
		this.backgroundColor = backgroundColor;
	}

	public Color getBoxColor() {
		return boxColor;
	}

	public void setBoxColor(Color boxColor) {
		playersTextdraw.setBoxColor(boxColor);
		this.boxColor = boxColor;
	}

	public TextDrawAlign getAlignment() {
		return alignment;
	}

	public void setAlignment(TextDrawAlign alignment) {
		playersTextdraw.setAlignment(alignment);
		this.alignment = alignment;
	}

	public TextDrawFont getFont() {
		return font;
	}

	public void setFont(TextDrawFont font) {
		playersTextdraw.setFont(font);
		this.font = font;
	}

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	public void setPosition(float x, float y) {
		setPosition(new Vector2D(x, y));
	}

	public Vector2D getLetterSize() {
		return letterSize;
	}

	public void setLetterSize(Vector2D letterSize) {
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
		playersTextdraw.setProportional(proportional);
		this.proportional = proportional;
	}

	public boolean isUseBox() {
		return useBox;
	}

	public void setUseBox(boolean useBox) {
		playersTextdraw.setUseBox(useBox);
		this.useBox = useBox;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		playersTextdraw.setSelectable(selectable);
		this.selectable = selectable;
	}

	public int getOutlineSize() {
		return outlineSize;
	}

	public void setOutlineSize(int outlineSize) {
		playersTextdraw.setOutlineSize(outlineSize);
		this.outlineSize = outlineSize;
	}

	public int getShadowSize() {
		return shadowSize;
	}

	public void setShadowSize(int shadowSize) {
		playersTextdraw.setShadowSize(shadowSize);
		this.shadowSize = shadowSize;
	}


	public boolean isShowed(Player player) {
		return playersTextdraw.isShowed(player);
	}

	public boolean isShowed() {
		for(Player player : getPlayers()) {
			if(isShowed(player))
				return true;
		}
		return false;
	}

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
		if(isShowed())                      newPlayersTextdraw.show();
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
		if(isShowed())                      newPlayersTextdraw.show();
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
		boolean showed = isShowed();
		if(showed)
			hide();
		playersTextdraw.destroy();
		playersTextdraw = Textdraw.create(vector2d);
		setPosition(vector2d);
		synchronize();
		if(showed) {
			show();
		}
	}

	public void move(float x, float y) {
		move(new Vector2D(x, y));
	}

	public void synchronize() { //TODO also sync position?
		if(!isDestroyed()) {
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

	public void hide() {
		getPlayers().forEach(this::hide);
	}

	public void hide(Player player) {
		playersTextdraw.hide(player);
	}

	public void show() {
		getPlayers().forEach(this::show);
	}

	public void show(Player player) {
		playersTextdraw.show(player);
	}

	public int getId() {
		return playersTextdraw.getId();
	}

	public TextdrawBase getPrimitive() {
		return playersTextdraw.getPrimitive();
	}

	public boolean isPlayerTextdraw(Textdraw textdraw) {
		return this.playersTextdraw == textdraw;
	}

	@Override
	public void destroy() {
		playersTextdraw.destroy();
		TextdrawSystem.getPlayersTextdraws().remove(this);
	}

	@Override
	public boolean isDestroyed() {
		return playersTextdraw.isDestroyed();
	}

	public void recreate() {
		if(isDestroyed()) {
			playersTextdraw = Textdraw.create(getPosition());
			synchronize();
		}
	}

	public ArrayList<Player> getPlayers() { return players; }

	public void addPlayer(Player player) {
		getPlayers().add(player);
		if(isShowed())
			show(player);
	}

	public void removePlayer(Player player) {
		getPlayers().remove(player);
		if(isShowed())
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
}
