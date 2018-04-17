package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

abstract public class ChildController
{
	public Controller parentController;
	public double xCoord, yCoord;
	
	public ChildController()
	{
//		setupFXML();
//		System.out.println("basepane : " + this.basePane);
//		setXY(xCoord,yCoord);
//		this.parentController.MotherPane.getChildren().add(this.basePane);
	}
	
//	abstract public void setXY(double x, double y);
	
	void setParentController(Controller parentController) {this.parentController = parentController;}
	
//	abstract void setupFXML();
}

