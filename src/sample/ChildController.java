package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

abstract public class ChildController
{
	private Controller parentController;
	private double xCoord, yCoord;
	AnchorPane basePane;
	
	public ChildController(Controller parentController, double xCoord, double yCoord)
	{
		this.parentController = parentController;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		setupFXML();
		basePane.setLayoutX(xCoord);
		basePane.setLayoutY(yCoord);
		this.parentController.MotherPane.getChildren().add(this.basePane);
	}
	
	void setParentController(Controller parentController) {this.parentController = parentController;}
	
	abstract void setupFXML();
}

