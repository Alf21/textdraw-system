Example how to create a panel with a list, a panelDialog and a MessageBox:

```java
Panel panel = TextdrawSystem.createPanel(player);
		TextdrawSystem.setPanel(player, panel);

		panel.setDialogLayout(DialogLayout.FORM);
		panel.getLeftButton().setText(localizedStringSet.get(player, "Dialogs.Back"));
		panel.getRightButton().setText(localizedStringSet.get(player, "Dialogs.Next"));

//example panelDialog
PanelDialog panelDialog = TextdrawSystem.createPanelDialog(player);

		List list = panelDialog.addList("optionList");
		//create group
		list.addListItem("createGroup")
				.setClickHandler(handler -> createGroupDialog());

			//delete group
			list.addListItem("deleteGroup")
					.setClickHandler(handler -> deleteGroupDialog());

			//edit group
			list.addListItem("EditGroup")
					.setClickHandler(handler -> editGroupDialog());
		//edit player
		list.addListItem("EditPlayer")
				.setClickHandler(handler -> editPlayerOptionDialog());

		panelDialog.toggleOkButton(false);
		panelDialog.setCaption("Caption");

		panelDialog.show();

//MsgBox
MsgBox msgBox = new MsgBox(player, "Caption", "Message", groupName));
			msgBox.setCancelButton("No");
			msgBox.setOkButton("Yes");
			msgBox.setClickOkHandler(msgBoxHandler -> createGroupDialog());
			msgBox.setClickCancelHandler(msgBoxHandler -> createOptions());
			msgBox.show(); //also possible to use the panel with content !
```