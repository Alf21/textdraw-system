package me.alf21.textdrawsystem.content.pages;

import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.dialogs.interfaces.PageInterface;
import net.gtaun.shoebill.object.Destroyable;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Created by Alf21 on 29.02.2016.
 */
public class Page implements Destroyable {

	ArrayList<Component> components;
	PageInterface pageInterface;

	private Page(PageInterface pageInterface) {
		components = new ArrayList<>();
		this.pageInterface = pageInterface;
		this.pageInterface.setPage(this);
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
		getComponents().forEach(Component::show);
	}

	public void hide() {
		getComponents().forEach(Component::hide);
	}

	public void recreate() {
		getComponents().forEach(Component::recreate);
	}

	public PageInterface getPageInterface() {
		return pageInterface;
	}

	public void clear() {
		hide();
		destroy();
		getComponents().clear();
	}

	@Override
	public void destroy() {
		getComponents().forEach(Component::destroy);
	}

	@Override
	public boolean isDestroyed() {
		for (Component component : getComponents()) {
			if(!component.isDestroyed())
				return false;
		}
		return true;
	}
}
