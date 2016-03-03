package me.alf21.textdrawsystem.dialogs.interfaces;

import me.alf21.textdrawsystem.content.Content;
import me.alf21.textdrawsystem.content.pages.Page;

/**
 * Created by Alf21 on 02.03.2016.
 */
public abstract class PageInterface {

	private Content content;
	private Page page;

	public PageInterface() { }

	public void onReach() {

	}

	public void onLeave() {

	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
