/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.factory;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.jdbc.JDBCPieDataset;
import org.jfree.util.Rotation;


/**
 *
 * @author Wilson
 */
public class StatisticsChartFactory {
    
    public static CategoryDataset createCategoryDataset(Connection conn, String query) throws SQLException{
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        
        while(resultSet.next()){
            Double value = resultSet.getDouble("value");
            String series = resultSet.getString("series");
            String category = resultSet.getString("category");
            dataset.addValue(value, series, category);
        }
        
        
        return dataset;
    }
    
    public static PieDataset createPieDataset(Connection conn, String query) throws SQLException{
        JDBCPieDataset dataset = new JDBCPieDataset(conn);
        dataset.executeQuery(query);
        return dataset;
    }
    
    public PieDataset createPieDataset(){
        DefaultPieDataset dataset = new DefaultPieDataset();
    
        dataset.setValue("Caloocan", 50);
        dataset.setValue("Malabon", 50);
        
        return dataset;    
    }
    
    public static JFreeChart createBarChart(CategoryDataset dataset, String title, String categoryAxisLabel, String valueAxisLabel){
        JFreeChart chart = ChartFactory.createBarChart(title, categoryAxisLabel, valueAxisLabel, dataset, PlotOrientation.VERTICAL, true, true, true);                
        return chart;
    }
    
    public static JFreeChart createStackedBarChart3D(CategoryDataset dataset, String title, String categoryAxisLabel, String valueAxisLabel){
        JFreeChart chart = ChartFactory.createStackedBarChart3D(title, categoryAxisLabel, valueAxisLabel, dataset, PlotOrientation.HORIZONTAL, true, true, true);
        return chart;
    }
    
    public static JFreeChart createPieChart3D(PieDataset dataset, String title){
        JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, true);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();        
        plot.setForegroundAlpha(0.5f);
        plot.setDirection(Rotation.CLOCKWISE);
        return chart;
    }
    
    public static void connectChartPanel(JPanel container, JFreeChart chart){
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setSize(container.getSize());
        container.add(chartPanel);
    }
}
