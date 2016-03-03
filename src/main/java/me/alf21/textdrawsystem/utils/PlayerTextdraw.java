package me.alf21.textdrawsystem.utils;

import net.gtaun.shoebill.constant.TextDrawAlign;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.object.Destroyable;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.TextdrawBase;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Alf21 on 26.02.2016.
 */
public class PlayerTextdraw implements Destroyable {

	private net.gtaun.shoebill.object.PlayerTextdraw playerTextdraw;
	private String text;
	private Color color, backgroundColor, boxColor;
	private TextDrawAlign alignment;
	private TextDrawFont font;
	private Vector2D position, letterSize, textSize;
	private boolean proportional, useBox, selectable;
	private int outlineSize, shadowSize;
	private Player player;
	//private boolean showed TODO

	public PlayerTextdraw(Player player, float x, float y) {
		this.player = player;
		playerTextdraw = net.gtaun.shoebill.object.PlayerTextdraw.create(player, x, y);
	}

	public static PlayerTextdraw create(Player player, float x, float y) {
		PlayerTextdraw playerTextdraw = new PlayerTextdraw(player, x, y);
		playerTextdraw.setPosition(x, y);
		return playerTextdraw;
	}

	public static PlayerTextdraw create(Player player, float x, float y, String text) {
		PlayerTextdraw playerTextdraw = create(player, x, y);
		playerTextdraw.setText(text);
		playerTextdraw.setPosition(x, y);
		return playerTextdraw;
	}

	public static PlayerTextdraw create(Player player, Vector2D vector2d) {
		return create(player, vector2d.getX(), vector2d.getY());
	}

	public static PlayerTextdraw create(Player player, Vector2D vector2d, String text) {
		return create(player, vector2d.getX(), vector2d.getY(), text);
	}

	public void setText(String text) {
		playerTextdraw.setText(text);
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		playerTextdraw.setColor(color);
		this.color = color;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		playerTextdraw.setBackgroundColor(backgroundColor);
		this.backgroundColor = backgroundColor;
	}

	public Color getBoxColor() {
		return boxColor;
	}

	public void setBoxColor(Color boxColor) {
		playerTextdraw.setBoxColor(boxColor);
		this.boxColor = boxColor;
	}

	public TextDrawAlign getAlignment() {
		return alignment;
	}

	public void setAlignment(TextDrawAlign alignment) {
		playerTextdraw.setAlignment(alignment);
		this.alignment = alignment;
	}

	public TextDrawFont getFont() {
		return font;
	}

	public void setFont(TextDrawFont font) {
		playerTextdraw.setFont(font);
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
		playerTextdraw.setProportional(proportional);
		this.proportional = proportional;
	}

	public boolean isUseBox() {
		return useBox;
	}

	public void setUseBox(boolean useBox) {
		playerTextdraw.setUseBox(useBox);
		this.useBox = useBox;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		playerTextdraw.setSelectable(selectable);
		this.selectable = selectable;
	}

	public int getOutlineSize() {
		return outlineSize;
	}

	public void setOutlineSize(int outlineSize) {
		playerTextdraw.setOutlineSize(outlineSize);
		this.outlineSize = outlineSize;
	}

	public int getShadowSize() {
		return shadowSize;
	}

	public void setShadowSize(int shadowSize) {
		playerTextdraw.setShadowSize(shadowSize);
		this.shadowSize = shadowSize;
	}


	public boolean isShowed() {
		return playerTextdraw.isShowed();
	}

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
		if(isShowed())                      newPlayerTextdraw.show();
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
		if(isShowed())                      newPlayerTextdraw.show();
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
		boolean showed = isShowed();
		if(showed)
			hide();
		playerTextdraw.destroy();
		playerTextdraw = net.gtaun.shoebill.object.PlayerTextdraw.create(getPlayer(), vector2d);
		setPosition(vector2d);
		synchronize();
		if(showed) {
			show();
		}
	}

	public void move(float x, float y) {
		move(new Vector2D(x, y));
	}

	public void changePlayer(Player player) {
		this.player = player;
		boolean showed = isShowed();
		if(showed)
			hide();
		playerTextdraw.destroy();
		playerTextdraw = net.gtaun.shoebill.object.PlayerTextdraw.create(player, getPosition());
		synchronize();
		if(showed) {
			show();
		}
	}

	public void synchronize() { //TODO also sync position?
		if(!isDestroyed()) {
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

	public void hide() {
		playerTextdraw.hide();
	}

	public void show() {
		playerTextdraw.show();
	}

	public int getId() {
		return playerTextdraw.getId();
	}

	public TextdrawBase getPrimitive() {
		return playerTextdraw.getPrimitive();
	}

	public boolean isPlayerTextdraw(net.gtaun.shoebill.object.PlayerTextdraw playerTextdraw) {
		return this.playerTextdraw == playerTextdraw;
	}

	@Override
	public void destroy() {
		playerTextdraw.destroy();
	}

	@Override
	public boolean isDestroyed() {
		return playerTextdraw.isDestroyed();
	}

	public void recreate() {
		if(isDestroyed()) {
			playerTextdraw = net.gtaun.shoebill.object.PlayerTextdraw.create(player, getPosition());
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
		PlayersTextdraw playersTextdraw = PlayersTextdraw.create(tmpPlayers, playerTextdraw.getPosition());
		playersTextdraw.copy(this);
		if(isShowed()) {
			playersTextdraw.show();
			hide();
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
}
