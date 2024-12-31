package animations;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppLauncher extends Application {

    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();
    private BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
    private XYChart.Series<String, Number> sr;
    private Alert alert = new Alert(AlertType.INFORMATION);
    private ObservableList<Data<String,Number>> list;

    @Override
    public void start(Stage primaryStage){
        
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Sorting");
        primaryStage.setScene(new Scene(bc));

        alert.setContentText("SORTING COMPLETE");
        alert.setTitle("SUCCESS");
        alert.initOwner(primaryStage);
        alert.initModality(Modality.NONE);

        xAxis.setLabel("Key-Values");
        yAxis.setLabel("Elements");

        sr = new XYChart.Series<String, Number>();
        list = sr.getData();
        for (int i = 0; i < 70; i++) {
            sr.getData().add(
                new Data<String,Number>(
                    "arr["+i+"]", (int)(Math.random()*100)
                    )
                );
        }
        bc.getData().add(sr);
        bc.setBarGap(0.5);
        bc.setCategoryGap(0.5);
        bc.setAnimated(false);

        primaryStage.show();

        new Thread(()->{

            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            quickSort(0, list.size()-1);
            // insertSort();
            // selectSort();
            // bubbleSort();

            informComplete();

        }).start();
    }

    public void quickSort(int lBound, int upBound){

        if(lBound >= upBound) return;
        int start = lBound;
        int end = upBound;
        int pivot = list.get(lBound).getYValue().intValue();

        while(end > start){
            while(start <= upBound &&
            list.get(start).getYValue().intValue() <= pivot){
                start++;
            }
            while( end >= lBound &&
            list.get(end).getYValue().intValue() > pivot){
                end--;
            }
            if(end < start) break;
            var tmp = list.get(start).getYValue();
            list.get(start).setYValue(list.get(end).getYValue());
            list.get(end).setYValue(tmp);
            try {
                Thread.sleep(25);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        var tmp = list.get(lBound).getYValue();
        list.get(lBound).setYValue(list.get(end).getYValue());
        list.get(end).setYValue(tmp);
        try {
            Thread.sleep(25);
        } catch (Exception e) {
            e.printStackTrace();
        }

        quickSort(lBound, end-1);
        quickSort(end+1, upBound);
    }

    public void insertSort(){
        
        int length = list.size();
        for(int i = 0; i<length; i++){
            for(int j = i; j >= 1; j--){
                if(list.get(j-1).getYValue().intValue() >
                   list.get(j).getYValue().intValue()){
                    var tmp = list.get(j).getYValue();
                    list.get(j).setYValue(list.get(j-1).getYValue());
                    list.get(j-1).setYValue(tmp);
                    try {
                        Thread.sleep(13);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }
        }
    }

    public void selectSort(){
        
        int length = list.size();
        for(int i = 0; i < length-1; i++){
            int small_id = i;
            for(int j = i; j< length; j++){
                if(list.get(small_id).getYValue().intValue() > 
                   list.get(j).getYValue().intValue()){
                    small_id = j;
                }
            }
            if(small_id!=i){
                var temp = list.get(i).getYValue();
                list.get(i).setYValue(list.get(small_id).getYValue());
                list.get(small_id).setYValue(temp);
                try{
                    Thread.sleep(25);     
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void bubbleSort(){
        
        int length = list.size();
        for(int j = 0; j < length-1; j++){
            var flag = true;
            for(int i =0; i < length-1-j; i++){
                if(list.get(i).getYValue().intValue() > 
                   list.get(i+1).getYValue().intValue()){
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

    public void informComplete(){
        Platform.runLater(()->alert.show());
    }

    public static void main(String[] args) {
        launch(args);
    }

}
