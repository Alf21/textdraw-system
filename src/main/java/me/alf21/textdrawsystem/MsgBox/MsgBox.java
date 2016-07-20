package me.alf21.textdrawsystem.msgBox;

import me.alf21.textdrawsystem.TextdrawSystem;
import me.alf21.textdrawsystem.player.PlayerData;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.constant.TextDrawAlign;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.object.Destroyable;
import net.gtaun.shoebill.object.Player;

/**
 * Created by Alf21 on 04.07.2016 in the project 'textdraw-system'.
 */
public class MsgBox implements Destroyable { // TODO if only cancelButton is enabled: center it

	private PlayerTextdraw progressBar;
	private PlayerTextdraw cancelButtonBackground;
	private PlayerTextdraw okButtonBackground;
	private PlayerTextdraw contentBackground;
	private PlayerTextdraw captionText;
	private PlayerTextdraw contentMessage;
	private PlayerTextdraw cancelButtonText;
	private PlayerTextdraw okButtonText;

	private Player player;

	private boolean showed, hiddenPanel;

	private MsgBoxHandler clickCancelHandler;
	private MsgBoxHandler clickOkHandler;

	public MsgBox(Player player) {
		init(player);
	}

	public MsgBox(Player player, String content) {
		init(player);
		setText(content);
	}

	public MsgBox(Player player, String caption, String content) {
		init(player);
		setCaption(caption);
		setText(content);
	}

	public void init(Player player) {
		this.player = player;

		progressBar = PlayerTextdraw.create(player, 320.000000f, 150.000000f, "_");
		progressBar.setAlignment(TextDrawAlign.get(2));
		progressBar.setBackgroundColor(new Color(0, 0, 0, 255));
		progressBar.setFont(TextDrawFont.get(1));
		progressBar.setLetterSize(new Vector2D(0.500000f, 19.000000f));
		progressBar.setColor(new Color(255, 255, 255, 255));
		progressBar.setOutlineSize(0);
		progressBar.setProportional(true);
		progressBar.setShadowSize(1);
		progressBar.setUseBox(true);
		progressBar.setBoxColor(new Color(0, 0, 0, 100));
		progressBar.setTextSize(new Vector2D(0.000000f, 330.000000f));
		progressBar.setSelectable(false);

		captionText = PlayerTextdraw.create(player, 320.000000f, 135.000000f, "_");
		captionText.setAlignment(TextDrawAlign.get(2));
		captionText.setBackgroundColor(new Color(0, 0, 0, 255));
		captionText.setFont(TextDrawFont.get(1));
		captionText.setLetterSize(new Vector2D(0.500000f, 1.000000f));
		captionText.setColor(new Color(255, 255, 255, 255));
		captionText.setOutlineSize(1);
		captionText.setProportional(true);
		captionText.setUseBox(true);
		captionText.setBoxColor(new Color(0, 0, 0, 255));
		captionText.setTextSize(new Vector2D(0.000000f, 330.000000f));
		captionText.setSelectable(false);

		contentBackground = PlayerTextdraw.create(player, 320.000000f, 148.000000f, "_");
		contentBackground.setAlignment(TextDrawAlign.get(2));
		contentBackground.setBackgroundColor(new Color(0, 0, 0, 255));
		contentBackground.setFont(TextDrawFont.get(1));
		contentBackground.setLetterSize(new Vector2D(0.500000f, -0.300000f));
		contentBackground.setColor(new Color(255, 255, 255, 255));
		contentBackground.setOutlineSize(0);
		contentBackground.setProportional(true);
		contentBackground.setShadowSize(1);
		contentBackground.setUseBox(true);
		contentBackground.setBoxColor(new Color(0, 255, 0, 255));
		contentBackground.setTextSize(new Vector2D(0.000000f, 330.000000f));
		contentBackground.setSelectable(false);

		contentMessage = PlayerTextdraw.create(player, 160.000000f, 151.000000f, "_");
		contentMessage.setBackgroundColor(new Color(0, 0, 0, 255));
		contentMessage.setFont(TextDrawFont.get(1));
		contentMessage.setLetterSize(new Vector2D(0.500000f, 1.000000f));
		contentMessage.setColor(new Color(255, 255, 255, 255));
		contentMessage.setOutlineSize(0);
		contentMessage.setProportional(true);
		contentMessage.setShadowSize(0);
		contentMessage.setUseBox(true);
		contentMessage.setBoxColor(new Color(0, 0, 0, 255));
		contentMessage.setTextSize(new Vector2D(480.000000f, 10.000000f));
		contentMessage.setSelectable(false);

		cancelButtonBackground = PlayerTextdraw.create(player, 211.000000f, 306.000000f, "_");
		cancelButtonBackground.setAlignment(TextDrawAlign.get(2));
		cancelButtonBackground.setBackgroundColor(new Color(0, 0, 0, 255));
		cancelButtonBackground.setFont(TextDrawFont.get(1));
		cancelButtonBackground.setLetterSize(new Vector2D(0.500000f, 1.300000f));
		cancelButtonBackground.setColor(new Color(255, 255, 255, 255));
		cancelButtonBackground.setOutlineSize(0);
		cancelButtonBackground.setProportional(true);
		cancelButtonBackground.setShadowSize(0);
		cancelButtonBackground.setUseBox(true);
		cancelButtonBackground.setBoxColor(new Color(0, 255, 0, 255));
		cancelButtonBackground.setTextSize(new Vector2D(0.000000f, 107.000000f));
		cancelButtonBackground.setSelectable(false);

		cancelButtonText = PlayerTextdraw.create(player, 211.000000f, 307.000000f, "_");
		cancelButtonText.setAlignment(TextDrawAlign.get(2));
		cancelButtonText.setBackgroundColor(new Color(0, 0, 0, 255));
		cancelButtonText.setFont(TextDrawFont.get(1));
		cancelButtonText.setLetterSize(new Vector2D(0.500000f, 1.000000f));
		cancelButtonText.setColor(new Color(255, 255, 255, 255));
		cancelButtonText.setOutlineSize(0);
		cancelButtonText.setProportional(true);
		cancelButtonText.setShadowSize(1);
		cancelButtonText.setUseBox(true);
		cancelButtonText.setBoxColor(new Color(0, 0, 0, 255));
		cancelButtonText.setTextSize(new Vector2D(10.000000f, 104.000000f));
		cancelButtonText.setSelectable(true);

		okButtonBackground = PlayerTextdraw.create(player, 429.000000f, 306.000000f, "_");
		okButtonBackground.setAlignment(TextDrawAlign.get(2));
		okButtonBackground.setBackgroundColor(new Color(0, 0, 0, 255));
		okButtonBackground.setFont(TextDrawFont.get(1));
		okButtonBackground.setLetterSize(new Vector2D(0.500000f, 1.300000f));
		okButtonBackground.setColor(new Color(255, 255, 255, 255));
		okButtonBackground.setOutlineSize(0);
		okButtonBackground.setProportional(true);
		okButtonBackground.setShadowSize(0);
		okButtonBackground.setUseBox(true);
		okButtonBackground.setBoxColor(new Color(0, 255, 0, 255));
		okButtonBackground.setTextSize(new Vector2D(0.000000f, 107.000000f));
		okButtonBackground.setSelectable(false);

		okButtonText = PlayerTextdraw.create(player, 429.000000f, 307.000000f, "_");
		okButtonText.setAlignment(TextDrawAlign.get(2));
		okButtonText.setBackgroundColor(new Color(0, 0, 0, 255));
		okButtonText.setFont(TextDrawFont.get(1));
		okButtonText.setLetterSize(new Vector2D(0.500000f, 1.000000f));
		okButtonText.setColor(new Color(255, 255, 255, 255));
		okButtonText.setOutlineSize(0);
		okButtonText.setProportional(true);
		okButtonText.setShadowSize(1);
		okButtonText.setUseBox(true);
		okButtonText.setBoxColor(new Color(0, 0, 0, 255));
		okButtonText.setTextSize(new Vector2D(10.000000f, 104.000000f));
		okButtonText.setSelectable(true);

		PlayerData playerData = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);
		playerData.getMsgBoxes().add(this);
	}

	public void show() {
		PlayerData playerData = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);
		hiddenPanel = false;
		if (playerData.getPanel() != null && !playerData.getPanel().isDestroyed() && playerData.getPanel().isShowed()) {
			playerData.getPanel().hide();
			playerData.getPanel().destroy();
			hiddenPanel = true;
		}

		showed = true;

		if (isDestroyed())
			recreate();

		progressBar.show();
		contentBackground.show();
		cancelButtonBackground.show();
		okButtonBackground.show();
		captionText.show();
		contentMessage.show();
		cancelButtonText.show();
		okButtonText.show();

		playerData.toggleMouse(true);
	}

	public void hide() {
		if (isShowed()) {
			PlayerData playerData = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);

			playerData.toggleMouse(false);

			showed = false;

			progressBar.hide();
			captionText.hide();
			contentBackground.hide();
			contentMessage.hide();
			cancelButtonText.hide();
			cancelButtonBackground.hide();
			okButtonBackground.hide();
			okButtonText.hide();

			if (hiddenPanel && playerData.getPanel() != null) {
				hiddenPanel = false;
			//	if (playerData.getPanel().isDestroyed())    //TODO
			//		playerData.getPanel().recreate();       //TODO this is BUGGY or remove it completely from classes !
				playerData.getPanel().show();
			}
		}
	}

	public void recreate() {
		progressBar.recreate();
		contentBackground.recreate();
		cancelButtonBackground.recreate();
		okButtonBackground.recreate();
		captionText.recreate();
		contentMessage.recreate();
		cancelButtonText.recreate();
		okButtonText.recreate();
	}

	@Override
	public void destroy() {
		if (isShowed())
			hide();
		progressBar.destroy();
		captionText.destroy();
		contentBackground.destroy();
		contentMessage.destroy();
		cancelButtonText.destroy();
		cancelButtonBackground.destroy();
		okButtonBackground.destroy();
		okButtonText.destroy();

		PlayerData playerData = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);
		if (playerData.getMsgBoxes().contains(this))
			playerData.getMsgBoxes().remove(this);
	}

	@Override
	public boolean isDestroyed() {
		return progressBar.isDestroyed() && captionText.isDestroyed()
				&& contentBackground.isDestroyed() && contentMessage.isDestroyed()
				&& cancelButtonBackground.isDestroyed() && cancelButtonText.isDestroyed()
				&& okButtonBackground.isDestroyed() && okButtonText.isDestroyed();
	}

	public boolean isShowed() {
		return showed;
	}

	public Player getPlayer() {
		return player;
	}

	public PlayerTextdraw getCancelButtonText() {
		return cancelButtonText;
	}

	public PlayerTextdraw getOkButtonText() {
		return okButtonText;
	}

	public MsgBoxHandler getClickCancelHandler() {
		return clickCancelHandler;
	}

	public void setClickCancelHandler(MsgBoxHandler clickCancelHandler) {
		this.clickCancelHandler = clickCancelHandler;
	}

	public MsgBoxHandler getClickOkHandler() {
		return clickOkHandler;
	}

	public void setClickOkHandler(MsgBoxHandler clickOkHandler) {
		this.clickOkHandler = clickOkHandler;
	}

	public void onClickOk() {
		if (clickOkHandler != null)
			clickOkHandler.handle(this);
		onClick();
	}

	public void onClickCancel() {
		if (clickCancelHandler != null)
			clickCancelHandler.handle(this);
		onClick();
	}

	private void onClick() {
		hide();
		destroy();
	}

	public void setText(String message) {
		contentMessage.setText(message.replaceAll(" ", "").isEmpty() ? "_" : message);
	}

	public void setCaption(String caption) {
		captionText.setText(caption.replaceAll(" ", "").isEmpty() ? "_" : caption);
	}

	public void setCancelButton(String text) {
		cancelButtonText.setText(text.replaceAll(" ", "").isEmpty() ? "_" : text);
	}

	public void setOkButton(String text) {
		okButtonText.setText(text.replaceAll(" ", "").isEmpty() ? "_" : text);
	}

	//get the rest of playerTextDraw objects

	public PlayerTextdraw getCancelButtonBackground() {
		return cancelButtonBackground;
	}

	public PlayerTextdraw getCaptionBackground() {
		return progressBar;
	}

	public PlayerTextdraw getCaptionText() {
		return captionText;
	}

	public PlayerTextdraw getContentBackground() {
		return contentBackground;
	}

	public PlayerTextdraw getContentMessage() {
		return contentMessage;
	}

	public PlayerTextdraw getOkButtonBackground() {
		return okButtonBackground;
	}
}
