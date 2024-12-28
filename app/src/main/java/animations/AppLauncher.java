package animations;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.stage.Stage;

public class AppLauncher extends Application {
    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();
    private BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
    private XYChart.Series<String, Number> sr;
    

    @Override
    public void start(Stage primaryStage){
        
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Sorting");
        primaryStage.setScene(new Scene(bc));


        xAxis.setLabel("Key-Values");
        yAxis.setLabel("Elements");

        sr = new XYChart.Series<String, Number>();
        for (int i = 0; i < 70; i++) {
            sr.getData().add(new Data<String,Number>("arr["+i+"]", (int)(Math.random()*100)));
        }
        bc.getData().add(sr);
        bc.setBarGap(0.5);
        bc.setCategoryGap(0.5);
        bc.setAnimated(false);

        primaryStage.show();

        new Thread(()->{
            bubbleSort(sr.getData());
        }).start();
    }

    public void bubbleSort(ObservableList<Data<String, Number>> list){
        try{
		Thread.sleep(5000);
	}catch(Exception e){
		e.printStackTrace();
	}
        int length = list.size();
        for(int j = 0; j < length-1; j++){
            var flag = true;
            for(int i =0; i < length-1-j; i++){
                if(list.get(i).getYValue().intValue() > list.get(i+1).getYValue().intValue()){
                    //swap
                    var temp = list.get(i+1).getYValue();
                    list.get(i+1).setYValue(list.get(i).getYValue());
                    list.get(i).setYValue(temp);
                    flag = false;
                    try {
                        Thread.sleep(7);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if(flag){
                break;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
