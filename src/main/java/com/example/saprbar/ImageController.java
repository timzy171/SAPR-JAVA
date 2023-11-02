package com.example.saprbar;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;

import java.util.ArrayList;
import java.util.List;

public class ImageController{

    @FXML
    private Canvas canvas;

    private GraphicsContext gc;

    private static Integer nodeCounter;

    private final Integer HEIGHT_K = 50;
    private final Integer WIDTH_K = 50;

    public static void setNodeCounter(Integer nc){
        nodeCounter = nc;
    }

    private static List<List<Double>> nodeInfo;

    public static void setNodeInfo(List<List<Double>> ncs){
        nodeInfo = ncs;
    }

    private static List<List<Double>> forces;
    
    private List<Rectangle> rectangles = new ArrayList<>();

    public static void setForces(List<List<Double>> fs){
        forces = fs;
    }

    @FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        System.out.println(nodeInfo);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2.0);
        gc.setFill(Color.WHITE);
        double x = 50.0;
        double y = 250.0;
        double currentHeight = nodeInfo.get(0).get(0) * HEIGHT_K;
        for (int i = 0; i < nodeCounter; i++) {
            Rectangle rectangle = new Rectangle();
            rectangle.setX(x);
            rectangle.setWidth(nodeInfo.get(i).get(1) * WIDTH_K);

            if(currentHeight < nodeInfo.get(i).get(0) * HEIGHT_K){
                double k = (nodeInfo.get(i).get(0) * HEIGHT_K - currentHeight) / 2;
                y -= k;
            }
            else if (currentHeight > nodeInfo.get(i).get(0) * HEIGHT_K){
                double k = (currentHeight - nodeInfo.get(i).get(0) * HEIGHT_K) / 2;
                y += k;
            }
            rectangle.setY(y);
            currentHeight = nodeInfo.get(i).get(0) * HEIGHT_K;
            rectangle.setHeight(currentHeight);
            drawRectangle(gc,rectangle);
            rectangles.add(rectangle);
            x = rectangle.getX() + rectangle.getWidth();
        }

        for (int i = 0; i < forces.size(); i++) {
            List<Double> currentForce = forces.get(i);
            System.out.println("НОМЕР УЗЛА = " + currentForce.get(0));
            Double index =  currentForce.get(0);
            Rectangle currentRectangle;
            if(index == 1){
                currentRectangle = rectangles.get(0);
            }
            else{
                currentRectangle = rectangles.get((int) (index - 2));
            }
            double plusWidth;
            if(index != 1){
                plusWidth = currentRectangle.getWidth();
            }
            else{
                plusWidth = rectangles.get(0).getWidth();
            }

            if(currentForce.get(2) == 1){
                System.out.println(currentRectangle);
                if(currentForce.get(1) < 0){
                    drawArrow(gc,currentRectangle.getX() + plusWidth,currentRectangle.getY() + (currentRectangle.getHeight() / 2),
                            currentRectangle.getX() + plusWidth - 25,currentRectangle.getY() + (currentRectangle.getHeight() / 2),true);
                }
                else{
                    drawArrow(gc,currentRectangle.getX() + plusWidth,currentRectangle.getY() + (currentRectangle.getHeight() / 2),
                            currentRectangle.getX() + plusWidth + 25,currentRectangle.getY() + (currentRectangle.getHeight() / 2),true);
                }
                System.out.println(currentRectangle);
            }
            else if(currentForce.get(2) == 2){
                if(currentForce.get(1) < 0){
                    int k = (int) (currentRectangle.getX() + plusWidth);
                    System.out.println(k);
                    System.out.println(currentRectangle.getX());
                    int minus = 15;
                    while(k - currentRectangle.getX() > 5){
                        drawArrow(gc,currentRectangle.getX() + plusWidth,currentRectangle.getY() + (currentRectangle.getHeight() / 2),
                                currentRectangle.getX() + plusWidth - minus,currentRectangle.getY() + (currentRectangle.getHeight() / 2),false);
                        minus += 15;
                        k -= 15;
                    }

                }
                else{
                    int k = (int) currentRectangle.getX();
                    System.out.println(currentRectangle.getX());
                    int plus = 15;
                    while((currentRectangle.getX() + plusWidth) - k > 5){
                        drawArrow(gc,currentRectangle.getX(),currentRectangle.getY() + (currentRectangle.getHeight() / 2),
                                currentRectangle.getX() + plus,currentRectangle.getY() + (currentRectangle.getHeight() / 2),false);
                        plus += 15;
                        k += 15;
                    }
                }
            }
        }

    }

    private void drawRectangle(GraphicsContext gc,Rectangle rect){
        gc.strokeRect(rect.getX(),
                rect.getY(),
                rect.getWidth(),
                rect.getHeight());
    }

    private void drawArrow(GraphicsContext gc, double x1, double y1, double x2, double y2,boolean flag) {
        int ARROW_WIDTH;
        if(flag){
            ARROW_WIDTH = 8;
            gc.setFill(Color.RED);
        }
        else{
            ARROW_WIDTH = 4;
            gc.setFill(Color.BLUE);
        }
        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);

        Transform transform = Transform.translate(x1, y1);
        transform = transform.createConcatenation(Transform.rotate(Math.toDegrees(angle), 0, 0));
        gc.setTransform(new Affine(transform));

        gc.strokeLine(0, 0, len, 0);
        gc.fillPolygon(new double[]{len, len - ARROW_WIDTH, len - ARROW_WIDTH, len}, new double[]{0, -ARROW_WIDTH, ARROW_WIDTH, 0},
                4);
    }
}
