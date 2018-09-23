package earth.guam.santool.view;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.greenrobot.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Guillaume
 * 18/09/2018 - 23:57
 */
@Route
@Push
@UIScope
public class HelloView extends Div {
	
	@Autowired EventBus eventBus;
	@Autowired ConsoleView consoleView;
	
	
	@Override
	protected void onAttach(AttachEvent attachEvent) {
		Button sendButton = new Button("Send");
		TextField textField = new TextField();
		
		sendButton.addClickListener((ComponentEventListener<ClickEvent<Button>>) event -> {
			eventBus.post(textField.getValue());
		});
		
		add(textField, sendButton, consoleView);
	}
}


