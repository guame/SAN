package earth.guam.santool.view;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.spring.annotation.UIScope;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Guillaume
 * 18/09/2018 - 23:54
 */
@UIScope
@Component
public class ConsoleView extends Div {
	
	@Autowired EventBus eventBus;
	VerticalLayout messages = new VerticalLayout();
	UI ui;
	
	
	@Subscribe
	public void logString(String event) {
		ui.access(() -> messages.add(new Span(event)));
	}
	
	@Override
	protected void onAttach(AttachEvent attachEvent) {
		ui = attachEvent.getUI();
		
		eventBus.register(this);
		
		setSizeFull();
		setWidth("200px");
		setHeight("200px");
		getElement().getStyle().set("position", "relative");
		
		add(messages);
	}
	
	@Override
	protected void onDetach(DetachEvent detachEvent) {
		eventBus.unregister(this);
	}
	
}
