package presentation.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import Classes.Joueur;

public class MyListener implements ActionListener {

	Label lphase;
	int phase;
	//PHASE ET ETAPE
	public MyListener(Label lpahse, int phase){
		this.lphase=lphase;
		this.phase=phase;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.lphase.setText(String.valueOf(phase));
	}
}
