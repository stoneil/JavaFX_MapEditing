package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Controller
{
	
	@FXML
	public AnchorPane miniMenu, backgroundPane, MotherPane, addNodeMenu;
	@FXML
	public Label editAttributes, addEdge, deleteNode, setAsKiosk, addNode;
	
	
	boolean miniMenuExists = false;
	boolean addNodeMenuExists = false;
	
	double xPressed;
	double yPressed;
	
	//    Circle clickedCircle;
//    double xCircleStart;
//    double yCircleStart;
	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;
	
	
	Set<AnchorPane> windows = new HashSet<AnchorPane>();
	
	double addNodeCoordX,addNodeCoordY;
	Circle currentCircle;
	
	@FXML
	public void initialize()
	{
		try
		{
//			windows.add(miniMenu);
//			windows.add(addNodeMenu);
//			this.createCircle(50, 50);
//			Circle newCircle;
//			backgroundPane.addEventFilter(MouseEvent.MOUSE_CLICKED, backgroundFilter);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void createCircle(double x, double y) throws IOException
	{
		
		Circle circle = new Circle();
		
		String randID = Double.toString(Math.random()).substring(0, 10);
		circle.setId(randID);
		circle.setCenterX(x);
		circle.setCenterY(y);
		circle.setRadius(5.0);
		circle.setStyle("-fx-fill: blue");
		
		//MouseEvent Handler calls
		circle.setOnMousePressed(circleOnMousePress);
		circle.setOnMouseDragged(circleOnMouseDrag);
		circle.setOnMouseClicked(circleOnMouseLeftClick);
		circle.setOnMouseEntered(circleOnMouseHover);
		circle.addEventFilter(MouseDragEvent.MOUSE_DRAG_RELEASED, new EventHandler<MouseDragEvent>() {
			@Override
			public void handle(MouseDragEvent event)
			{
				System.out.println("drag released");
				closeAll();
				event.consume();
			}
		});
		System.out.println("parentPane: " + backgroundPane);
		backgroundPane.getChildren().add(circle);
	}
	
	
	public void addNodeMenuAppear(MouseEvent event) throws IOException
	{
		addNodeCoordX = event.getSceneX();
		addNodeCoordY = event.getSceneY();
		//Should only run the first time
		if (backgroundPane.getChildren().contains(miniMenu))
		{
			backgroundPane.getChildren().remove(miniMenu);
//			miniMenu.toBack();
		}
		if (!backgroundPane.getChildren().contains(addNodeMenu))
			backgroundPane.getChildren().add(addNodeMenu);
		
		
		addNodeMenu.setLayoutX(addNodeCoordX - 26);
		addNodeMenu.setLayoutY(addNodeCoordY + 12);
		//addNodeMenu = newAddNodeMenu;
		//MotherPane.getChildren().add(addNodeMenu);
	}
	
	public void miniMenuAppear(MouseEvent event)
	{
		System.out.println("mini menu");
		double xCoord = ((Circle)event.getSource()).getCenterX();
		double yCoord = ((Circle)event.getSource()).getCenterY();
		try
		{
			if (backgroundPane.getChildren().contains(addNodeMenu))
			{
				backgroundPane.getChildren().remove(addNodeMenu);
				addNodeMenu.toBack();
			}
			if (!backgroundPane.getChildren().contains(miniMenu))
				backgroundPane.getChildren().add(miniMenu);
			
			double layoutToTriangleX = xCoord - 26;
			double layoutToTriangleY = yCoord + 12;
			
			miniMenu.setLayoutX(layoutToTriangleX);
			miniMenu.setLayoutY(layoutToTriangleY);
			miniMenu.toFront();
		}
		catch (Exception e)
		{
			System.out.println("IO Exception, man");
			e.printStackTrace();
		}
		
	}
	
	void closeAll()
	{
        /*MotherPane.getChildren().remove(miniMenu);
        miniMenuExists = false;
        MotherPane.getChildren().remove(addNodeMenu);
        addNodeMenuExists = false;
        */
		System.out.println("closing all windows");
		MotherPane.getChildren().removeAll(windows);
		backgroundPane.getChildren().remove(addNodeMenu);
		backgroundPane.getChildren().remove(miniMenu);
		
		miniMenuExists = false;
		addNodeMenuExists = false;
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
		System.out.println("deleteNode clicked: " + mouseEvent.getSource());
		backgroundPane.getChildren().remove(currentCircle);
	}
	
	public void addNodeClicked(MouseEvent mouseEvent) throws IOException
	{
		createCircle(addNodeCoordX,addNodeCoordY);
	}
	
	public void setKioskTrigger(MouseEvent mouseEvent)
	{
		System.out.println("Setting Kiosk");
	}
	//Circle Event Handlers
	
	EventHandler<MouseEvent> circleOnMousePress = new EventHandler<MouseEvent>()
	{
		@Override
		public void handle(MouseEvent event)
		{
//			xPressed = event.getSceneX();
//			yPressed = event.getSceneY();
//			clickedCircle = (Circle) event.getSource();
//			xCircleStart = clickedCircle.getTranslateX();
//			yCircleStart = clickedCircle.getCenterY();
			orgSceneX = event.getSceneX();
			orgSceneY = event.getSceneY();
			orgTranslateX = ((Circle) (event.getSource())).getTranslateX();
			orgTranslateY = ((Circle) (event.getSource())).getTranslateY();
		}
	};
	
	
	EventHandler<MouseEvent> circleOnMouseDrag = new EventHandler<MouseEvent>()
	{
		@Override
		public void handle(MouseEvent event)
		{
			double offsetX = event.getSceneX() - orgSceneX;
			double offsetY = event.getSceneY() - orgSceneY;
			double newTranslateX = orgTranslateX + offsetX;
			double newTranslateY = orgTranslateY + offsetY;
			
			((Circle) (event.getSource())).setTranslateX(newTranslateX);
			((Circle) (event.getSource())).setTranslateY(newTranslateY);
			event.consume();
		}
	};
	
	EventHandler<MouseEvent> circleOnMouseLeftClick = new EventHandler<MouseEvent>()
	{
		@Override
		public void handle(MouseEvent event)
		{
			if (event.getButton().equals(MouseButton.PRIMARY))
			{
				System.out.println("left click");
				miniMenuAppear(event);
				currentCircle = (Circle)event.getSource();
				event.consume();
			}
		}
	};
	

	
	EventHandler<MouseEvent> circleOnMouseHover = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event)
		{
			System.out.println("hovered over");
		}
	};
	
	public void backgroundClick(MouseEvent mouseEvent)
	{
		System.out.println("background click");
		try
		{
			if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && !mouseEvent.getSource().equals(miniMenu))
			{
				closeAll();
			}
			else if (mouseEvent.getButton().equals(MouseButton.SECONDARY))
				addNodeMenuAppear(mouseEvent); //TODO: make sure that this stops happening when you right click on a circle
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	

}

