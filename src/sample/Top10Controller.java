package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Top10Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addTopRecord();
        setListView();
    }


    @FXML
    private ListView listViewTop10;


    private static int top10[] = new int[10];
    private ArrayList<String> top10String = new ArrayList();

    public void addTopRecord() {
        for (int topCislo:top10) {
            top10String.add(String.valueOf(topCislo));
        }
    }

    public void setListView(){
        for (String arrayListItem:top10String) {
            listViewTop10.getItems().add("Dosiahnute skore:"+arrayListItem);
        }
    }

    public void sendSkore(int skore){
        for (int i = top10.length-1; i > 0; i--){
            if (skore > top10[i]){
                for(int j = top10.length-1 ; j > i; j--){
                    top10[j-1] = top10[j];
                }
                top10[i]=skore;
            }
        }
    }

}
