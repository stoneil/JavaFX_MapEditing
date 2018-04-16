package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller
{

	//public AddNodeController addNodeController;
	private HashMap<String,ChildController> childrenControllers = new HashMap<>();
	
	
	@FXML
	public AnchorPane MotherPane;
	
	
	boolean miniMenuExists = false;
	boolean addNodeMenuExists = false;
	
	double xPressed;
	double yPressed;

	
	
	
	public void initialize(URL url, ResourceBundle rb)
	{

	}
	
	public void createCircle(double x, double y) throws IOException
	{
		
		Circle circle = new Circle();
		
		String randID = Double.toString(Math.random()).substring(0, 10);
		circle.setId(randID);
		circle.setCenterX(x);
		circle.setCenterY(y);
		circle.setRadius(5.0);
		circle.setStyle("-fx-fill: hotpink");
//        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
//	        @Override
//	        public void handle(MouseEvent event)
//	        {
//	        	if (!miniMenuExists)
//					miniMenuAppear();
//	        	else
//		        {
//			        miniMenu.setLayoutX(x);
//			        miniMenu.setLayoutY(y);
//		        }
//	        }
//        });
		
		MotherPane.getChildren().add(circle);
	}
	
	// occurs at the start of the drag (when the mouse is pressed)
	public void mouseClicked(MouseEvent mouseEvent) throws IOException
	{
		
		xPressed = mouseEvent.getSceneX();
		yPressed = mouseEvent.getSceneY();
		
		if (mouseEvent.getButton().equals(MouseButton.SECONDARY))
		{
				addNodeMenu(xPressed, yPressed);
		}
	}
	
	public void addNodeMenu(double xCoord, double yCoord) throws IOException
	{
		if (childrenControllers.containsKey("addNodeMenu"))
		{
			childrenControllers.get("addNodeMenu").basePane.setLayoutX(xCoord);
			childrenControllers.get("addNodeMenu").basePane.setLayoutY(yCoord);
		}
		else //if the addNodeMenu doesn't exist
		{
//			AnchorPane newAddNodeMenu = FXMLLoader.load(getClass().getResource("addNodeMenu.fxml"));
//			newAddNodeMenu.setLayoutX(xCoord);
//			newAddNodeMenu.setLayoutY(yCoord);
			AddNodeController addNodeController = new AddNodeController(this, xCoord, yCoord);
			
			//Adds the Anchor Pane to child from within the child controller
			childrenControllers.put(addNodeController.basePane.getId(),addNodeController);
			
			//addNodeMenuExists = true;
		}
		
	}
	/*
	public void miniMenuAppear()
	{
		
		try
		{
			AnchorPane miniMenuNew = FXMLLoader.load(getClass().getResource("miniMenu.fxml"));
			miniMenuNew.setLayoutX(xPressed);
			miniMenuNew.setLayoutY(yPressed);
			
			miniMenu = miniMenuNew;
			MotherPane.getChildren().add(miniMenu);
			
			miniMenu.setOnMousePressed(new EventHandler<MouseEvent>()
			{
				@Override
				public void handle(MouseEvent event)
				{
					System.out.println("got here");
				}
			});
			
			miniMenuExists = true;
		}
		catch (IOException e)
		{
			System.out.println("IO Exception, man");
		}
	}
	*/
	void closeAll()
	{
		MotherPane.getChildren().clear();
	}
	
	public void editAttributes(MouseEvent mouseEvent)
	{
		System.out.println("edit Attributes clicked");
	}
	
	
	public void addEdge(MouseEvent mouseEvent)
	{
		System.out.println("add edge clicked");
	}
	
	public void deleteNode(MouseEvent mouseEvent)
	{
		System.out.println("deleteNode clicked");
	}
	
	
	
}
