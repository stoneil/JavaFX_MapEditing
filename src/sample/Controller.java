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

public class Controller {

    @FXML
    AnchorPane MotherPane;

    AnchorPane miniMenu;

    boolean miniMenuExists = false;
    double xPressed;
    double yPressed;
    double startPathPaneX;
    double startPathPaneY;


    public void createCircle(double x, double y) {

        System.out.println("mouse pressed");


        Circle circle = new Circle();

        String randID = Double.toString(Math.random()).substring(0, 10);
        circle.setId(randID);
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(5.0);
        circle.setStyle("-fx-fill: hotpink");

        MotherPane.getChildren().add(circle);
    }

    // occurs at the start of the drag (when the mouse is pressed)
    public void miniMenuAppear(MouseEvent mouseEvent) throws IOException {

        xPressed = mouseEvent.getSceneX();
        yPressed = mouseEvent.getSceneY();
        startPathPaneX = MotherPane.getLayoutX();
        startPathPaneY = MotherPane.getLayoutY();
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getSource().equals(MotherPane) && !mouseEvent.getSource().equals(miniMenu)) {
                closeAll();
            }
            //else if (mouseEvent.getSource().equals(A CIRCLE THAT EXISTS IN THE MAP)) , DO SOMETHING ELSE
        }
        else if (mouseEvent.getButton().equals(MouseButton.SECONDARY) && !mouseEvent.getSource().equals(miniMenu)) {
            if (!miniMenuExists) {
                miniMenuAppear();
            } else {
                miniMenu.setLayoutX(xPressed);
                miniMenu.setLayoutY(yPressed);
            }
        }
    }

    public void miniMenuAppear() throws IOException {
        AnchorPane miniMenuNew = FXMLLoader.load(getClass().getResource("miniMenu.fxml"));
        miniMenuNew.setLayoutX(xPressed);
        miniMenuNew.setLayoutY(yPressed);


        MotherPane.getChildren().add(miniMenuNew);
        miniMenu = miniMenuNew;
        miniMenu.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("got here");
            }
        });

        miniMenuExists = true;
    }

    void closeAll() {
        MotherPane.getChildren().remove(miniMenu);
        miniMenuExists = false;
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

    // occurs everytime mouse is moved while clicked (dragging)
    public void mapDragged(MouseEvent mouseEvent) {
        double currX = mouseEvent.getSceneX();
        double currY = mouseEvent.getSceneY();

        double vectorX = (currX - xPressed);
        double vectorY = (currY - yPressed);

        MotherPane.setLayoutX(startPathPaneX + vectorX);
        MotherPane.setLayoutY(startPathPaneY + vectorY);

        System.out.println("dragging: x: " + vectorX + ", y: " + vectorY);
    }
}
