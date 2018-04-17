package sample;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class Controller {
	
	public AnchorPane miniMenu;
	@FXML
    public AnchorPane MotherPane;
    @FXML
    public AnchorPane addNodeMenu;
    

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

    @FXML
    public void initialize(){
    	try
	    {
		    windows.add(miniMenu);
		    windows.add(addNodeMenu);
		    this.createCircle(50, 50);
		    
		    MotherPane.setOnMouseClicked(backgroundClick);
	    }
	    catch (IOException e)
	    {
	    	e.printStackTrace();
	    }
    }

    public void  createCircle(double x, double y) throws IOException{

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
		circle.setOnMouseClicked(circleOnMouseRightClick);

        MotherPane.getChildren().add(circle);
    }

    /*
    // occurs at the start of the drag (when the mouse is pressed)
    public void mouseClicked(MouseEvent mouseEvent) throws IOException {

        xPressed = mouseEvent.getSceneX();
        yPressed = mouseEvent.getSceneY();
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && !addNodeMenuExists) {
            if (mouseEvent.getSource().equals(MotherPane) && !mouseEvent.getSource().equals(miniMenu)) {
                closeAll();
            }
            //else if (mouseEvent.getSource().equals(A CIRCLE THAT EXISTS IN THE MAP)) , DO SOMETHING ELSE
        }
        else if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
            if (!addNodeMenuExists) {
                addNodeMenuAppear(xPressed,yPressed);
            } else {
                addNodeMenu.setLayoutX(xPressed);
                addNodeMenu.setLayoutY(yPressed);
            }
        }
    }
    */
    
    public void addNodeMenuAppear(double xCoord,double yCoord) throws IOException{
    	AnchorPane newAddNodeMenu = FXMLLoader.load(getClass().getResource("addNodeMenu.fxml"));
    	newAddNodeMenu.setLayoutX(xCoord);
    	newAddNodeMenu.setLayoutY(yCoord);
    	addNodeMenu = newAddNodeMenu;
    	MotherPane.getChildren().add(addNodeMenu);
    	
    	addNodeMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event)
		    {
		    	System.out.println("Want to add a node?");
		    }
	    });
    	
    	
    	addNodeMenuExists = true;
    }
    
    public void miniMenuAppear(double x, double y) {
     
    	try
	    {
	    	if (!miniMenuExists)
		    {
			    AnchorPane miniMenuNew = FXMLLoader.load(getClass().getResource("miniMenu.fxml"));
			    miniMenuNew.setLayoutX(xPressed);
			    miniMenuNew.setLayoutY(yPressed);
			
			    miniMenu = miniMenuNew;
			    MotherPane.getChildren().add(miniMenu);
			
			
			    miniMenuExists = true;
		    }
		    else
		    {
		    	miniMenu.setLayoutX(x);
		    	miniMenu.setLayoutY(y);
		    }
	    }
	    catch (IOException e)
	    {
	    	System.out.println("IO Exception, man");
	    }
    }

    void closeAll() {
        /*MotherPane.getChildren().remove(miniMenu);
        miniMenuExists = false;
        MotherPane.getChildren().remove(addNodeMenu);
        addNodeMenuExists = false;
        */
        MotherPane.getChildren().removeAll(windows);
    }

    public void editAttributes(MouseEvent mouseEvent) {
        System.out.println("edit Attributes clicked");
    }


    public void addEdge(MouseEvent mouseEvent) {
        System.out.println("add edge clicked");
    }

    public void deleteNode(MouseEvent mouseEvent) {
        System.out.println("deleteNode clicked");
    }
	
	public void addNodeClicked(MouseEvent mouseEvent) throws IOException
	{
		createCircle(mouseEvent.getSceneX(),mouseEvent.getSceneY());
	}
	
	//Circle Event Handlers
	
	EventHandler<MouseEvent> circleOnMousePress = new EventHandler<MouseEvent>() {
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
			orgTranslateX = ((Circle)(event.getSource())).getTranslateX();
			orgTranslateY = ((Circle)(event.getSource())).getTranslateY();
		}
	};
	
	
	EventHandler<MouseEvent> circleOnMouseDrag = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event)
		{
			double offsetX = event.getSceneX() - orgSceneX;
			double offsetY = event.getSceneY() - orgSceneY;
			double newTranslateX = orgTranslateX + offsetX;
			double newTranslateY = orgTranslateY + offsetY;
			
			((Circle)(event.getSource())).setTranslateX(newTranslateX);
			((Circle)(event.getSource())).setTranslateY(newTranslateY);
			
//			double xDragged = event.getSceneX() - xPressed;
//			double yDragged = event.getSceneY() - yPressed;
//
//			double xNewCircle = xCircleStart + xDragged;
//			double yNewCircle = yCircleStart + yDragged;
//
//			clickedCircle.setLayoutX(yCircleStart + 50);
//			clickedCircle.setTranslateY(event.getSceneY() + yPressed);

		}
	};
	
	EventHandler<MouseEvent> circleOnMouseRightClick = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event)
		{
			if (event.getButton().equals(MouseButton.SECONDARY))
				miniMenuAppear(event.getSceneX(),event.getSceneY());
		}
	};
	
	EventHandler<MouseEvent> backgroundClick = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event)
		{
			try
			{
				if (event.isPrimaryButtonDown())
					closeAll();
				else if (event.isSecondaryButtonDown())
					addNodeMenuAppear(event.getSceneX(), event.getSceneY());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	};
	
}
