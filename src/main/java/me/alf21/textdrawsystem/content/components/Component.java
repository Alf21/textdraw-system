package me.alf21.textdrawsystem.content.components;

import me.alf21.textdrawsystem.content.Content;
import me.alf21.textdrawsystem.content.attachments.Attachment;
import me.alf21.textdrawsystem.content.attachments.Box;
import me.alf21.textdrawsystem.content.attachments.Label;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.object.Destroyable;
import net.gtaun.shoebill.object.Player;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Alf21 on 27.02.2016 in the project 'textdraw-system'.
 */
public abstract class Component implements Destroyable {

	private boolean required, marked;
	private ComponentData componentData = new ComponentData(false);
	private String name;
	private ComponentAlignment componentAlignment;
	private ArrayList<Attachment> attachments;
	private Player player;
	private Content content;

	protected Component(Content content, ComponentAlignment componentAlignment, String name) {
		this.content = content;
		this.componentAlignment = componentAlignment;
		this.name = name;
		player = content.getDialog().getPlayer();
		attachments = new ArrayList<>();
	}

	public void show() {
		attachments.stream().forEach(Attachment::show);
	}

	public void hide() {
		attachments.stream().forEach(Attachment::hide);
	}

	public boolean isFilled() { return true; }

	public ArrayList<PlayerTextdraw> getAllPlayerTextdraws() {
		ArrayList<PlayerTextdraw> playerTextdraws = new ArrayList<>();
		attachments.stream().forEach(attachment -> playerTextdraws.addAll(attachment.getPlayerTextdraws().stream().collect(Collectors.toList())));
		return playerTextdraws;
	}

	public ArrayList<PlayerTextdraw> getComponentTextdraws() {
		return new ArrayList<>();
	}

	public void recreate() {
		attachments.stream().filter(Attachment::isDestroyed).forEach(Attachment::recreate);
	}

	public void onClick(net.gtaun.shoebill.object.PlayerTextdraw clickedPlayerTextdraw) { }

	public boolean isRequired() {
		return required;
	}

	public void toggleRequired(boolean required) {
		this.required = required;
	}

	public void mark() {
		marked = true;
	}

	public boolean isMarked() {
		return marked;
	}

	public void unmark() {
		marked = false;
	}

	@Override
	public void destroy() {
		attachments.stream().filter(attachment -> !attachment.isDestroyed()).forEach(Attachment::destroy);
	}

	@Override
	public boolean isDestroyed() {
		for (Attachment attachment : attachments) {
			if (!attachment.isDestroyed())
				return false;
		}
		return true;
	}

	public ComponentData getComponentData() {
		return componentData;
	}

	public void setComponentData(ComponentData componentData) {
		this.componentData = componentData;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ComponentAlignment getComponentAlignment() {
		return componentAlignment;
	}
/*
	public void setComponentAlignment(ComponentAlignment componentAlignment) {
		this.componentAlignment = componentAlignment;
		//TODO update position, if not there will issues occurs when using setPosition the next time
		setHeight(getHeight());
		setWidth(getWidth());
	}

	public void setHeight(float height) {
		boolean showed = false;
		if(playerTextdraw.isShowed()) {
			showed = true;
			playerTextdraw.hide();
		}
		switch (getComponentAlignment()) {
			case TOP_LEFT:
			case TOP_CENTER:
			case TOP_RIGHT:
				playerTextdraw.setLetterSize(playerTextdraw.getLetterSize().getX(), height / 8.225f);
				break;
			case CENTER_LEFT:
			case CENTER:
			case CENTER_RIGHT:
				height /= 16.45f;
				playerTextdraw.move(playerTextdraw.getPosition().getX(), playerTextdraw.getPosition().getY() - height);
				playerTextdraw.setLetterSize(playerTextdraw.getLetterSize().getX(), height);
				break;
			case BOTTOM_LEFT:
			case BOTTOM_CENTER:
			case BOTTOM_RIGHT:
				playerTextdraw.move(playerTextdraw.getPosition().getX(), playerTextdraw.getPosition().getY() - height);
				playerTextdraw.setLetterSize(playerTextdraw.getLetterSize().getX(), height / 8.225f);
				break;
		}
		if(showed) {
			playerTextdraw.show();
		}
	}

	public void setWidth(float width) {
		boolean showed = false;
		if(playerTextdraw.isShowed()) {
			showed = true;
			playerTextdraw.hide();
		}
		switch (getComponentAlignment()) {
			case TOP_LEFT:
			case CENTER_LEFT:
			case BOTTOM_LEFT:
				playerTextdraw.setTextSize(playerTextdraw.getPosition().getX() + width, playerTextdraw.getTextSize().getY());
				break;
			case TOP_CENTER:
			case CENTER:
			case BOTTOM_CENTER:
				playerTextdraw.setTextSize(playerTextdraw.getTextSize().getX(), width);
				break;
			case TOP_RIGHT:
			case CENTER_RIGHT:
			case BOTTOM_RIGHT:
				playerTextdraw.setTextSize(playerTextdraw.getPosition().getX() - width, playerTextdraw.getTextSize().getY());
				break;
		}
		if(showed) {
			playerTextdraw.show();
		}
	}

	public void setPosition(float x, float y) {
		boolean showed = false;
		if(playerTextdraw.isShowed()) {
			showed = true;
			playerTextdraw.hide();
		}
		float width = Calculation.getWidth(playerTextdraw), height = Calculation.getHeight(playerTextdraw);
		switch (getComponentAlignment()) {
			case TOP_LEFT:
				playerTextdraw.move(x, y);
				break;
			case TOP_CENTER:
				playerTextdraw.move(x - width / 2f, y);
				break;
			case TOP_RIGHT:
				playerTextdraw.move(x - width, y);
				break;
			case CENTER_LEFT:
				playerTextdraw.move(x, y - height / 2f);
				break;
			case CENTER:
				playerTextdraw.move(x - width / 2f, y - height / 2f);
				break;
			case CENTER_RIGHT:
				playerTextdraw.move(x - width, y - height / 2f);
				break;
			case BOTTOM_LEFT:
				playerTextdraw.move(x, y - height);
				break;
			case BOTTOM_CENTER:
				playerTextdraw.move(x - width / 2f, y - height);
				break;
			case BOTTOM_RIGHT:
				playerTextdraw.move(x - width, y - height);
				break;
		}
		if(showed) {
			playerTextdraw.show();
		}
	}

	public void setPosition(Vector2D vector2D) {
		setPosition(vector2D.getX(), vector2D.getY());
	}
*/
//	/**
//	 * @return the position of the component with ComponentAlignment calculation
//	 */
/*
	public Vector2D getPosition() {
		Vector2D position = getComponentPosition();
		switch (getComponentAlignment()) {
			case TOP_LEFT:
				position = new Vector2D(position.getX(), position.getY());
				break;
			case TOP_CENTER:
				position = new Vector2D(position.getX() - width / 2f, position.getY());
				break;
			case TOP_RIGHT:
				position = new Vector2D(position.getX() - width, position.getY());
				break;
			case CENTER_LEFT:
				position = new Vector2D(position.getX(), position.getY() - height / 2f);
				break;
			case CENTER:
				position = new Vector2D(position.getX() - width / 2f, position.getY() - height / 2f);
				break;
			case CENTER_RIGHT:
				position = new Vector2D(position.getX() - width, position.getY() - height / 2f);
				break;
			case BOTTOM_LEFT:
				position = new Vector2D(position.getX(), position.getY() - height);
				break;
			case BOTTOM_CENTER:
				position = new Vector2D(position.getX() - width / 2f, position.getY() - height);
				break;
			case BOTTOM_RIGHT:
				position = new Vector2D(position.getX() - width, position.getY() - height);
				break;
		}
		return position;
	}
*/
	/**
	 * @return only the main position of the component
	 */
	public Vector2D getComponentPosition() {
		return new Vector2D();
	}

	public Player getPlayer() {
		return player;
	}

/******************************************************/
/**                    Component                     **/
/******************************************************/

	public Label attachLabel(String text) {
		Label label = Label.create(content, text, getName() + "_label");
		attach(label);
		return label;
	}

	public Box attachBox(Color color) {
		Box box = Box.create(content, color, getName() + "_box");
		attach(box);
		return box;
	}

	public void attach(Attachment attachment) {
		Attachment oldAttachment = getAttachment(attachment.getName());
		if (oldAttachment != null) {
			detach(oldAttachment);
			oldAttachment.hide();
			oldAttachment.destroy();
		}
		attachment.attach(this);
	}

	public Attachment detach(Attachment attachment) {
		if (attachment != null) {
			if (attachment.getComponent() != null)
				attachment.detach();
		}
		return attachment;
	}

	public void detachAll() {
		attachments.forEach(this::detach);
		attachments.clear();
	}

	public ArrayList<Attachment> getAttachments() {
		return attachments;
	}

	public Attachment getAttachment(String name) {
		for (Attachment attachment : attachments) {
			if (attachment.getName().equals(name))
				return attachment;
		}
		return null;
	}

	public Content getContent() {
		return content;
	}
}
