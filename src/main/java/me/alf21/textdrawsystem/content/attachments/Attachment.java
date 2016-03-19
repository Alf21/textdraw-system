package me.alf21.textdrawsystem.content.attachments;

import me.alf21.textdrawsystem.content.Content;
import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.object.Destroyable;
import net.gtaun.shoebill.object.Player;

import java.util.ArrayList;

/**
 * Created by Alf21 on 27.02.2016 in the project 'textdraw-system'.
 */
public abstract class Attachment implements Destroyable {

	private String name;
	private AttachmentAlignment attachmentAlignment;
	private Player player;
	private Content content;
	private Component component;

	protected Attachment(Content content, AttachmentAlignment attachmentAlignment, String name) {
		this.content = content;
		this.attachmentAlignment = attachmentAlignment;
		this.name = name;
		player = content.getDialog().getPlayer();
	}

	public void attach(Component component) {
		detach();
		this.component = component;
		component.getAttachments().add(this);
	}

	public void advancedAttach(Component component) {
		detach();
		this.component = component;
		component.getAttachments().add(this);
	}

	public void detach() {
		if(component != null) {
			Component component = this.component;
			component.getAttachments().remove(this);
			this.component = null;
		}
	}

	public void show() {

	}

	public void hide() {

	}

	public ArrayList<PlayerTextdraw> getPlayerTextdraws() {
		return new ArrayList<>();
	}

	public void recreate() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public boolean isDestroyed() {
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AttachmentAlignment getAttachmentAlignment() {
		return attachmentAlignment;
	}

	/**
	 * @return only the main position of the component
	 */
	public Vector2D getAttachmentPosition() {
		return new Vector2D();
	}

	public Player getPlayer() {
		return player;
	}

	public Content getContent() {
		return content;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}
}
