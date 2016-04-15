package application;
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import presentation.control.MainUIController;


public class SantiagoApp extends Application {

	private Stage primaryStage;
	private AnchorPane rootLayout;
	private MainUIController controller;

	public static void main(String[] args) {
		System.out.println("ici");launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("SANTIAGO");
		initRootLayout();
	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			URL url =SantiagoApp.class.getResource("../presentation/view/menu.fxml");
			loader.setLocation(url);
			rootLayout = (AnchorPane) loader.load();
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			controller = loader.getController();
			controller.setPrimaryStage(primaryStage);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}