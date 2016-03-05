package me.alf21.textdrawsystem.content;

import me.alf21.textdrawsystem.dialogs.Dialog;
import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.content.components.ComponentData;
import me.alf21.textdrawsystem.content.pages.Page;
import me.alf21.textdrawsystem.dialogs.styles.Process;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.object.Destroyable;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Alf21 on 26.02.2016.
 */
public class Content implements Destroyable {

	private Dialog dialog;
	private PlayerTextdraw contentText, contentBackground;
	private ArrayList<Page> pages;
	private ArrayList<Component> components;
	private Page currentPage;

	public Content(Dialog dialog, PlayerTextdraw contentBackground, PlayerTextdraw contentText) {
		this.dialog = dialog;
		this.contentBackground = contentBackground;
		this.contentText = contentText;
		this.pages = new ArrayList<>();
		this.components = new ArrayList<>();
	}

	public void show() {
		contentBackground.show();
		contentText.show();
		if (currentPage != null) {
			if (currentPage.isDestroyed())
				currentPage.recreate();
			currentPage.show();
		}
		components.forEach(Component::show);
	}

	public void hide() {
		if (currentPage != null)
			currentPage.hide();
		components.forEach(Component::hide);
		contentText.hide();
		contentBackground.hide();
	}

	public void setText(String text) {
		if(text.isEmpty() || text.replaceAll(" ", "").isEmpty())
			text = "_";
		contentText.setText(text);
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

	@Override
	public void destroy() {
		pages.forEach(Page::destroy);
		components.forEach(Component::destroy);
		contentBackground.destroy();
		contentText.destroy();
	}

	@Override
	public boolean isDestroyed() {
		for (Page page : pages) {
			if (!page.isDestroyed())
				return false;
		}
		for (Component component : components) {
			if (!component.isDestroyed())
				return false;
		}
		return !(!contentBackground.isDestroyed() || !contentText.isDestroyed());
	}

	public Dialog getDialog() {
		return dialog;
	}

	public ArrayList<Component> getComponents() { return components; }

	public Component getComponent(net.gtaun.shoebill.object.PlayerTextdraw playerTextdraw) {
		for (Page page : pages) {
			for (Component component : page.getComponents()) {
				for (PlayerTextdraw playerTextdraws : component.getPlayerTextdraws()) {
					if (playerTextdraws.isPlayerTextdraw(playerTextdraw))
						return component;
				}
			}
		}
		for (Component component : components) {
			for (PlayerTextdraw playerTextdraws : component.getPlayerTextdraws()) {
				if (playerTextdraws.isPlayerTextdraw(playerTextdraw))
					return component;
			}
		}
		return null;
	}

	public Component getComponent(String name) {
		for (Page page : pages) {
			for (Component component : page.getComponents()) {
				if (component.getName().equals(name))
					return component;
			}
		}
		for (Component component : components) {
			if(component.getName().equals(name))
				return component;
		}
		return null;
	}

	public boolean hasMarkedComponents() {
		for (Page page : pages) {
			for (Component component : page.getComponents()) {
				if (component.isRequired())
					if(!component.isFilled())
						return true;
			}
		}
		for (Component component : components) {
			if (component.isRequired())
				if(!component.isFilled())
					return true;
		}
		return false;
	}

	public ArrayList<Component> getMarkedComponents() {
		ArrayList<Component> markedComponents = new ArrayList<>();
		for (Page page : pages) {
			page.getComponents().stream().filter(component -> component.isRequired() && !component.isFilled()).forEach(markedComponents::add);
		}
		markedComponents.addAll(components.stream().filter(component -> component.isRequired() && !component.isFilled()).collect(Collectors.toList()));
		return markedComponents;
	}

	public PlayerTextdraw getContentBackground() {
		return contentBackground;
	}

	public Page getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Page currentPage) {
		if(!pages.contains(currentPage))
			pages.add(currentPage);
		this.currentPage = currentPage;
		Process process = dialog.getProcess();
		process.setMaxProcess((double) getPages().size());
		process.setProcess(getPageValue(currentPage)+1);
		process.process();
		dialog.getTitle().setText("Page (" + (int) process.getProcess() + " / " + (int) process.getMaxProcess() + ")");

		pages.forEach(page -> {
			if(page == currentPage && page.isDestroyed())
				page.recreate();
			else if(!page.isDestroyed()) {
				page.getPageInterface().onLeave(this, page);
				if(!page.isDestroyed()) {
					page.hide();
					page.destroy();
				}
			}
		});
		currentPage.getPageInterface().onReach(this, currentPage);

		currentPage.show();

	}

	public void setCurrentPage(int index) {
		Page currentPage = getPages().get(index);
		setCurrentPage(currentPage);
	}

	public void nextPage() {
		currentPage.getPageInterface().onLeave(this, currentPage);
		if(!currentPage.isDestroyed()) {
			currentPage.hide();
			currentPage.destroy();
		}
		int i = 1;
		for(Page page : getPages()) {
			if(page == getCurrentPage())
				break;
			i++;
		}
		setCurrentPage(getPages().get(i));
	}

	public void previousPage() {
		currentPage.getPageInterface().onLeave(this, currentPage);
		if(!currentPage.isDestroyed()) {
			currentPage.hide();
			currentPage.destroy();
		}
		int i = -1;
		for(Page page : getPages()) {
			if(page == getCurrentPage())
				break;
			i++;
		}
		setCurrentPage(getPages().get(i));
	}

	public int getPageValue(Page page) {
		int i = 0;
		for(Page pages : getPages()) {
			if(page == pages)
				return i;
			i++;
		}
		return 0;
	}

	private ArrayList<Page> getPages() {
		return pages;
	}

	public void addPage(Page page) {
		getPages().add(page);
	}

	public void removePage(Page page) {
		//TODO uninit + destroy + update the content + panel
		getPages().remove(page);
	}

	/**
	 * get the data of the first component with given name
	 * @param name the name of the component
	 * @return the data of the component
	 */
	public ComponentData getComponentData(String name) {
		for(Page page : pages) {
			for(Component component : page.getComponents()) {
				if(component.getName().equals(name))
					return component.getComponentData();
			}
		}
		for(Component component : components) {
			if(component.getName().equals(name))
				return component.getComponentData();
		}
		return null;
	}
}
