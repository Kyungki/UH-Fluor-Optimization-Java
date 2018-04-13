package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    static boolean addModule = false;
    static boolean addConnection = false;

    static int moduleId = 1;

    private void addConnection(Pane pane) {


        //Pane childPane = new Pane();

        TextField idTextField = new TextField ();
        idTextField.setText("");
        TextField weightTextField = new TextField ();
        weightTextField.setText("");
        TextField areaTextField = new TextField ();
        areaTextField.setText("");

        // Pop-up window for module input
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));

        grid.add(new Label("Module 1 Id: "), 0, 0);
        grid.add(idTextField, 1, 0);

        grid.add(new Label("Module 2 Id: "), 0, 1);
        grid.add(weightTextField, 1, 1);

        grid.add(new Label("Connection Id: "), 0, 2);
        grid.add(areaTextField, 1, 2);

        Button saveButton = new Button("Save");

        grid.add(new Button("Save"), 2,2);

        Scene scene = new Scene(grid, 300, 100 );


        //make another stage for scene2
        Stage newStage = new Stage();
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                newStage.close();
            }
        });

        newStage.setScene(scene);
        newStage.showAndWait();


        Rectangle moduleRectangle = new Rectangle();
        moduleRectangle.setFill(Color.BLUE);
        moduleRectangle.setLayoutX(429);
        moduleRectangle.setLayoutY(23);
        moduleRectangle.setWidth(10);
        moduleRectangle.setHeight(50);
        moduleRectangle.setArcWidth(10);
        moduleRectangle.setArcHeight(10);
        Label idLabel = new Label(moduleId + "");
        //moduleId ++;

        pane.getChildren().add(moduleRectangle);
        pane.getChildren().add(idLabel);


        addConnection = false;
    }

    private void addRectangle(Pane pane, int x, int y) {


        // Pop-up window for module input

        TextField idTextField = new TextField ();
        idTextField.setText("");
        TextField weightTextField = new TextField ();
        weightTextField.setText("");
        TextField areaTextField = new TextField ();
        areaTextField.setText("");

        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));

        grid.add(new Label("Id: "), 0, 0);
        grid.add(idTextField, 1, 0);

        grid.add(new Label("Weight: "), 0, 1);
        grid.add(weightTextField, 1, 1);

        grid.add(new Label("Area: "), 0, 2);
        grid.add(areaTextField, 1, 2);

        Button saveButton = new Button("Save");
        grid.add(new Button("Save"), 2,2);

        Scene scene = new Scene(grid, 300, 100 );


        //make another stage for scene2
        Stage newStage = new Stage();
        newStage.setScene(scene);
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                newStage.close();
            }
        });
        newStage.showAndWait();

        // Add module reactangle
        Rectangle moduleRectangle = new Rectangle();
        moduleRectangle.setFill(Color.LIGHTGREEN);
        moduleRectangle.setLayoutX(x);
        moduleRectangle.setLayoutY(y - 80);
        moduleRectangle.setWidth(50);
        moduleRectangle.setHeight(50);
        moduleRectangle.setArcWidth(10);
        moduleRectangle.setArcHeight(10);
        Label idLabel = new Label(moduleId + "");
        moduleId ++;
        idLabel.setLayoutX(x);
        idLabel.setLayoutY(y - 80);
        pane.getChildren().add(moduleRectangle);
        pane.getChildren().add(idLabel);

        addModule = false;

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Image image = new Image("file:bg.png");

        ImageView imageView = new ImageView();
        imageView.setImage(image);

        Pane pane = new Pane();

        pane.getChildren().add(imageView);
        Button moduleButton = new Button("Add Module");
        moduleButton.setLayoutX(900);
        moduleButton.setLayoutY(100);
        moduleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                addModule = true;
            }
        });
        pane.getChildren().add(moduleButton);

        Button connButton = new Button("Add Connection");
        connButton.setLayoutX(900);
        connButton.setLayoutY(130);
        connButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                addConnection = true;
                addConnection(pane);

            }
        });
        pane.getChildren().add(connButton);

        Button excelButton = new Button("Show Scatter");
        excelButton.setLayoutX(900);
        excelButton.setLayoutY(160);
        excelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                new POI().start(primaryStage);
            }
        });
        pane.getChildren().add(excelButton);

        Button barButton = new Button("Show Bar");
        barButton.setLayoutX(900);
        barButton.setLayoutY(190);
        barButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                new Bar().start(primaryStage);
            }
        });
        pane.getChildren().add(barButton);

        Scene scene = new Scene(pane, 1000, 1000 );

        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (addModule == true) {
                    int x = (int) mouseEvent.getSceneX();
                    int y = (int) mouseEvent.getScreenY();
                    addRectangle(pane, x, y);
                    System.out.println(x+ " " + y +" mouse click detected! " + mouseEvent.getSource());

                }
            }
        });

        primaryStage.setTitle("Module Construction");

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
