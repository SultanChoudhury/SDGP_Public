package sdgp.dashboardFrames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.Axis;
import javafx.scene.chart.XYChart;
//import this
import javafx.stage.Stage;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import sdgp.db;

public class LineGraph extends Application {

    private static Statement st;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    //  public List ActivityTypeArray = new ArrayList<>();
    //  public int HES = Collections.frequency(ActivityTypeArray, "HES");

    @Override
    public void start(Stage myStage) {
//        int HES = Collections.frequency(ActivityTypeArray, "HES");
        try {
            conn = db.EU_data();
            String query = "SELECT ActivityType FROM 'DataSet'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            List ActivityTypeArray = new ArrayList<>();
            while (rs.next()) {
                String ActivityType = rs.getString("ActivityType");
                ActivityTypeArray.add(ActivityType);
            }
            int HES = Collections.frequency(ActivityTypeArray, "HES");
            int OTH = Collections.frequency(ActivityTypeArray, "OTH");
            int PRC = Collections.frequency(ActivityTypeArray, "PRC");
            int PUB = Collections.frequency(ActivityTypeArray, "PUB");
            int REC = Collections.frequency(ActivityTypeArray, "REC");
            myStage.setTitle("Line Graph");
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("Activity Type");
            yAxis.setLabel("ecContribution(Millions)");
            final LineChart<String, Number> lineChart
                    = new LineChart<String, Number>(xAxis, yAxis);
            lineChart.setTitle("W1682884 Data Diagram");
            XYChart.Series series = new XYChart.Series();
            series.setName("Activity type per ecContribution");
            //populating the series with data
            series.getData().add(new XYChart.Data("HES", HES));
            series.getData().add(new XYChart.Data("OTH", OTH));
            series.getData().add(new XYChart.Data("PRC", PRC));
            series.getData().add(new XYChart.Data("PUB", PUB));
            series.getData().add(new XYChart.Data("REC", REC));
            Scene myScene = new Scene(lineChart, 800, 600);
            lineChart.getData().add(series);
            myStage.setScene(myScene);
            myStage.show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }
    
    public static void main(String[] args) {
        launch(args); 
    }
}
