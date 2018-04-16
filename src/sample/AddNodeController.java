package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AddNodeController extends ChildController
{
	AnchorPane basepane;
	
	public AddNodeController(Controller parentController, double xCoord, double yCoord)
	{
		super(parentController, xCoord, yCoord);
	}
	
	void setupFXML()
	{
		try
		{
			AnchorPane newPane = FXMLLoader.load(getClass().getResource("addNodeMenu.fxml"));
			this.basePane = newPane;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public AnchorPane getBasepane()
	{
		return basepane;
	}
	
	public void addNodeClicked(MouseEvent mouseEvent)
	{
	
	}
}
