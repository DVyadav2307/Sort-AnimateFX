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

        yAxis.setLabel("Key-Values");
        xAxis.setLabel("Elements");

        sr = new XYChart.Series<String, Number>();
        list = sr.getData();
        for (int i = 0; i < 140; i++) {
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

            // heapSort();
            mergeSort();
            // quickSort();
            // insertSort();
            // selectSort();
            //  bubbleSort();

            informComplete();

        }).start();
    }
    
    public void heapSort(){
        int size = list.size();
        for(int i = (size-2)/2; i >= 0; i--){
            heapifyDown(size, i);
        }
        for(int i = size-1; i > 0; i--){
            var temp = list.get(0).getYValue();
            list.get(0).setYValue(
                list.get(i).getYValue()
            );
            list.get(i).setYValue(temp);
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            heapifyDown(i, 0);
        }
    }
    void heapifyDown(int size, int i){
        int lchild = 2*i + 1;
        int rchild = 2*i + 2;
        int largest = i;
        if(lchild < size && 
            list.get(largest)
            .getYValue().intValue() < list.get(lchild)
                                      .getYValue().intValue()){
                                        largest = lchild;
                                      }
        if(rchild < size && 
            list.get(largest)
            .getYValue().intValue() < list.get(rchild)
                                      .getYValue().intValue()){
                                        largest = rchild;
                                      }
        if(largest != i){
            var temp  = list.get(largest).getYValue();
            list.get(largest).setYValue(
                list.get(i).getYValue()
            );
            list.get(i).setYValue(temp);
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            heapifyDown(size, largest);
        }
    }

    public void mergeSort(){
        partition(0, list.size()-1);
    }
    public void partition(int lbound, int upbound){

        if(lbound == upbound) return;
        int mid = lbound + (upbound - lbound)/2;
        partition(lbound, mid);
        partition(mid + 1, upbound);
        merge(lbound, mid, upbound);
    }
    public void merge(int lbound, int mid, int upBound){

        int size = upBound - lbound + 1;
        Number[] narr = new Number[size];
        int lptr, rptr, i = 0;
        lptr = lbound; rptr = mid + 1;
        while(lptr < mid +1 && rptr < upBound +1){
            if(list.get(lptr).getYValue().intValue() <=
            list.get(rptr).getYValue().intValue()){
                narr[i] = list.get(lptr).getYValue();
                lptr++;
            }else{
                narr[i] = list.get(rptr).getYValue();
                rptr++;
            }
            i++;
        }
        while(lptr < mid +1){
            narr[i] = list.get(lptr).getYValue();
            lptr++; i++;
        }
        while(rptr < upBound +1){
            narr[i] = list.get(rptr).getYValue();
            rptr++; i++;
        }
        for(i = 0; i < size; i++){
            list.get(lbound + i).setYValue(narr[i]) ;
            try {
                Thread.sleep(20);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void quickSort(){
        quickSortHelper(0, list.size()-1);
    }
    public void quickSortHelper(int lBound, int upBound){

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
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        var tmp = list.get(lBound).getYValue();
        list.get(lBound).setYValue(list.get(end).getYValue());
        list.get(end).setYValue(tmp);
        try {
            Thread.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        quickSortHelper(lBound, end-1);
        quickSortHelper(end+1, upBound);
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
