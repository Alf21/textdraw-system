Example how to create an register:

```Java
panel = new Panel(player);
			if(panel.getPanelType() == PanelType.PAGE) {
				panel.setPanelLayout(PanelLayout.FORM);
				panel.getLeftButton().setText("BACK");
				panel.getRightButton().setText("NEXT");

				Input input = Input.create(panel.getContent(), panel.getLayout().getSlot(panel.getContent(), 2, 2), "email");
				input.attachLabel("Enter an E-Mail");
				input.setInputTypes(InputType.EMAIL);
				input.toggleRequired(true);

				Input input2 = Input.create(panel.getContent(), panel.getLayout().getSlot(panel.getContent(), 1, 2), "name");
				input2.attachLabel("Enter a Name");
				input2.setInputTypes(InputType.TEXT);
				input2.toggleRequired(true);

				Input input3 = Input.create(panel.getContent(), panel.getLayout().getSlot(panel.getContent(), 3, 2), "password");
				input3.attachLabel("Enter a Password");
				input3.setInputTypes(InputType.PASSWORD);
				input3.toggleRequired(true);

				Page page1 = Page.create();
				page1.getComponents().add(input);
				page1.getComponents().add(input2);
				page1.getComponents().add(input3);

				Input input4 = Input.create(panel.getContent(), panel.getLayout().getSlot(panel.getContent(), 1, 2), "description");
				input4.attachLabel("Enter a Description");
				input4.setInputTypes(InputType.TEXT);
				input4.toggleRequired(false);

				Page page2 = Page.create();
				page2.getComponents().add(input4);

				Page page3 = Page.create(new PageInterface() {
					@Override
					public void onReach() {
						Text text = Text.create(getContent(), getContent().getContentBackground().getPosition(), "text");
						String string = "Email: " + getContent().getComponentData("email").getString();
						string += "~n~Name: " + getContent().getComponentData("name").getString();
						string += "~n~Password: " + getContent().getComponentData("password").getString();
						string += "~n~Description: " + getContent().getComponentData("description").getString();
						text.setText(string);
						getPage().getComponents().add(text);
					}

					@Override
					public void onLeave() {
						getPage().clear();
					}
				});

				panel.getContent().addPage(page1);
				panel.getContent().addPage(page2);
				panel.getContent().addPage(page3);

				panel.getContent().setCurrentPage(page1);
			}
			panel.show();
```