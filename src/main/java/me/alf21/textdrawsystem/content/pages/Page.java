package me.alf21.textdrawsystem.content.pages;

import me.alf21.textdrawsystem.content.Content;
import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.dialogs.Dialog;
import me.alf21.textdrawsystem.dialogs.styles.Process;
import net.gtaun.shoebill.object.Destroyable;

import java.util.ArrayList;

/**
 * Created by Alf21 on 29.02.2016 in the project 'textdraw-system'.
 */
public class Page implements Destroyable {

	ArrayList<Component> components;
	PageInterface pageInterface;
	Content content;

	private Page(PageInterface pageInterface) {
		components = new ArrayList<>();
		this.pageInterface = pageInterface;
	}

	public static Page create(PageInterface pageInterface) {
		return new Page(pageInterface);
	}

	public static Page create() {
		return create(new PageInterface() {});
	}

	public ArrayList<Component> getComponents() {
		return components;
	}

	public void show() {
		if (content != null) {
			Dialog dialog = content.getDialog();
			Process process = dialog.getProcess();
			if (!process.isDisabled()) {
				process.setMaxProcess((double) content.getPagesAmount());
				process.setProcess(content.getPageValue(this) + 1);
				process.process();
				dialog.getTitle().setText("Page (" + (int) process.getProcess() + " / " + (int) process.getMaxProcess() + ")");
			}

			this.getComponents().forEach(Component::show);
		}
	}

	public void hide() {
		this.getComponents().forEach(Component::hide);
	}

	public void recreate() {
		this.getComponents().forEach(Component::recreate);
	}

	public PageInterface getPageInterface() {
		return pageInterface;
	}

	public void clear() {
		hide();
		destroy();
		this.getComponents().clear();
	}

	@Override
	public void destroy() {
		this.getComponents().forEach(Component::destroy);
	}

	@Override
	public boolean isDestroyed() {
		for (Component component : this.getComponents()) {
			if(!component.isDestroyed())
				return false;
		}
		return true;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public Content getContent() {
		return content;
	}
}
