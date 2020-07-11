package Program;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	} 
	@Override
	public void start(Stage primaryStage) throws Exception {
		ObjectInputStream readData= new ObjectInputStream(new FileInputStream("electionRound.data"));
		ElectionRound theModel = (ElectionRound)readData.readObject();
		View theView = new View(primaryStage);
		ControllerElection<? extends Citizen> controller= new ControllerElection<Citizen>(theModel, theView);
	}

}
