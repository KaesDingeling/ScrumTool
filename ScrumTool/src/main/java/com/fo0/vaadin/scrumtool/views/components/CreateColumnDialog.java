package com.fo0.vaadin.scrumtool.views.components;

import com.fo0.vaadin.scrumtool.broadcast.BroadcasterBoard;
import com.fo0.vaadin.scrumtool.session.SessionUtils;
import com.fo0.vaadin.scrumtool.utils.Utils;
import com.fo0.vaadin.scrumtool.views.KanbanView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class CreateColumnDialog extends Dialog {

	private static final long serialVersionUID = 3959841920378174696L;

	public CreateColumnDialog(KanbanView view) {
		TextField t = new TextField("Name");
		t.focus();
		Button b = new Button("Create");
		b.addClickShortcut(Key.ENTER);
		b.addClickListener(e -> {
			view.addColumn(Utils.randomId(), SessionUtils.getSessionId(), t.getValue());
			BroadcasterBoard.broadcast(view.getId().get(), "update");
//			view.reload();
			close();
		});

		HorizontalLayout l = new HorizontalLayout(t, b);
		l.setMargin(true);
		add(l);
	}

}
