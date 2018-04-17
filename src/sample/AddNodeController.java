package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class AddNodeController extends ChildController
{
	
//	@FXML
//	public AnchorPane newAddNode;
	@FXML
	public AnchorPane addNodeMenu;
	@FXML
	public Rectangle rectangle;
	
//	public Label addNode;
	
	
	public AddNodeController()
	{
		super();
		System.out.println("anchor pane in constructor" + addNodeMenu);
	}
	
	
	
	public Pane getBasepane()
	{
		return addNodeMenu;
	}
	
	
	public void setXY(double x, double y)
	{
		this.xCoord = x;
		this.yCoord = y;
//		newAddNode.setLayoutX(x);
//		newAddNode.setLayoutY(y);
	}
	
	@FXML
	public void addNodeClicked(MouseEvent mouseEvent) throws IOException
	{
		System.out.println("add node clicked, parent" + this.parentController);
		parentController.createCircle(this.xCoord,this.yCoord);
	}
}
